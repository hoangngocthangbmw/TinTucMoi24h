package com.watermelon.thanghn.bottomnavigation.screen.detailcategories;

import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;

import java.util.List;

/**
 * Created by thang on 3/12/2018.
 */

public interface IDetailCategoriesView {
    void onHandleGetRssNewSuccess(List<News> list);
    void onHandleGetRssNewFailed(String erroMsg);
}
