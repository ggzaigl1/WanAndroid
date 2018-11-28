package com.example.gab.babylove.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 初夏小溪
 * data :2018/11/28 0028 10:39
 * app:layout_behavior="com.example.gab.babylove.widget.ScaleDownShowBehavior"
 */
public class ScaleDownShowBehavior extends FloatingActionButton.Behavior {

    public ScaleDownShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        // 判断如果是垂直滚动则返回true
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        // 如果向上滑动 则隐藏
        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            child.hide();
        } else if (dyConsumed < 0  && child.getVisibility() != View.VISIBLE) {
            // 向下则显示
            child.show();
        }
    }
}
