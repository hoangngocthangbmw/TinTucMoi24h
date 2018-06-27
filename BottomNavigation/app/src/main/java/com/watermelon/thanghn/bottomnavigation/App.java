package com.watermelon.thanghn.bottomnavigation;

import android.app.Application;
import android.content.Context;

/**
 * Created by thang on 2/9/2018.
 */

public class App extends Application{
    private static Context context;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return App.context;
    }

}
