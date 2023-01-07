package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madchef.Models.LogOut;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private FirebaseDatabase db;
    private DatabaseReference userRef;
    private String email,name,birthdate,phone;
    String email0;

    @Override
    protected void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        email0 = intent.getStringExtra("email");

        TextView name = (TextView) findViewById(R.id.showName);
        TextView birthdate = (TextView) findViewById(R.id.showBD);
        TextView phone = (TextView) findViewById(R.id.showPhoneNum);
        TextView email = (TextView) findViewById(R.id.showEmail);
        ImageView logout = (ImageView)findViewById(R.id.imageView23);

        db = FirebaseDatabase.getInstance();
        userRef = db.getReference("users");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(email0)){
                        name.setText(ds.child("name").getValue(String.class));
                        birthdate.setText(ds.child("birthDate").getValue(String.class));
                        phone.setText(ds.child("phoneNum").getValue(String.class));
                        email.setText(email0);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Profile.this, LogOut.class);
                startActivity(intent);
            }
        });

    }

}
