package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.R;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.model.HourData;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.model.WeatherData;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.databinding.FragmentViewMoreBinding;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.main.WeatherViewModel;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.utils.ForecastUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewMoreFragment extends Fragment {
    FragmentViewMoreBinding binding;
    private WeatherViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentViewMoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        //Update ui from LiveData
        viewModel.loadWeather(viewModel.getCurrentCity().getValue()).observe(getViewLifecycleOwner(), weatherData -> {
            if (weatherData != null) {
                binding.cityName.setText(weatherData.getAddress());
                addDetailedHourlyForecast(weatherData);
            }
        });

        //Back to MainActivity
        binding.backButton.setOnClickListener(v ->
                requireActivity()
                        .getSupportFragmentManager()
                        .popBackStack("VIEW_MORE_FRAGMENT", FragmentManager.POP_BACK_STACK_INCLUSIVE));
    }

    private void addDetailedHourlyForecast(WeatherData weatherData) {
        binding.hourlyDetailedForecast.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(requireActivity());
        long currentTimeEpoch = System.currentTimeMillis() / 1000L;

        List<HourData> todayHours = weatherData.getDays().get(0).getHours();
        List<HourData> tomorrowHours = weatherData.getDays().get(1).getHours();
        int limit = 12;
        int hourCounter = 0;

        for (HourData hourData : todayHours) {
            if (hourData.getDatetimeEpoch() > currentTimeEpoch && hourCounter < limit) {
                addHourlyCard(inflater, hourData, hourCounter == 0);
                hourCounter++;
            }
        }
        if (hourCounter < limit) {
            for (HourData hourData : tomorrowHours) {
                if (hourCounter < limit) {
                    addHourlyCard(inflater, hourData, hourCounter == 0);
                    hourCounter++;
                } else {
                    break;
                }
            }
        }
    }

    private void addHourlyCard(LayoutInflater inflater, HourData hourData, boolean isNow) {
        View hourlyCard = inflater.inflate(R.layout.item_detailed_hourly_forecast, binding.hourlyDetailedForecast, false);

        String temperature = getString(R.string.temp_value, hourData.getTemp());
        String uvIndex = getString(R.string.uvi_value, hourData.getUvindex());
        String solarRadiation = getString(R.string.srad_value, hourData.getSolarradiation());
        int iconResId = ForecastUtil.getIcon(hourData.getIcon());

        ImageView hourlyIcon = hourlyCard.findViewById(R.id.hourly_icon);
        TextView hourlyTemperature = hourlyCard.findViewById(R.id.hourly_temperature);
        TextView hourlyTime = hourlyCard.findViewById(R.id.hourly_time);
        TextView hourlySolarRadiation = hourlyCard.findViewById(R.id.hourly_solar_radiation);
        TextView hourlyUvIndex = hourlyCard.findViewById(R.id.hourly_uvindex);

        hourlyTemperature.setText(temperature);
        hourlyIcon.setImageResource(iconResId);
        hourlySolarRadiation.setText(solarRadiation);
        hourlyUvIndex.setText(uvIndex);

        if (isNow) {
            hourlyTime.setText(getString(R.string.now));
        } else {
            hourlyTime.setText(hourData.getDatetime().substring(0, 5));
        }

        int margin = (int) getResources().getDimension(R.dimen._12dp);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) getResources().getDimension(R.dimen.hourly_detailed_card_width),
                (int) getResources().getDimension(R.dimen.hourly_detailed_card_height)
        );

        int currentIndex = binding.hourlyDetailedForecast.getChildCount();
        int columnIndex = (currentIndex % 3);

        if (columnIndex == 0) {
            params.setMargins(0, margin, margin, margin);
        } else if (columnIndex == 1) {
            params.setMargins(margin, margin, margin, margin);
        } else {
            params.setMargins(margin, margin, 0, margin);
        }

        binding.hourlyDetailedForecast.addView(hourlyCard, params);
    }
}