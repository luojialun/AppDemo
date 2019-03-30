package com.android.mydemoapp.network;

import android.widget.Toast;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 网络监听
 * created by luojialun on 2019/3/30
 */
public class NetworkActivity extends BaseActivity {

    @Override
    public int setViewId() {
        return R.layout.activity_network;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public boolean bindEventbus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void networkChange(NetworkChangeEvent event) {
        if (event.isConnected) {
            Toast.makeText(this, "network true", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "network false", Toast.LENGTH_SHORT).show();
        }
    }

}
