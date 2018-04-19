package com.example.gab.babylove.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.tbs.FileBrowsingActivity;
import com.example.gab.babylove.utils.CleanMessageUtil;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/20 0020.
 * 工具栏
 */

public class ToolsActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.tv_cache_size)
    TextView tv_cache_size;

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what == 0) {
                //在主线程中需要执行的操作，一般是UI操作
                tv_cache_size.setText("0K");
            }
        }
    };

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_tools;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {

        try {
            tv_cache_size.setText(CleanMessageUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.night_switch, R.id.Ll_cache, R.id.Ll_praise, R.id.Ll_phoneUtils, R.id.Ll_stroke,R.id.Ll_Button})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //夜间模式
            case R.id.night_switch:
                Switch sw = (Switch) view;
                boolean isChecked = sw.isChecked();
                if (isChecked) {
                    T.showShort("开启夜间模式");
                } else {
                    T.showShort("关闭夜间模式");
                }
                break;
            //给个好评
            case R.id.Ll_praise:
                startMarket();
                break;
            //手机相关信息
            case R.id.Ll_phoneUtils:
                JumpUtils.jump(this, PhoneUtilsActivity.class, null);
                break;
            //清除缓存
            case R.id.Ll_cache:
                Cache(this);
                break;
            //指纹相关
            case R.id.Ll_stroke:
                JumpUtils.jump(this, FingerprintMainActivity.class, null);
                break;
            //指纹相关
            case R.id.Ll_Button:
                JumpUtils.jump(this, SelectorButtonActivity.class, null);
                break;
            //指纹相关
            case R.id.Ll_personal_center:
                JumpUtils.jump(this, FileBrowsingActivity.class, null);
                break;
        }
    }

    @Override
    public void reTry() {

    }

    private void Cache(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle("确认清除")
                .setMessage("是否清除缓存")
                .setCancelable(false)
                .setNegativeButton("确定", (dialogInterface, i) -> new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CleanMessageUtil.clearAllCache(getApplicationContext());
                        mHandler.sendEmptyMessage(0);
                    }
                }).start()).setPositiveButton("取消", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.show();
    }

    public void startMarket() {
        Uri uri = Uri.parse(String.format("market://details?id=%s", getPackageName()));
        if (isIntentSafe(this, uri)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            T.showShort("无法打开应用市场");
        }
    }

    public static boolean isIntentSafe(Activity activity, Uri uri) {
        Intent mapCall = new Intent(Intent.ACTION_VIEW, uri);
        PackageManager packageManager = activity.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mapCall, 0);
        return activities.size() > 0;
    }

}
