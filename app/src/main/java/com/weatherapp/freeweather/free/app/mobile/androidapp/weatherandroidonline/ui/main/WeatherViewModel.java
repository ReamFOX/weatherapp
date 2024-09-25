package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.model.WeatherData;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.repository.WeatherDataRepository;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.utils.Const;

public class WeatherViewModel extends ViewModel {
    private final WeatherDataRepository weatherDataRepository;
    private final LiveData<String> errorData;
    private final MutableLiveData<WeatherData> weatherData;
    private final MutableLiveData<String> currentCity;

    public WeatherViewModel(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
        this.weatherData = new MutableLiveData<>();
        this.currentCity = new MutableLiveData<>(Const.DEFAULT_CITY);
        this.errorData = weatherDataRepository.getError();
    }

    public LiveData<WeatherData> loadWeather(String cityName) {
        LiveData<WeatherData> data = weatherDataRepository.getWeatherData(cityName);
        data.observeForever(weatherData -> {
            if (weatherData != null) {
                currentCity.setValue(weatherData.getAddress());
            }
        });
        return data;
    }

    public LiveData<String> getErrorData() {
        return errorData;
    }

    public MutableLiveData<WeatherData> getWeatherData() {
        return weatherData;
    }

    public LiveData<String> getCurrentCity() {
        return currentCity;
    }
}
