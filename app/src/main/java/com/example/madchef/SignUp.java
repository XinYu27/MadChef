package com.example.madchef;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private static FirebaseAuth mAuth;
    public static FirebaseDatabase db = FirebaseDatabase.getInstance();
    public static DatabaseReference userRef =db.getReference("users");

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

        mAuth=FirebaseAuth.getInstance();


//        if(mAuth.getCurrentUser()!= null ){
//            startActivity(new Intent(SignUp.this,MainActivity.class));
//            finish();

//        }

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


                if(TextUtils.isEmpty(email)){
                    editEmail.setError("Please enter your email");
                    return;
                }

                if (TextUtils.isEmpty(pwd)){
                    editPassword.setError("Password is required.");
                    return;
                }

                if(pwd.length()<6){
                    editPassword.setError("Password must be more than 6 characters.");
                    return;
                }

                if(!pwd.equals(confirmPwd)){
                    confirmPassword.setError("Confirm password cannot be different.");
                    return;
                }

                if(!emailValidation(email)){
                    editEmail.setError("Email format is incorrect!");
                    return;
                }


                //Register the user

                mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user= new User (email,name,pwd,"none","none","none","none","none","none");
                            userRef.child(mAuth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUp.this,"Account registered successfully. Please Log In Now.",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignUp.this, LogIn.class));


                                    }else{
                                        Toast.makeText(SignUp.this,"Please try again!",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        }else{
                            Toast.makeText(SignUp.this,"Failed to register",Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }

        });

    }

    public static boolean emailValidation(String email){
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }else{
            return false;
        }
    }
}
