package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayoutMediator;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.R;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.api.RetrofitClientInstance;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.api.WeatherApiService;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.model.WeatherData;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.data.repository.WeatherDataRepository;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.databinding.ActivityMainBinding;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.view.SearchFragment;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.view.adapter.MyPagerAdapter;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.utils.Const;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        List<String> cities = new ArrayList<>();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, cities);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spinner.setAdapter(spinnerAdapter);

        WeatherApiService weatherApiService =
                RetrofitClientInstance.getRetrofitInstance().create(WeatherApiService.class);
        WeatherDataRepository weatherDataRepository = new WeatherDataRepository(weatherApiService);
        viewModel = new ViewModelProvider(this,
                new ViewModelFactory(weatherDataRepository)).get(WeatherViewModel.class);
        viewModel.loadWeather("Kyiv");

        // fetch weather by selecting city from dropdown menu
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.loadWeather(cities.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Add city to the top dropdown menu
        viewModel.loadWeather(viewModel.getCurrentCity().getValue()).observe(this, weatherData -> {
            getSupportFragmentManager().popBackStack("SEARCH_FRAGMENT", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            if (weatherData != null) {
                if (!cities.contains(weatherData.getAddress())) {
                    cities.add(weatherData.getAddress());
                    spinnerAdapter.notifyDataSetChanged();
                }
                int position = cities.indexOf(weatherData.getAddress());
                binding.spinner.setSelection(position);
            }
        });

        //Adapter for tabs
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), getLifecycle());
        binding.viewPager.setAdapter(pagerAdapter);
        new TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                (tab, position) -> tab.setText(Const.TAB_TITLES.get(position))).attach();

        //Open setting menu
        binding.settingsButton.setOnClickListener(view -> {
            if (binding.settingsMenu.isDrawerOpen(GravityCompat.START)) {
                binding.settingsMenu.closeDrawer(GravityCompat.START);
            } else {
                binding.settingsMenu.openDrawer(GravityCompat.START);
            }
        });

        //Open search fragment
        binding.searchButton.setOnClickListener(view -> {
            String tag = "SEARCH_FRAGMENT";
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);

            if (fragment == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new SearchFragment(), tag)
                        .addToBackStack(tag)
                        .commit();
            }
        });

        //Toast about error from request
        viewModel.getErrorData().observe(this, error -> {
            Toast.makeText(this, "Something went wrong...", Toast.LENGTH_SHORT).show();
        });
    }
}