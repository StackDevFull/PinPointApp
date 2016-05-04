package pinpoint.ideamath.com.pinpoint.Views;

import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;

import pinpoint.ideamath.com.pinpoint.R;

/**
 * Created by firdous on 04/05/16.
 */
public class MainMapActivityView extends BaseView {
    public MainMapActivityView(Controller controller) {
        super(controller);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.content_main_map;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void setActionListeners() {

    }
}
