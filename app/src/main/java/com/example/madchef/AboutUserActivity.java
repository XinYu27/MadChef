package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AboutUserActivity extends AppCompatActivity {


    AboutUser aboutuserfragment = new AboutUser();
    BottomNavigationView bottom_navbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutuser);

        //bottomnavigationview navigation
        bottom_navbar = findViewById(R.id.bottom_nav_view);
        bottom_navbar.setSelectedItemId((R.id.Profile));
        getSupportFragmentManager().beginTransaction().replace(R.id.profilefrag, aboutuserfragment).commit();
        bottom_navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Book:
                        startActivity(new Intent(getApplicationContext(), CookingBook.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Community:
                        startActivity(new Intent(getApplicationContext(), Community.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Post:
                        startActivity(new Intent(getApplicationContext(), post.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Profile:
                        Intent intent = new Intent(getApplicationContext(), AboutUserActivity.class);
                        startActivity(intent);
                        return true;
                }

                return false;
            }
        });

    }

    public void OnClickToBook (View v){
        Intent intent = new Intent(this, meal_planning.class);
        startActivity(intent);
    }
}