package com.android.mydemoapp.swipeback;

import android.content.Intent;
import android.view.View;

import com.android.mydemoapp.R;
import com.android.mydemoapp.swipeback.xbeats.SwipeBackActivity;

import butterknife.OnClick;

public class SwipeBack1Activity extends SwipeBackActivity {

    @Override
    public int setViewId() {
        return R.layout.activity_swipe_back1;
    }

    @Override
    public boolean isDarkStatusBar() {
        return false;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.click_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.click_tv:
                startActivity(new Intent(this, SwipeBack2Activity.class));
                break;
        }
    }

}
