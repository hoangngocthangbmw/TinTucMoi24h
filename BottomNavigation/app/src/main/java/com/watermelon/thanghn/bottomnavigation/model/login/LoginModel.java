package com.watermelon.thanghn.bottomnavigation.model.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.watermelon.thanghn.bottomnavigation.App;
import com.watermelon.thanghn.bottomnavigation.screen.login.ILoginModel;

/**
 * Created by thang on 3/16/2018.
 */

public class LoginModel {
    private FirebaseAuth mAuth;
    private ILoginModel callback;

    public LoginModel(ILoginModel callback) {
        this.callback = callback;
    }

    public void HandleLogin(Activity activity, String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callback.onLoginSuccess("Đăng nhập thành công");
                        } else {
                            callback.onLoginFailed("Đăng nhập thất bại");
                        }
                    }
                });
    }
}
