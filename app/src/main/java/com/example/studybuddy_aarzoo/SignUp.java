package com.example.studybuddy_aarzoo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    private TextView loginLink;
    private boolean isPasswordVisible = false; //Initializing to false as initially the password is not visible
    private boolean isConfirmPasswordVisible = false; //Initializing to false as initially the password is not visible

    private TextInputLayout passwordLayout;
    private TextInputEditText passwordEditText;
    private TextInputLayout confirmPasswordLayout;
    private TextInputEditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //TODO: password validation and maybe show a strength meter

        loginLink = findViewById(R.id.loginLink);
        String htmlText = "Already a member?  <font color='#58a6ff'>Login here!</font>";
        loginLink.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));

        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, LoginPage.class);
            startActivity(intent);
        });

        //The below code helps to toggle the password field
        passwordLayout = findViewById(R.id.passwordLayout);
        passwordEditText = findViewById(R.id.passwordEditText);

        confirmPasswordLayout = findViewById(R.id.confirmPasswordLayout);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

        passwordLayout.setEndIconOnClickListener(new View.OnClickListener() {
            final String fieldName = "password";
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(isPasswordVisible, passwordLayout, passwordEditText, fieldName);
            }
        });

        confirmPasswordLayout.setEndIconOnClickListener(new View.OnClickListener() {
            final String fieldName = "confirmPassword";
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(isConfirmPasswordVisible, confirmPasswordLayout, confirmPasswordEditText, fieldName);
            }
        });
    }

    //This function determines where the password is currently showing or not and accordingly toggles it.
    public void togglePasswordVisibility(Boolean isPasswordVisible, TextInputLayout layout, TextInputEditText editText, String fieldName){
        isPasswordVisible = !isPasswordVisible;

        if(fieldName.equals("password")){
            this.isPasswordVisible = !this.isPasswordVisible;
        }else{
            this.isConfirmPasswordVisible = !this.isConfirmPasswordVisible;
        }

        if(isPasswordVisible){ //This means that password is currently visible and we need to hide it
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            layout.setEndIconDrawable(R.drawable.baseline_visibility_off_24);
        }else{ //Password is hidden and we need to show it
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            layout.setEndIconDrawable(R.drawable.baseline_visibility_24);
        }

        editText.setSelection(editText.getText().length());
    }
}