package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class CBPreferenceActivity extends AppCompatActivity {

    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("users");
    FirebaseAuth fb = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = fb.getCurrentUser();

    RadioGroup RGDish;
    RadioButton RBDish;

    EditText ETDiet, ETAllerg, ETCuisine;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cb_preference);

        ETDiet = findViewById(R.id.ETDiet);
        ETAllerg = findViewById(R.id.ETAllergies);
        ETCuisine = findViewById(R.id.ETCuisine);

        RGDish = (RadioGroup) findViewById(R.id.RGDish);


        Button BtnSave = findViewById(R.id.BtnSave);

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TDiet = ETDiet.getText().toString();
                String TAllerg = ETAllerg.getText().toString();
                String TCuisine = ETCuisine.getText().toString();
                String TDish = null;
                int selectedID = RGDish.getCheckedRadioButtonId();


                if(selectedID!=-1){
                    RBDish = findViewById(selectedID);
                    TDish = RBDish.getText().toString();
                }

                FirebaseUtils.addDiet(TDiet);
                FirebaseUtils.addAllergies(TAllerg);
                FirebaseUtils.addCuisine(TCuisine);
                FirebaseUtils.addDish(TDish);
                Intent intent = new Intent(CBPreferenceActivity.this, CookingBook.class);
                startActivity(intent);


            }
        });


    }


}