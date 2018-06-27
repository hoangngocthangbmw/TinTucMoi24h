package com.watermelon.thanghn.bottomnavigation.screen.detailnewoffline;

import com.watermelon.thanghn.bottomnavigation.model.app.database.NewsOff;

import java.util.List;

/**
 * Created by thang on 3/3/2018.
 */

public interface IDetailNewsOfflineView {
    void onHandleGetNewsInDatabaseSuccess(List<NewsOff> listNews);
    void onHandleGetNewsInDatabaseFailed(String erroMsg);
}
