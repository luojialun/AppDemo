package com.android.mydemoapp.swipeback.xbeats;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.android.mydemoapp.R;
import com.android.mydemoapp.util.SystemBarHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * https://github.com/XBeats/and_swipeback
 * Created by fhf11991 on 2016/7/25.
 */
public abstract class SwipeBackActivity extends FragmentActivity implements SwipeBackHelper.SlideBackManager {

    private Unbinder unBinder;
    private SwipeBackHelper mSwipeBackHelper;
    private View statusBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatus();
        setContentView(setViewId());
        unBinder = ButterKnife.bind(this);
        initParams();
        initView();
        initData();
    }

    public abstract int setViewId();

    private void initStatus() {
        if (isSteepStatusBar()) {
            SystemBarHelper.immersiveStatusBar(this, 0);//沉浸式状态栏
        }
        if (isDarkStatusBar()) {
            SystemBarHelper.setStatusBarDarkMode(this);//6.0黑字
        }
        //统一设置手动填充的状态栏高度
        statusBar = findViewById(R.id.statusBar);
        if (statusBar != null) {
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 100;//BaseApp.getStatusBarHeight()
            statusBar.setLayoutParams(layoutParams);
        }
    }

    /**
     * [是否设置沉浸状态栏] 默认是
     * 默认做沉浸 且需布局里自己放置假的状态栏 如不需要 重写isSteepStatusBar()返回false
     */
    public boolean isSteepStatusBar() {
        return true;
    }

    /**
     * [是否设置状态栏黑字] 默认是
     * 如不需要 重写返回false
     */
    public boolean isDarkStatusBar() {
        return true;
    }


    public void initParams() {
    }

    public abstract void initView();

    public abstract void initData();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!supportSlideBack()) {
            return super.dispatchTouchEvent(ev);
        }
        if (mSwipeBackHelper == null) {
            mSwipeBackHelper = new SwipeBackHelper(this, new SlideActivityAdapter());
            mSwipeBackHelper.setOnSlideFinishListener(new SwipeBackHelper.OnSlideFinishListener() {
                @Override
                public void onFinish() {
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, R.anim.hold_on);
                }
            });
        }
        return mSwipeBackHelper.processTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    @Override
    public void finish() {
        if (mSwipeBackHelper != null) {
            mSwipeBackHelper.finishSwipeImmediately();
        }
        super.finish();
    }

    @Override
    public boolean supportSlideBack() {
        return true;
    }

    @Override
    public boolean canBeSlideBack() {
        return true;
    }

    private static class SlideActivityAdapter implements SlideActivityCallback {

        @Override
        public Activity getPreviousActivity() {
            return ActivityLifecycleHelper.getPreviousActivity();
        }
    }
}
