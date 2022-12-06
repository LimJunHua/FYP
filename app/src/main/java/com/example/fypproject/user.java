package com.example.fypproject;

public class user {
    public String name, iCNumber, phoneNumber, email,password, uId;


    user(String name,String iCNumber,String phoneNumber,String email,String password, String uId){
        this.name= name;
        this.iCNumber= iCNumber;
        this.phoneNumber= phoneNumber;
        this.email= email;
        this.password = password;
        this.uId = uId;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {this.email = email;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getUid() {return uId;}

    public void setUid(String uId) {this.uId = uId;}
}
