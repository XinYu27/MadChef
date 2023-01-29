package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CookingBook extends AppCompatActivity {

    ArrayList<String> AllData = new ArrayList<String>();
    BottomNavigationView bottom_navbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_book);

        //bottomnavigationview navigation
        bottom_navbar = findViewById(R.id.bottom_nav_view);
        bottom_navbar.setSelectedItemId((R.id.Book));
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

        readLog();
        TextView TVDispDiet = findViewById(R.id.TVDispDiet);
        final TextView[] TVDispDish = {findViewById(R.id.TVDispDish)};
        final TextView TVDispAllerg = findViewById(R.id.TVDispAllerg);
        TextView TVDispCuisine = findViewById(R.id.TVDispCuisine);




        String TDiet = getIntent().getStringExtra("DIET");
        TVDispDiet.setText(TDiet);

        String TAllerg = getIntent().getStringExtra("ALLERGIES");
        TVDispAllerg.setText(TAllerg);

        String TCuisine = getIntent().getStringExtra("CUISINE");
        TVDispCuisine.setText(TCuisine);

        String TDish = getIntent().getStringExtra("DISH");
        TVDispDish[0].setText(TDish);


    }

    public void readLog(){
        try{
            FileInputStream fileIn=openFileInput("log.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            BufferedReader BuffReader = new BufferedReader(InputRead);

            String readString = BuffReader.readLine();
            while(readString != null){
                AllData.add(readString);
                readString = BuffReader.readLine();
            }
            InputRead.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void OnClickToPref (View v){
        Intent intent = new Intent(this, CBPreferenceActivity.class);
        startActivity(intent);
    }

    public void OnClickToFav (View v){
        Intent intent = new Intent(this, CB_Favourite.class);
        startActivity(intent);
    }


    }
