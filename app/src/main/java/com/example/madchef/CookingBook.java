package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CookingBook extends AppCompatActivity {
    FirebaseAuth fb = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = fb.getCurrentUser();
    private String TDiet,TAllerg,TCuisine,TDish;
    private TextView TVDispDiet,  TVDispAllerg, TVDispCuisine;
    private TextView TVDispDish;

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
        TVDispDiet = findViewById(R.id.TVDispDiet);
        TVDispDish = findViewById(R.id.TVDispDish);
        TVDispAllerg = findViewById(R.id.TVDispAllerg);
        TVDispCuisine = findViewById(R.id.TVDispCuisine);




        TDiet = getIntent().getStringExtra("DIET");

        TAllerg = getIntent().getStringExtra("ALLERGIES");

        TCuisine = getIntent().getStringExtra("CUISINE");

        TDish = getIntent().getStringExtra("DISH");

        if(firebaseUser ==null){
            Toast.makeText(CookingBook.this,"Something went wrong.",Toast.LENGTH_LONG).show();
        }
        else{
            showUserPreferences(firebaseUser);
        }



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

    private void showUserPreferences(FirebaseUser firebaseUser){
        String userID = firebaseUser.getUid();

        DatabaseReference refProf = FirebaseDatabase.getInstance().getReference("users");
        refProf.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if(readUserDetails!=null){
                    TDiet=readUserDetails.diet;
                    TAllerg = readUserDetails.allergies;
                    TCuisine = readUserDetails.cuisine;
                    TDish=readUserDetails.food;

                    TVDispDiet.setText(TDiet);
                    TVDispAllerg.setText(TAllerg);
                    TVDispCuisine.setText(TCuisine);
                    TVDispDish.setText(TDish);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }
