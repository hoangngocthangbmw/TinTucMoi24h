package com.watermelon.thanghn.bottomnavigation.screen.videos;

import com.watermelon.thanghn.bottomnavigation.model.videos.Video;

import java.util.List;

/**
 * Created by thang on 4/1/2018.
 */

public interface IListVideoView {
    void onGetListVideoSuccess(List<Video> videoList);

    void onGetListVideoFailed(String erroMsg);
}
