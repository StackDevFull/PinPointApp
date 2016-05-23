package pinpoint.ideamath.com.pinpoint.services;

/**
 * Created by firdous on 23/05/16.
 */
public class ServiceFactory extends com.tenpearls.android.service.ServiceFactory {
    public CarService carService;

    public void loadRepoService() {
        if(carService == null) {
            carService = loadService(CarService.class);
        }
    }

    @Override
    public com.tenpearls.android.service.ServiceProtocol getServiceProtocol() {
        return new ServiceProtocol();
    }
}
