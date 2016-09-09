package gppmds.wikilegis.dao;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import gppmds.wikilegis.exception.BillException;
import gppmds.wikilegis.model.Bill;

/**
 * Created by marcelo on 9/8/16.
 */
public class JSONHelper {


        public static List<Bill> billListFromJSON(String billList) throws JSONException, BillException {

            List<Bill> billListApi = new ArrayList<>();

            JSONObject bills = new JSONObject(billList);
            JSONArray results = bills.getJSONArray("results");

            for(int i=0; i<results.length(); i++){
                JSONObject f = results.getJSONObject(i);

                Bill billAux = new Bill(f.getInt("id"),
                                        f.getString("title"),
                                        f.getString("epigraph"),
                                        f.getString("status"),
                                        f.getString("description"),
                                        f.getString("theme"));

                JSONArray segments = f.getJSONArray("segments");

                for(int j = 0; j < segments.length(); j++) {
                    billAux.setSegments(segments.getInt(j));
                }

                billListApi.add(billAux);
            }

            for (int i = 0; i < billListApi.size(); i++) {
                Log.d("Id:" + i+1, billListApi.get(i).getId().toString());
                Log.d("Title:" + i+1, billListApi.get(i).getTitle());
                Log.d("Epigraph" + i+1, billListApi.get(i).getEpigraph());
                Log.d("Status" + i+1, billListApi.get(i).getStatus());
                Log.d("Description" + i+1, billListApi.get(i).getDescription());
                Log.d("Theme" + i+1, billListApi.get(i).getTheme());
                Log.d("Segments:", " ");
                for (int j=0; j<billListApi.get(i).getSegments().size(); j++) {
                    Log.d(" ", billListApi.get(i).getSegments().get(j).toString());
                }
            }

            return billListApi;
        }
}
