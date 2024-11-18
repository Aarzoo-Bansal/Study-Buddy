package com.example.studybuddy_aarzoo.UIHelper;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.studybuddy_aarzoo.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.collection.LLRBNode;

public class SnackbarUI {

    public Snackbar createErrorSnackbar(String message, View v, Context context){
        Snackbar snackbar = Snackbar.make(v, message, Snackbar.LENGTH_LONG);
        snackbar.setTextColor(Color.rgb(139, 0, 0));
        snackbar.setBackgroundTint(ContextCompat.getColor(context, androidx.cardview.R.color.cardview_light_background));
        return snackbar;
    }

    public Snackbar createSuccessSnackbar(String message, View v, Context context){
        Snackbar snackbar = Snackbar.make(v, message, Snackbar.LENGTH_LONG);
        snackbar.setTextColor(Color.rgb(0, 100, 0));
        snackbar.setBackgroundTint(ContextCompat.getColor(context, androidx.cardview.R.color.cardview_light_background));
        return snackbar;
    }
}
