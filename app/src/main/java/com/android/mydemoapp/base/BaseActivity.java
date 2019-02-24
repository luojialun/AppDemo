package com.android.mydemoapp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by luojialun on 2017/8/27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SystemBarHelper.immersiveStatusBar(this,0);//沉浸式状态栏
        setContentView(setViewId());
        unBinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    /**
     * 设置布局
     * @return 布局ID
     */
    public abstract int setViewId();

    public abstract void initView();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBinder.unbind();
    }
}
