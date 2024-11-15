package com.example.studybuddy_aarzoo;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

public class LoginPage extends AppCompatActivity {

    private TextInputLayout passwordLayout;
    private TextInputEditText passwordEditText;
    private TextView signUpLink;
    private TextView forgetPasswordLink;
    private boolean passwordVisible = false; //initially the password is not visible

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);

        signUpLink = findViewById(R.id.signUpLink);
        String htmlText = "Don't have an account?  <font color='#58a6ff'>Sign up here!</font>";
        signUpLink.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));

        //If the user clicks on sign-up, we start this activity
        signUpLink = findViewById(R.id.signUpLink);
        signUpLink.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });

        //Directing user to Forgot Screen when user forgets their password
        forgetPasswordLink = findViewById(R.id.forgotPasswordLink);
        forgetPasswordLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginPage.this, ForgotPassword.class);
            startActivity(intent);
        });



        passwordLayout = findViewById(R.id.confirmPasswordLayout);
        passwordEditText = findViewById(R.id.confirmPasswordEditText);

        // Ensure the end icon mode is set to password toggle
        passwordLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });
    }

    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;
        if (passwordVisible) {
            // Show password
            //The function HideReturnsTransformationMethod - shows password in a readable plain format
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordLayout.setEndIconDrawable(R.drawable.baseline_visibility_off_24);
        } else {
            // Hide password
            passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordLayout.setEndIconDrawable(R.drawable.baseline_visibility_24);
        }
        // Move cursor to the end of the text
        passwordEditText.setSelection(passwordEditText.getText().length());
    }

    }













