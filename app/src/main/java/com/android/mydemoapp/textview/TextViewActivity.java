package com.android.mydemoapp.textview;

import android.os.Bundle;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;

public class TextViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
    }

    @Override
    public int setViewId() {
        return R.layout.activity_text_view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}