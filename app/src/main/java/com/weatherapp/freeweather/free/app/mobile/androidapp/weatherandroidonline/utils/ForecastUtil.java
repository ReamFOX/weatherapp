package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.utils;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.R;

public class ForecastUtil {

    public static int getIcon(String icon) {
        if (icon.startsWith("clear")) {
            return R.drawable.ic_sun;
        } else if (icon.equals("rain")) {
            return R.drawable.ic_rain;
        } else if (icon.equals("fog")) {
            return R.drawable.ic_fog;
        } else {
            return R.drawable.ic_cloud;
        }
    }

    public static String formatData(String inputDate) {
        String[] parts = inputDate.split("-");
        return parts[2] + "." + parts[1] + "." + parts[0];
    }

}
