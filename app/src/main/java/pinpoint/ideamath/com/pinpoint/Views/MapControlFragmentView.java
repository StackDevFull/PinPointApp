package pinpoint.ideamath.com.pinpoint.views;

import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tenpearls.android.components.TextView;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;
import com.google.android.gms.maps.GoogleMap;
import pinpoint.ideamath.com.pinpoint.R;
import pinpoint.ideamath.com.pinpoint.fragments.MapControlFragment;
import pinpoint.ideamath.com.pinpoint.helpers.LocationHelper;

/**
 * Created by firdous on 04/05/16.
 */
public class MapControlFragmentView extends BaseView {

    static final LatLng TutorialsPoint = new LatLng(21 , 57);
    private GoogleMap googleMap;
    LocationHelper locationHelper;

    // UI elements
    private TextView lblLocation;
    private Button btnShowLocation, btnStartLocationUpdates;

    //constructor
    public MapControlFragmentView(Controller controller) {
        super(controller);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_map;
    }

    @Override
    public void onCreate() {

//        ((MapControlFragment) controller).    To call self controller

        locationHelper = new LocationHelper();
        locationHelper.setListener((MapControlFragment)controller);

        try {
            if (googleMap == null) {
                googleMap = ((MapFragment)(((MapControlFragment) controller).getBaseActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Marker TP = googleMap.addMarker(new MarkerOptions().
                    position(TutorialsPoint).title("TutorialsPoint"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        lblLocation = (TextView) findViewById(R.id.lblLocation);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        btnStartLocationUpdates = (Button) findViewById(R.id.btnLocationUpdates);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                locationHelper.displayLocation();
            }
        });
    }

    @Override
    public void setActionListeners() {

    }
}
