package com.fy.baselibrary.base.popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

import com.fy.baselibrary.utils.DensityUtils;

/**
 * 公共 popupWindow
 * Created by fangs on 2017/6/15.
 */
public class CommonPopupWindow extends PopupWindow {

    final PopupController controller;

    private CommonPopupWindow(Context context) {
        controller = new PopupController(context, this);
    }

    @Override
    public int getWidth() {
        return controller.mPopupView.getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return controller.mPopupView.getMeasuredHeight();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (null != controller.listner){
            controller.listner.onDismiss();
        }
    }



    /** 弹出关闭监听 接口 */
    public interface PopDismissListner{
        void onDismiss();
    }

    public interface ViewInterface {
        void getChildView(View view, int layoutResId);
    }



    public static class Builder {
        private final PopupController.PopupParams params;
        private ViewInterface listener;

        public Builder(Context context) {
            params = new PopupController.PopupParams(context);
        }

        /**
         * @param layoutResId 设置PopupWindow 布局ID
         * @return Builder
         */
        public Builder setView(int layoutResId) {
            params.mView = null;
            params.layoutResId = layoutResId;
            return this;
        }

        /**
         * @param view 设置PopupWindow布局
         * @return Builder
         */
        public Builder setView(View view) {
            params.mView = view;
            params.layoutResId = 0;
            return this;
        }

        /**
         * 设置子View
         *
         * @param listener ViewInterface
         * @return Builder
         */
        public Builder setViewOnclickListener(ViewInterface listener) {
            this.listener = listener;
            return this;
        }

        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content
         *
         * @param width 宽
         * @return Builder
         */
        public Builder setWidthAndHeight(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }

        /**
         * 设置背景灰色程度
         *
         * @param level 0.0f-1.0f
         * @return Builder
         */
        public Builder setBackGroundLevel(float level) {
            params.isShowBg = true;
            params.bg_level = level;
            return this;
        }

        /**
         * 是否可点击Outside消失
         *
         * @param touchable 是否可点击
         * @return Builder
         */
        public Builder setOutsideTouchable(boolean touchable) {
            params.isTouchable = touchable;
            return this;
        }

        /**
         * 设置动画
         *
         * @return Builder
         */
        public Builder setAnimationStyle(int animationStyle) {
            params.isShowAnim = true;
            params.animationStyle = animationStyle;
            return this;
        }

        /**
         * 设置 窗口 dismiss 监听
         * @param listener
         * @return
         */
        public Builder setListener(PopDismissListner listener) {
            params.listner = listener;
            return this;
        }

        public CommonPopupWindow create() {
            final CommonPopupWindow popupWindow = new CommonPopupWindow(params.mContext);
            params.apply(popupWindow.controller);
            if (listener != null && params.layoutResId != 0) {
                listener.getChildView(popupWindow.controller.mPopupView, params.layoutResId);
            }
            DensityUtils.measureWidthAndHeight(popupWindow.controller.mPopupView);

            return popupWindow;
        }
    }
}
