package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class CBPreferenceActivity extends AppCompatActivity {

    protected String StrDiet = " ";
    protected String StrAllergies = " ";
    protected String StrCuisine = " ";
    protected String StrDish = " ";
    RadioGroup RGDish, RGDish2;

    EditText ETDiet, ETAllerg, ETCuisine;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cb_preference);

        ETDiet = findViewById(R.id.ETDiet);
        ETAllerg = findViewById(R.id.ETAllergies);
        ETCuisine = findViewById(R.id.ETCuisine);

        RGDish = (RadioGroup) findViewById(R.id.RGDish);
        //RGDish2 = (RadioGroup) findViewById(R.id.RGDish2);

        Button BtnSave = findViewById(R.id.BtnSave);
        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        /*
                EditText ETDiet = findViewById(R.id.ETDiet);
                StrDiet = ETDiet.getText().toString();
                if(StrDiet.isEmpty())
                    StrDiet = " - ";

                EditText ETAllerg = findViewById(R.id.ETAllergies);
                StrAllergies = ETAllerg.getText().toString();
                if(StrAllergies.isEmpty())
                    StrAllergies = " - ";

                EditText ETCuisine = findViewById(R.id.ETCuisine);
                StrCuisine = ETCuisine.getText().toString();
                if(StrCuisine.isEmpty())
                    StrCuisine = " - ";

                System.out.println(StrDiet);
                System.out.println(StrAllergies);
                System.out.println(StrCuisine);
                appendLog();

                         */
            }
        });


    }

    public void appendLog(){
        try {
            FileOutputStream fileout = openFileOutput("log.txt", MODE_APPEND);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            BufferedWriter BuffWriter = new BufferedWriter(outputWriter);
            BuffWriter.write(StrDiet);
            BuffWriter.newLine();
            BuffWriter.write(StrAllergies);
            BuffWriter.newLine();
            BuffWriter.write(StrCuisine);
            BuffWriter.newLine();
            BuffWriter.newLine();
            BuffWriter.close();

            Toast.makeText(getBaseContext(), "Saved successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }




    public void OnClickToSave (View v){

        String TDiet = ETDiet.getText().toString();
        String TAllerg = ETAllerg.getText().toString();
        String TCuisine = ETCuisine.getText().toString();

        RadioButton RBDish = findViewById(RGDish.getCheckedRadioButtonId());
        String TDish = RBDish.getText().toString();

        //RadioButton RBDish2 = findViewById(RGDish2.getCheckedRadioButtonId());
        //String TDish2 = RBDish2.getText().toString();

        Intent intent = new Intent(CBPreferenceActivity.this, CookingBook.class);
        intent.putExtra("DIET", TDiet);
        intent.putExtra("ALLERGIES", TAllerg);
        intent.putExtra("CUISINE", TCuisine);
        intent.putExtra("DISH", TDish);
        //intent.putExtra("DISH2", TDish2);
        startActivity(intent);

    }

    public void OnClickToHome (View v){
        Intent intent = new Intent(this, CookingBook.class);
        startActivity(intent);
    }




}