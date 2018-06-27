package com.watermelon.thanghn.bottomnavigation;

import android.content.pm.PackageManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.watermelon.thanghn.bottomnavigation.helper.Helper;
import com.watermelon.thanghn.bottomnavigation.screen.menu.MenuFragment;
import com.watermelon.thanghn.bottomnavigation.screen.newsonline.NewsFragment;
import com.watermelon.thanghn.bottomnavigation.screen.notification.MyNotification;
import com.watermelon.thanghn.bottomnavigation.screen.personal.PersonalFragment;
import com.watermelon.thanghn.bottomnavigation.screen.videos.VideosFragment;

public class MainActivity extends AppCompatActivity {
    private String TAG="MainActivity";
    private FragmentManager fragmentManager;
    private BottomBar bottomBar;
    private NewsFragment fragmentNews;
    private VideosFragment fragmentVideos;
    private PersonalFragment fragmentPersonal;
    private MenuFragment fragmentMenu;
    private static final int PERMISSION_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckPermissionsResult();
        Helper.printKeyHash(MainActivity.this);
        initView();
        initAction();
    }

    private void CheckPermissionsResult() {
//        String[] permissions = new String[]{
//                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                android.Manifest.permission.READ_EXTERNAL_STORAGE
//        };
//        List<String> listPermissionsNeeded = new ArrayList<>();
//        for (String permission : permissions) {
//            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
//                listPermissionsNeeded.add(permission);
//            }
//        }
//        if (!listPermissionsNeeded.isEmpty()) {
//            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
//        }
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE

            },PERMISSION_REQUEST_CODE);

    }

    private void initView() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        MyNotification notification=new MyNotification();
    }

    private void initAction() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                getFragment(tabId);
            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {

            }
        });
    }

    public Fragment getFragment(int menuItemId) {
        Fragment fragment = null;
        switch (menuItemId) {
            case R.id.tab_news:
                if (fragmentNews == null) {
                    fragmentNews = new NewsFragment();
                }
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transactionnews = fragmentManager.beginTransaction();
                transactionnews.replace(R.id.layoutMain, fragmentNews);
                transactionnews.commit();
                break;
            case R.id.tab_videos:
                if (fragmentVideos == null) {
                    fragmentVideos = new VideosFragment();
                }
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transactionVideos = fragmentManager.beginTransaction();
                transactionVideos.replace(R.id.layoutMain, fragmentVideos);
                transactionVideos.commit();
                break;
            case R.id.tab_me:
                if (fragmentPersonal == null) {
                    fragmentPersonal = new PersonalFragment();
                }
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transactionPersonal = fragmentManager.beginTransaction();
                transactionPersonal.replace(R.id.layoutMain, fragmentPersonal);
                transactionPersonal.commit();
                break;
            case R.id.tab_menu:
                if (fragmentMenu == null) {
                    fragmentMenu = new MenuFragment();
                }
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transactionMenu = fragmentManager.beginTransaction();
                transactionMenu.replace(R.id.layoutMain, fragmentMenu);
                transactionMenu.commit();
                break;
        }
        return fragment;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case PERMISSION_REQUEST_CODE:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"Permission Granted");
                else
//                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"Permission Denied");
            }
            break;
        }
    }
}
