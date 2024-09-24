package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.main;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.tabs.TabLayoutMediator;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.R;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.databinding.ActivityMainBinding;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.view.adapter.MyPagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ArrayAdapter<CharSequence> spinnerAdapter =
                ArrayAdapter.createFromResource(this, R.array.spinner, R.layout.spinner_item);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spinner.setAdapter(spinnerAdapter);

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), getLifecycle());
        binding.viewPager.setAdapter(pagerAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            List<String> titles = List.of("Today", "7 days", "14 days");
            tab.setText(titles.get(position));
        }).attach();

        binding.settingsButton.setOnClickListener(view -> {
            if (binding.settingsMenu.isDrawerOpen(GravityCompat.START)) {
                binding.settingsMenu.closeDrawer(GravityCompat.START);
            } else {
                binding.settingsMenu.openDrawer(GravityCompat.START);
            }
        });

        binding.searchButton.setOnClickListener(view -> {

        });
    }


}