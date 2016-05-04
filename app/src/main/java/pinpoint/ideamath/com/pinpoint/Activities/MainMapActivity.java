package pinpoint.ideamath.com.pinpoint.activities;

import android.os.Bundle;

import com.tenpearls.android.activities.BaseActivity;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.service.ServiceFactory;
import com.tenpearls.android.views.BaseView;
import pinpoint.ideamath.com.pinpoint.views.MainMapActivityView;

public class MainMapActivity extends BaseActivity {

    @Override
    public BaseView getViewForController(Controller controller) {
        return new MainMapActivityView(controller);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected ServiceFactory getServiceFactory() {
        return null;
    }

}
