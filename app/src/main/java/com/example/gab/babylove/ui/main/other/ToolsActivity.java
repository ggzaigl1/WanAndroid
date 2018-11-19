package com.example.gab.babylove.ui.main.other;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.gab.babylove.BuildConfig;
import com.example.gab.babylove.R;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.utils.CleanMessageUtil;
import com.example.gab.babylove.utils.CustomDialog;
import com.example.gab.babylove.utils.Util;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.utils.L;
import com.ggz.baselibrary.utils.NetworkUtils;
import com.ggz.baselibrary.utils.T;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.pgyersdk.update.javabean.AppBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Notification.VISIBILITY_SECRET;

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

    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder builder;
    private Notification.Builder mBuilder;
    private Notification mNotification;
    private File mFile;
    //应用宝下载地址
    private static String DOWNLOAD_URL = "http://migmkt.qq.com/g/myapp/yyb-common.html?ADTAG=buy.bd.yyb01";


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
        mFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "wanAndroid.apk");
        mNotificationManager = (NotificationManager) getSystemService(Activity.NOTIFICATION_SERVICE);
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
                getVersionUpdate();
                break;
//            //搜索
            case R.id.Ll_check_test:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
    private void getEvaluation() {
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
            new MaterialDialog.Builder(this)
                    .title(R.string.system_title)
                    .cancelable(false)
                    .content("您没有安装应用宝,是否安装应用宝?")
                    .negativeText(getString(R.string.ok))
                    .onNegative((dialog, which) -> {
                        Uri uri = Uri.parse(DOWNLOAD_URL);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        dialog.dismiss();
                    }).positiveText(getString(R.string.cancel))
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

    /**
     * 获得更新
     */
    public void getVersionUpdate() {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        new PgyUpdateManager.Builder()
                //设置是否强制更新,非自定义回调更新接口此方法有用
                .setForced(true)
                //失败后是否提示重新下载，非自定义下载 apk 回调此方法有用
                .setUserCanRetry(false)
                // 检查更新前是否删除本地历史 Apk， 默认为true
                .setDeleteHistroyApk(true)
                .setUpdateManagerListener(new UpdateManagerListener() {
                    @Override
                    public void onNoUpdateAvailable() {
                        //没有更新是回调此方法
                        Log.d("wanAndroid", "没有新的版本");
                        T.showShort("已经是最新版本");
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    public void onUpdateAvailable(AppBean appBean) {
                        //有更新回调此方法
                        mKProgressHUD.dismiss();
                        L.d("wanAndroid", "有新版本可以更新" + "new versionCode is " + appBean.getVersionCode());
                        T.showShort("有新版本可以更新");
                        //调用以下方法，DownloadFileListener 才有效；
                        //如果完全使用自己的下载方法，不需要设置DownloadFileListener
                        CustomDialog dialog = new CustomDialog(ToolsActivity.this, R.style.DialogActivity);
                        dialog.setContentView(R.layout.dialog_update);
                        dialog.setCancelable(false);
                        dialog.show();
                        Button mBtSure = dialog.findViewById(R.id.bt_sure);
                        Button mBtCancel = dialog.findViewById(R.id.bt_cancel);
                        TextView mTvVersionUpdateContent = dialog.findViewById(R.id.tv_version_update_content);

                        if (TextUtils.isEmpty(appBean.getReleaseNote())) {
                            mTvVersionUpdateContent.setText("修复已知问题");
                        } else {
                            mTvVersionUpdateContent.setText(appBean.getReleaseNote());
                        }

                        mBtSure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (NetworkUtils.is4G(ToolsActivity.this)) {
                                    new MyDownloadAsync().execute(appBean.getDownloadURL());
                                    T.showShort("移动网络数据下载");
                                } else {
                                    new MyDownloadAsync().execute(appBean.getDownloadURL());
                                }
                                L.d("wanAndroid", appBean.getDownloadURL());
                                dialog.dismiss();
                            }
                        });
                        mBtCancel.setOnClickListener(v -> dialog.dismiss());
                    }

                    @Override
                    public void checkUpdateFailed(Exception e) {
                        mKProgressHUD.dismiss();
                        //更新检测失败回调
                        //更新拒绝（应用被下架，过期，不在安装有效期，下载次数用尽）以及无网络情况会调用此接口
                        Log.e("wanAndroid", "检查更新包失败", e);
                        T.showShort("检查更新包失败");
                    }
                }).register();
    }

    /**
     * 下载更新包
     */
    @SuppressLint("StaticFieldLeak")
    public class MyDownloadAsync extends AsyncTask<String, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            T.showShort("开始下载更新");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //8.0通知
                mBuilder = getNotificationBuilder();
                mBuilder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);
                getManager().notify(2, mBuilder.build());
            } else {
                //8.0以下通知
                builder = new NotificationCompat.Builder(getBaseContext(), "wanAndroid_id")
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("正在下载新版本，请稍后...");
                mNotification = builder.build();
            }
        }

        @Override
        protected Integer doInBackground(String... params) {
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                URL url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                //设置超时时间
                httpURLConnection.setConnectTimeout(5 * 1000);
                //判断是否连接成功
                if (httpURLConnection.getResponseCode() == 200) {
                    int fileLength = httpURLConnection.getContentLength();
                    //获取输入
                    inputStream = httpURLConnection.getInputStream();
                    outputStream = new FileOutputStream(mFile);
                    byte[] buffer = new byte[1024 * 1024 * 10];
                    long total = 0;
                    int count;
                    int pro1 = 0;
                    int pro2 = 0;
                    while ((count = inputStream.read(buffer)) != -1) {
                        total += count;
                        if (fileLength > 0) {
                            //传递进度（注意顺序）
                            pro1 = (int) (total * 100 / fileLength);
                        }
                        if (pro1 != pro2) {
                            publishProgress(pro2 = pro1);
                            outputStream.write(buffer, 0, count);
                        }
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if (result == 1) {
                T.showShort("下载完成");
            } else {
                T.showShort("下载失败");
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //关闭通知通道
                mNotificationManager.deleteNotificationChannel("wanAndroid_id");
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d("===", "" + values[0]);
            super.onProgressUpdate(values);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mBuilder.setProgress(100, values[0], false);
                getManager().notify(2, mBuilder.build());
                //下载完成后点击安装
                if (values[0] == 100) {
                    installAPK(ToolsActivity.this);
                }
            } else {
                builder.setProgress(100, values[0], false);
                mNotification = builder.build();
                mNotificationManager.notify(0, mNotification);
                //下载完成后点击安装
                if (values[0] == 100) {
                    installAPK(ToolsActivity.this);
                }
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        /**
         * 通知管理
         *
         * @return
         */
        private NotificationManager getManager() {
            if (mNotificationManager == null) {
                mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            }
            return mNotificationManager;
        }

        /**
         * 创建通知栏信息
         * 大于8.0
         *
         * @return
         */
        @RequiresApi(api = Build.VERSION_CODES.O)
        private Notification.Builder getNotificationBuilder() {
            //id随便指定
            NotificationChannel channel = new NotificationChannel("wanAndroid_id", "wanAndroid_name", NotificationManager.IMPORTANCE_LOW);
            //锁屏显示通知
            channel.setLockscreenVisibility(VISIBILITY_SECRET);
            channel.canShowBadge();//桌面laucher消息角标
            channel.getGroup();//获取通知渠道组
            //通知管理者创建的渠道
            getManager().createNotificationChannel(channel);
            return new Notification.Builder(ToolsActivity.this).setAutoCancel(true)
                    .setChannelId("wanAndroid_id")
                    .setContentTitle("正在下载新版本，请稍后...")
                    .setSmallIcon(R.mipmap.ic_launcher_round);
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
        void installAPK(Activity activity) {
            String authority = BuildConfig.APPLICATION_ID + ".provider";
            activity.startActivity(getInstallAppIntent(authority));
        }
    }

    private Intent getInstallAppIntent(final String authority) {
        if (mFile == null) {
            return null;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUrl = FileProvider.getUriForFile(this, authority, mFile);
            intent.setDataAndType(contentUrl, "application/vnd.android.package-archive");
            PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentTitle("下载完成").setContentText("点击安装").setContentIntent(pendingIntent);
            mNotification = mBuilder.build();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //判断是否是Android N以及更高版本
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUrl = FileProvider.getUriForFile(this, authority, mFile);
            intent.setDataAndType(contentUrl, "application/vnd.android.package-archive");
            PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentTitle("下载完成").setContentText("点击安装").setContentIntent(pendingIntent);
            mNotification = builder.build();
        } else {
            Uri contentUrl = Uri.fromFile(mFile);
            intent.setDataAndType(contentUrl, "application/vnd.android.package-archive");
            PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentTitle("下载完成").setContentText("点击安装").setContentIntent(pendingIntent);
            mNotification = builder.build();
        }
        mNotificationManager.notify(0, mNotification);
        return getIntent(intent);
    }

    private Intent getIntent(final Intent intent) {
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  如果为空,所有的回调函数和消息将被删除
        mHandler.removeCallbacksAndMessages(null);
    }
}
