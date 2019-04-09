package com.android.mydemoapp.danmu;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mydemoapp.R;
import com.android.mydemoapp.util.SizeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 一行弹幕
 * created by luojialun on 2019/3/28
 */
public class DanmuView extends RelativeLayout {

    public boolean pause = false;
    public static final int speed = 200;  //像素每毫秒

    private Context context;
    public List<String> contentList = new ArrayList<>();
    private String insertComment;

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }

    public void setInsertComment(String insertComment) {
        this.insertComment = insertComment;
    }

    public DanmuView(Context context) {
        this(context, null, 0);
    }

    public DanmuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DanmuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.measure(0, 0);
            view.layout(getWidth(), 0, getWidth() + view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        startChildViewAnimation(child);
    }

    public void start() {
        if (!TextUtils.isEmpty(insertComment) && !pause) {
            TextView tv = new TextView(context);
            tv.setText(insertComment);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            tv.setBackgroundResource(R.drawable.rectangle_stroke_4d00000_17radius);
            tv.setPadding(SizeUtils.dp2px(15), SizeUtils.dp2px(7), SizeUtils.dp2px(15), SizeUtils.dp2px(7));
            addView(tv);
            setInsertComment("");
            tv.measure(0, 0);
            int time = (tv.getMeasuredWidth() + SizeUtils.dp2px(5)) * 1000 / DanmuView.speed;
            handler.sendEmptyMessageDelayed(0, time);
        } else if (0 < contentList.size() && !pause) {
            TextView tv = new TextView(context);
            tv.setText(contentList.get(0));
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            tv.setBackgroundResource(R.drawable.rectangle_4d00000_17radius);
            tv.setPadding(SizeUtils.dp2px(15), SizeUtils.dp2px(7), SizeUtils.dp2px(15), SizeUtils.dp2px(7));
            addView(tv);
            contentList.remove(0);
            tv.measure(0, 0);
            int time = (tv.getMeasuredWidth() + SizeUtils.dp2px(5)) * 1000 / DanmuView.speed;
            handler.sendEmptyMessageDelayed(0, time);
        } else {
            handler.sendEmptyMessageDelayed(0, 2000);
        }
    }

    public void pause() {
        pause = true;
    }

    public void resume() {
        pause = false;
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            start();
        }
    };

    private void startChildViewAnimation(View view) {
        view.measure(0, 0);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 0, -getWidth() - view.getMeasuredWidth());
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration((getWidth() + view.getMeasuredWidth()) * 1000 / speed);
        animator.start();
    }

    public void removeDanmuOnScreen() {
        contentList.clear();
        removeAllViews();
    }


}
