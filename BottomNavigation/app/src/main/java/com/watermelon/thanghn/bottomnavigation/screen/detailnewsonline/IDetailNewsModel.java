package com.watermelon.thanghn.bottomnavigation.screen.detailnewsonline;

/**
 * Created by thang on 2/20/2018.
 */

public interface IDetailNewsModel {
    void onHandleGetHtmlDetailNewsSuccess(String htmlContext);
    void onHandleGetHtmlDetailNewsFailed(String erroMsg);

    void onHandleSaveNewsInDeviceSuccess(String Msg);
    void onHandleSaveNewsInDeviceFailed(String erroMsg);

    void onHandleSaveImageInStorageSuccess(String Msg);
    void onHandleSaveImageInStorageFailed(String erroMsg);
}
