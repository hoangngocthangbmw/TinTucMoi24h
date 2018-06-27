package com.watermelon.thanghn.bottomnavigation.screen.detailcategoriesnews;

/**
 * Created by thang on 3/13/2018.
 */

public interface IDetailCategoriesNewsView {
    void onHandleGetHtmlByLinkSuccess(String content);

    void onHandleGetHtmlByLinkFailed(String erroMsg);

    void onHandleSaveNewsInDeviceSuccess(String Msg);

    void onHandleSaveNewsInDeviceFailed(String erroMsg);

    void onHandleSaveImageInStorageSuccess(String Msg);

    void onHandleSaveImageInStorageFailed(String erroMsg);
}
