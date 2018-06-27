package com.watermelon.thanghn.bottomnavigation.screen.videos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.helper.animation.ZoomOutPageTransformer;
import com.watermelon.thanghn.bottomnavigation.screen.videos.adapter.VideosFragmentPagerAdapter;

/**
 * Created by thang on 1/16/2018.
 */

public class VideosFragment extends Fragment{
    private String TAG="VideosFragment";
    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_videos,container,false);
        initView();
        initData();
        initAction();
        return view;
    }

    private void initAction() {
    }

    private void initData() {
    }

    private void initView() {
        viewPager=(ViewPager)view.findViewById(R.id.ViewPager);
        tabLayout=(TabLayout)view.findViewById(R.id.tabLayout);

        VideosFragmentPagerAdapter videosFragmentPagerAdapter = new VideosFragmentPagerAdapter(getChildFragmentManager(), getContext());
        viewPager.setAdapter(videosFragmentPagerAdapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        tabLayout.setupWithViewPager(viewPager);
    }

}
