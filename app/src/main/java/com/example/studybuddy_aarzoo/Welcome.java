package com.example.studybuddy_aarzoo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {


        private static final int SPLASH_DURATION = 2500; // 3 seconds

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome);

            // Hide the action bar if present
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }

           //Getting all the components from UI and adding animation to it.
            ImageView logoImageView = findViewById(R.id.logo);
            TextView appName = findViewById(R.id.appName);
            TextView subLine = findViewById(R.id.subLine);
            Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            logoImageView.startAnimation(fadeIn);
            appName.startAnimation(fadeIn);
            subLine.startAnimation(fadeIn);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Welcome.this, LoginPage.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_DURATION);

    }
}