package com.example.gab.babylove.ui.main.other;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.gab.babylove.R;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.utils.CleanMessageUtil;
import com.example.gab.babylove.utils.Util;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Gab
 * @date 2017/12/20 0020
 * 设置
 */

public class ToolsActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.tv_cache_size)
    TextView mTvCacheSize;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.Ll_tools)
    LinearLayout mLinearLayoutTools;


    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new MyHandler(this);

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_tools;
    }

    //    @StatusBarLollipop(statusColor = R.color.statusBar, navColor = R.color.statusBar)
    @Override
    public void setStatusBar(Activity activity) {
//        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
//        StatusBar.setStatusBarColor(activity, ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        try {
            mTvCacheSize.setText(CleanMessageUtil.getTotalCacheSize(this));
            mTvVersion.setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.Ll_cache_clear, R.id.Ll_praise, R.id.Ll_check_update, R.id.Ll_check_test})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //清除缓存
            case R.id.Ll_cache_clear:
                getCache();
                break;
            //给个好评
            case R.id.Ll_praise:
                getEvaluation();
                break;
//            //SelectorButton
            //版本更新
            case R.id.Ll_check_update:
                getVersionUpdate(1);
                break;
//            //搜索
            case R.id.Ll_check_test:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//                JumpUtils.jumpFade(this, SurfaceActivity.class, null);
                break;
            default:
                break;
        }
    }

    /**
     * 清除缓存
     */
    private void getCache() {
        new MaterialDialog.Builder(this)
                .title(R.string.system_title)
                .cancelable(false)
                .content(R.string.tools_clear_cache)
                .positiveText(R.string.ok).onPositive((dialog, which) -> new Thread(() -> {
                    CleanMessageUtil.clearAllCache(ConfigUtils.getAppCtx());
                    mHandler.sendEmptyMessage(0);
                }).start()).negativeText(R.string.cancel).onNegative((dialog, which) -> dialog.dismiss()).show();
    }

    /**
     * 去评价
     */
    private void getEvaluation() {
        //判断应用市场是否存在
        if (Util.isAvilible(this, "com.tencent.android.qqdownloader")) {
            //存在进入应用市场
            Util.launchAppDetail(this, "com.example.gab.babylove", "com.tencent.android.qqdownloader");
        } else {
            //不存在提示用户安装应用市场
            new MaterialDialog.Builder(this)
                    .title(R.string.system_title)
                    .cancelable(false)
                    .content("您没有安装应用宝,是否安装应用宝?")
                    .positiveText(getString(R.string.ok))
                    .onNegative((dialog, which) -> {
                        //应用宝下载地址
                        Uri uri = Uri.parse("http://migmkt.qq.com/g/myapp/yyb-common.html?ADTAG=buy.bd.yyb01");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        dialog.dismiss();
                    }).negativeText(getString(R.string.cancel))
                    .onPositive((dialog, which) -> dialog.dismiss()).show();
        }
    }


    /**
     * 防止Handler泄露 使用静态
     */

    @SuppressLint("HandlerLeak")
    class MyHandler extends Handler {
        WeakReference<ToolsActivity> mWeakReference;

        MyHandler(ToolsActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ToolsActivity activity = mWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        //在主线程中需要执行的操作，一般是UI操作
                        mTvCacheSize.setText(R.string.zero_k);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  如果为空,所有的回调函数和消息将被删除
        mHandler.removeCallbacksAndMessages(null);
    }
}
