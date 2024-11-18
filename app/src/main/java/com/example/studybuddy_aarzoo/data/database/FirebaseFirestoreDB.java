package com.example.studybuddy_aarzoo.data.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.studybuddy_aarzoo.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class FirebaseFirestoreDB {

    private FirestoreCallback callback;

    public FirebaseFirestoreDB(FirestoreCallback callback){
        this.callback = callback;
    }

    public void addNewUser(Map<String, Object> userDetails){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("users")
                .add(userDetails)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.println(Log.WARN, "firestore add", "user added to db successfully");
                        callback.onSuccess(documentReference);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.println(Log.WARN, "firestore add ", "user unable to add to firestore");
                        callback.onFailure(e);
                    }
                });
    }

    public CompletableFuture<Boolean> checkUserAlreadyExists(String email){
        CompletableFuture<Boolean> ans = new CompletableFuture<>();

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference users = firestore.collection("users");

        users.whereEqualTo("userEmail", email)
                .limit(1)
                .get()
                .addOnCompleteListener( task -> {
                    if(task.isSuccessful()){
                        QuerySnapshot querySnapshot = task.getResult();
                        boolean exists = querySnapshot != null && !querySnapshot.isEmpty();
                        ans.complete(exists);
                    }else{
                        ans.completeExceptionally(task.getException());
                    }
                });
        return ans;
    }

}
