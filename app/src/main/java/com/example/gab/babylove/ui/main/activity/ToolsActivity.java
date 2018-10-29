package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.gab.babylove.BuildConfig;
import com.example.gab.babylove.R;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.utils.CleanMessageUtil;
import com.example.gab.babylove.utils.CustomDialog;
import com.example.gab.babylove.utils.Util;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.ggz.baselibrary.utils.L;
import com.ggz.baselibrary.utils.T;
import com.pgyersdk.update.DownloadFileListener;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.pgyersdk.update.javabean.AppBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/20 0020.
 * 工具栏
 */

public class ToolsActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.tv_cache_size)
    TextView tv_cache_size;
    @BindView(R.id.tv_check_update)
    TextView tv_check_update;
    private static String DOWNLOAD_URL = "http://migmkt.qq.com/g/myapp/yyb-common.html?ADTAG=buy.bd.yyb01";//应用宝下载地址

    private ProgressDialog pBar;
    private int fileSize, sumSize;
    private FileOutputStream fos;
    private File file;

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

    //    @StatusBar(statusColor = R.color.statusBar, navColor = R.color.statusBar)
    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        file = new File(Environment.getExternalStorageDirectory(), "wanAndroid.apk");

        try {
            tv_cache_size.setText(CleanMessageUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.Ll_cache_clear, R.id.tv_praise, R.id.tv_check_update})
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
                GetCache();
                break;
//            //SelectorButton
            case R.id.tv_check_update:
                getUpdate();
//                JumpUtils.jump(this, UpdateActivity.class, null);
                break;
            default:
                break;
        }
    }

    /**
     * 清除缓存
     */
    private void GetCache() {
        new MaterialDialog.Builder(this)
                .title(R.string.system_title)
                .cancelable(false)
                .content(R.string.tools_clear_cache)
                .positiveText(R.string.ok).onPositive((dialog, which) -> new Thread(new Runnable() {
            @Override
            public void run() {
                CleanMessageUtil.clearAllCache(ToolsActivity.this.getApplicationContext());
                mHandler.sendEmptyMessage(0);
            }
        }).start()).negativeText(R.string.cancel).onNegative((dialog, which) -> dialog.dismiss()).show();
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
        if (Util.isAvilible(this, "com.tencent.android.qqdownloader")) {
            //存在进入应用市场
            Util.launchAppDetail(this, "com.example.gab.babylove", "com.tencent.android.qqdownloader");
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

    /**
     * 检查更新
     */
    public void getUpdate() {
        new PgyUpdateManager.Builder()
                .setForced(true)                //设置是否强制更新,非自定义回调更新接口此方法有用
                .setUserCanRetry(false)         //失败后是否提示重新下载，非自定义下载 apk 回调此方法有用
                .setDeleteHistroyApk(true)     // 检查更新前是否删除本地历史 Apk， 默认为true
                .setUpdateManagerListener(new UpdateManagerListener() {
                    @Override
                    public void onNoUpdateAvailable() {
                        //没有更新是回调此方法
                        T.showShort("当前是最新版本");
                    }

                    @Override
                    public void onUpdateAvailable(AppBean appBean) {
                        //有更新回调此方法
                        L.d("pgyer", "有新版本可以更新" + "new versionCode is " + appBean.getVersionCode());
                        //调用以下方法，DownloadFileListener 才有效；
                        //如果完全使用自己的下载方法，不需要设置DownloadFileListener
                        CustomDialog dialog = new CustomDialog(ToolsActivity.this, R.style.DialogActivity);
                        dialog.setContentView(R.layout.dialog_update);
                        dialog.setCancelable(false);
                        dialog.show();
                        Button bt_sure = dialog.findViewById(R.id.bt_sure);
                        Button bt_cancel = dialog.findViewById(R.id.bt_cancel);
                        TextView version_update_content = dialog.findViewById(R.id.tv_version_update_content);

                        if (TextUtils.isEmpty(appBean.getReleaseNote())) {
                            version_update_content.setText("修复已知问题");
                        } else {
                            version_update_content.setText(appBean.getReleaseNote());
                        }

                        bt_cancel.setOnClickListener(v -> dialog.dismiss());
                        bt_sure.setOnClickListener(view -> {
                            pBar = new ProgressDialog(ToolsActivity.this);
                            pBar.setTitle("正在下载...");
                            pBar.setCancelable(false);
                            pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            pBar.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.cancel), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    try {
                                        if (fos != null) {
                                            fos.close();
                                            file.delete();
                                        }
                                        dialogInterface.dismiss();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                            downFile(appBean.getDownloadURL());
                            dialog.dismiss();
                        });
                    }

                    @Override
                    public void checkUpdateFailed(Exception e) {
                        //更新检测失败回调
                        //更新拒绝（应用被下架，过期，不在安装有效期，下载次数用尽）以及无网络情况会调用此接口
                        Log.e("pgyer", "check update failed ", e);
                    }
                })
                //注意 ：
                //下载方法调用 PgyUpdateManager.downLoadApk(appBean.getDownloadURL()); 此回调才有效
                //此方法是方便用户自己实现下载进度和状态的 UI 提供的回调
                //想要使用蒲公英的默认下载进度的UI则不设置此方法
                .setDownloadFileListener(new DownloadFileListener() {
                    @Override
                    public void downloadFailed() {
                        //下载失败
                        L.e("pgyer", "download apk failed");
                        T.showShort("download apk failed");
                    }

                    @Override
                    public void downloadSuccessful(Uri uri) {
                        L.e("pgyer", "download apk failed");
                        // 使用蒲公英提供的安装方法提示用户 安装apk
//                        PgyUpdateManager.installApk(uri);
                    }

                    @Override
                    public void onProgressUpdate(Integer... integers) {
                        L.e("pgyer", "update download apk progress" + integers);
                    }
                })
                .register();
    }

    private void downFile(String DownUrl) {
        pBar.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(DownUrl);
                    URLConnection conn = url.openConnection();
                    fileSize = conn.getContentLength();
                    InputStream is = conn.getInputStream();
                    if (!file.exists()) {
                        file.createNewFile();
                    } else {
                        file.delete();
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(file);
                    byte[] b = new byte[1024];
                    while (sumSize < fileSize) {
                        int len = is.read(b);
                        sumSize += len;
                        fos.write(b, 0, len);
                        fos.flush();
                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    }
                    fos.close();
                    is.close();
                    Message msg = new Message();
                    msg.what = 2;
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 关闭 ProgressDialog 窗口泄露
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pBar != null) {
            pBar.dismiss();
        }
        //  如果为空,所有的回调函数和消息将被删除
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (pBar != null) {
            pBar.dismiss();
        }
    }

    /**
     * 防止Handler泄露 使用静态
     * Ggz
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
                        tv_cache_size.setText(R.string.zero_k);
                        break;
                    case 1:
                        activity.pBar.setProgress(activity.sumSize * 100 / activity.fileSize);
                        break;
                    case 2:
                        install(activity);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 1、首先我们对Android N及以上做判断；
     * 2、然后添加flags，表明我们要被授予什么样的临时权限
     * 3、以前我们直接 Uri.fromFile(apkFile)构建出一个Uri,现在我们使用FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", apkFile);
     * 4、BuildConfig.APPLICATION_ID直接是应用的包名
     * 5、Android O 需要添加 <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/> 权限
     *
     * @param activity
     */
    public static void install(ToolsActivity activity) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是Android N以及更高版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "wanAndroid.apk");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUrl = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", file);
            intent.setDataAndType(contentUrl, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(activity.file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        activity.startActivity(intent);
    }
}
