package com.fy.baselibrary.utils;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.fy.baselibrary.application.BaseApplication;

/**
 * 通过 getResources() 为控件 设置内容
 * Created by fangs on 2017/9/13.
 */
public class ResourceUtils {

    private ResourceUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

//    %d   （表示整数）
//    %f   （表示浮点数）
//    %s   （表示字符串）
    /**
     * %1$s Android string （java & Android 格式化字符串）
     * @param tv
     * @param id 资源ID（如：ID内容为 “病人ID：%1$s”）
     * @param replaceStr 将要替换的内容
     */
    public static void setText(TextView tv, int id, String replaceStr){
        String format = BaseApplication.getApplication().getResources().getString(id);
        String text = String.format(format, replaceStr);
        tv.setText(text);
    }

    /**
     * %1$d Android string （java & Android 格式化字符串）
     * @param tv
     * @param id 资源ID（如：ID内容为 “病人ID：%1$d”）
     * @param replaceStr 将要替换的内容
     */
    public static void setText(TextView tv, int id, int replaceStr){
        String format = BaseApplication.getApplication().getResources().getString(id);
        String text = String.format(format, replaceStr);
        tv.setText(text);
    }

    /**
     * 设置TextView的drawableLeft、drawableRight、drawableTop、drawableBottom
     * @param tv
     * @param id
     * @param position 设置的位置
     */
    public static void setCompoundDrawable(TextView tv, int id, int position) {
        Drawable drawable = BaseApplication.getApplication()
                .getResources()
                .getDrawable(id);

        drawable.setBounds(0, 0,
                drawable.getMinimumWidth(), drawable.getMinimumHeight());

        switch (position) {
            case 1:
                tv.setCompoundDrawables(drawable, null, null, null);
                break;
            case 2:
                tv.setCompoundDrawables(null, drawable, null, null);
                break;
            case 3:
                tv.setCompoundDrawables(null, null, drawable, null);
                break;
            case 4:
                tv.setCompoundDrawables(null, null, null, drawable);
                break;
        }
    }

    /**
     * 动态设置 TextView 颜色 (getResources().getColor 过时 替代方式)
     * @param colorId
     * @return
     */
    public static void setTvColor(TextView tv, int colorId){
        tv.setTextColor(ContextCompat.getColor(BaseApplication.getApplication(), colorId));
    }
}
