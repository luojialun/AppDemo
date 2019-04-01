package com.android.mydemoapp.danmu;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;
import com.android.mydemoapp.util.SizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DanmuActivity extends BaseActivity {

    @BindView(R.id.danmuview)
    DanmuView danmuView;
    @BindView(R.id.danmugroup)
    DanmuGroup danmuGroup;

    private List<String> contentList = new ArrayList<>();

    @Override
    public int setViewId() {
        return R.layout.activity_danmu;
    }

    @Override
    public void initView() {
        TextView tv = new TextView(this);
        tv.setText("Hello");
        tv.setTextColor(Color.WHITE);
        tv.setPadding(SizeUtils.dp2px(15), SizeUtils.dp2px(7), SizeUtils.dp2px(15), SizeUtils.dp2px(7));
        tv.setBackgroundResource(R.drawable.rectangle_d4000000_17radius);
        danmuView.addView(tv);
    }

    @Override
    public void initData() {
        contentList.add("稀罕你");
        contentList.add("真美呀");
        contentList.add("好好看哦");
        contentList.add("露好多啊啊啊");
        contentList.add("美女");
        contentList.add("恋女妹妹");
        danmuGroup.setContentList(contentList);
    }

    @OnClick(R.id.click_tv)
    public void onClick(View view) {
        danmuGroup.start();
    }

}
