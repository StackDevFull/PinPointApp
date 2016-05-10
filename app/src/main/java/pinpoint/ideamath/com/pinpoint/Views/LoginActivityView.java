package pinpoint.ideamath.com.pinpoint.views;

import android.support.annotation.NonNull;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;

import pinpoint.ideamath.com.pinpoint.R;
import pinpoint.ideamath.com.pinpoint.activities.LoginActivity;

/**
 * Created by firdous on 10/05/16.
 */
public class LoginActivityView extends BaseView implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {


    public LoginActivityView(Controller controller) {
        super(controller);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void onCreate() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        ((LoginActivity)controller).mGoogleApiClient = new GoogleApiClient.Builder(getBaseActivity())
                .enableAutoManage(getBaseActivity(), this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    @Override
    public void setActionListeners() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                ((LoginActivity)controller).signIn();
                break;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
