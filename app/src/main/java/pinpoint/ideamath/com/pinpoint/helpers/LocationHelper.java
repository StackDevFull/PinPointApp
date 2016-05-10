package pinpoint.ideamath.com.pinpoint.helpers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.tenpearls.android.activities.BaseActivity;

import pinpoint.ideamath.com.pinpoint.PinPointApplication;

/**
 * Created by firdous on 09/05/16.
 */
public class LocationHelper implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    static LocationHelper helperInstance;
    private String TAG = "";//MainActivity.class.getSimpleName();
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private Location mLastLocation;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    public boolean mRequestingLocationUpdates = false;

    private LocationRequest mLocationRequest;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    LocationHelperEventListener mListener;

    public LocationHelper() {}

    public static LocationHelper getInstance() {
        if (helperInstance == null){
            helperInstance = new LocationHelper();
        }
        return helperInstance;
    }

    public void setListener(LocationHelperEventListener listener) {
        mListener = listener;
        TAG = listener.getConsumerActivity().getClass().getSimpleName();
    }

    public void connect(){
        if (checkPlayServices()) {
            buildGoogleApiClient();
            createLocationRequest();
        }

        start();
    }

    public void disconnect(){
        stopLocationUpdates();
        stop();
    }

    public interface LocationHelperEventListener {
        public BaseActivity getConsumerActivity();
        public void returnLocation(double latitude, double longitude);
    }

    /**
     * Creating google api client object
     * */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mListener.getConsumerActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices() {
        BaseActivity consumerActivity = mListener.getConsumerActivity();
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(consumerActivity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, consumerActivity,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(consumerActivity.getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                consumerActivity.finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Method to display the location on UI
     * */
    public void displayLocation() {

        BaseActivity consumerActivity = mListener.getConsumerActivity();

        if (ActivityCompat.checkSelfPermission(consumerActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(consumerActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            //lblLocation.setText(latitude + ", " + longitude);
            Log.i(TAG, "LocationFound "
                    + latitude + ", " + longitude);

            mListener.returnLocation(latitude, longitude);

        } else {
            Log.i(TAG, "Couldn't get the location. Make sure location is enabled on the device");
        }
    }

    /**
     * Method to toggle periodic location updates
     * */
    public void togglePeriodicLocationUpdates() {
        if (!mRequestingLocationUpdates) {

            mRequestingLocationUpdates = true;
            // Starting the location updates
            startLocationUpdates();
            Log.d(TAG, "Periodic location updates started!");
        } else {

            mRequestingLocationUpdates = false;
            // Stopping the location updates
            stopLocationUpdates();
            Log.d(TAG, "Periodic location updates stopped!");
        }
    }

    /**
     * Creating location request object
     * */
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT); // 10 meters
    }

    /**
     * Starting the location updates
     * */
    protected void startLocationUpdates() {

        BaseActivity consumerActivity = mListener.getConsumerActivity();

        if (ActivityCompat.checkSelfPermission(consumerActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(consumerActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

    }

    /**
     * Stopping location updates
     */
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    public void start() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    public void resume() {
        checkPlayServices();

        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    public void stop(){
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    public void pause(){
        stopLocationUpdates();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
// Once connected with google api, get the location
        displayLocation();

        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }

    //LocationListener callback
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;

        BaseActivity consumerActivity = mListener.getConsumerActivity();

        Toast.makeText(consumerActivity.getApplicationContext(), "Location changed!",
                Toast.LENGTH_SHORT).show();

        // Displaying the new location on UI
        displayLocation();
    }
}
