package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.R;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.model.DayData;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.databinding.FragmentFourteenDaysBinding;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.main.WeatherViewModel;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.utils.ForecastUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FourteenDaysFragment extends Fragment {

    FragmentFourteenDaysBinding binding;
    private WeatherViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFourteenDaysBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        viewModel.loadWeather(viewModel.getCurrentCity().getValue()).observe(getViewLifecycleOwner(), weatherData -> {
            if (weatherData != null) {
                binding.fourteenDaysForecast.removeAllViews();
                LayoutInflater inflater = LayoutInflater.from(requireActivity());
                List<DayData> days = weatherData.getDays();
                int limit = 14;
                int daysCounter = 0;
                for (DayData day : days) {
                    if (daysCounter == limit) {
                        break;
                    } else {
                        View card = formDayleCard(inflater, day, daysCounter == 0);
                        binding.fourteenDaysForecast.addView(card);
                        daysCounter++;
                    }
                }
            }
        });
    }

    public View formDayleCard(LayoutInflater inflater, DayData day, boolean isToday) {
        View dayleCard = inflater.inflate(R.layout.item_daily_forecast, binding.fourteenDaysForecast, false);
        String temperature = getString(R.string.temp_value, day.getTemp());
        String pressure = getString(R.string.pressure_value, day.getPressure());
        String humidity = getString(R.string.value_with_persentage, day.getHumidity());
        String wind = getString(R.string.wind_value, day.getWindspeed());
        String datetime;
        if (isToday) {
            datetime = getString(R.string.today);
        } else {
            datetime = ForecastUtil.formatData(day.getDatetime());
        }

        int iconResId = ForecastUtil.getIcon(day.getIcon());
        ImageView dayleIcon = dayleCard.findViewById(R.id.forecast_icon);
        TextView dayleTemperature = dayleCard.findViewById(R.id.temp_card_value);
        TextView daylePressure = dayleCard.findViewById(R.id.pressure_card_value);
        TextView dayleWind = dayleCard.findViewById(R.id.wind_card_value);
        TextView dayleHumidity = dayleCard.findViewById(R.id.humidity_card_value);
        TextView date = dayleCard.findViewById(R.id.date);

        dayleTemperature.setText(temperature);
        dayleIcon.setImageResource(iconResId);
        daylePressure.setText(pressure);
        dayleHumidity.setText(humidity);
        dayleWind.setText(wind);
        date.setText(datetime);
        return dayleCard;
    }
}