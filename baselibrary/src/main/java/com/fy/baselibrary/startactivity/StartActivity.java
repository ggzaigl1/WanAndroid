package com.fy.baselibrary.startactivity;

import android.content.Intent;
import android.os.Bundle;

import com.fy.baselibrary.R;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;

/**
 * 不可见Activity 用于控制程序 退出(入口activity)
 * Created by fangs on 2017/4/26.
 */
public class StartActivity extends BaseActivity {

    private static final String FLAG_EXIT = "FLAG_EXIT_APP";

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void setStatusBarType() {
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (null != savedInstanceState) {
            if (savedInstanceState.getBoolean(FLAG_EXIT)) {
                exitApp();
                return;
            }
        } else if ((Intent.FLAG_ACTIVITY_CLEAR_TOP & intent.getFlags()) != 0) {
            L.e("StartActivity", "----- 1");
            exitApp();
        } else {
            L.e("StartActivity", "----- 2");
            isStartActivityOnly();
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        L.e("StartActivity", "onNewIntent- false");
        if ((Intent.FLAG_ACTIVITY_CLEAR_TOP & intent.getFlags()) != 0) {
            L.e("StartActivity", "onNewIntent");
            exitApp();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putBoolean(FLAG_EXIT, true);
            L.e("StartActivity", "onSaveInstanceState");
        }
    }

    // 避免从桌面启动程序后，会重新实例化入口类的activity
    private void isStartActivityOnly() {
        if (!this.isTaskRoot()) {
            Intent intent = getIntent();
            if (intent != null) {
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                    exitApp();
                    return;
                }
            }
        } else {
            JumpUtils.jump(mContext, "com.hjy.mobiledoctor.main.MainActivity", null);
        }
    }

    /**
     * 退出应用
     */
    private void exitApp() {
        finish();
        overridePendingTransition(R.anim.anim_slide_right_in, R.anim.anim_slide_right_out);
        System.exit(0);
    }
}
