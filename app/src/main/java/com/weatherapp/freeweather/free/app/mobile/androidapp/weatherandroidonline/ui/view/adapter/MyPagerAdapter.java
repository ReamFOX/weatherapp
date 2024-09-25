package com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.view.FourteenDaysFragment;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.view.SevenDaysFragment;
import com.weatherapp.freeweather.free.app.mobile.androidapp.weatherandroidonline.ui.view.TodayFragment;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentStateAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();

    public MyPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        fragmentList.add(new TodayFragment());
        fragmentList.add(new SevenDaysFragment());
        fragmentList.add(new FourteenDaysFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
