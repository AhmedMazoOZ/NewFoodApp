package com.example.foodplanner.Register.Model;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.foodplanner.HomeScreen.View.View.Home_Activity;
import com.example.foodplanner.Register.View.RegisterScreen;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterModel {
    FirebaseAuth firebaseAuth;

    public RegisterModel() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

   public void createAccountInFirebase(String email, String password, String confirmPassword, Context context) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                       // listener.onSuccess();
//                        Toast.makeText(RegisterScreen.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                       context.startActivity(new Intent(context, Home_Activity.class));
//
//                        finish();
                    } else {
                        //Toast.makeText(RegisterScreen.this, "Failed to create account: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
    public interface RegisterListener {
        void onSuccess();
        void onFailure(String errorMessage);
    }

}
