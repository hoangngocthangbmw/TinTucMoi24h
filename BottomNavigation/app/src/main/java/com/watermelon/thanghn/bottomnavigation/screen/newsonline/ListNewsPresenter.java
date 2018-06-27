package com.watermelon.thanghn.bottomnavigation.screen.newsonline;

import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;
import com.watermelon.thanghn.bottomnavigation.model.newsonline.NewsModel;

import java.util.List;

/**
 * Created by thang on 2/9/2018.
 */

public class ListNewsPresenter implements INewsModel {
    private NewsModel newsModel;
    private INewsView callback;

    public ListNewsPresenter(INewsView callback){
        this.callback=callback;
    }

    public void onHandleGetRssApi(int position){
        newsModel=new NewsModel(this);
        newsModel.HandleGetRssApi(position);
    }

    @Override
    public void onGetApiRssSuccess(List<News> listNews) {
        callback.onGetApiRssSuccess(listNews);
    }

    @Override
    public void onGetApiRssFailed(String erroMsg) {
        callback.onGetApiRssFailed(erroMsg);
    }
}
