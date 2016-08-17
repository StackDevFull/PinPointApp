package pinpoint.ideamath.com.pinpoint.entities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tenpearls.android.entities.BaseEntity;
import com.tenpearls.android.utilities.JsonUtility;

/**
 * Created by firdous on 23/05/16.
 */
public class CarEntity extends BaseEntity {

    public String getName() {
        return name;
    }
    public String getMake() {
        return make;
    }
    public String getYear() {
        return year;
    }

    String name;
    String make;
    String year;

    @Override
    public void loadJson(JsonElement jsonElement) {
        if(JsonUtility.isJsonElementNull(jsonElement)) {
            return;
        }

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        name = JsonUtility.getString(jsonObject, "name");
        make = JsonUtility.getString(jsonObject, "make");
        year = JsonUtility.getString(jsonObject, "year");
    }
}
