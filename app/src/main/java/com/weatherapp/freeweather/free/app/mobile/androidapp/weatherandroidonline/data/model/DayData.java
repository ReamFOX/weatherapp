package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.model;

import java.util.List;

public class DayData {
    private String datetime;
    private double tempmax;
    private double tempmin;
    private double temp;
    private double humidity;
    private double windspeed;
    private double pressure;
    private String icon;
    private List<HourData> hours;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public double getTempmax() {
        return tempmax;
    }

    public void setTempmax(double tempmax) {
        this.tempmax = tempmax;
    }

    public double getTempmin() {
        return tempmin;
    }

    public void setTempmin(double tempmin) {
        this.tempmin = tempmin;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<HourData> getHours() {
        return hours;
    }

    public void setHours(List<HourData> hours) {
        this.hours = hours;
    }
}
