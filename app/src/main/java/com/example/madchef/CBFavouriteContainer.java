package com.example.madchef;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CBFavouriteContainer extends AppCompatActivity {
    CB_Favourite favfragment = new CB_Favourite();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbfavourite_container);


        getSupportFragmentManager().beginTransaction().replace(R.id.favcontainerfrag, favfragment).commit();
    }
}