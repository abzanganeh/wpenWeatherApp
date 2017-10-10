package edu.pcc.barzinzanganeh.alireza.myweatherapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class OpenWeatherService {

    public static void forecastWeather(String city, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        String myUrl = Constants.OPEN_WEATHER_BASE_URL +"?q=" + city+"&APPID="+ Constants.OPEN_WEATHER_KEY;
        Log.d("URL: ", myUrl);
        Request request = new Request.Builder().url(myUrl)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static Weather processResults(String responseJSON) {
        //ArrayList<Weather> weathers = new ArrayList<>();
        try {

            JSONObject rootJSON = new JSONObject(responseJSON);

            JSONArray weatherJSON = rootJSON.getJSONArray("weather");
            JSONObject weatherItemsJSON = weatherJSON.getJSONObject(0);

            String city = rootJSON.getString("name");
            int id = weatherItemsJSON.getInt("id");
            String main = weatherItemsJSON.getString("main");
            String description = weatherItemsJSON.getString("description");
            String icon = weatherItemsJSON.getString("icon");

            Weather weather = new Weather(city, id, main, description, icon );
            //weathers.add(weather);
            return weather;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
