package com.android.mydemoapp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.android.mydemoapp.R;
import com.android.mydemoapp.network.NetworkConnectChangedReceiver;
import com.android.mydemoapp.pop.ProgressDialog;
import com.android.mydemoapp.util.SystemBarHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by luojialun on 2017/8/27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unBinder;
    public static boolean network_state = true;
    private View statusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setViewId());
        unBinder = ButterKnife.bind(this);
        initStatus();
        initParams();
        initView();
        initData();
        if (bindEventbus()) {
            EventBus.getDefault().register(this);
        }
        network_state = NetworkConnectChangedReceiver.isConnected(this);
    }

    /**
     * 设置布局
     *
     * @return 布局ID
     */
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
            layoutParams.height = BaseApp.getStatusBarHeight();
            statusBar.setLayoutParams(layoutParams);
        }
    }

    /**
     * [是否允许全屏]
     */
    public boolean isAllowFullScreen() {
        return false;
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

    public boolean bindEventbus() {
        return false;
    }

    public void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }


    public void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    public void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    public void showProgress() {
        ProgressDialog.showDialog(this, "", true, null);
    }

    public void dismissProgress() {
        ProgressDialog.dissmiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBinder.unbind();
        if (bindEventbus()) {
            EventBus.getDefault().unregister(this);
        }
    }
}
