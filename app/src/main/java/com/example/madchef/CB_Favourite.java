package com.example.madchef;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CB_Favourite extends Fragment {



    public CB_Favourite() {
        // Required empty public constructor
    }

    public static CB_Favourite newInstance(String param1, String param2) {
        CB_Favourite fragment = new CB_Favourite();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c_b__favourite, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}