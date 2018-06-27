package com.watermelon.thanghn.bottomnavigation.screen.newsoffline;

import com.watermelon.thanghn.bottomnavigation.model.app.database.NewsOff;
import com.watermelon.thanghn.bottomnavigation.model.newoffline.ListNewsOfflineModel;

import java.util.List;

/**
 * Created by thang on 2/27/2018.
 */

public class ListNewsOfflinePresenter implements IListNewsOfflineModel {
    private ListNewsOfflineModel listNewsOfflineModel;
    private IListNewsOfflineView callback;

    public ListNewsOfflinePresenter(IListNewsOfflineView callback) {
        this.callback = callback;
    }

    public void onHandleGetNewsInDatabase() {
        listNewsOfflineModel = new ListNewsOfflineModel(this);
        listNewsOfflineModel.HandleGetNewsInDatabase();
    }

    @Override
    public void onHandleGetNewsInDatabaseSuccess(List<NewsOff> listNews) {
        callback.onHandleGetNewsInDatabaseSuccess(listNews);
    }

    @Override
    public void onHandleGetNewsInDatabaseFailed(String erroMsg) {
        callback.onHandleGetNewsInDatabaseFailed(erroMsg);
    }

    public void onHandleDeleteNew(NewsOff newsOff) {
        listNewsOfflineModel = new ListNewsOfflineModel(this);
        listNewsOfflineModel.HandleDeleteNew(newsOff);
    }

    @Override
    public void onHandleDeleteNewSuccess(String msg) {
        callback.onHandleDeleteNewSuccess(msg);
    }

    @Override
    public void onHandleDeleteNewsFailed(String erroMsg) {
        callback.onHandleDeleteNewsFailed(erroMsg);
    }

}
