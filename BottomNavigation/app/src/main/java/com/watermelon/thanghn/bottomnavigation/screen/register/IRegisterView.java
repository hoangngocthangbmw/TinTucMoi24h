package com.watermelon.thanghn.bottomnavigation.screen.register;

/**
 * Created by thang on 3/23/2018.
 */

public interface IRegisterView {
    void onHandelRegisterSucces(String msg);
    void onHandleRegisterFailed(String erroMsg);

    void onHaveUserName(String msg);
    void onNotHaveUserName(String erroMsg);
}
