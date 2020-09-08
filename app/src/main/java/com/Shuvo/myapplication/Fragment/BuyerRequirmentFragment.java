package com.Shuvo.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.Shuvo.myapplication.R;


public class BuyerRequirmentFragment extends Fragment {


    public BuyerRequirmentFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_buyer_requirment, container, false);
    }
}