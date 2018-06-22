package com.example.gab.babylove;

import com.fy.baselibrary.application.BaseApp;

/**
 *
 * @author Gab
 * @date 2017/12/12 0012
 */

public class MyApplication extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        //根据app上次退出的状态来判断是否需要设置夜间模式,提前在SharedPreference中存了一个是否是夜间模式的boolean值
//        boolean isNightMode = NightModeConfig.getInstance().getNightMode(getApplicationContext());
//        if (isNightMode) {
//            //使用夜间模式
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }else {
//            //不使用夜间模式
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }

        //  tbs
    }
}
