package pinpoint.ideamath.com.pinpoint.fragments;

import com.tenpearls.android.fragments.BaseFragment;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.service.ServiceFactory;
import com.tenpearls.android.views.BaseView;

import pinpoint.ideamath.com.pinpoint.views.TestFragmentView;

/**
 * Created by firdous on 09/05/16.
 */
public class TestFragment extends BaseFragment {
    @Override
    protected ServiceFactory getServiceFactory() {
        return null;
    }

    @Override
    public BaseView getViewForController(Controller controller) {
        return new TestFragmentView(controller);
    }
}
