package pl.adluna.combatzone.model;

import android.widget.DatePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Natalia Stawowy on 04.07.14.
 */
public class TrainingTable {
    public Map<String,Hours>  jsonToTable(JSONArray jsonArray){
        Map<String,Hours> table = new HashMap<String, Hours>();
        table.put("Pon",new Hours("",""));
        table.put("Wt",new Hours("",""));
        table.put("Sr",new Hours("",""));
        table.put("Czw",new Hours("",""));
        table.put("Pt",new Hours("",""));
        table.put("Sob",new Hours("",""));
        table.put("Nd",new Hours("",""));

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                if(object.get("grupa").toString().equals("Gr. 1")){
                    table.get(object.get("dzien").toString()).setHour1(object.get("godzina").toString());
                } else
                {
                    table.get(object.get("dzien").toString()).setHour2(object.get("godzina").toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return table;
    }
}
