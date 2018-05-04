package com.fy.baselibrary.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.fy.baselibrary.R;

import java.util.List;

/**
 * 属性动画 工具类
 * Created by fangs on 2017/10/30.
 */
public class AnimUtils {
    public static final String TAG = "CircleMenu";
    public static final int radius1 = 500;

    /**
     * 设置fragment 转场动画
     *
     * @param fragmentTransaction
     * @param currentIndex        当前显示的fragment的下标
     * @param position            将要显示的fragment的下标
     */
    public static void setFragmentTransition(FragmentTransaction fragmentTransaction,
                                             int currentIndex, int position) {
        //设置自定义过场动画
        if (currentIndex > position) {
            fragmentTransaction.setCustomAnimations(
                    R.anim.anim_slide_right_in,
                    R.anim.anim_slide_right_out);
        } else if (currentIndex < position){
            fragmentTransaction.setCustomAnimations(
                    R.anim.anim_slide_left_in,
                    R.anim.anim_slide_left_out);
        }
    }

    /**
     * 箭头的动画
     */
    public static void doArrowAnim(View iv_arrow, boolean isExpand) {
        if (isExpand) {
            // 当前是收起，箭头由上变为下
            ObjectAnimator.ofFloat(iv_arrow, "rotation", -180, 0).start();
        } else {
            // 当前是展开，箭头由下变为上
            ObjectAnimator.ofFloat(iv_arrow, "rotation", 0, 180).start();
        }
    }

    /**
     * 关闭扇形菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static void closeSectorMenu(List<RadioButton> buttonList) {
        for (int i = 0; i < buttonList.size(); i++) {
            PointF point = new PointF();
            int avgAngle = (180 / (buttonList.size() + 1));
            int angle = avgAngle * (i + 1);
            Log.d(TAG, "angle=" + angle);
            point.x = (float) Math.cos(angle * (Math.PI / 180)) * radius1;
            point.y = (float) -Math.sin(angle * (Math.PI / 180)) * radius1;
            Log.d(TAG, point.toString());

            ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(buttonList.get(i), "translationX", point.x, 0);
            ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(buttonList.get(i), "translationY", point.y, 0);
            ObjectAnimator objectAnimatorA = ObjectAnimator.ofFloat(buttonList.get(i), "alpha", 1, 0);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            animatorSet.play(objectAnimatorX).with(objectAnimatorY).with(objectAnimatorA);
            animatorSet.start();
        }
    }

    /**
     * 显示半圆弧 菜单
     * @param buttonList
     */
    public static void showSemicircleMenu(List<RadioButton> buttonList) {
        for (int i = 0; i < buttonList.size(); i++) {
            PointF point = new PointF();
            int avgAngle = (180 / (buttonList.size() + 1));
            int angle = avgAngle * (i + 1);
            Log.d(TAG, "angle=" + angle);
            point.x = (float) Math.cos(angle * (Math.PI / 180)) * radius1;
            point.y = (float) -Math.sin(angle * (Math.PI / 180)) * radius1;
            Log.d(TAG, point.toString());

            ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(buttonList.get(i), "translationX", 0, point.x);
            ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(buttonList.get(i), "translationY", 0, point.y);
            ObjectAnimator objectAnimatorA = ObjectAnimator.ofFloat(buttonList.get(i), "alpha", 0, 1);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            animatorSet.play(objectAnimatorX).with(objectAnimatorY).with(objectAnimatorA);
            animatorSet.start();
        }
    }
}
