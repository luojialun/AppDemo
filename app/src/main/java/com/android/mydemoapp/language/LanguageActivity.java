package com.android.mydemoapp.language;

import android.view.View;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;
import com.blankj.utilcode.util.LanguageUtils;

import java.util.Locale;

import butterknife.OnClick;

/**
 * 语言切换
 * https://juejin.im/post/5d1970dd6fb9a07ee63f7c79
 */
public class LanguageActivity extends BaseActivity {

    @Override
    public int setViewId() {
        return R.layout.activity_language;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.textview,R.id.textview2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textview:
                LanguageUtils.applyLanguage(Locale.CHINA,LanguageActivity.class);
                break;
            case R.id.textview2:
                LanguageUtils.applyLanguage(Locale.ENGLISH,LanguageActivity.class);
                break;
        }
    }
}
