package com.example.studybuddy_aarzoo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout passwordLayout;
    private TextInputEditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_welcome_screen);

        TextView signupLink = findViewById(R.id.signUpLink);
        String htmlText = "Don't have an account?  <font color='#0000EE'>Sign up here!</font>";
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
