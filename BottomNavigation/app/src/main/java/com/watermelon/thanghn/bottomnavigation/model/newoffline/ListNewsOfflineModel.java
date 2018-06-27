package com.watermelon.thanghn.bottomnavigation.model.newoffline;

import android.util.Log;

import com.watermelon.thanghn.bottomnavigation.App;
import com.watermelon.thanghn.bottomnavigation.model.app.database.DBNews;
import com.watermelon.thanghn.bottomnavigation.model.app.database.NewsOff;
import com.watermelon.thanghn.bottomnavigation.screen.detailnewsonline.IDetailNewsModel;
import com.watermelon.thanghn.bottomnavigation.screen.newsoffline.IListNewsOfflineModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thang on 2/27/2018.
 */

public class ListNewsOfflineModel {
    private DBNews dbNews;
    private List<NewsOff> listNews;
    IListNewsOfflineModel callback;

    public ListNewsOfflineModel(IListNewsOfflineModel callback) {
        this.callback = callback;
    }

    public void HandleGetNewsInDatabase() {
        try {
            dbNews = new DBNews(App.getAppContext());
            listNews = new ArrayList<NewsOff>();
            listNews = dbNews.getAllNewPaper();
            for (int i=0;i<listNews.size();i++){
                Log.d("testcc",listNews.get(i).getDate());
            }
            callback.onHandleGetNewsInDatabaseSuccess(listNews);
        } catch (Exception ex) {
            callback.onHandleGetNewsInDatabaseFailed(String.valueOf(ex));
        }

    }

    public void HandleDeleteNew(NewsOff newsOff){
        try {
            dbNews=new DBNews(App.getAppContext());
            dbNews.delete(newsOff);
            callback.onHandleDeleteNewSuccess("Đã xóa tin");
        }catch (Exception ex){
            callback.onHandleDeleteNewsFailed(String.valueOf(ex));
        }
    }
}
