package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.R;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.model.HourData;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.model.WeatherData;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.databinding.FragmentTodayBinding;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.main.WeatherViewModel;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.utils.ForecastUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TodayFragment extends Fragment {

    FragmentTodayBinding binding;
    private WeatherViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTodayBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        // Update ui from LiveData
        viewModel.loadWeather(viewModel.getCurrentCity().getValue()).observe(getViewLifecycleOwner(), weatherData -> {
            if (weatherData != null) {
                binding.temperatureText.setText(
                        getString(R.string.temp_value, weatherData.getCurrentConditions().getTemp())
                );
                binding.condition.setText(weatherData.getCurrentConditions().getConditions());
                binding.temperatureMinMax.setText(getString(R.string.min_max_temp_value,
                        weatherData.getDays().get(0).getTempmin(),
                        weatherData.getDays().get(0).getTempmax()));
                addHourlyForecast(weatherData);
                binding.windValue.setText(
                        getString(R.string.wind_value,weatherData.getCurrentConditions().getWindspeed())
                );
                binding.humidityValue.setText(getString(R.string.value_with_persentage, weatherData.getCurrentConditions().getHumidity()));
                binding.precipitationValue.setText(getString(R.string.value_with_persentage, weatherData.getCurrentConditions().getPrecip()));
                binding.pressureValue.setText(getString(R.string.pressure_value, weatherData.getCurrentConditions().getPressure()));
            }
        });

        binding.viewMore.setOnClickListener(v -> {
            String tag = "VIEW_MORE_FRAGMENT";
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag(tag);

            if (fragment == null) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ViewMoreFragment(), tag)
                        .addToBackStack(tag)
                        .commit();
            }
        });
    }

    private void addHourlyForecast(WeatherData weatherData) {
        binding.hourlyForecast.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(requireActivity());
        long currentTimeEpoch = System.currentTimeMillis() / 1000L;

        List<HourData> todayHours = weatherData.getDays().get(0).getHours();
        List<HourData> tomorrowHours = weatherData.getDays().get(1).getHours();
        int limit = 5;
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
        View hourlyCard = inflater.inflate(R.layout.item_hourly_forecast, binding.hourlyForecast, false);

        String temperature = getString(R.string.temp_value, hourData.getTemp());

        int iconResId = ForecastUtil.getIcon(hourData.getIcon());
        ImageView hourlyIcon = hourlyCard.findViewById(R.id.hourly_icon);
        TextView hourlyTemperature = hourlyCard.findViewById(R.id.hourly_temperature);
        TextView hourlyTime = hourlyCard.findViewById(R.id.hourly_time);

        hourlyTemperature.setText(temperature);
        hourlyIcon.setImageResource(iconResId);

        if (isNow) {
            hourlyTime.setText(getString(R.string.now));
        } else {
            hourlyTime.setText(hourData.getDatetime().substring(0, 5));
        }

        int margin = (int) getResources().getDimension(R.dimen._10dp);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) getResources().getDimension(R.dimen.hourly_card_width),
                (int) getResources().getDimension(R.dimen.hourly_card_height)
        );

        if (binding.hourlyForecast.getChildCount() == 0) {
            params.setMargins(0, margin, margin, margin);
        } else if (binding.hourlyForecast.getChildCount() == 4) {
            params.setMargins(margin, margin, 0, margin);
        } else {
            params.setMargins(margin, margin, margin, margin);
        }

        binding.hourlyForecast.addView(hourlyCard, params);
    }
}