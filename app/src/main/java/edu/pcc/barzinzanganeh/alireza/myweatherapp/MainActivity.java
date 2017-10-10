package edu.pcc.barzinzanganeh.alireza.myweatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private Activity mContext;

    @BindView(R.id.weather_id) TextView mWeatherID;
    @BindView(R.id.weather_description) TextView mWeatherDescription;
    @BindView(R.id.weather_main) TextView mWeatherMain;
    @BindView(R.id.weather_icon) TextView mWeatherIcon;
//public ArrayList<Weather> mWeathers = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "It doesn't work");
            }

            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "It works!");

                String responseJSON = null;
                try {
                    responseJSON = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v(TAG, responseJSON);
                        Weather mWeathers = OpenWeatherService.processResults(responseJSON);
                        Log.d(TAG, "mWeathers is produced.");
                        System.out.println(mWeathers);
                        final int mID = mWeathers.getmWeatherID();
                        final String mMain = mWeathers.getmWeatherMain();
                        final String mDescription = mWeathers.getmWeatherDescription();
                        final String mIcon = mWeathers.getmWeatherIcon();
                        Log.d(TAG, "mID is produced.");
                       // System.out.println(mID);
                        System.out.println(mMain);
                        System.out.println(mDescription);
                        System.out.println(mIcon);

                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mWeatherID.setText(Integer.toString(mID));
                                mWeatherMain.setText(mMain);
                                mWeatherDescription.setText(mDescription);
                                mWeatherIcon.setText(mIcon);
                            }
                        });

//                        System.out.println(mMain);
//                        System.out.println(mDescription);
//                        System.out.println(mIcon);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, responseJSON);
            }
        };

        OpenWeatherService.forecastWeather("Portland", callback);

    }

}
