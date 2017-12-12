package com.fy.baselibrary.base.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * popupWindow 控制器
 * Created by fangs on 2017/6/15.
 */
public class PopupController {

    private Context context;
    private PopupWindow popupWindow;
    private int layoutResId;//布局id
    View mPopupView;//弹窗布局View
    private View mView;
    CommonPopupWindow.PopDismissListner listner;

    PopupController(Context context, PopupWindow popupWindow) {
        this.context = context;
        this.popupWindow = popupWindow;
    }

    public void setView(int layoutResId) {
        mView = null;
        this.layoutResId = layoutResId;
        installContent();
    }

    public void setView(View view) {
        mView = view;
        this.layoutResId = 0;
        installContent();
    }

    private void installContent() {
        if (layoutResId != 0) {
            mPopupView = LayoutInflater.from(context).inflate(layoutResId, null);
        } else if (mView != null) {
            mPopupView = mView;
        }
        popupWindow.setContentView(mPopupView);
    }

    /**
     * 设置宽度
     *
     * @param width  宽
     * @param height 高
     */
    private void setWidthAndHeight(int width, int height) {
        if (width == 0 || height == 0) {
            //如果没设置宽高，默认是WRAP_CONTENT
            popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            popupWindow.setWidth(width);
            popupWindow.setHeight(height);
        }
    }


    /**
     * 设置背景灰色程度
     *
     * @param level 0.0f-1.0f
     */
    void setBackGroundLevel(float level) {
        Window mWindow = ((Activity) context).getWindow();
        WindowManager.LayoutParams params = mWindow.getAttributes();
        params.alpha = level;
        mWindow.setAttributes(params);
    }


    /**
     * 设置动画
     */
    private void setAnimationStyle(int animationStyle) {
        popupWindow.setAnimationStyle(animationStyle);
    }

    /**
     * 设置Outside是否可点击
     *
     * @param touchable 是否可点击
     */
    private void setOutsideTouchable(boolean touchable) {
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));//设置透明背景
        popupWindow.setOutsideTouchable(touchable);//设置outside可点击
        popupWindow.setFocusable(touchable);
    }

    /**
     * 设置 窗口 dismiss 监听
     * @param listner
     */
    public void setListner(CommonPopupWindow.PopDismissListner listner) {
        this.listner = listner;
    }

    static class PopupParams {
        Context mContext;
        int layoutResId;//布局id
        int mWidth, mHeight;//弹窗的宽和高
        boolean isShowBg, isShowAnim;
        float bg_level;//屏幕背景灰色程度
        int animationStyle;//动画Id
        View mView;
        boolean isTouchable = true;
        CommonPopupWindow.PopDismissListner listner;

        PopupParams(Context mContext) {
            this.mContext = mContext;
        }

        void apply(PopupController controller) {
            if (mView != null) {
                controller.setView(mView);
            } else if (layoutResId != 0) {
                controller.setView(layoutResId);
            } else {
                throw new IllegalArgumentException("PopupView's contentView is null");
            }
            controller.setWidthAndHeight(mWidth, mHeight);
            controller.setOutsideTouchable(isTouchable);//设置outside可点击
            if (isShowBg) {
                //设置背景
                controller.setBackGroundLevel(bg_level);
            }
            if (isShowAnim) {
                controller.setAnimationStyle(animationStyle);
            }

            if (null != listner){
                controller.setListner(listner);
            }
        }
    }
}
