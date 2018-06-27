package com.watermelon.thanghn.bottomnavigation.screen.videos;

import com.watermelon.thanghn.bottomnavigation.model.videos.ListVideosModel;
import com.watermelon.thanghn.bottomnavigation.model.videos.Video;

import java.util.List;

/**
 * Created by thang on 2/9/2018.
 */

public class ListVideosPresenter implements IListVideoModel{
    private ListVideosModel listVideosModel;
    private IListVideoView callback;

    public ListVideosPresenter (IListVideoView callback){
        this.callback=callback;
    }

    public void onHandleGetListVideo(int position){
        listVideosModel=new ListVideosModel(this);
        listVideosModel.HandleGetListVideo(position);
    }


    @Override
    public void onGetListVideoSuccess(List<Video> videoList) {
        callback.onGetListVideoSuccess(videoList);
    }

    @Override
    public void onGetListVideoFailed(String erroMsg) {
        callback.onGetListVideoFailed(erroMsg);
    }
}
