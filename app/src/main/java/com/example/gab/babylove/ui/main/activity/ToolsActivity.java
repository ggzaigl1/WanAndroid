package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.utils.CleanMessageUtil;
import com.example.gab.babylove.utils.Util;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.statusbar.MdStatusBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/20 0020.
 * 工具栏
 */

public class ToolsActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.tv_cache_size)
    TextView tv_cache_size;
    private static String DOWNLOAD_URL = "http://migmkt.qq.com/g/myapp/yyb-common.html?ADTAG=buy.bd.yyb01";//应用宝下载地址
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what == 0) {
                //在主线程中需要执行的操作，一般是UI操作
                tv_cache_size.setText(R.string.zero_k);
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

    //    @StatusBar(statusColor = R.color.statusBar, navColor = R.color.statusBar)
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


    @OnClick({R.id.Ll_cache_clear, R.id.tv_praise})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //给个好评
            case R.id.tv_praise:
//                startMarket();
                GetUpdate();
                break;
            //清除缓存
            case R.id.Ll_cache_clear:
//                JumpUtils.jump(this, SurfaceActivity.class, null);
                GetCache(this);
                break;
//            //SelectorButton
//            case R.id.Ll_Button:
//                JumpUtils.jump(this, SelectorButtonActivity.class, null);
//                break;
            default:
                break;
        }
    }

    @Override
    public void reTry() {

    }

    /**
     * 清除缓存
     *
     * @param activity
     */
    private void GetCache(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(R.string.system_title)
                .setMessage(R.string.tools_clear_cache)
                .setCancelable(false)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> new Thread(() -> {
                    CleanMessageUtil.clearAllCache(getApplicationContext());
                    mHandler.sendEmptyMessage(0);
                }).start()).setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss());
        builder.show();
    }

    /**
     * 去评价
     */
    private void GetUpdate() {
        //        主流应用商店对应的包名如下:
//        com.qihoo.appstore360手机助手
//        com.taobao.appcenter淘宝手机助手
//        com.tencent.android.qqdownloader应用宝
//        com.hiapk.marketpho 安卓市场
//        cn.goapk.market 安智市场

        //判断应用市场是否存在
        if (Util.isAvilible(this, "com.xiaomi.market")) {
            //存在进入应用市场
            Util.launchAppDetail(this, "com.example.gab.babylove", "com.xiaomi.market");
        } else {
            //不存在提示用户安装应用市场
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.system_title)).setMessage("您没有安装应用宝,是否安装应用宝?");
            builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Uri uri = Uri.parse(DOWNLOAD_URL);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    dialog.dismiss();
                }
            }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
    }
}
