package com.Shuvo.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.Shuvo.myapplication.Fragment.BuyerFragment;
import com.Shuvo.myapplication.Fragment.SellerFragment;

public class MainPageAdapter extends FragmentPagerAdapter {

    public MainPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BuyerFragment buyerFragment = new BuyerFragment();
                return buyerFragment;
            case 1:
                SellerFragment sellerFragment = new SellerFragment();
                return sellerFragment;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Buyer";
            case 1:
                return "Seller";
            default:
                return null;
        }
    }
}
