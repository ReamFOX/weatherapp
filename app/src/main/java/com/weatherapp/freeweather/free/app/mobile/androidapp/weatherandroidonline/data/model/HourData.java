package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.model;

public class HourData {
    private String datetime;
    private long datetimeEpoch;
    private double temp;
    private double solarradiation;
    private int uvindex;
    private String icon;

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

    public double getSolarradiation() {
        return solarradiation;
    }

    public void setSolarradiation(double solarradiation) {
        this.solarradiation = solarradiation;
    }

    public int getUvindex() {
        return uvindex;
    }

    public void setUvindex(int uvindex) {
        this.uvindex = uvindex;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
