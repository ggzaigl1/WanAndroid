package com.ggz.baselibrary.application;

import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.ggz.baselibrary.utils.NightModeConfig;


/**
 * 基础 application
 * Created by fangs on 2017/5/5.
 */
public class BaseApp extends MultiDexApplication {

    private static BaseApp mApplication; // 单例模式

    //也可以直接使用代码块直接设置
    //static {
    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_ NIGHT_YES);
    //}
    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;
//        设置activity 生命周期回调
        registerActivityLifecycleCallbacks(new BaseActivityLifecycleCallbacks());

        //根据app上次退出的状态来判断是否需要设置夜间模式,提前在SharedPreference中存了一个是否是夜间模式的boolean值
        boolean isNightMode = NightModeConfig.getInstance().getNightMode(getApplicationContext());
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /**
     * 单例模式，获取BTApplication的实例
     *
     * @return
     */
    public static BaseApp getAppCtx() {
        return mApplication;
    }

}
