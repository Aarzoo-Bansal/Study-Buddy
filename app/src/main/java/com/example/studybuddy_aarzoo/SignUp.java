package com.example.studybuddy_aarzoo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.studybuddy_aarzoo.data.database.FirebaseFirestoreDB;
import com.example.studybuddy_aarzoo.data.database.FirestoreCallback;
import com.example.studybuddy_aarzoo.data.model.User;
import com.example.studybuddy_aarzoo.utils.InputValidations;
import com.example.studybuddy_aarzoo.UIHelper.SnackbarUI;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;

import java.util.Map;

public class SignUp extends AppCompatActivity {

    private TextView loginLink;
    private boolean isPasswordVisible = false; //Initializing to false as initially the password is not visible
    private boolean isConfirmPasswordVisible = false; //Initializing to false as initially the password is not visible
    private Button signUpButton;
    private Context context;
    private SnackbarUI snackbar;

    private Dialog loadingDialog;

    private TextInputEditText userName;
    private TextInputEditText userEmail;
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

        context = this;
        snackbar = new SnackbarUI();

        signUpButton = findViewById(R.id.signUpbutton);
        userName = findViewById(R.id.userNameEditText);
        userEmail = findViewById(R.id.emailEditText);

        loginLink = findViewById(R.id.loginLink);
        String htmlText = "Already a member?  <font color='#58a6ff'>Login here!</font>";
        loginLink.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));

        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, LoginPage.class);
            startActivity(intent);
            finish();
        });

        //The below code helps to toggle the password field
        passwordLayout = findViewById(R.id.passwordLayout);
        passwordEditText = findViewById(R.id.passwordEditText);

        confirmPasswordLayout = findViewById(R.id.confirmPasswordLayout);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

        passwordLayout.setEndIconOnClickListener(new View.OnClickListener() {
            final String fieldName = getString(R.string.password);
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(isPasswordVisible, passwordLayout, passwordEditText, fieldName);
            }
        });

        confirmPasswordLayout.setEndIconOnClickListener(new View.OnClickListener() {
            final String fieldName = getString(R.string.confirm_password);
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(isConfirmPasswordVisible, confirmPasswordLayout, confirmPasswordEditText, fieldName);
            }
        });

        //Adding on-click listener to the sign-up button
        signUpButton.setOnClickListener(this::submitSignUpRequest);
    }

    //This function determines where the password is currently showing or not and accordingly toggles it.
    public void togglePasswordVisibility(Boolean isPasswordVisible, TextInputLayout layout, TextInputEditText editText, String fieldName){
        isPasswordVisible = !isPasswordVisible;

        if(fieldName.equals(getString(R.string.password))){
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

    //This function is called when the user clicks on the Sign-Up button
    public void submitSignUpRequest(View v){
        String userName = this.userName.getText().toString().trim();
        String userEmail = this.userEmail.getText().toString().trim();
        String password = this.passwordEditText.getText().toString().trim();
        String confirmPassword = this.confirmPasswordEditText.getText().toString().trim();

        InputValidations check = new InputValidations();


//        if(userName.isEmpty() || userName.isBlank()){
//            String message = getString(R.string.empty_name);
//            snackbar.createErrorSnackbar(message, v, context).show();
//            return;
//        }
//
//        if(userEmail.isEmpty() || userEmail.isBlank()) {
//            if (userEmail.isEmpty() || userEmail.isBlank()) {
//                String message = getString(R.string.empty_email);
//                snackbar.createErrorSnackbar(message, v, context).show();
//                return;
//            }
//        }
//
//            boolean isEmailOk = new InputValidations().validateEmailAddress(userEmail);
//            if(!isEmailOk){
//                String message = getString(R.string.invalid_email);
//                snackbar.createErrorSnackbar(message, v, context).show();
//                return;
//            }
//
//
//        if(password.isEmpty() || password.isBlank() || confirmPassword.isEmpty() || confirmPassword.isBlank()){
//            String message = getString(R.string.empty_password);
//            snackbar.createErrorSnackbar(message, v, context);
//            return;
//        }
//
//        if(!password.equals(confirmPassword)){
//            String message = getString(R.string.password_no_match);
//            snackbar.createErrorSnackbar(message, v, context);
//            return;
//        }

        if(check.performValidationOnInput(userName, userEmail, password, confirmPassword, context, v)){

            //If all validations are passed - we will create a user record in the database

            //Creating an instance of FirebaseFirestoreDB class
            FirebaseFirestoreDB db = new FirebaseFirestoreDB(new FirestoreCallback() {

                @Override
                public void onSuccess(DocumentReference documentReference) {
                    hideLoadingDialog();
                    String message = getString(R.string.added_successfully);
                    snackbar.createSuccessSnackbar(message, v, context).show();

                    //If the user is added successfully, we redirect it to the login page
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SignUp.this, LoginPage.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 4000);
                }

                @Override
                public void onFailure(Exception e) { //This occurs when we are unable to connect to the firestore due to some issue
                    hideLoadingDialog();
                    String message = getString(R.string.not_added_successfully);
                    snackbar.createErrorSnackbar(message, v, context);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SignUp.this, LoginPage.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 3500);

                }
            });

            //check if the user already exists
            db.checkUserAlreadyExists(userEmail)
                            .thenAccept(exists -> {
                                if (exists) {
                                    String message = getString(R.string.existing_member);
                                    snackbar.createErrorSnackbar(message, v, context).show();
                                    return;
                                } else {
                                    User user = new User();
                                    Map<String, Object> userDetails = user.createNewUserMap(userName, userEmail, password);
                                    showLoadingDialog();
                                    signUpButton.setEnabled(false);
                                    db.addNewUser(userDetails);
                                }
                            })
                    .exceptionally( e -> {
                        return null;
                    });
        }else{
            return;
        }
    }



    //This function is used to show the
    private void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new Dialog(this);
            loadingDialog.setContentView(R.layout.activity_loading_icon);
            loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            loadingDialog.setCancelable(false);
        }
        loadingDialog.show();
    }

    private void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


}