package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    FirebaseAuth fb;
    private String email,name,birthdate,phone;
    TextView TVname, TVbirthdate, TVphone, TVemail;

    @Override
    protected void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_profile);


        TVname = (TextView) findViewById(R.id.showName);
        TVbirthdate = (TextView) findViewById(R.id.showBD);
        TVphone = (TextView) findViewById(R.id.showPhoneNum);
        TVemail = (TextView) findViewById(R.id.showEmail);
        ImageView logout = (ImageView)findViewById(R.id.imageView23);
        Button editprofile = (Button)findViewById(R.id.editProf);

        fb = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = fb.getCurrentUser();

        if(firebaseUser == null){
            Toast.makeText(Profile.this,"Something went wrong.",Toast.LENGTH_LONG).show();
        }else{
            showUserProfile(firebaseUser);
        }

        //button

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Profile.this, LogOut.class);
                startActivity(intent);
            }
        });

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Profile.this, ProfileSetting.class);
                startActivity(intent);
            }
        });
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();

        DatabaseReference refProf = FirebaseDatabase.getInstance().getReference("users");
        refProf.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if (readUserDetails != null){
                    name=readUserDetails.name;
                    email = firebaseUser.getEmail();
                    birthdate = readUserDetails.birthDate;
                    phone = readUserDetails.phoneNum;

                    TVname.setText(name);
                    TVemail.setText(email);
                    TVbirthdate.setText(birthdate);
                    TVphone.setText(phone);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this,"Something went wrong.",Toast.LENGTH_LONG).show();
            }
        });

    }

}
