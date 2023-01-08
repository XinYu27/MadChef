package com.example.madchef;

public class ReadWriteUserDetails {
    public String birthDate, phoneNum, name,password;

    public ReadWriteUserDetails() {
        super();
    }

    public ReadWriteUserDetails(String name,String birthDate, String phoneNum, String password){
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNum = phoneNum;
        this.password = password;
    }
}
