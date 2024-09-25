package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.model;

import java.util.List;

public class WeatherData {
    ;
    private String address;
    private String description;
    private List<DayData> days;
    private CurrentConditions currentConditions;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DayData> getDays() {
        return days;
    }

    public void setDays(List<DayData> days) {
        this.days = days;
    }

    public CurrentConditions getCurrentConditions() {
        return currentConditions;
    }

    public void setCurrentConditions(CurrentConditions currentConditions) {
        this.currentConditions = currentConditions;
    }
}
