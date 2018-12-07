import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class AirVisualApiReader {

    public static Object getAirQualityForCity(String city, String state, String country) throws Exception {
        LinkedHashMap<String, Object> apiResponse = new LinkedHashMap<>();
        try {
            final String apiKey = "XXX";

            HttpUrl.Builder builder = HttpUrl.parse("http://api.airvisual.com/v2/city/").newBuilder();
            builder.addQueryParameter("city", city);
            builder.addQueryParameter("state", state);
            builder.addQueryParameter("country", country);
            builder.addQueryParameter("key", apiKey);

            Request request = new Request.Builder()
                    .url(builder.build().toString())
                    .build();

            OkHttpClient httpClient = new OkHttpClient();

            Response response = httpClient.newCall(request).execute();

            JSONObject jsonObject = new JSONObject(response.body().string())
                    .getJSONObject("data");
            apiResponse.put("city",jsonObject.get("city"));
            apiResponse.put("state",jsonObject.get("state"));
            apiResponse.put("country",jsonObject.get("country"));

            JSONObject locationObj = jsonObject.getJSONObject("location");
            apiResponse.put("coordinates", locationObj.get("coordinates"));

            JSONObject weatherObj = jsonObject.getJSONObject("current")
                    .getJSONObject("weather");
            apiResponse.put("Humidity",weatherObj.get("hu"));
            apiResponse.put("Atmospheric Pressure", weatherObj.get("pr"));
            apiResponse.put("Temp. in Celsius", weatherObj.get("tp"));
            apiResponse.put("Wind Direction", weatherObj.get("wd"));
            apiResponse.put("Wind Speed", weatherObj.get("ws"));

            JSONObject pollutionObj = jsonObject.getJSONObject("current")
                    .getJSONObject("pollution");
            apiResponse.put("AQI US EPA Standards", pollutionObj.get("aqius"));
            apiResponse.put("AQI China MEP Standards", pollutionObj.get("aqicn"));
            apiResponse.put("Dominant Pollutant US EPA Standards", pollutionObj.get("mainus"));
            apiResponse.put("Dominant Pollutant China MEP Standards", pollutionObj.get("maincn"));
        }catch(Exception e) {
            throw new Exception(e);
        }
        return apiResponse;
    }

}
