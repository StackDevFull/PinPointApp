package pinpoint.ideamath.com.pinpoint.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.tenpearls.android.service.response.BaseResponse;
import com.tenpearls.android.utilities.JsonUtility;

/**
 * Created by firdous on 23/05/16.
 */
public class CarsResponse extends BaseResponse<CarEntity> {
    @Override
    public void loadJson(JsonElement jsonElement) {
        if(JsonUtility.isJsonElementNull(jsonElement)) {
            return;
        }

        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (JsonElement repoElement: jsonArray) {

            CarEntity carEntity = new CarEntity();
            carEntity.loadJson(repoElement);
            list.add(carEntity);
        }
    }
}
