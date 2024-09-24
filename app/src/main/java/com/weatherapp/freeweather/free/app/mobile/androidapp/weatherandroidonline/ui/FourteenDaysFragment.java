package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.R;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.databinding.FragmentFourteenDaysBinding;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.databinding.FragmentTodayBinding;

import org.jetbrains.annotations.NotNull;

public class FourteenDaysFragment extends Fragment {

    FragmentFourteenDaysBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFourteenDaysBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}