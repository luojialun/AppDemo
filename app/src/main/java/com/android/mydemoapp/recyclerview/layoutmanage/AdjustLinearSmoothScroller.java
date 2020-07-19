package com.android.mydemoapp.recyclerview.layoutmanage;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.widget.LinearSmoothScroller;
import android.util.DisplayMetrics;

/**
 * Author:  andy.xwt
 * Date:    2020/3/28 18:06
 * Description:
 */

public class AdjustLinearSmoothScroller extends LinearSmoothScroller {

    private int scrollType;
    private  int time;
    public static final int DEFAULT_MILLISECONDS_PER_INCH = 25;


    @IntDef({SNAP_TO_ANY, SNAP_TO_START, SNAP_TO_END})
    public @interface ScrollType {
    }


    public AdjustLinearSmoothScroller(Context context, @ScrollType int scrollType) {
        super(context);
        this.scrollType = scrollType;
    }

    @Override
    protected int calculateTimeForScrolling(int dx) {
        return  time;
    }

    @Override
    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return time/displayMetrics.densityDpi;
    }

    public  void setTime(int milliseconds) {
        time = milliseconds;
    }

    @Override
    protected int getVerticalSnapPreference() {
        return scrollType;
    }
}
