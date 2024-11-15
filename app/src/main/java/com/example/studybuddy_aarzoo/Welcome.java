package com.example.studybuddy_aarzoo;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Welcome extends AppCompatActivity {

    private TextInputLayout passwordLayout;
    private TextInputEditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);

        TextView signupLink = findViewById(R.id.signUpLink);
        String htmlText = "Don't have an account?  <font color='#58a6ff'>Sign up here!</font>";
        signupLink.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));
        signupLink.setOnClickListener(v -> {
            Log.println(Log.WARN, "Signup link works", "hello from signup link!");
        });


        passwordLayout = findViewById(R.id.passwordLayout);
        passwordEditText = findViewById(R.id.passwordEditText);

        // Ensure the end icon mode is set to password toggle
        passwordLayout.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);


    }












}
