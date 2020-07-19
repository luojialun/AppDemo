package com.android.mydemoapp.recyclerview;

import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;
import com.android.mydemoapp.recyclerview.layoutmanage.AdjustLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecyclerviewActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.num_tv)
    TextView numTv;

    private AdjustLinearLayoutManager adjustLinearLayoutManager;

    @Override
    public int setViewId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    public void initView() {
        adjustLinearLayoutManager = new AdjustLinearLayoutManager(this);
        adjustLinearLayoutManager.setMillisecondsPerInch(100);
        adjustLinearLayoutManager.setScrollType(LinearSmoothScroller.SNAP_TO_ANY);
        recyclerview.setLayoutManager(adjustLinearLayoutManager);
    }

    @Override
    public void initData() {
        List<String> contentList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            contentList.add(String.valueOf(i));
        }
        MyAdapter myAdapter = new MyAdapter(contentList);
        recyclerview.setAdapter(myAdapter);


        numTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adjustLinearLayoutManager.findLastVisibleItemPosition() > 10) {
                    recyclerview.scrollToPosition(10);
                }
                recyclerview.smoothScrollToPosition(0);
            }
        });
    }
}
