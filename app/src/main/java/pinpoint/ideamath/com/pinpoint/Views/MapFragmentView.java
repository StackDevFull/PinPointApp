package pinpoint.ideamath.com.pinpoint.views;

import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;

import pinpoint.ideamath.com.pinpoint.R;

/**
 * Created by firdous on 04/05/16.
 */
public class MapFragmentView extends BaseView {
    public MapFragmentView(Controller controller) {
        super(controller);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_map;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void setActionListeners() {

    }
}
