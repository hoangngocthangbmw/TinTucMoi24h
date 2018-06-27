package com.watermelon.thanghn.bottomnavigation.screen.newsoffline;

import com.watermelon.thanghn.bottomnavigation.model.app.database.NewsOff;

import java.util.List;

/**
 * Created by thang on 3/1/2018.
 */

public interface IListNewsOfflineModel {
    void onHandleGetNewsInDatabaseSuccess(List<NewsOff> listNews);

    void onHandleGetNewsInDatabaseFailed(String erroMsg);

    void onHandleDeleteNewSuccess(String msg);

    void onHandleDeleteNewsFailed(String erroMsg);
}
