package com.example.foodplanner.Register.Presenter;

import android.content.Context;

public interface RegisterPresenter {
    void createAccount(String email, String password, String confirmPassword, Context context);

}
