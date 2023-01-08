package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ProfileSetting extends AppCompatActivity {

    DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users");
    FirebaseAuth fb = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = fb.getCurrentUser();
    EditText ETname,ETbd,ETphonenum,ETemail,ETpwd;
    private String email,name,birthdate,phone,password;
    Button save;
    ImageView logout;

    @Override
    protected void onCreate (Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_edit_profile);

//        userRef=db.getReference("users");

        ETname = (EditText) findViewById(R.id.editName);
        ETbd = (EditText) findViewById(R.id.editBD);
        ETphonenum = (EditText) findViewById(R.id.editPhoneNum);
        ETemail = (EditText) findViewById(R.id.editEmail);
        ETpwd = (EditText) findViewById(R.id.editPwd);
        save = (Button)findViewById(R.id.saveBtn);
        logout=(ImageView)findViewById(R.id.imageView23);




        if(firebaseUser ==null){
            Toast.makeText(ProfileSetting.this,"Something went wrong.",Toast.LENGTH_LONG).show();
        }
        else{
            showUserProfile(firebaseUser);
        }


//        showUserData();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v);
            }
        });

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ProfileSetting.this, LogOut.class);
                startActivity(intent);
            }
        });
    }
    
//    private void showUserData(){
//        Intent intent = getIntent();
//        name=intent.getStringExtra("name");
//        System.out.println(NAME);
//        BIRTHDATE=intent.getStringExtra("birthDate");
//        PHONENUMBER=intent.getStringExtra("phoneNum");
//        EMAIL=intent.getStringExtra("email");
//
//        ETname.setText(NAME);
//        ETbd.setText(BIRTHDATE);
//        ETphonenum.setText(PHONENUMBER);
//        ETemail.setText(EMAIL);
//
//    }
    public void update (View view){
        if (isNameChanged()||isPasswordChanged()||isBirthdayChanged()||isPhoneNumChanged()||isEmailChanged()){
            Toast.makeText(this,"Data has been updated",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Data unable to update",Toast.LENGTH_LONG).show();
        }
    }

    private boolean isEmailChanged() {
        if(!email.equals(ETemail.getText().toString())){
            ref.child(fb.getCurrentUser().getUid()).child("email").setValue(ETemail.getText().toString());
            email = ETemail.getText().toString();
            firebaseUser.updateEmail(email);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isPhoneNumChanged() {
        if(!phone.equals(ETphonenum.getText().toString())){
            ref.child(fb.getCurrentUser().getUid()).child("phoneNum").setValue(ETphonenum.getText().toString());
            phone = ETphonenum.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isBirthdayChanged() {
        if(!birthdate.equals(ETbd.getText().toString())){
            ref.child(fb.getCurrentUser().getUid()).child("birthDate").setValue(ETbd.getText().toString());
            birthdate = ETbd.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if(!password.equals(ETpwd.getText().toString())){
            ref.child(fb.getCurrentUser().getUid()).child("password").setValue(ETpwd.getText().toString());
            password = ETpwd.getText().toString();
            firebaseUser.updatePassword(password);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!name.equals(ETname.getText().toString())){
            ref.child(fb.getCurrentUser().getUid()).child("name").setValue(ETname.getText().toString());
            name = ETname.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private void showUserProfile(FirebaseUser firebaseUser){
        String userID = firebaseUser.getUid();

        DatabaseReference refProf = FirebaseDatabase.getInstance().getReference("users");
        refProf.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if(readUserDetails!=null){
                    name=readUserDetails.name;
                    email = firebaseUser.getEmail();
                    birthdate = readUserDetails.birthDate;
                    phone = readUserDetails.phoneNum;
                    password=readUserDetails.password;

                    ETname.setText(name);
                    ETemail.setText(email);
                    ETbd.setText(birthdate);
                    ETphonenum.setText(phone);
                    ETpwd.setText(password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
