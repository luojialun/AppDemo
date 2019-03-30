package com.android.mydemoapp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.mydemoapp.network.NetworkConnectChangedReceiver;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by luojialun on 2017/8/27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unBinder;
    public static boolean network_state = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setViewId());
        unBinder = ButterKnife.bind(this);
        initView();
        initData();
        if (bindEventbus()) {
            EventBus.getDefault().register(this);
        }
        network_state= NetworkConnectChangedReceiver.isConnected(this);
    }

    /**
     * 设置布局
     *
     * @return 布局ID
     */
    public abstract int setViewId();

    public abstract void initView();

    public abstract void initData();

    public boolean bindEventbus() {
        return false;
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
