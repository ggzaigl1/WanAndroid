package com.fy.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fy.baselibrary.application.BaseApplication;

/**
 * SharedPreferences 管理工具类
 * Created by fangs on 2017/5/18.
 */
public class SpfUtils {

//    创建的Preferences文件存放位置可以在Eclipse中查看：
//	  DDMS->File Explorer /<package name>/shared_prefs/setting.xml

    private static String spfFileName = "fySpf";

    private SpfUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static SharedPreferences getSpf(){
        Context ctx = BaseApplication.getApplication();
        SharedPreferences mSpf = ctx.getSharedPreferences(spfFileName, Context.MODE_PRIVATE);

        return mSpf;
    }

    /**
     * 向默认的SharedPreferences文件保存String内容
     *
     * @param key   保存的键
     * @param value 保存的内容
     */
    public static void saveStrToSpf(String key, String value) {
        SharedPreferences.Editor editor = getSpf().edit();

        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 从默认的SharedPreferences文件 取String数据
     *
     * @param key
     * @return   没有对应的key 默认返回的""
     */
    public static String getSpfSaveStr(String key) {

        return getSpf().getString(key, "");
    }

    /**
     * 向默认的SharedPreferences文件保存int内容
     *
     * @param key   保存的键
     * @param value 保存的内容
     */
    public static void saveIntToSpf(String key, int value) {
        SharedPreferences.Editor editor = getSpf().edit();

        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 从默认的SharedPreferences文件 取int数据
     *
     * @param key
     * @return   没有对应的key  默认返回-1值
     */
    public static int getSpfSaveInt(String key) {

        return getSpf().getInt(key, -1);
    }

    /**
     * 向默认的SharedPreferences文件保存boolean内容
     * @param key
     * @param value
     */
    public static void saveBooleanToSpf(String key, boolean value){
        SharedPreferences.Editor editor = getSpf().edit();

        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 从默认的SharedPreferences文件 boolean内容
     *
     * @param key
     * @return      没有对应的key 默认返回false
     */
    public static boolean getSpfSaveBoolean(String key) {

        return getSpf().getBoolean(key, false);
    }

}
