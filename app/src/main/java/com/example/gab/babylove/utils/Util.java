package com.example.gab.babylove.utils;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gab.babylove.R;

/**
 * 时间: 2016-08-15 19:36
 */
public class Util {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static double change(double a){
        return a * Math.PI  / 180;
    }

    public static double changeAngle(double a){
        return a * 180 / Math.PI;
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
                    View root = LayoutInflater.from(ctx).inflate(R.layout.toast_custom_common, null, false);//自定义样式，自定义布局文件
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
