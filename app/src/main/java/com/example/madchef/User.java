package com.example.madchef;



public class User {
    public String name="none";
    public String email="none";
    public String password="none";
    public String birthDate="none";
    public String phoneNum="none";

    public String diet="none";
    public String allergies="none";
    public String cuisine="none";
    public String food="none";


    User(String email, String name, String password, String birthDate, String phoneNum, String diet, String allergies, String cuisine, String food){
        super();
        this.email=email;
        this.name=name;
        this.password=password;
        this.birthDate=birthDate;
        this.phoneNum=phoneNum;
        this.diet=diet;
        this.allergies=allergies;
        this.cuisine=cuisine;
        this.food=food;

    }

    User(String email, String password){

    }

}
