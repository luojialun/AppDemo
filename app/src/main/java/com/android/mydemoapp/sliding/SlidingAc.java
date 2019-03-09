package com.android.mydemoapp.sliding;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 侧滑出现右部删除、免打扰按钮
 */
public class SlidingAc extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    public int setViewId() {
        return R.layout.activity_sliding;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
        final List<String> mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add("item" + i);
        }
        SlidingRecyclerViewAdapter adapter = new SlidingRecyclerViewAdapter(this, mData);
        recyclerView.setAdapter(adapter);


    }
}
