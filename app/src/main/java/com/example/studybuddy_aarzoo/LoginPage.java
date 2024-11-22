package com.example.studybuddy_aarzoo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studybuddy_aarzoo.UIHelper.SnackbarUI;
import com.example.studybuddy_aarzoo.data.database.FirebaseFirestoreDB;
import com.example.studybuddy_aarzoo.data.database.FirestoreCallback;
import com.example.studybuddy_aarzoo.data.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    private TextInputLayout passwordLayout;
    private TextInputEditText passwordEditText;
    private TextView signUpLink;
    private TextView forgetPasswordLink;
    private boolean passwordVisible = false; //initially the password is not visible
    private Button loginButton;
    private TextInputEditText email;
    private SnackbarUI snackbar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);
        context = this;
        snackbar = new SnackbarUI();

        loginButton = findViewById(R.id.loginButton);

        signUpLink = findViewById(R.id.signUpLink);
        String htmlText = "Don't have an account?  <font color='#58a6ff'>Sign up here!</font>";
        signUpLink.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));

        //If the user clicks on sign-up, we start this activity
        signUpLink = findViewById(R.id.signUpLink);
        signUpLink.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
            finish();
        });

        email = findViewById(R.id.emailEditText);

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

        //setting the on click listener for the login button
        loginButton.setOnClickListener(this::checkCredentials);
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

    public void checkCredentials(View v) {
        FirebaseFirestoreDB db = new FirebaseFirestoreDB();
        String userEmail = email.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        Log.println(Log.WARN, "user data", password);
        db.getUserCredentials(userEmail)
                .thenAccept(users -> {
                    Log.println(Log.WARN, "user data", users.getUserEmail() + users.getUserPassword() + users.getUserName());
                    if (users.getUserEmail().isEmpty() || users.getUserEmail().isBlank()) { //This means that the user doesn't not exists.
                        String message = getString(R.string.user_not_present);
                        snackbar.createErrorSnackbar(message, v, context).show();
                    } else if (!users.getUserPassword().equals(password)) {
                        //Password is incorrect
                        String message = getString(R.string.password_doesnot_match);
                        snackbar.createErrorSnackbar(message, v, context).show();
                    } else {
                        //TODO: Start a new intent activity!!
                    }
                })
                .exceptionally(e -> {
                    return null;
                });


    }

}













