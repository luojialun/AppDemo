package com.android.mydemoapp.danmu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import com.android.mydemoapp.util.ScreenUtils;

/**
 * created by luojialun on 2019/3/28
 */
public class DanmuView extends ViewGroup {

    public boolean pause = false;
    private int speed = 200;  //像素每毫秒
    private Context context;
    private int internal = 200;

    private Scroller scroller;

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
        scroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public void addView(View child) {
        super.addView(child);
        //startChildViewAnimation(child);

//        scroller.startScroll(ScreenUtils.getScreenWidth(), 300, -ScreenUtils.getScreenWidth() - child.getMeasuredWidth(), 300);
//        invalidate();
    }

   /* @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollBy(scroller.getCurrX(), 0);
            invalidate();
        }


    }*/

    private void startChildViewAnimation(View view) {
        measure(0, 0);
        view.measure(0, 0);

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationX", ScreenUtils.getScreenWidth(), ScreenUtils.getScreenWidth() - view.getMeasuredWidth());
        animator1.setDuration(view.getMeasuredWidth() * 1000 / speed);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (null != danmuListener) {
                    danmuListener.viewTotalShow();
                }
            }
        });


    }

    public DanmuListener danmuListener;

    public void setDanmuListener(DanmuListener danmuListener) {
        this.danmuListener = danmuListener;
    }

    public interface DanmuListener {
        void viewTotalShow();
    }
}
