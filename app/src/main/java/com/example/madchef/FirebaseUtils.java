package com.example.madchef;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FirebaseUtils {
    private static FirebaseAuth mAuth =FirebaseAuth.getInstance();
    public static FirebaseDatabase db =FirebaseDatabase.getInstance();
    public static DatabaseReference userRef = db.getReference("users");
    private static String TAG="firebase utils";

    public static DatabaseReference getUserRef(){
        return userRef;
    }

    public static FirebaseAuth getmAuth(){
        return mAuth;
    }

    public static void registerUser (String email, String name, String password, String birthDate, String phoneNum, String diet, String allergies, String cuisine, String food){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user=new User(email, name, password, birthDate, phoneNum,diet,allergies,cuisine,food);
                            userRef
                                    .child(mAuth.getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(CommonUtils.getSContext(),"User has been registered successfully!", Toast.LENGTH_SHORT).show();
                                                CommonUtils.getSContext().startActivity(new Intent(CommonUtils.getsApplication(),LogIn.class));
                                                Log.i(TAG,"Successfully registered user: "+mAuth.getCurrentUser().getUid());
                                            }else{
                                                Toast.makeText(CommonUtils.getSContext(),"Failed to register, try again!",Toast.LENGTH_SHORT).show();
                                                Log.e(TAG,"Failed to register user: "+mAuth.getCurrentUser().getUid());
                                            }
                                        }
                                    });

                        }else{
                            Toast.makeText(CommonUtils.getSContext(),"Failed to register, try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void addPreference(String diet, String allergies, String cuisine, String dish){
        Map<String,Object> values = new HashMap<>();
        values.put("diet",diet);
        values.put("allergies",allergies);
        values.put("cuisine",cuisine);
        values.put("food",dish);

        userRef.child(mAuth.getCurrentUser().getUid()).updateChildren(values).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d("Firebase Utils","Successfully add preferences");
                }
                else{
                    Log.d("Firebase Utils","Unable to add preferences");
                }
            }
        });
    }

    public static void addDiet(String diet){
        if(!diet.isEmpty()){
            userRef.child(mAuth.getCurrentUser().getUid()).child("diet").setValue(diet);
        }
    }

    public static void addAllergies(String allergies){
        if(!allergies.isEmpty()){
            userRef.child(mAuth.getCurrentUser().getUid()).child("allergies").setValue(allergies);
        }
    }

    public static void addCuisine(String cuisine){
        if(!cuisine.isEmpty()){
            userRef.child(mAuth.getCurrentUser().getUid()).child("cuisine").setValue(cuisine);
        }
    }

    public static void addDish(String dish){
        if(!(dish ==null)){
            userRef.child(mAuth.getCurrentUser().getUid()).child("food").setValue(dish);
        }
    }


}
