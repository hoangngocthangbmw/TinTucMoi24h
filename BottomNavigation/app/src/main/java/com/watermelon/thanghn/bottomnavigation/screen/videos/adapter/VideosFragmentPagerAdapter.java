package com.watermelon.thanghn.bottomnavigation.screen.videos.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.watermelon.thanghn.bottomnavigation.screen.videos.ListVideosFragment;

/**
 * Created by thang on 2/9/2018.
 */

public class VideosFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public VideosFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        return ListVideosFragment.newInstance(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return 11;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Mới";
            case 1: return "Hài Hước";
            case 2: return "Game";
            case 3: return "Thời sự";
            case 4: return "Cute";
            case 5: return "Haha";
            case 6: return "Lifestyle";
            case 7: return "Ngẫm";
            case 8: return "2Tek";
            case 9: return "Điệu";
            case 10: return "Top";
        }
        return "tab";
    }
}
