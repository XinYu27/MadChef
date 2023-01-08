package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madchef.R;

public class LogOut extends AppCompatActivity {
    Button goback, logout;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_logout);

        goback=(Button) findViewById(R.id.goBackBtn);
        logout=(Button) findViewById(R.id.logoutBtn);

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogOut.this,Profile.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogOut.this,LogIn.class));
            }
        });

    }

}
