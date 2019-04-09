package com.android.mydemoapp.danmu;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.mydemoapp.R;
import com.android.mydemoapp.util.SizeUtils;

import java.util.List;
import java.util.Random;

/**
 * 多行弹幕
 * created by luojialun on 2019/4/3
 */
public class DanmuGroup extends LinearLayout {

    private int raw;   //行
    private boolean start = false;

    public int getRaw() {
        return raw;
    }

    public void setRaw(int raw) {
        start = false;
        this.raw = raw;
        removeAllViews();
        initChildView();
        start();
    }

    public void addContentList(List<String> contentList) {
        for (int i = 0; i < contentList.size(); i++) {
            Random random = new Random();
            int index = random.nextInt(raw);
            ((DanmuView) getChildAt(index)).getContentList().add(contentList.get(i));
        }
    }

    public DanmuGroup(Context context) {
        this(context, null, 0);
    }

    public DanmuGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DanmuGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DanmuGroup);
        raw = typedArray.getInt(R.styleable.DanmuGroup_raw, 3);
        if (0 < raw) {
            initChildView();
        }
        typedArray.recycle();
    }

    private void initChildView() {
        for (int i = 0; i < raw; i++) {
            DanmuView danmuView = new DanmuView(getContext());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.topMargin = SizeUtils.dp2px(10);
            addView(danmuView, layoutParams);
        }
    }

    /**
     * 启动弹幕  外部只能调用一次
     */
    public void start() {
        if (!start) {
            start = true;
        } else {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            ((DanmuView) getChildAt(i)).start();
        }
    }

    public boolean isStart() {
        return start;
    }

    /**
     * 暂停弹幕  已出现的继续走
     */
    public void pause() {
        for (int i = 0; i < getChildCount(); i++) {
            ((DanmuView) getChildAt(i)).pause();
        }
    }

    /**
     * 恢复弹幕
     */
    public void resume() {
        for (int i = 0; i < getChildCount(); i++) {
            ((DanmuView) getChildAt(i)).resume();
        }
    }

    public boolean isPause() {
        if (0 < getChildCount()) {
            return ((DanmuView) getChildAt(0)).pause;
        } else {
            return false;
        }
    }


    /**
     * 插入弹幕
     *
     * @param insertComment
     */
    public void setInsertComment(String insertComment) {
        Random random = new Random();
        int index = random.nextInt(raw);
        ((DanmuView) getChildAt(index)).setInsertComment(insertComment);
    }

    public void removeDanmuOnScreen() {
        for (int i = 0; i < getChildCount(); i++) {
            ((DanmuView) getChildAt(i)).removeDanmuOnScreen();
        }
    }

    public void release() {
        for (int i = 0; i < getChildCount(); i++) {
            ((DanmuView) getChildAt(i)).removeDanmuOnScreen();
            ((DanmuView) getChildAt(i)).handler.removeCallbacksAndMessages(null);
        }
        removeAllViews();
    }


}
