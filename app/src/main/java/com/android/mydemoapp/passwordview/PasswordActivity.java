package com.android.mydemoapp.passwordview;

import com.android.mydemoapp.R;
import com.android.mydemoapp.base.BaseActivity;
import com.android.mydemoapp.util.ToastUtils;

import butterknife.BindView;

public class PasswordActivity extends BaseActivity {

    @BindView(R.id.passwordview)
    PasswordInputView passwordview;

    @Override
    public int setViewId() {
        return R.layout.activity_password;
    }

    @Override
    public void initView() {
        passwordview.setInputCompleteListener(new PasswordInputView.InputCompleteListener() {
            @Override
            public void inputComplete(String password) {
                ToastUtils.showShort(PasswordActivity.this, "输入密码" + password);
            }
        });
    }

    @Override
    public void initData() {

    }
}
