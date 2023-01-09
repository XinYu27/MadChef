package com.example.madchef;



public class User {
    public String name="none";
    public String email="none";
    public String password="none";
    public String birthDate="none";
    public String phoneNum="none";


    User(String email, String name, String password, String birthDate, String phoneNum){
        super();
        this.email=email;
        this.name=name;
        this.password=password;
        this.birthDate=birthDate;
        this.phoneNum=phoneNum;

    }

    User(String email, String password){

    }

}
