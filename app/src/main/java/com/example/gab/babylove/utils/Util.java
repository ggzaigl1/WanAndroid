package com.example.gab.babylove.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gab.babylove.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间: 2016-08-15 19:36
 */
@SuppressWarnings("ALL")
public class Util {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static double change(double a) {
        return a * Math.PI / 180;
    }

    public static double changeAngle(double a) {
        return a * 180 / Math.PI;
    }

    /**
     * 自定义Toast
     */
    public enum CustomToast {
        INSTANCE;// 实现单例
        private Toast mToast;
        private TextView mTvToast;

        public void showToast(Context ctx, String content) {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                Toast.makeText(ctx, content, Toast.LENGTH_SHORT).show();
            } else {
                if (mToast == null) {
                    mToast = new Toast(ctx);
                    mToast.setDuration(Toast.LENGTH_SHORT);//设置toast显示的时长
                    View root = LayoutInflater.from(ctx).inflate(R.layout.item_toast_custom_common, null, false);//自定义样式，自定义布局文件
                    mTvToast = root.findViewById(R.id.tvCustomToast);
                    mToast.setView(root);//设置自定义的view
                }
                mTvToast.setText(content);//设置文本
                mToast.show();//展示toast
            }
        }

        public void showToast(Context ctx, int stringId) {
            showToast(ctx, ctx.getString(stringId));
        }

        public void cancelToast() {
            if (mToast != null) {
                mToast.cancel();
                mToast = null;
                mTvToast = null;
            }
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
    public static void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg))
                intent.setPackage(marketPkg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
