package com.ggz.baselibrary.statusbarutils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 初夏小溪
 * @date 2018/11/1 0001
 */
public class StatusBar {

    /**
     * 设置状态栏颜色
     *
     * @param activity
     * @param statusColor
     */
    public static void setStatusBarColor(Activity activity, int statusColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarLollipop.setStatusBarColor(activity, statusColor);
        } else {
            StatusBarKitKat.setStatusBarColor(activity, statusColor);
        }
    }

    public static void translucentStatusBar(Activity activity) {
        translucentStatusBar(activity, false);
    }

    /**
     * 设置状态栏是否为透明
     *
     * @param activity
     * @param hideStatusBarBackground
     */
    public static void translucentStatusBar(Activity activity, boolean hideStatusBarBackground) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarLollipop.translucentStatusBar(activity, hideStatusBarBackground);
        } else {
            StatusBarKitKat.translucentStatusBar(activity);
        }
    }

    /**
     * CoordinatorLayout CollapsingToolbarLayout
     *
     * @param activity
     * @param appBarLayout
     * @param collapsingToolbarLayout
     * @param toolbar
     * @param statusColor
     */
    public static void setStatusBarColorForCollapsingToolbar
    (@NonNull Activity activity
            , AppBarLayout appBarLayout
            , CollapsingToolbarLayout collapsingToolbarLayout
            , Toolbar toolbar
            , @ColorInt int statusColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarLollipop.setStatusBarColorForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, toolbar, statusColor);
        } else {
            StatusBarKitKat.setStatusBarColorForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, toolbar, statusColor);
        }
    }

    /**
     * 需要使用 <style name="AppTheme.StatusBarWhite" parent="AppTheme/> 主题
     *
     * @param activity
     * @param color
     */
    public static void setStatusBarLightMode(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //判断是否为小米或魅族手机，如果是则将状态栏文字改为黑色
            if (MIUISetStatusBarLightMode(activity, true) || FlymeSetStatusBarLightMode(activity, true)) {
                //设置状态栏为指定颜色 //5.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.getWindow().setStatusBarColor(color);
                    //4.4
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    //调用修改状态栏颜色的方法
                    setStatusBarColor(activity, color);
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //如果是6.0以上将状态栏文字改为黑色，并设置状态栏颜色
                activity.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                activity.getWindow().setStatusBarColor(color);

                //fitsSystemWindow 为 false, 不预留系统栏位置.
                ViewGroup mContentView = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
                View mChildView = mContentView.getChildAt(0);
                if (mChildView != null) {
                    mChildView.setFitsSystemWindows(true);
                    ViewCompat.requestApplyInsets(mChildView);
                }
            }
        }
    }

    /**
     * BarLightForCollapsingToolbar 高亮
     *
     * @param activity
     * @param appBarLayout
     * @param collapsingToolbarLayout
     * @param toolbar
     * @param statusBarColor
     */
    public static void setStatusBarLightForCollapsingToolbar
    (@NonNull Activity activity
            , AppBarLayout appBarLayout
            , CollapsingToolbarLayout collapsingToolbarLayout
            , Toolbar toolbar
            , @ColorInt int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarLollipop.setStatusBarWhiteForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, toolbar, statusBarColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarKitKat.setStatusBarWhiteForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, toolbar, statusBarColor);
        }
    }


    /**
     * MIUI的沉浸支持透明白色字体和透明黑色字体
     * https://dev.mi.com/console/doc/detail?pId=1159
     */
    static boolean MIUISetStatusBarLightMode(Activity activity, boolean darkmode) {
        try {
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");

            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            Class<? extends Window> clazz = activity.getWindow().getClass();
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格，Flyme4.0以上
     */
    static boolean FlymeSetStatusBarLightMode(Activity activity, boolean darkmode) {
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class
                    .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class
                    .getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (darkmode) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            activity.getWindow().setAttributes(lp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static void setContentTopPadding(Activity activity, int padding) {
        ViewGroup mContentView = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        mContentView.setPadding(0, padding, 0, 0);
    }

    static int getPxFromDp(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
