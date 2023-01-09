package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;


public class CookingBook extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        ScrollView scrollView = view.findViewById(R.id.SVcookingbook);
        NavController navController = Navigation.findNavController(scrollView);
        scrollView.setNavController(navController);

         */

        /*
        Button CB_pref = view.findViewById(R.id.CB_pref);
        View.OnClickListener OCLpref = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_DestCB_to_DestCB_Pref);

            }
        };
        CB_pref.setOnClickListener(OCLpref);

        Button CB_fav = view.findViewById(R.id.CB_fav);
        View.OnClickListener OCLfav = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_DestCB_to_DestCB_Fav);

            }
        };
        CB_fav.setOnClickListener(OCLfav);

         */

    }

    public CookingBook() {
        // Required empty public constructor
    }


    public static CookingBook newInstance(String param1, String param2) {
        CookingBook fragment = new CookingBook();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cooking_book,container, false);
        Button CB_pref = (Button)view.findViewById(R.id.CB_pref);
        Button CB_fav = (Button)view.findViewById(R.id.CB_fav);


        CB_pref.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.CB_Preference, new CB_Preference());
                ft.commit();

            }
        });
        CB_fav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.CB_Favourite, new CB_Favourite());
                ft.commit();

            }
        });
        return view;
    }

    /*
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        Button CB_pref = view.findViewById(R.id.CB_pref);
        View.OnClickListener OCLpref = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Navigation.findNavController(view).navigate(R.id.action_cookingBook_to_CB_Preference);
                Navigation.findNavController(requireView()).navigate(R.id.action_cookingBook_to_CB_Preference);

            }
            };
            CB_pref.setOnClickListener(OCLpref);
        }

     */


    }
