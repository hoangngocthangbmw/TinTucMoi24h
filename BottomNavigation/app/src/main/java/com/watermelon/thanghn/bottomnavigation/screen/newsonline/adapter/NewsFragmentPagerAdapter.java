package com.watermelon.thanghn.bottomnavigation.screen.newsonline.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.watermelon.thanghn.bottomnavigation.screen.newsonline.ListNewsFramgent;

/**
 * Created by thang on 1/16/2018.
 */

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public NewsFragmentPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
       return ListNewsFramgent.newInstance(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return 17;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Trang chủ";
            case 1: return "Sức khỏe";
            case 2: return "Xã hội";
            case 3: return "Giải trí";
            case 4: return "Giáo dục";
            case 5: return "Thể thao";
            case 6: return "Thế giới";
            case 7: return "Kinh doanh";
            case 8: return "Ô tô-Xe máy";
            case 9: return "Sức mạnh số";
            case 10: return "Tình yêu giới tính";
            case 11: return "Chuyện lạ";
            case 12: return "Việc làm";
            case 13: return "Văn hóa";
            case 14: return "Pháp luật";
            case 15: return "Đời sống";
            case 16: return "Du lịch";
        }
      return "tab";
    }

}
