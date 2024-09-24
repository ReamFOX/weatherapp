package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.model;

import java.util.List;

public class WeatherData {
    ;
    private String address;
    private String description;
    private List<DayData> days;
    private List<String> alerts;
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

    public List<String> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<String> alerts) {
        this.alerts = alerts;
    }

    public CurrentConditions getCurrentConditions() {
        return currentConditions;
    }

    public void setCurrentConditions(CurrentConditions currentConditions) {
        this.currentConditions = currentConditions;
    }
}
