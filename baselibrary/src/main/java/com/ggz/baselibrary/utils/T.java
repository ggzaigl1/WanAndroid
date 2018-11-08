package com.ggz.baselibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ggz.baselibrary.R;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/9/29
 *     desc  : 吐司相关工具类
 * </pre>
 */
public class T {

    /**
     * 显示toast 开关
     */
    public static boolean isShow = true;
    private static Toast toast;

    private T() {
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
        show(ResourceUtils.getStr(message), Toast.LENGTH_SHORT);
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
        show(ResourceUtils.getStr(message), Toast.LENGTH_LONG);
    }

    /**
     * 显示系统 toast
     *
     * @param message 消息
     */
    @SuppressLint("ShowToast")
    private static void show(String message, int duration) {
        if (isShow) {
            if (null == toast) {
                toast = Toast.makeText(ConfigUtils.getAppCtx(), "", duration);
            } else {
                toast.cancel();
                toast = Toast.makeText(ConfigUtils.getAppCtx(), "", duration);
            }

            toast.setText(message);
            toast.show();
        }
    }


    /**
     * 自定义 布局的Toast
     *
     * @param duration
     * @param message
     * @param tLayouId  自定义布局
     */
    public static void showQulifier(int duration, CharSequence message, @LayoutRes int tLayouId) {
        if (null == toast) {
            LayoutInflater inflate = (LayoutInflater) ConfigUtils.getAppCtx().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflate.inflate(tLayouId, null);

            toast = new Toast(ConfigUtils.getAppCtx());
            toast.setView(view);
            toast.setDuration(duration);
        } else {
            toast.setText(message);
        }

        show(message.toString(), Toast.LENGTH_LONG);
    }

    /**
     * 自定义Toast
     */
    public enum CustomToast {
        INSTANCE;// 实现单例
        private Toast mToast;
        private TextView mTvToast;

        public void showToast(Context ctx, String content) {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                Toast.makeText(ctx, content, Toast.LENGTH_SHORT).show();
            } else {
                if (mToast == null) {
                    mToast = new Toast(ctx);
                    mToast.setDuration(Toast.LENGTH_SHORT);//设置toast显示的时长
                    View root = LayoutInflater.from(ctx).inflate(R.layout.item_toast_custom_common, null, false);//自定义样式，自定义布局文件
                    mTvToast = root.findViewById(R.id.tvCustomToast);
                    mToast.setView(root);//设置自定义的view
                }
                mTvToast.setText(content);//设置文本
                mToast.show();//展示toast
            }
        }

        public void showToast(Context ctx, int stringId) {
            showToast(ctx, ctx.getString(stringId));
        }

        public void cancelToast() {
            if (mToast != null) {
                mToast.cancel();
                mToast = null;
                mTvToast = null;
            }
        }
    }
}