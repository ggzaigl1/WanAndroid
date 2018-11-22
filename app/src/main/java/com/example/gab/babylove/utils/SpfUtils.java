package com.example.gab.babylove.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;

import java.util.Map;

/**
 * SharedPreferences 管理工具类
 *
 * @author fangs
 * @date 2017/5/18
 */
public class SpfUtils {
    private static final String SHARED_NAME = "WeYue_SP";
    private static SpfUtils sInstance;
    private static SharedPreferences sharedReadable;
    private static SharedPreferences.Editor sharedWritable;

//    创建的Preferences文件存放位置可以在Eclipse中查看：
//	  DDMS->File Explorer /<package name>/shared_prefs/setting.xml

    private static String spfFileName = "fySpf";

    private SpfUtils() {
        sharedReadable = ConfigUtils.getAppCtx().getSharedPreferences(SHARED_NAME, Context.MODE_MULTI_PROCESS);
        sharedWritable = sharedReadable.edit();

//        /* cannot be instantiated */
//        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static SpfUtils getInstance() {
        if (sInstance == null) {
            synchronized (SpfUtils.class) {
                if (sInstance == null) {
                    sInstance = new SpfUtils();
                }
            }
        }
        return sInstance;
    }

    private static SharedPreferences getSpf(){
        Context ctx = ConfigUtils.getAppCtx();
        SharedPreferences mSpf = ctx.getSharedPreferences(spfFileName, Context.MODE_PRIVATE);

        return mSpf;
    }

    public String getString(String key, String defValue) {
        return sharedReadable.getString(key, defValue);
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
        editor.apply();
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
     * 向默认的SharedPreferences文件保存int数据
     *
     * @param key   保存的键
     * @param value 保存的内容
     */
    public static void saveIntToSpf(String key, int value) {
        SharedPreferences.Editor editor = getSpf().edit();

        editor.putInt(key, value);
        editor.apply();
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
        editor.apply();
    }

    /**
     * 从默认的SharedPreferences文件获取 boolean数据
     *
     * @param key
     * @return      没有对应的key 默认返回false
     */
    public static boolean getSpfSaveBoolean(String key) {

        return getSpf().getBoolean(key, false);
    }


    /**
     * 获取所有键值对
     * @return
     */
    public Map<String, ?> getAll() {
        return getSpf().getAll();
    }

    /**
     * Return whether the sp contains the preference.
     *
     * @param key The key of sp.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public boolean contains(@NonNull final String key) {
        return getSpf().contains(key);
    }

    /**
     * 删除已存内容
     * @param key The key of sp.
     */
    public static void remove(@NonNull final String key) {
        remove(key, false);
    }

    /**
     * Remove the preference in sp.
     * @param key      The key of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     */
    public static void remove(@NonNull final String key, final boolean isCommit) {
        if (isCommit) {
            getSpf().edit().remove(key).commit();
        } else {
            getSpf().edit().remove(key).apply();
        }
    }

    /** 清除所有数据 */
    public static void clear() {
        clear(false);
    }

    /**
     * 清除所有数据
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     */
    public static void clear(final boolean isCommit) {
        if (isCommit) {
            getSpf().edit().clear().commit();
        } else {
            getSpf().edit().clear().apply();
        }
    }
}
