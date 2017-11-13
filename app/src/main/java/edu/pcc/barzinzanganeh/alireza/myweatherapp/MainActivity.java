package edu.pcc.barzinzanganeh.alireza.myweatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();

    private Activity mContext;

    @BindView(R.id.weather_id) TextView mWeatherID;
    @BindView(R.id.weather_description) TextView mWeatherDescription;
    @BindView(R.id.weather_main) TextView mWeatherMain;
    @BindView(R.id.weather_icon) TextView mWeatherIcon;
    @BindView(R.id.temp_view) TextView mTempView;
    @BindView(R.id.weather_image_icon) ImageView mWeatherImageIcon;
    @BindView(R.id.city_name) TextView mCityName;
    @BindView(R.id.city_input) EditText mCityInput;
    @BindView(R.id.city_button) Button mCityButton;
    @BindView(R.id.humidity_view) TextView mHumidityView;
    @BindView(R.id.seekbar) SeekBar mSeekbar;
//    @BindView(R.id.web_icon) WebView mWebIcon;


//public ArrayList<Weather> mWeathers = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        mCityButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText text = (EditText)findViewById(R.id.city_input);
        final String mUserCity = text .getText().toString();
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
                        final String mCity = mWeathers.getmCity();
                        final int mID = mWeathers.getmWeatherID();
                        final String mMain = mWeathers.getmWeatherMain();
                        final String mDescription = mWeathers.getmWeatherDescription();
                        final String mIcon = mWeathers.getmWeatherIcon();
                        double mTemp = mWeathers.getmTemp();
                        mTemp = 1.8 * (mTemp - 273) + 32;
                        final int mHumidity = mWeathers.getmMainHumidity();
                        final double mTempF = Double.parseDouble(new DecimalFormat("###.#").format(mTemp));
                        Log.d(TAG, "mID is produced.");
//                        System.out.println(mID);
//                        System.out.println(mMain);
//                        System.out.println(mDescription);
//                        System.out.println(mIcon);
// // TODO: 11/12/17 Add function for menu to do change the temperature toggle between C and F
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mCityName.setText(mCity);
                                mWeatherID.setText(Integer.toString(mID));
                                mWeatherMain.setText(mMain);
                                mWeatherDescription.setText(mDescription);
                                mWeatherIcon.setText(mIcon);
                                mHumidityView.setText(Integer.toString(mHumidity));
                                mSeekbar.setProgress(mHumidity);
                                mTempView.setText((Double.toString(mTempF)) + "  F");
                                Picasso.with(mContext).load(Constants.OPEN_WEATHER_ICON_URL + mIcon+ ".png").into( mWeatherImageIcon);
//                                WebView web = (WebView) findViewById(R.id.web_icon);
//                                web.loadUrl(Constants.OPEN_WEATHER_ICON_URL + mIcon + ".png");
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

        OpenWeatherService.forecastWeather(mUserCity, callback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_celsius:

                break;
            case R.id.menu_action_fahrenheit:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
