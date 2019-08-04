package com.android.mydemoapp.topview;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;
import com.android.mydemoapp.util.ScreenUtils;
import com.android.mydemoapp.util.SizeUtils;
import com.android.mydemoapp.util.ToastUtils;

import butterknife.OnClick;

public class TopView1Activity extends BaseActivity {

    WindowManager wm;
    WindowManager.LayoutParams params;
    View floatView;


    @Override
    public int setViewId() {
        return R.layout.activity_top_view1;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.button)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                readyGo(TopView2Activity.class);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkPermissiom();

                    }
                }, 2000);
                break;
        }
    }

    public void checkPermissiom() {
        if (Build.VERSION.SDK_INT > 23 && !Settings.canDrawOverlays(TopView1Activity.this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 10);
        } else {
            showView();
        }

    }

    private void showView() {
        wm = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE); // 注意：这里必须是全局的context
        // 判断UI控件是否存在，存在则移除，确保开启任意次应用都只有一个悬浮窗
        if (floatView != null) {
            wm.removeView(floatView);
        }
        params = new WindowManager.LayoutParams();
        // 系统级别的窗口
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Android 8.0
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            //其他版本
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        //params.gravity = Gravity.BOTTOM|Gravity.END;
        // 设置图片格式，效果为背景透明
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        floatView = View.inflate(getApplicationContext(), R.layout.float_view, null);
        ImageView iv = floatView.findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(TopView1Activity.this, "floatView click");
            }
        });
        params.width = SizeUtils.dp2px(126);
        params.height = SizeUtils.dp2px(68);
        params.x = ScreenUtils.getScreenWidth() / 2;
        params.y = (ScreenUtils.getScreenHeight() ) / 2- SizeUtils.dp2px(100);
        wm.addView(floatView, params);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "translationX", -SizeUtils.dp2px(126));
        objectAnimator.setDuration(300);
        objectAnimator.start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showView();
    }
}
