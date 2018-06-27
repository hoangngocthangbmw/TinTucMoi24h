package com.watermelon.thanghn.bottomnavigation.model.detailnewsoffline;

import com.watermelon.thanghn.bottomnavigation.App;
import com.watermelon.thanghn.bottomnavigation.model.app.database.DBNews;
import com.watermelon.thanghn.bottomnavigation.model.app.database.NewsOff;
import com.watermelon.thanghn.bottomnavigation.screen.detailnewoffline.IDetailNewsOfflineModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thang on 3/3/2018.
 */

public class DetailNewsOfflineModel {
    private DBNews dbNews;
    private List<NewsOff> listNews;
    IDetailNewsOfflineModel callback;

    public DetailNewsOfflineModel(IDetailNewsOfflineModel callback){
        this.callback=callback;
    }

    public void HandleGetNewsInDatabase(){
        try {
            dbNews = new DBNews(App.getAppContext());
            listNews = new ArrayList<NewsOff>();
            listNews = dbNews.getAllNewPaper();
            callback.onHandleGetNewsInDatabaseSuccess(listNews);
        } catch (Exception ex) {
            callback.onHandleGetNewsInDatabaseFailed(String.valueOf(ex));
        }
    }
}
