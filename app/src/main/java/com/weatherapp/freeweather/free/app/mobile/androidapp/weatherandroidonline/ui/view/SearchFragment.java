package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.databinding.FragmentSearchBinding;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.main.WeatherViewModel;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.utils.Const;


public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;
    private WeatherViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        binding.searchInput.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                String cityName = binding.searchInput.getText().toString();
                String filteredCityName = formatCityName(cityName);
                if (filteredCityName.isBlank()) {
                    Toast.makeText(getActivity(), "Can't find city " + cityName, Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.loadWeather(filteredCityName);
                }
                return true;
            }
            return false;
        });
    }

    private static String formatCityName(String cityName) {
        String cleanedName = cityName.replaceAll("[^a-zA-Z0-9\\s-']", "").trim();

        if (cleanedName.isEmpty()) {
            return cleanedName;
        }

        return cleanedName.substring(0, 1).toUpperCase() + cleanedName.substring(1).toLowerCase();
    }
}