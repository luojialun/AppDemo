package com.android.mydemoapp.defineviewpage;

import android.widget.ImageView;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;

import butterknife.BindView;

public class MyViewPagerActivity extends BaseActivity {

    @BindView(R.id.myscrollview)
    MyScrollView myscrollview;

    @Override
    public int setViewId() {
        return R.layout.activity_my_view_pager;
    }

    @Override
    public void initView() {
        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(R.mipmap.pic1);
        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(R.mipmap.pic2);
        ImageView iv3 = new ImageView(this);
        iv3.setImageResource(R.mipmap.pic3);

        myscrollview.addView(iv1);
        myscrollview.addView(iv2);
        myscrollview.addView(iv3);
    }

    @Override
    public void initData() {

    }
}
