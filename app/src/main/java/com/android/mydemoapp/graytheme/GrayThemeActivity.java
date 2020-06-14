package com.android.mydemoapp.graytheme;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;

/**
 * 灰色主题（清明主题）
 * Created by luojialun on 2020-04-06.
 */
public class GrayThemeActivity extends BaseActivity {
    @Override
    public int setViewId() {
        return R.layout.activity_gray_theme;
    }

    @Override
    public void initView() {

        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, paint);

    }

    @Override
    public void initData() {

    }
}
