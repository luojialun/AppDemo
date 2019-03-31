package com.android.mydemoapp.defineviewpage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 自定义viewpager练习demo
 */
public class MyScrollView extends ViewGroup {

    private GestureDetector gestureDetector;
    //    private MyScroller myScroller;
    private Scroller myScroller;

    private int currId = 0;  //当前的ID值  显示在屏幕上的子View的下标

    private int firstX = 0;  //down 事件时的x坐标
    private boolean isFling = false;

    public MyScrollView(Context context) {
        this(context, null, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        gestureDetector = new GestureDetector(context, new MyGestureDectorListener());
        myScroller = new Scroller(context);
    }

    public class MyGestureDectorListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            scrollBy((int) distanceX, 0);
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            isFling = true;
            if (velocityX > 0 && currId > 0) {
                currId--;
            } else if (velocityX < 0 && currId < getChildCount() - 1) {
                currId++;
            }
            movetoDest(currId);

            return false;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:

                if (!isFling) {
                    if (event.getX() - firstX > getWidth() / 2) { // 手指向右滑动，超过屏幕的1/2  当前的currid - 1
                        currId--;
                    } else if (firstX - event.getX() > getWidth() / 2) { // 手指向左滑动，超过屏幕的1/2  当前的currid + 1
                        currId++;
                    }
                    movetoDest(currId);
                }
                isFling = false;

                break;

        }
        return true;
    }

    private void movetoDest(int nextId) {
        if (nextId < 0) {
            nextId = 0;
        } else if (nextId > getChildCount() - 1) {
            nextId = getChildCount() - 1;
        }

        int distance = nextId * getWidth() - getScrollX();
        myScroller.startScroll(getScrollX(), 0, distance, 0);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (myScroller.computeScrollOffset()) {
            scrollTo(myScroller.getCurrX(), 0);
            invalidate();
        }

    }
}
