package com.example.studybuddy_aarzoo.data.model;

import java.util.HashMap;
import java.util.Map;

public class User {

    private Map<String, Object> userDetails ;

    public User(){
        userDetails = new HashMap<>();
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
