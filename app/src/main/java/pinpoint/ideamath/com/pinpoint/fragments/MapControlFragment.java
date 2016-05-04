package pinpoint.ideamath.com.pinpoint.fragments;

import android.support.v4.app.Fragment;

import com.tenpearls.android.activities.BaseActivity;
import com.tenpearls.android.fragments.BaseFragment;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.service.ServiceFactory;
import com.tenpearls.android.views.BaseView;

import pinpoint.ideamath.com.pinpoint.activities.MainMapActivity;
import pinpoint.ideamath.com.pinpoint.views.MapControlFragmentView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapControlFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapControlFragment extends BaseFragment {



    @Override
    protected ServiceFactory getServiceFactory() {
        return null;
    }

    @Override
    public BaseView getViewForController(Controller controller) {
        return new MapControlFragmentView(controller);
    }

    //this.getBaseActivity() to get attached activity
}
