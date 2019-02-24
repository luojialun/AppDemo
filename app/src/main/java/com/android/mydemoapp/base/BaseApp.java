package com.android.mydemoapp.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by luojialun on 2017/8/27.
 */

public class BaseApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
