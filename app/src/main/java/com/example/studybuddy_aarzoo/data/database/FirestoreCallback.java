package com.example.studybuddy_aarzoo.data.database;

import com.google.firebase.firestore.DocumentReference;

public interface FirestoreCallback {
    void onSuccess(DocumentReference documentReference);
    void onFailure(Exception e);
}
