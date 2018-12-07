import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class AqicnReader {

    public static Object getAirQualityIndexForCity(String city) throws Exception {
        LinkedHashMap<String, Object> apiResponse = new LinkedHashMap<String, Object>();
        try {
            HttpUrl.Builder builder = HttpUrl.parse("https://api.waqi.info/feed/"+city+"/").newBuilder();
            builder.addQueryParameter("token", "XXX");
            Request request = new Request.Builder()
                    .url(builder.build().toString())
                    .build();
            OkHttpClient httpClient = new OkHttpClient();
            Response response = httpClient.newCall(request).execute();

            JSONObject jsonObject = new JSONObject(response.body().string())
                    .getJSONObject("data");
            apiResponse.put("aqi", jsonObject.get("aqi"));
            apiResponse.put("dominentpol",jsonObject.get("dominentpol"));

            JSONObject cityObj = jsonObject.getJSONObject("city");
            apiResponse.put("geo",cityObj.get("geo"));
            apiResponse.put("name",cityObj.get("name"));

            JSONObject iaqiObj = jsonObject.getJSONObject("iaqi");
            apiResponse.put
                    (jsonObject.getString("dominentpol"),
                            iaqiObj.getJSONObject(jsonObject.getString("dominentpol")).get("v"));

            return apiResponse;
        } catch(Exception e) {
            throw new Exception(e);
        }
    }

}
