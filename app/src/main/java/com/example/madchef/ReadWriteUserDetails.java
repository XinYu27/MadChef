package com.example.madchef;

public class ReadWriteUserDetails {
    public String birthDate, phoneNum, name,password,diet,allergies,cuisine,food;

    public ReadWriteUserDetails() {
        super();
    }

    public ReadWriteUserDetails(String name,String birthDate, String phoneNum, String password,String diet, String allergies, String cuisine, String dish){
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNum = phoneNum;
        this.password = password;
        this.diet=diet;
        this.allergies=allergies;
        this.cuisine=cuisine;
        this.food=dish;
    }
}
