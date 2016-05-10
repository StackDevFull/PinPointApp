package pinpoint.ideamath.com.pinpoint.activities.base;

import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.service.ServiceFactory;
import com.tenpearls.android.views.BaseView;

import pinpoint.ideamath.com.pinpoint.PinPointApplication;

/**
 * Created by firdous on 10/05/16.
 */
public abstract class BaseActivity extends com.tenpearls.android.activities.BaseActivity {
    @Override
    public BaseView getViewForController(Controller controller) {
        return null;
    }

    @Override
    protected ServiceFactory getServiceFactory() {
        return null;
    }

    @Override
    protected void onStart(){
        super.onStart();
        PinPointApplication.getInstance().setCurrentContext( this );
    }
}
