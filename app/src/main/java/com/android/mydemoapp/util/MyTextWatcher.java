package com.android.mydemoapp.util;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by luojialun on 2018/4/13.
 */
public abstract class MyTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
