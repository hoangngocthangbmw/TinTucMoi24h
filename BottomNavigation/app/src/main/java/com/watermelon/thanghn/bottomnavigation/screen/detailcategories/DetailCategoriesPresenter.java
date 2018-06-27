package com.watermelon.thanghn.bottomnavigation.screen.detailcategories;

import com.watermelon.thanghn.bottomnavigation.model.detailcategories.DetailCategoriesModel;
import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;

import java.util.List;

/**
 * Created by thang on 3/12/2018.
 */

public class DetailCategoriesPresenter implements IDetailCategoriesModel{

    IDetailCategoriesView callback;
    DetailCategoriesModel detailCategoriesModel;

    public DetailCategoriesPresenter(IDetailCategoriesView callback){
        this.callback=callback;
    }
    public void onHandleGetRssNew(String link){
        detailCategoriesModel=new DetailCategoriesModel(this);
        detailCategoriesModel.HanldeGetRssNew(link);
    }

    @Override
    public void onHandleGetRssNewSuccess(List<News> list) {
        callback.onHandleGetRssNewSuccess(list);
    }

    @Override
    public void onHandleGetRssNewFailed(String erroMsg) {
        callback.onHandleGetRssNewFailed(erroMsg);
    }
}
