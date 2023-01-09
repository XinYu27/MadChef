package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Community2 extends AppCompatActivity {

    BottomNavigationView bottom_navbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community2);

        bottom_navbar = findViewById((R.id.bottom_nav_view));
        bottom_navbar.setSelectedItemId((R.id.Home));

        bottom_navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.Book:
                        startActivity(new Intent(getApplicationContext(),CookingBook2.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Community:
                        return true;
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(),Main_home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext(),Profile2.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}