package com.example.gab.babylove.ui.main.other;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.gab.babylove.BuildConfig;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiServiceKotlin;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.UpDateBean;
import com.example.gab.babylove.utils.CustomDialog;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 初夏小溪
 * @date 2018/5/28 0028
 */
public class UpdateActivity extends BaseActivity implements IBaseActivity {

    private ProgressDialog pBar;
    private int fileSize, sumSize;
    private FileOutputStream fos;
    private File file;

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new UpdateActivity.MyHandler(this);

    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return R.layout.activity_update;
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        getUpdate();
//        getVersionsUpdate();

    }

    private void getVersionsUpdate() {
        RequestUtils.create(ApiServiceKotlin.class)
                .getVersionsUpdate("", "android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetCallBack<UpDateBean>() {
                    @Override
                    protected void onSuccess(UpDateBean upDateBean) {
                        try {
                            String[] versionName = getPackageManager().getPackageInfo(" com.example.gab.babylove", 0).versionName.replace(".", "-").split("-");
                            String[] version = upDateBean.getVersion().getVersion().replace(".", "-").split("-");
                            if (Integer.valueOf(version[0]) > Integer.valueOf(versionName[0])) {
//                                doNewVersionUpdate();
                            } else if (Integer.valueOf(version[1]) > Integer.valueOf(versionName[1])) {
//                                doNewVersionUpdate();
                            } else if (Integer.valueOf(version[2]) > Integer.valueOf(versionName[2])) {
//                                doNewVersionUpdate();
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
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
                        CustomDialog dialog = new CustomDialog(UpdateActivity.this, R.style.DialogActivity);
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
                            pBar = new ProgressDialog(UpdateActivity.this);
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
        file = new File(Environment.getExternalStorageDirectory(), "wanAndroid.apk");
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

    @SuppressLint("HandlerLeak")
    class MyHandler extends Handler {
        WeakReference<UpdateActivity> mWeakReference;

        MyHandler(UpdateActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            UpdateActivity activity = mWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 1:
                        activity.pBar.setProgress(activity.sumSize * 100 / activity.fileSize);
                        break;
                    case 2:
                        installAPK(activity);
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
    public void installAPK(Activity activity) {
        String authority = BuildConfig.APPLICATION_ID + ".provider";
        activity.startActivity(getInstallAppIntent(authority, true));
    }

    private Intent getInstallAppIntent(final String authority, final boolean isNewTask) {
        if (file == null) {
            return null;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Uri contentUrl = Uri.fromFile(file);
            intent.setDataAndType(contentUrl, "application/vnd.android.package-archive");
        } else {
            //判断是否是Android N以及更高版本
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "wanAndroid.apk");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUrl = FileProvider.getUriForFile(this, authority, file);
            intent.setDataAndType(contentUrl, "application/vnd.android.package-archive");
        }
        return getIntent(intent, isNewTask);
    }

    private Intent getIntent(final Intent intent, final boolean isNewTask) {
        return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
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
}
