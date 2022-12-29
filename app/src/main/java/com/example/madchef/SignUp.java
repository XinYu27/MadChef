package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText editEmail =(EditText) findViewById(R.id.emailInput);
        EditText editName = (EditText) findViewById(R.id.nameInput);
        EditText editPassword = (EditText) findViewById(R.id.pwdInput);
        EditText confirmPassword = (EditText) findViewById(R.id.pwdInput2);
        Button signUp = (Button) findViewById(R.id.signUpBtn);
        Button logIn = (Button) findViewById(R.id.loginBtn);

        logIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(SignUp.this,LogIn.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String email= editEmail.getText().toString().trim();
                String name= editName.getText().toString();
                String pwd= editPassword.getText().toString().trim();
                String confirmPwd= confirmPassword.getText().toString().trim();
                String errorMessage="";

                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        FirebaseUtils.registerUser(email, name, pwd, "none", "none");
                    }
                };

                if(!CommonUtils.emailValid(email))
                    errorMessage="The email address is invalid.";

                if (errorMessage.isEmpty()){
                    if(!CommonUtils.pwdValid(pwd,confirmPwd))
                        errorMessage="Please check your password again.";
                }
                if (errorMessage.isEmpty()){
                    thread.start();
                }

            }
        });

    }
}
