package com.android.mydemoapp.topview;

import android.view.View;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;
import com.android.mydemoapp.util.ToastUtils;

import butterknife.OnClick;

public class TopView2Activity extends BaseActivity {

    @Override
    public int setViewId() {
        return R.layout.activity_top_view2;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.button})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
                ToastUtils.showShort(this,"click");
                break;
        }
    }
}
