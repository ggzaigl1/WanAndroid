package com.ggz.baselibrary.utils;

/**
 * Created by Administrator on 2016/11/15 0015.
 * 防止控件被重复点击
 */

public class CommonUtils {

    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
