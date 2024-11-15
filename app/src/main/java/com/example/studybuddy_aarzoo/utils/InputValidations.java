package com.example.studybuddy_aarzoo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidations {

    public boolean validateEmailAddress(String email){

        if(email.isEmpty() || email.isBlank()){
            return false;
        }

        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
}
