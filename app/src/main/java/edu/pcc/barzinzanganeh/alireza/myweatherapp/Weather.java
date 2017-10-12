package edu.pcc.barzinzanganeh.alireza.myweatherapp;

/**
 * Created by ABZanganeh on 10/9/17.
 */

public class Weather {
    private String mCity;
    private int mWeatherID;
    private String mWeatherMain;
    private String mWeatherDescription;
    private String mWeatherIcon;
    private double mTemp;
//    private float mMainHumidity;
//    private float mMainTempMin;
//    private float mMainTempMax;
//    private float mWindSpeed;



    public Weather(String mCity, int mWeatherID, String mWeatherMain, String mWeatherDescription,
                   String mWeatherIcon, double mTemp) {
        this.mCity = mCity;
        this.mWeatherID = mWeatherID;
        this.mWeatherMain = mWeatherMain;
        this.mWeatherDescription = mWeatherDescription;
        this.mWeatherIcon = mWeatherIcon;
        this.mTemp = mTemp;
//        this.mMainHumidity = mMainHumidity;
//        this.mMainTempMin = mMainTempMin;
//        this.mMainTempMax = mMainTempMax;
//        this.mWindSpeed = mWindSpeed;
    }

    public double getmTemp() {
        return mTemp;
    }

    public String getmWeatherIcon() {
        return mWeatherIcon;
    }

    public String getmCity() {
        return mCity;
    }

    public int getmWeatherID() {
        return mWeatherID;
    }

    public String getmWeatherMain() {
        return mWeatherMain;
    }

    public String getmWeatherDescription() {
        return mWeatherDescription;
    }

//    public float getmMainHumidity() {
//        return mMainHumidity;
//    }
//
//    public float getmMainTempMin() {
//        return mMainTempMin;
//    }
//
//    public float getmMainTempMax() {
//        return mMainTempMax;
//    }
//
//    public float getmWindSpeed() {
//        return mWindSpeed;
//    }
}

//String mCity, , float mMainHumidity, float mMainTempMin, float mMainTempMax, float mWindSpeed