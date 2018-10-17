package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.demi.FingerprinActivity;
import com.example.gab.babylove.utils.CleanMessageUtil;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.ggz.baselibrary.utils.JumpUtils;

import java.util.ArrayList;
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


    @OnClick({R.id.Ll_cache_clear, R.id.tv_praise, R.id.Ll_stroke})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //给个好评
            case R.id.tv_praise:
//                startMarket();
                Update();
                break;
            //清除缓存
            case R.id.Ll_cache_clear:
//                JumpUtils.jump(this, SurfaceActivity.class, null);
                Cache(this);
                break;
            //指纹相关
            case R.id.Ll_stroke:
                JumpUtils.jump(this, FingerprinActivity.class, null);
                break;
//            //SelectorButton
//            case R.id.Ll_Button:
//                JumpUtils.jump(this, SelectorButtonActivity.class, null);
//                break;
//            //FileBrowsing
//            case R.id.Ll_personal_center:
//                JumpUtils.jump(this, FileBrowsingActivity.class, null);
//                break;
            default:
                break;
        }
    }

    @Override
    public void reTry() {

    }

    private void Cache(Activity activity) {
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

    private void Update(){
        //        主流应用商店对应的包名如下:
//        com.qihoo.appstore360手机助手
//        com.taobao.appcenter淘宝手机助手
//        com.tencent.android.qqdownloader应用宝
//        com.hiapk.marketpho 安卓市场
//        cn.goapk.market 安智市场

        //判断应用市场是否存在
        if (isAvilible(this, "com.xiaomi.market")) {
            //存在进入应用市场
            launchAppDetail("com.example.gab.babylove", "com.xiaomi.market");
        } else {
            //不存在提示用户安装应用市场
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示").setMessage("您没有安装应用宝,是否安装应用宝?");
            builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Uri uri = Uri.parse(DOWNLOAD_URL);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    dialog.dismiss();
                }
            }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
    }
    /**
     * 判断市场是否存在的方法
     *
     * @param context
     * @param packageName 应用市场包名
     * @return true or false
     */
    public static boolean isAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();//获取packagemanager
        List<PackageInfo> packageInfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息
        List NameList = new ArrayList();//用于存储所有已安装程序的包名
        //从packageInfo中取出包名,放入NameList中
        if (packageInfo != null) {
            for (int i = 0; i < packageInfo.size(); i++) {
                String pn = packageInfo.get(i).packageName;
                NameList.add(pn);
            }
        }
        return NameList.contains(packageName);//判断pName中是否有目标程序的包名,有TRUE,没有FALSE
    }

    /**
     * 跳转到app详情界面
     *
     * @param appPkg    App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面,某些应用商店可能会失败
     */
    public void launchAppDetail(String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg))
                intent.setPackage(marketPkg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
