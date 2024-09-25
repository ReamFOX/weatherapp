package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.model;

import java.util.List;

public class CurrentConditions {
    private String datetime;
    private long datetimeEpoch;
    private double temp;
    private double humidity;
    private double precip;
    private double windspeed;
    private double pressure;
    private String conditions;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public long getDatetimeEpoch() {
        return datetimeEpoch;
    }

    public void setDatetimeEpoch(long datetimeEpoch) {
        this.datetimeEpoch = datetimeEpoch;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecip() {
        return precip;
    }

    public void setPrecip(double precip) {
        this.precip = precip;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
}
