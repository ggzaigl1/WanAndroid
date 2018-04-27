package com.fy.baselibrary.utils;

import android.widget.Toast;

import com.fy.baselibrary.application.BaseApp;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/9/29
 *     desc  : 吐司相关工具类
 * </pre>
 */
public class ToastUtils {

    /** 显示toast 开关 */
    public static boolean isShow = true;
    private static Toast toast;

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        show(message.toString(), Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(int message) {
        show(BaseApp.getAppCtx().getResources().getString(message), Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        show(message.toString(), Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(int message) {
        show(BaseApp.getAppCtx().getResources().getString(message), Toast.LENGTH_LONG);
    }

    /**
     * 显示系统 toast
     * @param message 消息
     */
    private static void show(String message, int duration){
        if (isShow){

            if (null == toast){
                toast =  Toast.makeText(BaseApp.getAppCtx(), message, duration);
            } else {
                toast.setText(message);
            }

            toast.setDuration(duration);
            toast.show();
        }
    }
}