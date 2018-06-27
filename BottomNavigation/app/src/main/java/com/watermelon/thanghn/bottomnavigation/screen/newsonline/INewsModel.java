package com.watermelon.thanghn.bottomnavigation.screen.newsonline;

import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;

import java.util.List;

/**
 * Created by thang on 2/9/2018.
 */

public interface INewsModel {
    void onGetApiRssSuccess(List<News> listNews);

    void onGetApiRssFailed(String erroMsg);
}
