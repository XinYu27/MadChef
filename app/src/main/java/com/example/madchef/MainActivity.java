package com.example.madchef;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view. MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.madchef.Adapters.RandomRecipeAdapter;
import com.example.madchef.Listeners.RandomRecipeResponseListener;
import com.example.madchef.Models.RandomRecipeApiResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.example.madchef.Listeners.RecipeClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView searchbutton, settingbutton;
    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;
    Spinner spinner;
    List<String> tags = new ArrayList<>();

    BottomNavigationView bottom_navbar;
    Main_home mainhomeFragment = new Main_home();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchbutton = findViewById(R.id.search_button);
        settingbutton = findViewById(R.id.setting_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchMenu.class);
                startActivity(intent);
            }
        });

        settingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Profile.class);
                startActivity(intent);
            }
        });


        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading....");



        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener,tags);
        dialog.show();



        //bottomnavigationview navigation
        bottom_navbar = findViewById(R.id.bottom_nav_view);
        bottom_navbar.setSelectedItemId((R.id.Home));
        getSupportFragmentManager().beginTransaction().replace(R.id.Mainfrag, mainhomeFragment).commit();
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


        /*
        ImageView setting = (ImageView)findViewById(R.id.imageView23);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Profile.this, LogOut.class);
                startActivity(intent);
            }
        });

         */


    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, response.recipes,recipeClickListener);
            recyclerView.setAdapter(randomRecipeAdapter);

        }

        @Override
        public void didError(String messaage) {
            Toast.makeText(MainActivity.this,messaage,Toast.LENGTH_SHORT);
        }
    };
    //aku x import (so nnti ade error kat sini)-just import
    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
            intent.putExtra("id",id);
            Toast.makeText(MainActivity.this, "loading...", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            //startActivity(new Intent(MainActivity.this, RecipeDetailActivity.class)
                    //.putExtra("id",id));
        }
    };
/*
    private final RandomRecipeResponseListener randomRecipeResponseListener2 = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id.foodsuggest);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, response.recipes);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String messaage) {
            Toast.makeText(MainActivity.this,messaage,Toast.LENGTH_SHORT);
        }
    };*/

    public void BtmMealPlanOnClick(View v){
        Intent intent = new Intent(getApplicationContext(),meal_planning.class);
        startActivity(intent);
    }
    public void BtmPostOnClick(View v){
        Intent intent = new Intent(getApplicationContext(),post.class);
        startActivity(intent);
    }




}
