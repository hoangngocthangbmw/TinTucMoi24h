package com.watermelon.thanghn.bottomnavigation.screen.register;

import android.app.Activity;

import com.watermelon.thanghn.bottomnavigation.model.register.RegisterModel;
import com.watermelon.thanghn.bottomnavigation.screen.login.ILoginModel;
import com.watermelon.thanghn.bottomnavigation.screen.login.ILoginView;

/**
 * Created by thang on 3/23/2018.
 */

public class RegisterPresenter implements IRegisterModel {
    private RegisterModel registerModel;
    private IRegisterView callback;

    public RegisterPresenter(IRegisterView callback) {
        this.callback = callback;
    }

    public void onHandleRegister(Activity activity, String username, String email, String password, String password2) {
        registerModel = new RegisterModel(this);
        registerModel.HandleRegister(activity, username, email, password, password2);
    }

    @Override
    public void onHandelRegisterSucces(String msg) {
        callback.onHandelRegisterSucces(msg);
    }

    @Override
    public void onHandleRegisterFailed(String erroMsg) {
        callback.onHandleRegisterFailed(erroMsg);
    }


    public void onHandleCheckUserName(Activity activity, String username, String email, String password, String password2) {
        registerModel = new RegisterModel(this);
        registerModel.HandleCheckUserName(activity, username, email, password, password2);
    }

    @Override
    public void onHaveUserName(String msg) {
        callback.onHaveUserName(msg);
    }

    @Override
    public void onNotHaveUserName(String erroMsg) {
        callback.onNotHaveUserName(erroMsg);
    }

}
