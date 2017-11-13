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

        String myUrl = Constants.OPEN_WEATHER_BASE_URL +"?q=" + city+"&APPID=f0484ffaa31481367a2cc852d18f475d";
        Log.d("URL: ", myUrl);
        Request request = new Request.Builder().url(myUrl)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static Weather processResults(String responseJSON) {

        try {

            JSONObject rootJSON = new JSONObject(responseJSON);

            JSONArray weatherJSON = rootJSON.getJSONArray("weather");
            JSONObject mainJSON = rootJSON.getJSONObject("main");
            JSONObject weatherItemsJSON = weatherJSON.getJSONObject(0);


            double temp = mainJSON.getDouble("temp");
            String city = rootJSON.getString("name");
            int id = weatherItemsJSON.getInt("id");
            String main = weatherItemsJSON.getString("main");
            String description = weatherItemsJSON.getString("description");
            String icon = weatherItemsJSON.getString("icon");
            int humidity = mainJSON.getInt("humidity");

            Weather weather = new Weather(city, id, main, description, icon, temp, humidity );
            //weathers.add(weather);
            return weather;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
