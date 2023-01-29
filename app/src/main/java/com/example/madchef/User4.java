package com.example.madchef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class User4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user4);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(User4.this, Community.class));
    }
}