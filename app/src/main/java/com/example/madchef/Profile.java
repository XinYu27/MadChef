package com.example.madchef;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_profile);

        TextView name = (TextView) findViewById(R.id.showName);
        TextView birthdate = (TextView) findViewById(R.id.showBD);
        TextView phone = (TextView) findViewById(R.id.showPhoneNum);
        TextView email = (TextView) findViewById(R.id.showEmail);


    }

}
