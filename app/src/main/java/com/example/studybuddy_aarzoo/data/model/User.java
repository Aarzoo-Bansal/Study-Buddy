package com.example.studybuddy_aarzoo.data.model;

import java.util.HashMap;
import java.util.Map;

public class User {

    private Map<String, Object> userDetails ;
    private String userEmail;
    private String password;
    private String userName;

    public User(){
        userDetails = new HashMap<>();
    }

    public User(String email, String password, String userName){
        this.userEmail = email;
        this.password = password;
        this.userName = userName;
    }

    public String getUserEmail(){
        return this.userEmail;
    }

    public String getUserPassword(){
        return this.password;
    }

    public String getUserName(){
        return this.userName;
    }


    public Map<String, Object> createNewUserMap(String userName, String userEmail, String userPassword){
        this.userDetails.put("userName", userName);
        this.userDetails.put("userEmail", userEmail);
        this.userDetails.put("password", userPassword);
        this.userDetails.put("isEmailVerified", false);
        this.userDetails.put("isPasswordResetRequired", false);
        return this.userDetails;
    }

}
