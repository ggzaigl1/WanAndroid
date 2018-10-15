package com.ggz.baselibrary.startactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ggz.baselibrary.R;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.ggz.baselibrary.utils.FileUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.LogUtils;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 不可见Activity 用于控制程序 退出(入口activity)
 * Created by fangs on 2017/4/26.
 */
public class StartActivity extends AppCompatActivity implements IBaseActivity {

    private static final String FLAG_EXIT = "FLAG_EXIT_APP";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.e("StartActivity", "onNewIntent- false");
        if ((Intent.FLAG_ACTIVITY_CLEAR_TOP & intent.getFlags()) != 0) {
            LogUtils.e("StartActivity", "onNewIntent");
            exitApp();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putBoolean(FLAG_EXIT, true);
            LogUtils.e("StartActivity", "onSaveInstanceState");
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
            JumpUtils.jump(StartActivity.this, "com.hjy.mobiledoctor.main.MainActivity", null);
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

    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return 0;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setTransparentBar(activity, R.color.transparent, R.color.transparent);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        //rx 递归删除缓存的压缩文件
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            FileUtils.recursionDeleteFile(new File(FileUtils.getPath("head.img.temp")));
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> LogUtils.e("aaaab", "" + Thread.currentThread().getName() ));

        Intent intent = getIntent();
        if (null != savedInstanceState) {
            if (savedInstanceState.getBoolean(FLAG_EXIT)) {
                exitApp();
                return;
            }
        } else if ((Intent.FLAG_ACTIVITY_CLEAR_TOP & intent.getFlags()) != 0) {
            LogUtils.e("StartActivity", "----- 1");
            exitApp();
        } else {
            LogUtils.e("StartActivity", "----- 2");
            isStartActivityOnly();
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }
}
