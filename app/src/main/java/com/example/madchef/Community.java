package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Community extends AppCompatActivity {

    private Button RecipePostTitle1, RecipePostTitle2, RecipePostTitle3, RecipePostTitle4;
    private ImageView whiteheart1, redheart1, whiteheart2, redheart2, whiteheart3, redheart3;

    BottomNavigationView bottom_navbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        RecipePostTitle1 = findViewById(R.id.RecipePostTitle1);
        RecipePostTitle2 = findViewById(R.id.RecipePostTitle2);
        RecipePostTitle3 = findViewById(R.id.RecipePostTitle3);
        RecipePostTitle4 = findViewById(R.id.RecipePostTitle4);

        whiteheart1 = findViewById(R.id.whiteheart1);
        whiteheart2 = findViewById(R.id.whiteheart2);
        whiteheart3 = findViewById(R.id.whiteheart3);

        redheart1 = findViewById(R.id.redheart1);
        redheart2 = findViewById(R.id.redheart2);
        redheart3 = findViewById(R.id.redheart3);


        RecipePostTitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Community.this, User1.class));
                finish();
            }
        });

        RecipePostTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Community.this, User2.class));
                finish();
            }
        });

        RecipePostTitle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Community.this, User3.class));
                finish();
            }
        });

        RecipePostTitle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Community.this, User4.class));
                finish();
            }
        });

        redheart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redheart1.setVisibility(View.GONE);
                whiteheart1.setVisibility(View.VISIBLE);
            }
        });

        whiteheart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redheart1.setVisibility(View.VISIBLE);
                whiteheart1.setVisibility(View.GONE);
            }
        });

        redheart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redheart2.setVisibility(View.GONE);
                whiteheart2.setVisibility(View.VISIBLE);
            }
        });

        whiteheart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redheart2.setVisibility(View.VISIBLE);
                whiteheart2.setVisibility(View.GONE);
            }
        });

        redheart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redheart3.setVisibility(View.GONE);
                whiteheart3.setVisibility(View.VISIBLE);
            }
        });

        whiteheart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redheart3.setVisibility(View.VISIBLE);
                whiteheart3.setVisibility(View.GONE);
            }
        });


        bottom_navbar = findViewById((R.id.bottom_nav_view));
        bottom_navbar.setSelectedItemId((R.id.Community));
        bottom_navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
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
}