package com.android.mydemoapp.danmu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mydemoapp.R;
import com.android.mydemoapp.util.SizeUtils;

import java.util.List;

public class DanmuGroup extends LinearLayout {

    private final int raw;
    private List<String> contentList;

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }

    public DanmuGroup(Context context) {
        this(context, null, 0);
    }

    public DanmuGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DanmuGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DanmuGroup);
        raw = ta.getInteger(R.styleable.DanmuGroup_danmuRaw, 3);
        ta.recycle();

        for (int i = 0; i < raw; i++) {
            addView(new DanmuView(context));
        }
        for (int i = 0; i < raw; i++) {
            DanmuView danmuView = (DanmuView) getChildAt(i);
            danmuView.setDanmuListener(new DanmuView.DanmuListener() {
                @Override
                public void viewTotalShow() {
                    if (!TextUtils.isEmpty(getContent())) {
                        addView(danmuView, getContent());
                    }
                }
            });
        }

    }

    public void start() {
        for (int i = 0; i < raw; i++) {
            DanmuView danmuView = (DanmuView) getChildAt(i);
            if (!TextUtils.isEmpty(getContent())) {
                addView(danmuView, getContent());
            }
        }
    }

    public synchronized String getContent() {
        if (0 < contentList.size()) {
            String content = contentList.get(0);
            contentList.remove(0);
            return content;
        } else {
            return "    ";
        }

    }

    private void addView(DanmuView danmuView, String content) {
        TextView tv = new TextView(getContext());
        tv.setText(content);
        tv.setTextColor(Color.WHITE);
        tv.setPadding(SizeUtils.dp2px(15), SizeUtils.dp2px(7), SizeUtils.dp2px(15), SizeUtils.dp2px(7));
        tv.setBackgroundResource(R.drawable.rectangle_d4000000_17radius);
        danmuView.addView(tv);
    }


}
