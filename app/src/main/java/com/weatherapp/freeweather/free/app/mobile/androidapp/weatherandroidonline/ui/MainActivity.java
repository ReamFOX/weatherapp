package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.tabs.TabLayoutMediator;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.MyPagerAdapter;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.R;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
    }


}