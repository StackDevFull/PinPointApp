package pinpoint.ideamath.com.pinpoint.services;

import com.tenpearls.android.service.ServiceCall;

import pinpoint.ideamath.com.pinpoint.services.ResponseObjects.CarsResponse;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by firdous on 23/05/16.
 */
public interface carService {
    @Headers({
            "Content-Type:application/json",
            "x-ibm-client-id:default",
            "x-ibm-client-secret:SECRET",
            "Accept:application/json"
    })

    @GET("Cars")
    ServiceCall<CarsResponse> getCars();
}
