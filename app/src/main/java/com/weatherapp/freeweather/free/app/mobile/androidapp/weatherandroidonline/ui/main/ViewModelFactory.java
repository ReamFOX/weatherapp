package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.repository.WeatherDataRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final WeatherDataRepository weatherRepository;

    public ViewModelFactory(WeatherDataRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WeatherViewModel.class)) {
            return (T) new WeatherViewModel(weatherRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}