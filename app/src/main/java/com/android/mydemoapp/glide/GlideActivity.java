package com.android.mydemoapp.glide;

import android.os.Bundle;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;

public class GlideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
    }

    @Override
    public int setViewId() {
        return R.layout.activity_glide;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
