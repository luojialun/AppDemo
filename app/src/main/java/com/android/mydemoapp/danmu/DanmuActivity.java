package com.android.mydemoapp.danmu;

import android.view.View;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DanmuActivity extends BaseActivity {

    @BindView(R.id.danmugroup)
    DanmuGroup danmuGroup;

    @Override
    public int setViewId() {
        return R.layout.activity_danmu;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<String> contentList = new ArrayList<>();
        contentList.add("好漂亮啊");
        contentList.add("美美哒");
        contentList.add("靓女");
        contentList.add("约不约");
        contentList.add("一起happy啊");
        contentList.add("来呀来呀");
        contentList.add("哈哈哈哈哈");
        contentList.add("呵呵呵呵呵呵");
        contentList.add("空虚寂寞冷");
        contentList.add("啦啦啦啦啦啦");
        danmuGroup.addContentList(contentList);

    }


    @OnClick({R.id.start_tv, R.id.pause_tv, R.id.resume_tv, R.id.insert_tv,R.id.remove_all_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_tv:
                danmuGroup.start();
                break;
            case R.id.pause_tv:
                danmuGroup.pause();
                break;
            case R.id.resume_tv:
                danmuGroup.resume();
                break;
            case R.id.insert_tv:
                danmuGroup.setInsertComment("我是一条插入的弹幕哦~");
                break;
            case R.id.remove_all_tv:
                danmuGroup.removeDanmuOnScreen();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        danmuGroup.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        danmuGroup.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        danmuGroup.release();
        danmuGroup = null;
    }

}
