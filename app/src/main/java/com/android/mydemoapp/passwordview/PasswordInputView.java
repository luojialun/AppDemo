package com.android.mydemoapp.passwordview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mydemoapp.R;
import com.android.mydemoapp.util.KeyboardUtils;

/**
 * 密码锁  4位
 * created by luojialun on 2019/3/8
 */
@SuppressLint("AppCompatCustomView")
public class PasswordInputView extends RelativeLayout {

    private EditText inputEt;
    private LinearLayout containerLl;

    public PasswordInputView(@NonNull Context context) {
        this(context, null, 0);
    }

    public PasswordInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_password, this, true);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        containerLl = findViewById(R.id.container_ll);
        inputEt = findViewById(R.id.input_et);
        containerLl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showKeyboard();
            }
        });

        inputEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for (int i = 0; i < 4; i++) {
                    if (i < s.length()) {
                        ((TextView) containerLl.getChildAt(i)).setText(String.valueOf(s.charAt(i)));
                    } else {
                        ((TextView) containerLl.getChildAt(i)).setText("");
                    }
                }

                if (4 == s.length() && null != inputCompleteListener) {
                    inputCompleteListener.inputComplete(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void showKeyboard() {
        KeyboardUtils.showSoftInput(inputEt);
    }

    public void hideKeyboard() {
        KeyboardUtils.hideSoftInput(inputEt);
    }

    public void clearPassword() {
        inputEt.setText("");
    }

    public InputCompleteListener inputCompleteListener;

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    public interface InputCompleteListener {

        void inputComplete(String password);

    }


}
