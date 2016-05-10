package pinpoint.ideamath.com.pinpoint;

import android.app.Application;
import android.content.Context;

/**
 * Created by firdous on 10/05/16.
 */
public class PinPointApplication extends Application {
    static PinPointApplication applicationInstance;
    Context currentContext;

    public static PinPointApplication getInstance() {

        return applicationInstance;
    }

    public Context getCurrentContext() {
        return currentContext;
    }

    public void setCurrentContext( Context context ) {
        this.currentContext = context;
    }

    public void onAuthenticationFailed() {

//        if ( ( this.getCurrentContext() instanceof LoginController ) )
//            return;
//
//        if ( !( this.getCurrentContext() instanceof TabController ) ) {
//            try {
//                ( ( BaseController ) this.getCurrentContext() ).finish();
//            } catch ( Exception e ) {
//                // TODO: handle exception
//            }
//        }
//
//        if ( tabController != null ) {
//            this.tabController.onLogout();
//        }
    }

    @Override
    public void onCreate() {

        super.onCreate();
        applicationInstance = this;

//        initializeConfigurationManager();
//        initializeVolley();
//        initializeGoogleAnalytics();

        // LocationManager.getInstance ().getLocationManger
        // ().addGpsStatusListener (this);
    }

    @Override
    public void onTerminate() {

        super.onTerminate();
    }
}

