package com.watermelon.thanghn.bottomnavigation.screen.detailnewsonline;

import com.watermelon.thanghn.bottomnavigation.model.detailnewsonline.DetailNewsModel;

/**
 * Created by thang on 2/9/2018.
 */

public class DetailNewsPresenter implements IDetailNewsModel{
    private DetailNewsModel detailNewsModel;
    private IDetailNewsView callback;

    public DetailNewsPresenter(IDetailNewsView callback){
        this.callback=callback;
    }

    public void onHandleGetHtmlDetailNews(String url){
        detailNewsModel=new DetailNewsModel(this);
        detailNewsModel.HandleGetHtmlDetailNews(url);
    }

    @Override
    public void onHandleGetHtmlDetailNewsSuccess(String htmlContext) {
        callback.onHandleGetHtmlDetailNewsSuccess(htmlContext);
    }

    @Override
    public void onHandleGetHtmlDetailNewsFailed(String erroMsg) {
        callback.onHandleGetHtmlDetailNewsFailed(erroMsg);
    }

    public void onHandleSaveNewsInDevice(String linkNew,String titleNew,String pubDateNew,String imgNew,String contentNew){
        detailNewsModel=new DetailNewsModel(this);
        detailNewsModel.HandleSaveNewsInDevice(linkNew,titleNew,pubDateNew,imgNew,contentNew);
    }

    @Override
    public void onHandleSaveNewsInDeviceSuccess(String Msg) {
        callback.onHandleSaveNewsInDeviceSuccess(Msg);
    }

    @Override
    public void onHandleSaveNewsInDeviceFailed(String erroMsg) {
        callback.onHandleSaveNewsInDeviceFailed(erroMsg);
    }

    public void onHandleSaveImageInStorage(String linkImage,String nameImage){
        detailNewsModel=new DetailNewsModel(this);
        detailNewsModel.HandleSaveImageInStorage(linkImage,nameImage);
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

