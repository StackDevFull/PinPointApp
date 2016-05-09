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
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.tenpearls.android.activities.BaseActivity;

/**
 * Created by firdous on 09/05/16.
 */
public class LocationHelper implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private String TAG = "";//MainActivity.class.getSimpleName();
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private Location mLastLocation;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    private LocationRequest mLocationRequest;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    LocationHelperEventListener mListener;

    public LocationHelper() {

    }

    public void setListener(LocationHelperEventListener listener){
        mListener = listener;

        TAG = mListener.getConsumerActivity().getClass().getSimpleName();

        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();
        }
    }

    public interface LocationHelperEventListener {
        public BaseActivity getConsumerActivity();
        public void returnLocation(double latitude, double longitude);
        //public void onFragmentInteraction(Uri uri);
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
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
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
            //lblLocation
                    //.setText("(Couldn't get the location. Make sure location is enabled on the device)");
        }
    }

    public void start() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

     public void resume() {
        checkPlayServices();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
// Once connected with google api, get the location
        displayLocation();
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
}