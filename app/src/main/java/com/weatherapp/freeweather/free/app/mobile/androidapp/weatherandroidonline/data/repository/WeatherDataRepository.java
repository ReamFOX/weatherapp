package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.api.WeatherApiService;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.model.WeatherData;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.utils.Const;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherDataRepository {
    private final WeatherApiService weatherApiService;
    private final MutableLiveData<WeatherData> weatherData = new MutableLiveData<>();
    private final MutableLiveData<String> errorData = new MutableLiveData<>();

    public WeatherDataRepository(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    public LiveData<WeatherData> getWeatherData(String cityName) {
        weatherApiService.getWeatherData(cityName, Const.UNIT, Const.API_KEY, Const.CONTENT_TYPE)
                .enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    weatherData.setValue(response.body());
                } else {
                    errorData.setValue("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherData> call, @NonNull Throwable throwable) {
                errorData.setValue("Error: " + throwable.getMessage());
            }
        });
        return weatherData;
    }

    public LiveData<String> getError() {
        return errorData;
    }
}
