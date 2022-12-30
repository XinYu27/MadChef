package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText keyEmail = (EditText)findViewById(R.id.emailInput2);
        EditText keyPwd = (EditText)findViewById(R.id.pwdInput3);
        Button signUp = (Button) findViewById(R.id.signUpBtn1);
        Button logIn = (Button)findViewById(R.id.loginBtn2);
        mAuth= FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, SignUp.class));
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= keyEmail.getText().toString().trim();
                String pwd= keyPwd.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    keyEmail.setError("Please enter your email");
                    return;
                }

                if (TextUtils.isEmpty(pwd)){
                    keyPwd.setError("Password is required.");
                    return;
                }

                if(pwd.length()<6){
                    keyPwd.setError("Password must be more than 6 characters.");
                    return;
                }

                //Authenticate User Account

                mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LogIn.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Profile.class));
                        }else{
                            Toast.makeText(LogIn.this,"Unable to log in.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
