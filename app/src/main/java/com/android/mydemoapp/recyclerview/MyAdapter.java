package com.android.mydemoapp.recyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.mydemoapp.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by luojialun on 2020-07-19.
 */
public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyAdapter(@Nullable List<String> data) {
        super(R.layout.item_myadapter, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv, item);
    }
}
