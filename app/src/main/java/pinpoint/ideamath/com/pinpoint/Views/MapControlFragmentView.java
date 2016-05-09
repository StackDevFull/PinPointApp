package pinpoint.ideamath.com.pinpoint.views;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;
import com.google.android.gms.maps.GoogleMap;
import pinpoint.ideamath.com.pinpoint.R;
import pinpoint.ideamath.com.pinpoint.fragments.MapControlFragment;

/**
 * Created by firdous on 04/05/16.
 */
public class MapControlFragmentView extends BaseView {

    static final LatLng TutorialsPoint = new LatLng(21 , 57);
    private GoogleMap googleMap;

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
    }

    @Override
    public void setActionListeners() {

    }
}
