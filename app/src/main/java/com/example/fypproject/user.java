package com.example.fypproject;

public class user {
    public String name, icNumber, phoneNumber, email,password;


    user(String name,String icNumber,String phoneNumber,String email,String password){
        this.name= name;
        this.icNumber= icNumber;
        this.phoneNumber= phoneNumber;
        this.email= email;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {this.email = email;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
