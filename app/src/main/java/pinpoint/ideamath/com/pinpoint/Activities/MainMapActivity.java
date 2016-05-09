package pinpoint.ideamath.com.pinpoint.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.tenpearls.android.activities.BaseActivity;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.service.ServiceFactory;
import com.tenpearls.android.views.BaseView;

import pinpoint.ideamath.com.pinpoint.R;
import pinpoint.ideamath.com.pinpoint.fragments.MapControlFragment;
import pinpoint.ideamath.com.pinpoint.fragments.TestFragment;
import pinpoint.ideamath.com.pinpoint.helpers.LocationHelper;
import pinpoint.ideamath.com.pinpoint.views.MainMapActivityView;

public class MainMapActivity extends BaseActivity implements LocationHelper.LocationHelperEventListener {

    public void showMapFragment(){
        FragmentManager fragmentManager = getBaseActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MapControlFragment mapFragment = new MapControlFragment();
        fragmentTransaction.add(R.id.parentFrame, mapFragment);
        fragmentTransaction.commit();
    }

    @Override
    public BaseView getViewForController(Controller controller) {
        return new MainMapActivityView(controller);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationHelper locationHelper = new LocationHelper();
        locationHelper.setListener(this);
    }

    @Override
    protected ServiceFactory getServiceFactory() {
        return null;
    }

    @Override
    public BaseActivity getConsumerActivity() {
        return this;
    }

    @Override
    public void returnLocation(double latitude, double longitude) {

    }
}
