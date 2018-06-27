package com.watermelon.thanghn.bottomnavigation.screen.login;

import android.app.Activity;
import android.content.Context;

import com.watermelon.thanghn.bottomnavigation.model.login.LoginModel;

/**
 * Created by thang on 3/16/2018.
 */

public class LoginPresenter implements ILoginModel {
    private LoginModel loginModel;
    private ILoginView callback;

    public LoginPresenter(ILoginView callback) {
        this.callback = callback;
    }

    public void onHandleLogin(Activity activity, String email, String password) {
        loginModel = new LoginModel(this);
        loginModel.HandleLogin(activity, email, password);
    }

    @Override
    public void onLoginSuccess(String msg) {
        callback.onLoginSuccess(msg);
    }

    @Override
    public void onLoginFailed(String erroMsg) {
        callback.onLoginFailed(erroMsg);
    }
}
