package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.databinding.FragmentSevenDaysBinding;

import org.jetbrains.annotations.NotNull;

public class SevenDaysFragment extends Fragment {

    FragmentSevenDaysBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSevenDaysBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}