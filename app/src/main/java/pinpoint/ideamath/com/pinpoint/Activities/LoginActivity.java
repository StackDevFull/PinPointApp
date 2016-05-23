package pinpoint.ideamath.com.pinpoint.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.interfaces.ServiceSecondaryEventHandler;
import com.tenpearls.android.service.ServiceCallback;
import com.tenpearls.android.views.BaseView;

import pinpoint.ideamath.com.pinpoint.activities.base.BaseActivity;
import pinpoint.ideamath.com.pinpoint.services.CarsResponse;
import pinpoint.ideamath.com.pinpoint.services.ServiceFactory;
import pinpoint.ideamath.com.pinpoint.views.LoginActivityView;

public class LoginActivity extends BaseActivity implements ServiceSecondaryEventHandler {
    private static final int RC_SIGN_IN = 33;
    private static final String TAG = "LoginActivity";
    public GoogleApiClient mGoogleApiClient;

    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ServiceFactory)serviceFactory).loadRepoService();
        ((ServiceFactory)serviceFactory).carService.getCars().enqueue(new ServiceCallback(this, this) {
            @Override
            protected void onSuccess(Object response, int code) {

                CarsResponse carsResponse = (CarsResponse) response;
                carsResponse.getList();
                //((MainActivityView)view).setRepositoryList(repoResponse.getList());
            }

            @Override
            protected void onFailure(String errorMessage, int code) {

                showToast(String.valueOf(code) + " " + errorMessage);
            }
        });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
//            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
    }

    @Override
    public BaseView getViewForController(Controller controller) {
        return new LoginActivityView(controller);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void willStartCall() {

    }

    @Override
    public void didFinishCall(boolean isSuccess) {

    }
}
