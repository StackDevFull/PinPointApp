package pinpoint.ideamath.com.pinpoint.services;

/**
 * Created by firdous on 23/05/16.
 */
public class ServiceFactory extends com.tenpearls.android.service.ServiceFactory {
    public pinpoint.ideamath.com.pinpoint.services.carService carService;

    public void loadRepoService() {
        if(carService == null) {
            carService = loadService(pinpoint.ideamath.com.pinpoint.services.carService.class);
        }
    }

//    @Override
//    public ServiceProtocol getServiceProtocol() {
//        return new ServiceProtocol();
//    }

    @Override
    public com.tenpearls.android.service.ServiceProtocol getServiceProtocol() {
        return new ServiceProtocol();
    }
}
