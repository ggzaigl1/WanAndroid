package com.fy.baselibrary.application;

import android.app.Application;

import com.fy.baselibrary.widget.GlideImageLoader;
import com.lzy.ninegrid.NineGridView;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * 基础 application
 * Created by fangs on 2017/5/5.
 */
public class BaseApp extends Application {

    private static BaseApp mApplication; // 单例模式

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "afb9b266b0", false);

        mApplication = this;
//        设置activity 生命周期回调
        registerActivityLifecycleCallbacks(new BaseActivityLifecycleCallbacks());

//        bugly
        CrashReport.initCrashReport(getApplicationContext(), "b51864b95a", false);

        NineGridView.setImageLoader(new GlideImageLoader());
    }


    /**
     * 单例模式，获取BTApplication的实例
     *
     * @return
     */
    public static BaseApp getAppCtx() {
        return mApplication;
    }


    //    //设置 使用指定的 Header 和 Footer 全局设置
//    static {
//        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
//            @NonNull
//            @Override
//            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//
//                return new ClassicsHeader(context);
//            }
//        });
//        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
//            @NonNull
//            @Override
//            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
//                return new ClassicsFooter(context);
//            }
//        });
//    }
}
