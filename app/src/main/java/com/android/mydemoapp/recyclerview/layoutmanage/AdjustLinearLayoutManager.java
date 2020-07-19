package com.android.mydemoapp.recyclerview.layoutmanage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import static com.android.mydemoapp.recyclerview.layoutmanage.AdjustLinearSmoothScroller.DEFAULT_MILLISECONDS_PER_INCH;


/**
 * https://www.jianshu.com/p/7110bedfdb5e
 */
public class AdjustLinearLayoutManager extends LinearLayoutManager {

    private int scrollType;
    private int time = DEFAULT_MILLISECONDS_PER_INCH;

    public AdjustLinearLayoutManager(Context context) {
        super(context);
    }

    public AdjustLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public AdjustLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //每英寸滑动的时间
    public void setMillisecondsPerInch(int time) {
        this.time = time;
    }

    public void setScrollType(@AdjustLinearSmoothScroller.ScrollType int scrollType) {
        this.scrollType = scrollType;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        AdjustLinearSmoothScroller scroller = new AdjustLinearSmoothScroller(recyclerView.getContext(), scrollType);
        scroller.setTime(time);
        scroller.setTargetPosition(position);
        startSmoothScroll(scroller);
    }

}
