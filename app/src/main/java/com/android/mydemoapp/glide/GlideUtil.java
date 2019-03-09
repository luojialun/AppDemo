package com.android.mydemoapp.glide;

import android.content.Context;

import com.android.mydemoapp.base.BaseApp;
import com.android.mydemoapp.base.GlideApp;
import com.android.mydemoapp.base.GlideRequests;

/**
 * Created by luojialun  on 2019/02/20.
 */

public class GlideUtil {

    public static GlideRequests with(Context context) {
        try {
            return GlideApp.with(context);
        } catch (Exception e) {
            return GlideApp.with(BaseApp.getContext());
        }
    }

}
