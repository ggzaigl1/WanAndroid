package com.ggz.baselibrary.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ggz.baselibrary.R;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.statuslayout.LoadSirUtil;
import com.ggz.baselibrary.statuslayout.StatusLayout;
import com.ggz.baselibrary.statuslayout.StatusLayoutManager;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.L;
import com.ggz.baselibrary.utils.LogUtils;
import com.ggz.baselibrary.utils.ResourceUtils;
import com.ggz.baselibrary.utils.os.OSUtils;

import butterknife.ButterKnife;
import io.reactivex.subjects.BehaviorSubject;

/**
 * activity 生命周期回调 (api 14+)
 * 注意：使用本框架 activity 与 activity 之间传递数据 统一使用 Bundle
 *
 * @author fangs
 * @date 2017/5/18
 */
public class BaseActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    public static String TAG = "ActivityCallbacks";

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtils.d(TAG, "Create()");
        //是华为手机则 执行
        if (OSUtils.getRomType() == OSUtils.EMUI && onCheck(activity)) {
            activity.finish();
            return;
        }

        BaseActivityBean activityBean = new BaseActivityBean();
        IBaseActivity act = null;
        if (activity instanceof IBaseActivity) {
            act = (IBaseActivity) activity;
            if (act.setView() != 0) {
                activity.setContentView(R.layout.activity_base);
                LinearLayout linearLRoot = activity.findViewById(R.id.linearLRoot);

                if (act.isShowHeadView()) {
                    initHead(activity);
                }

                View view = LayoutInflater.from(activity).inflate(act.setView(), null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -1);
                linearLRoot.addView(view, params);

            }
            act.setStatusBar(activity);
        }

//                注册屏幕旋转监听
        if (ConstantUtils.isOrientation) {
            BaseOrientationListener orientoinListener = new BaseOrientationListener(activity);
            boolean autoRotateOn = (android.provider.Settings.System.getInt(activity.getContentResolver(),
                    Settings.System.ACCELEROMETER_ROTATION, 0) == 1);

            //检查系统是否开启自动旋转
            if (autoRotateOn) {
                orientoinListener.enable();
            }

            activityBean.setOrientoinListener(orientoinListener);
        }

        //设置 黄油刀 简化 Android 样板代码
        activityBean.setUnbinder(ButterKnife.bind(activity));

        //设置 activity 多状态布局
        if (activity instanceof StatusLayout.OnSetStatusView) {
            StatusLayout.OnSetStatusView setStatusView = (StatusLayout.OnSetStatusView) activity;
            StatusLayoutManager slManager = LoadSirUtil.initStatusLayout(activity, setStatusView.setStatusView());
            if (null != slManager) {
                activityBean.setSlManager(slManager);
            }
        }

        activityBean.setSubject(BehaviorSubject.create());
        activity.getIntent().putExtra("ActivityBean", activityBean);

        //基础配置 执行完成，再执行 初始化 activity 操作
        if (null != act) {
            act.initData(activity, savedInstanceState);
        }
    }


    @Override
    public void onActivityStarted(Activity activity) {
        L.e(TAG, activity.getClass().getName() + "--Start()");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        L.e(TAG, activity.getClass().getName() + "--Resume()");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        L.e(TAG, activity.getClass().getName() + "--Pause()");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        L.e(TAG, activity.getClass().getName() + "--Stop()");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        L.e(TAG, activity.getClass().getName() + "--SaveInstanceState()");
    }


    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtils.d(TAG, "Destroy()");

        BaseActivityBean activityBean = (BaseActivityBean) activity.getIntent()
                .getSerializableExtra("ActivityBean");

        if (null != activityBean) {
            //解绑定 黄油刀
            if (null != activityBean.getUnbinder()) {
                activityBean.getUnbinder().unbind();
            }
            //销毁 屏幕旋转监听
            if (null != activityBean.getOrientoinListener()) {
                activityBean.getOrientoinListener().disable();
            }
            if (null != activityBean.getSubject()) {
                activityBean.getSubject().onNext(ConstantUtils.DESTROY);
            }
        }
    }

    /**
     * 初始化 toolbar
     *
     * @param activity
     */
    private void initHead(Activity activity) {
        ViewStub vStubTitleBar = activity.findViewById(R.id.vStubTitleBar);
        vStubTitleBar.inflate();
        //这里全局给Activity设置toolbar和title mate
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        if (ConfigUtils.isTitleCenter()) {
            toolbar.setTitle("");
            TextView toolbarTitle = activity.findViewById(R.id.toolbarTitle);
            toolbarTitle.setText(activity.getTitle());
            toolbarTitle.setTextColor(ResourceUtils.getColor(ConfigUtils.getTitleColor()));
            toolbarTitle.setVisibility(View.VISIBLE);
        } else {
            toolbar.setTitle(activity.getTitle());
        }

        if (activity instanceof AppCompatActivity) {
            AppCompatActivity act = (AppCompatActivity) activity;
            //设置导航图标要在setSupportActionBar方法之后
            act.setSupportActionBar(toolbar);
            //在Toolbar左边显示一个返回按钮
            act.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //替换toolbar 自带的返回按钮
            if (ConfigUtils.getBackImg() > 0) {
                toolbar.setNavigationIcon(ConfigUtils.getBackImg());
            }
            //设置返回按钮监听事件
            toolbar.setNavigationOnClickListener(v -> JumpUtils.exitActivity(act));
            if (ConfigUtils.getBgColor() > 0) {
                toolbar.setBackgroundColor(ResourceUtils.getColor(ConfigUtils.getBgColor()));
            }
        }
    }

    private boolean onCheck(Activity activity) {
        boolean isRun;
        ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);

        if (info.numRunning == 1 && !"com.fy.baselibrary.startactivity.StartActivity".equals(info.topActivity.getClassName())) {
            //被杀死重启
            isRun = true;
            L.e(TAG, activity.getClass().getName() + "关闭此界面");
        } else {
            //手动启动
            isRun = false;
        }
        return isRun;
    }

}
