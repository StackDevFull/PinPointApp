package pinpoint.ideamath.com.pinpoint.services;

import com.tenpearls.android.service.ServiceCall;

import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by firdous on 23/05/16.
 */
public interface CarService {
    @Headers({
            "x-ibm-client-id: default",
            "x-ibm-client-secret: SECRET"
    })

    @GET("Cars")
    ServiceCall<CarsResponse> getCars();
}
