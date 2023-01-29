package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CookingBook2 extends AppCompatActivity {

    BottomNavigationView bottom_navbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_book2);

        bottom_navbar = findViewById((R.id.bottom_nav_view));
        bottom_navbar.setSelectedItemId((R.id.Home));

        bottom_navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.Book:
                        return true;
                    case R.id.Community:
                        startActivity(new Intent(getApplicationContext(),Community2.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(),Main_home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext(),Profile2.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Post:
                        startActivity(new Intent(getApplicationContext(),MealPlan3.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.cookingbookfrag, new CookingBook());
        ft.commit();
    }

    /*
    public void CB_prefOnClick(View view){
        FragmentContainerView FCVcookingbook = findViewById(R.id.cookingbookfrag);
        FragmentContainerView FCVPreference = findViewById(R.id.CBpreffrag);
        FragmentContainerView FCVFavourite = findViewById(R.id.CBfavfrag);
        FCVcookingbook.setVisibility(View.GONE);
        FCVPreference.setVisibility(View.VISIBLE);
        FCVFavourite.setVisibility(View.GONE);
    }
    public void CB_favOnClick(View view){
        FragmentContainerView FCVcookingbook = findViewById(R.id.cookingbookfrag);
        FragmentContainerView FCVPreference = findViewById(R.id.CBpreffrag);
        FragmentContainerView FCVFavourite = findViewById(R.id.CBfavfrag);
        FCVcookingbook.setVisibility(View.GONE);
        FCVPreference.setVisibility(View.GONE);
        FCVFavourite.setVisibility(View.VISIBLE);
    }

     */


}
