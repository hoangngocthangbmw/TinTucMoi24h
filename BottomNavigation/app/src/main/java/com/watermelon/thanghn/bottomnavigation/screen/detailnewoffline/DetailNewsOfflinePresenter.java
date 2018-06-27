package com.watermelon.thanghn.bottomnavigation.screen.detailnewoffline;

import com.watermelon.thanghn.bottomnavigation.model.app.database.NewsOff;
import com.watermelon.thanghn.bottomnavigation.model.detailnewsoffline.DetailNewsOfflineModel;

import java.util.List;

/**
 * Created by thang on 3/1/2018.
 */

public class DetailNewsOfflinePresenter implements IDetailNewsOfflineModel{

    DetailNewsOfflineModel detailNewsOfflineModel;
    IDetailNewsOfflineView callback;

    public DetailNewsOfflinePresenter(IDetailNewsOfflineView callback){
        this.callback=callback;
    }

    public void onHandleGetNewsInDatabase(){
        detailNewsOfflineModel=new DetailNewsOfflineModel(this);
        detailNewsOfflineModel.HandleGetNewsInDatabase();
    }

    @Override
    public void onHandleGetNewsInDatabaseSuccess(List<NewsOff> listNews) {
        callback.onHandleGetNewsInDatabaseSuccess(listNews);
    }

    @Override
    public void onHandleGetNewsInDatabaseFailed(String erroMsg) {
        callback.onHandleGetNewsInDatabaseFailed(erroMsg);
    }
}
