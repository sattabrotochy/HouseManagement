package com.Shuvo.myapplication.PostFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.Shuvo.myapplication.R;


public class HousePostEditFragment extends Fragment {


    public HousePostEditFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_house_post_edit, container, false);

        return view;
    }
}