package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.api;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.utils.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit INSTANCE = null;

    private RetrofitClientInstance() {

    }

    public static Retrofit getRetrofitInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}
