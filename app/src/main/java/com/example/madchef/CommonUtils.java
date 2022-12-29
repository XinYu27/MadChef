package com.example.madchef;

import android.app.Application;
import android.content.Context;
import android.util.Patterns;

public class CommonUtils {
    private static Application sApplication;

    public static Application getsApplication(){
        assert sApplication!=null : "Application in Common Utils is null";
        return sApplication;
    }

    public static Context getSContext(){
        return getsApplication().getApplicationContext();
    }

    public static boolean emailValid(String email){
        if(!email.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }
        return false;
    }

    public static boolean pwdValid(String password, String confirmPassword){
        if(password.length()<6 | !password.equals(confirmPassword))
            return false;
        return true;
    }
}
