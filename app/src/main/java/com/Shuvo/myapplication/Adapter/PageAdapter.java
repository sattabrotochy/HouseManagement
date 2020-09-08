package com.Shuvo.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.Shuvo.myapplication.PostFragment.FlateSaleFragment;
import com.Shuvo.myapplication.PostFragment.HouseRentFragment;
import com.Shuvo.myapplication.PostFragment.HouseWithLandFragment;
import com.Shuvo.myapplication.PostFragment.LandRentFragment;

public class PageAdapter extends FragmentPagerAdapter {


    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HouseRentFragment houseRentFragment = new HouseRentFragment();
                return houseRentFragment;
            case 1:
                LandRentFragment landRentFragment = new LandRentFragment();
                return landRentFragment;
            case 2:
                FlateSaleFragment flateSaleFragment = new FlateSaleFragment();
                return flateSaleFragment;
            case 3:
                HouseWithLandFragment withLandFragment = new HouseWithLandFragment();
                return withLandFragment;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "House";
            case 1:
                return "Land";
            case 2:
                return "Flat";
            case 3:
                return "House With Land";

            default:
                return null;
        }
    }
}
