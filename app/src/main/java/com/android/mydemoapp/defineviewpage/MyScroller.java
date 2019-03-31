package com.android.mydemoapp.defineviewpage;

import android.content.Context;
import android.os.SystemClock;

/**
 * 滑动帮助类
 */
public class MyScroller {

    private Context context;
    private int startX;
    private int startY;
    private int distanceX;
    private int distanceY;

    private int currX;
    private int duration = 500;
    private long startTime;
    private boolean isFinish = false;

    public MyScroller(Context context) {
        this.context = context;
    }

    public void startScroll(int startX, int startY, int distanceX, int distanceY) {
        this.startX = startX;
        this.startY = startY;
        this.distanceX = distanceX;
        this.distanceY = distanceY;

        this.startTime = SystemClock.uptimeMillis();
        isFinish = false;

    }

    public boolean computeScroll() {
        if (isFinish) {
            return false;
        }

        long passTime = SystemClock.uptimeMillis() - startTime;
        if (passTime < duration) {
            currX = (int) (startX + distanceX * passTime / duration);
        } else {
            currX = startX + distanceX;
            isFinish = true;
        }
        return true;
    }

    public int getCurrX() {
        return currX;
    }
}
