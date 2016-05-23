package pinpoint.ideamath.com.pinpoint.services;

import java.util.HashMap;

/**
 * Created by firdous on 23/05/16.
 */
public class ServiceProtocol extends com.tenpearls.android.service.ServiceProtocol {
    @Override
    protected HashMap<String, String> getHeaders() {
        return null;
    }

    @Override
    public String getAPIUrl() {
        return "http://172.16.7.134:4002/api/";
    }
}
