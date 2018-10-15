package com.ggz.baselibrary.utils;

import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.ggz.baselibrary.application.BaseApp;

import java.util.Random;

/**
 * 通过 getResources() 为控件 设置内容
 * Created by fangs on 2017/9/13.
 */
public class ResourceUtils {

    private ResourceUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取资源 dimens 文件 内容
     * @param dimenId
     * @return
     */
    public static float getDimen(int dimenId){
        return BaseApp. getAppCtx().getResources().getDimension(dimenId);
    }

    /**
     * 生成随机颜色
     * @return
     */
    public static int getRandomColor(){
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return Color.rgb(r,g,b);
    }

    /**
     * 获取 strings 资源文件 指定 id 的资源
     * @param stringId
     * @return
     */
    public static String getStr(@StringRes int stringId){
        return BaseApp. getAppCtx().getResources().getString(stringId);
    }

    /**
     * 获取 colors 资源文件 指定 id 的资源 (getResources().getColor 过时 替代方式)
     * @param colorId
     * @return
     */
    public static int getColor(@ColorRes int colorId){
        return ContextCompat.getColor(BaseApp. getAppCtx(), colorId);
    }

//    %d   （表示整数）
//    %f   （表示浮点数）
//    %s   （表示字符串）
    /**
     * @param tv
     * @param id 资源ID（如：ID内容为 “病人ID：%1$s”）
     * @param replaceStr 将要替换的内容
     */
    public static void setText(TextView tv, int id, String replaceStr){
        String format = BaseApp. getAppCtx().getResources().getString(id);
        String text = String.format(format, replaceStr);
        tv.setText(text);
    }

    /**
     *
     * @param tv
     * @param id 资源ID（如：ID内容为 “病人ID：%1$d”）
     * @param replaceInt 将要替换的内容
     */
    public static void setText(TextView tv, int id, int replaceInt){
        String format = BaseApp. getAppCtx().getResources().getString(id);
        String text = String.format(format, replaceInt);
        tv.setText(text);
    }

    /**
     * 获取string资源文件 指定 id 的资源，替换后的字符串
     * @param id            资源ID（如：ID内容为 “病人ID：%1$s”）
     * @param replaceStr    将要替换的内容
     * @return 替换后的字符串
     */
    public static String getText(int id, String replaceStr){
        String format = BaseApp. getAppCtx().getResources().getString(id);

        return String.format(format, replaceStr);
    }

    /**
     * 获取string资源文件 指定 id 的资源，替换后的字符串
     * @param id            资源ID（如：ID内容为 “病人ID：%1$d”）
     * @param replaceStr    将要替换的内容
     * @return 替换后的字符串
     */
    public static String getText(int id, int replaceStr){
        String format = BaseApp. getAppCtx().getResources().getString(id);

        return String.format(format, replaceStr);
    }

    /**
     * 动态设置 TextView 颜色 (getResources().getColor 过时 替代方式)
     * @param colorId
     */
    public static void setTvColor(TextView tv, int colorId){
        tv.setTextColor(ContextCompat.getColor(BaseApp. getAppCtx(), colorId));
    }


    /**
     * %d   （表示整数）
     * %f   （表示浮点数）
     * %s   （表示字符串）<br>
     * 获取 strings 资源文件，指定 id 的资源，替换后的字符串
     * @param id      资源ID（如：ID内容为 “病人ID：%1$d”）
     * @param args    将要替换的内容
     * @return 替换后的字符串
     */
    public static String getReplaceStr(@StringRes int id, Object... args){
        String format = getStr(id);
        return String.format(format, args);
    }
}
