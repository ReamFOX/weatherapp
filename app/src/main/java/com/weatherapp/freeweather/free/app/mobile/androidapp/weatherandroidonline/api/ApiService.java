package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.api;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("{location}")
    Call<WeatherData> getWeatherData(@Path("location") String location,
                                     @Query("unitGroup") String unit,
                                     @Query("key") String apiKey,
                                     @Query("contentType") String contentType);
}
