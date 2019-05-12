package com.android.mydemoapp.swipeback;

import android.content.Intent;
import android.view.View;

import com.android.mydemoapp.R;
import com.android.mydemoapp.swipeback.xbeats.SwipeBackActivity;

import butterknife.OnClick;
//import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class SwipeBack2Activity extends SwipeBackActivity {

    @Override
    public int setViewId() {
        return R.layout.activity_swipe_back2;
    }

    @Override
    public boolean isDarkStatusBar() {
        return false;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.click_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.click_tv:
                startActivity(new Intent(this, SwipeBack3Activity.class));
                break;
        }
    }
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemBarHelper.immersiveStatusBar(this, 0);//沉浸式状态栏
        setContentView(R.layout.activity_swipe_back2);
        findViewById(R.id.click_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SwipeBack2Activity.this, SwipeBack2Activity.class));
            }
        });
    }
*/

}
