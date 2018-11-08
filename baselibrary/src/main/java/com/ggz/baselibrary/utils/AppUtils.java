package com.ggz.baselibrary.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Environment;


import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;

import java.io.File;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * App相关的辅助类
 * Created by fangs on 2017/3/1.
 */
public class AppUtils {

    // 下载存储的文件名
    private static final String DOWNLOAD_NAME = "channelWe";

    private AppUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName() {
        try {
            Context context = ConfigUtils.getAppCtx();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前应用的版本号 versionCode
     *
     * @return
     */
    public static int getVersionCode() {
        Context context = ConfigUtils.getAppCtx();
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
        return info.versionCode;
    }


    /**
     * 获取当前应用的 包名
     *
     * @return
     */
    public static String getLocalPackageName() {
        Context context = ConfigUtils.getAppCtx();
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "获取失败";
        }
        return info.packageName;
    }

    /**
     * 当前的包是否存在
     *
     * @param context
     * @param pckName
     * @return
     */
    public static boolean isPackageExist(Context context, String pckName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo pckInfo = packageManager.getPackageInfo(pckName, 0);

            if (null != pckInfo) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e("TDvice", e.getMessage());
        }
        return false;
    }

    private static boolean isSpace(final String s) {
        if (s == null) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回应用程序的签名
     *
     * @param packageName The name of the package.
     * @return the application's signature
     */
    public static Signature[] getAppSignature(final String packageName) {
        Context context = ConfigUtils.getAppCtx();

        if (isSpace(packageName)) {
            return null;
        }
        try {
            PackageManager pm = context.getPackageManager();
            @SuppressLint("PackageManagerGetSignatures")
            PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return pi == null ? null : pi.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setUpdate(Activity activity) {
        //安装应用
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), DOWNLOAD_NAME)), "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }


    /**
     * 判断app是否在前台还是在后台运行
     *
     * @param context
     * @return
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                /*
                BACKGROUND=400 EMPTY=500 FOREGROUND=100
                GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
                 */
                LogUtils.i(context.getPackageName(), "此appimportace =" + appProcess.importance + ",context.getClass().getName()=" + context.getClass().getName());
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    LogUtils.i(context.getPackageName(), "处于后台" + appProcess.processName);
                    return true;
                } else {
                    LogUtils.i(context.getPackageName(), "处于前台" + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }


}
