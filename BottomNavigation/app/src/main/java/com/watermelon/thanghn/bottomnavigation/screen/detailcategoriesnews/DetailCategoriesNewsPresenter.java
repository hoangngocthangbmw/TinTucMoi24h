package com.watermelon.thanghn.bottomnavigation.screen.detailcategoriesnews;

import com.watermelon.thanghn.bottomnavigation.model.detailcategoriesnews.DetailCategoriesNewsModel;

/**
 * Created by thang on 3/13/2018.
 */

public class DetailCategoriesNewsPresenter implements IDetailCategoriesNewsModel {

    private DetailCategoriesNewsModel detailCategoriesNewsModel;
    private IDetailCategoriesNewsView callback;

    public DetailCategoriesNewsPresenter(IDetailCategoriesNewsView callback) {
        this.callback = callback;
    }

    public void onHandleGetHtmlByLink(String link) {
        detailCategoriesNewsModel = new DetailCategoriesNewsModel(this);
        detailCategoriesNewsModel.HandleGetHtmlByLink(link);

    }

    @Override
    public void onHandleGetHtmlByLinkSuccess(String content) {
        callback.onHandleGetHtmlByLinkSuccess(content);
    }

    @Override
    public void onHandleGetHtmlByLinkFailed(String erroMsg) {
        callback.onHandleGetHtmlByLinkFailed(erroMsg);
    }

    public void onHandleSaveNewsInDevice(String linkNew, String titleNew, String pubDateNew, String imgNew, String contentNew) {
        detailCategoriesNewsModel = new DetailCategoriesNewsModel(this);
        detailCategoriesNewsModel.HandleSaveNewsInDevice(linkNew, titleNew, pubDateNew, imgNew, contentNew);
    }

    @Override
    public void onHandleSaveNewsInDeviceSuccess(String Msg) {
        callback.onHandleSaveNewsInDeviceSuccess(Msg);
    }

    @Override
    public void onHandleSaveNewsInDeviceFailed(String erroMsg) {
        callback.onHandleSaveNewsInDeviceFailed(erroMsg);
    }

    public void onHandleSaveImageInStorage(String linkImage, String imageName) {
        detailCategoriesNewsModel = new DetailCategoriesNewsModel(this);
        detailCategoriesNewsModel.HandleSaveImageInStorage(linkImage, imageName);
    }

    @Override
    public void onHandleSaveImageInStorageSuccess(String Msg) {
        callback.onHandleSaveImageInStorageSuccess(Msg);
    }

    @Override
    public void onHandleSaveImageInStorageFailed(String erroMsg) {
        callback.onHandleSaveImageInStorageFailed(erroMsg);
    }


}
