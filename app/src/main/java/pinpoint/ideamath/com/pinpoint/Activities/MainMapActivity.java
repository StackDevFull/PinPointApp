package pinpoint.ideamath.com.pinpoint.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.service.ServiceFactory;
import com.tenpearls.android.views.BaseView;

import com.tenpearls.android.activities.BaseActivity;
import pinpoint.ideamath.com.pinpoint.R;
import pinpoint.ideamath.com.pinpoint.Views.MainMapActivityView;

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
