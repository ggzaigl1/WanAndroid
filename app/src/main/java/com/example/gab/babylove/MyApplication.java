package com.example.gab.babylove;

import android.util.Log;

import com.fy.baselibrary.application.BaseApplication;
import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by Gab on 2017/12/12 0012.
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //  tbs
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。
                Log.e("下载：", "onCoreInitFinished");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("嘿嘿", "X5内核初始化：" + b);
            }
        });
    }
}
