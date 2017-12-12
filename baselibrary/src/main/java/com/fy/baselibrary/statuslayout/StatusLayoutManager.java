package com.fy.baselibrary.statuslayout;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewStub;

/**
 * 多状态视图 管理类
 * Created by chenpengfei on 2016/12/15.
 */
public class StatusLayoutManager {

     final Context context;
     final ViewStub netWorkErrorVs;
     final int netWorkErrorRetryViewId;
     final ViewStub emptyDataVs;
     final int emptyDataRetryViewId;
     final ViewStub errorVs;
     final int errorRetryViewId;
     final int loadingLayoutResId;
     final int contentLayoutResId;
     final int retryViewId;

     final RootFrameLayout rootFrameLayout;
     final OnShowHideViewListener onShowHideViewListener;
     final OnRetryListener onRetryListener;

    public StatusLayoutManager(Builder builder) {
        this.context = builder.context;
        this.loadingLayoutResId = builder.loadingLayoutResId;
        this.netWorkErrorVs = builder.netWorkErrorVs;
        this.netWorkErrorRetryViewId = builder.netWorkErrorRetryViewId;
        this.emptyDataVs = builder.emptyDataVs;
        this.emptyDataRetryViewId = builder.emptyDataRetryViewId;
        this.errorVs = builder.errorVs;
        this.errorRetryViewId = builder.errorRetryViewId;
        this.contentLayoutResId = builder.contentLayoutResId;
        this.onShowHideViewListener = builder.onShowHideViewListener;
        this.retryViewId = builder.retryViewId;
        this.onRetryListener = builder.onRetryListener;

        this.rootFrameLayout = builder.rootFrameLayout;

        rootFrameLayout.setStatusLayoutManager(this);
    }


    /**
     *  显示loading
     */
    public void showLoading() {
        rootFrameLayout.showLoading();
    }

    /**
     *  显示内容
     */
    public void showContent() {
        rootFrameLayout.showContent();
    }

    /**
     *  显示空数据
     */
    public void showEmptyData() {
        rootFrameLayout.showEmptyData();
    }

    /**
     *  显示网络异常
     */
    public void showNetWorkError() {
        rootFrameLayout.showNetWorkError();
    }

    /**
     *  显示异常
     */
    public void showError() {
        rootFrameLayout.showError();
    }

    /**
     *  得到root 布局
     */
    public View getRootLayout() {
        return rootFrameLayout;
    }



    public static final class Builder {

        private Context context;
        private RootFrameLayout rootFrameLayout;

        private int loadingLayoutResId;//加载页 布局文件ID
        private int contentLayoutResId;//正常页 布局文件ID

        private ViewStub netWorkErrorVs;//网络错误
        private int netWorkErrorRetryViewId;//网络错误 布局文件ID

        private ViewStub emptyDataVs;//空数据
        private int emptyDataRetryViewId;//空数据 布局文件ID

        private ViewStub errorVs;//请求错误（失败）
        private int errorRetryViewId;// 请求错误（失败）布局文件ID

        private int retryViewId;//请求错误或网络错误时候 的刷新按钮

        private OnShowHideViewListener onShowHideViewListener;
        private OnRetryListener onRetryListener;

        public Builder(Context context) {
            this.context = context;
        }

        /** 设置 顶级布局 */
        public Builder setRootLayout(RootFrameLayout rootFrameLayout){
            this.rootFrameLayout = rootFrameLayout;
            return this;
        }

        public Builder loadingView(@LayoutRes int loadingLayoutResId) {
            this.loadingLayoutResId = loadingLayoutResId;
            return this;
        }

        public Builder netWorkErrorView(@LayoutRes int newWorkErrorId) {
            netWorkErrorVs = new ViewStub(context);
            netWorkErrorVs.setLayoutResource(newWorkErrorId);
            return this;
        }

        public Builder emptyDataView(@LayoutRes int noDataViewId) {
            emptyDataVs = new ViewStub(context);
            emptyDataVs.setLayoutResource(noDataViewId);
            return this;
        }

        public Builder errorView(@LayoutRes int errorViewId) {
            errorVs = new ViewStub(context);
            errorVs.setLayoutResource(errorViewId);
            return this;
        }

        public Builder contentView(@LayoutRes int contentLayoutResId) {
            this.contentLayoutResId = contentLayoutResId;
            return this;
        }

        public Builder netWorkErrorRetryViewId(int netWorkErrorRetryViewId) {
            this.netWorkErrorRetryViewId = netWorkErrorRetryViewId;
            return this;
        }

        public Builder emptyDataRetryViewId(int emptyDataRetryViewId) {
            this.emptyDataRetryViewId = emptyDataRetryViewId;
            return this;
        }

        public Builder errorRetryViewId(int errorRetryViewId) {
            this.errorRetryViewId = errorRetryViewId;
            return this;
        }

        /**
         * 设置出现错误时 显示界面的 刷新按钮view id
         * @param retryViewId
         * @return
         */
        public Builder retryViewId(int retryViewId) {
            this.retryViewId = retryViewId;
            return this;
        }

        public Builder onShowHideViewListener(OnShowHideViewListener onShowHideViewListener) {
            this.onShowHideViewListener = onShowHideViewListener;
            return this;
        }

        public Builder onRetryListener(OnRetryListener onRetryListener) {
            this.onRetryListener = onRetryListener;
            return this;
        }

        /**
         * 返回 多状态视图 管理类
         * @return
         */
        public StatusLayoutManager build() {
            return new StatusLayoutManager(this);
        }
    }

    public static Builder newBuilder(Context context) {
       return new Builder(context);
    }

}
