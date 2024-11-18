package com.example.studybuddy_aarzoo.utils;

import android.content.Context;
import android.view.View;

import com.example.studybuddy_aarzoo.R;
import com.example.studybuddy_aarzoo.UIHelper.SnackbarUI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidations {

    public boolean validateEmailAddress(String email){
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public boolean performValidationOnInput(String userName, String userEmail, String password, String confirmPassword, Context context, View v){
        SnackbarUI snackbar = new SnackbarUI();

        if(userName.isEmpty() || userName.isBlank()){
            String message = context.getString(R.string.empty_name);
            snackbar.createErrorSnackbar(message, v, context).show();
            return false;
        }

        if(userEmail.isEmpty() || userEmail.isBlank()) {
            if (userEmail.isEmpty() || userEmail.isBlank()) {
                String message = context.getString(R.string.empty_email);
                snackbar.createErrorSnackbar(message, v, context).show();
                return false;
            }
        }

        boolean isEmailOk = new InputValidations().validateEmailAddress(userEmail);
        if(!isEmailOk){
            String message =context.getString(R.string.invalid_email);
            snackbar.createErrorSnackbar(message, v, context).show();
            return false;
        }


        if(password.isEmpty() || password.isBlank() || confirmPassword.isEmpty() || confirmPassword.isBlank()){
            String message = context.getString(R.string.empty_password);
            snackbar.createErrorSnackbar(message, v, context).show();
            return false;
        }

        if(!password.equals(confirmPassword)){
            String message = context.getString(R.string.password_no_match);
            snackbar.createErrorSnackbar(message, v, context).show();
            return false;
        }
        return true;
    }


}
