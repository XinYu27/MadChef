package com.example.madchef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class User1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(User1.this, Community.class));
    }
}