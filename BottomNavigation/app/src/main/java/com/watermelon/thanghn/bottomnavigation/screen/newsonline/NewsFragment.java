package com.watermelon.thanghn.bottomnavigation.screen.newsonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.helper.animation.DepthPageTransformer;
import com.watermelon.thanghn.bottomnavigation.screen.categories.CategoriesActivity;
import com.watermelon.thanghn.bottomnavigation.screen.newsonline.adapter.NewsFragmentPagerAdapter;
import com.watermelon.thanghn.bottomnavigation.screen.search.SearchActivity;

/**
 * Created by thang on 1/16/2018.
 */

public class NewsFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView btnCategories,btnSearch;
    View view;
    private FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmet_news, container, false);
        initView();
        initData();
        initAction();
        return view;
    }

    private void initData() {
    }

    private void initAction() {
        btnCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),CategoriesActivity.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnSearch=(ImageView)view.findViewById(R.id.buttonSearch);
        btnCategories=(ImageView)view.findViewById(R.id.buttonCategories);
        viewPager = (ViewPager) view.findViewById(R.id.ViewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        NewsFragmentPagerAdapter newsFragmentPagerAdapter = new NewsFragmentPagerAdapter(getChildFragmentManager(), getContext());
        viewPager.setAdapter(newsFragmentPagerAdapter);
        viewPager.setPageTransformer(true,new DepthPageTransformer());//xet animation
        tabLayout.setupWithViewPager(viewPager);
    }
}
