package com.example.studybuddy_aarzoo;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class ForgotPassword extends AppCompatActivity {

    TextView emailConfirmationInfo;
    Button sendEmail;
    TextInputEditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailConfirmationInfo = findViewById(R.id.emailConfirmation);
        emailConfirmationInfo.setVisibility(View.INVISIBLE);
        sendEmail = findViewById(R.id.sendResetPasswordEmail);
        email = findViewById(R.id.emailEditText);

        sendEmail.setOnClickListener(v -> {
            String emailInfo = email.getText().toString();
            String htmlText;

            if(emailInfo.isEmpty() || emailInfo.isBlank()){
                htmlText = "<font color='#CF6679'>Email can not be empty!</font>";

            }else{
                htmlText = "<font color='#FFFFFF'>We've processed your request. If the email address is linked to an account, you'll receive reset instructions soon.";
            }

            emailConfirmationInfo.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));
            emailConfirmationInfo.setVisibility(View.VISIBLE);

        });
    }
}