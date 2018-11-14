package com.ggz.baselibrary.statuslayout;

import android.view.View;

/**
 * 定义 多布局 相关接口
 * Created by fangs on 2017/12/15.
 */
public interface StatusLayout {

    /**
     * 设置 多布局 显示的 区域
     */
    interface OnSetStatusView {
        /**
         * 设置 多状态视图显示的 区域(内容视图，必须有父视图)
         * @return
         */
        View setStatusView();
    }

    /**
     * 多布局 重试加载按钮监听事件
     */
    interface OnRetryListener {

        /**
         * 多布局 点击重试
         */
        void onRetry();
    }

}
