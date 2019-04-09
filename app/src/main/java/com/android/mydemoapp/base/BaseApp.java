package com.android.mydemoapp.base;

import android.app.Application;
import android.content.Context;

import com.android.mydemoapp.util.NotchScreenUtils;

/**
 * Created by luojialun on 2017/8/27.
 */

public class BaseApp extends Application {

    private static Context context;
    private static int mStatusBarHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

    public static int getStatusBarHeight() {
        if (mStatusBarHeight == 0) {
            mStatusBarHeight = NotchScreenUtils.getStatusBarHeight();
        }
        return mStatusBarHeight;
    }
}
