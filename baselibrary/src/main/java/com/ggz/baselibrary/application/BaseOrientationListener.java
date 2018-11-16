package com.ggz.baselibrary.application;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.OrientationEventListener;

import com.ggz.baselibrary.utils.LogUtils;


/**
 * 监听系统 屏幕方向
 *
 * @author fangs
 * @date 2017/11/16
 */
public class BaseOrientationListener extends OrientationEventListener {

    public static final String TAG = "activity";
    private Activity context;

    public BaseOrientationListener(Activity context) {
        super(context);
        this.context = context;
    }

    public BaseOrientationListener(Context context, int rate) {
        super(context, rate);
    }

    @Override
    public void onOrientationChanged(int orientation) {
        LogUtils.d(TAG, "orention" + orientation);
        int screenOrientation = context.getResources().getConfiguration().orientation;
        //设置竖屏
        if (((orientation >= 0) && (orientation < 45)) || (orientation > 315)) {
            if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT && orientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
                context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
            //设置横屏
        } else if (orientation > 225 && orientation < 315) {
            if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            // 设置反向横屏
        } else if (orientation > 45 && orientation < 135) {
            if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
            }
        } else if (orientation > 135 && orientation < 225) {
            if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
                context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
            }
        }
    }
}
