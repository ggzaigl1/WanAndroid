package com.example.gab.babylove.ui.main.activity;

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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gab.babylove.BuildConfig;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.UpDateBean;
import com.example.gab.babylove.widget.CustomDialog;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;

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
 * Created by 初夏小溪 on 2018/5/28 0028.
 */
public class UpdateActivity extends AppCompatActivity implements IBaseActivity {

    private ProgressDialog pBar;
    private int fileSize, sumSize;
    private FileOutputStream fos;
    private File file;
    private final Handler mHandler = new MyHandler(this);
    UpDateBean upDateBean;
    private Button mConfirm;
    private Button mCancel;
    private TextView mVersion;

    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return R.layout.activity_update;
    }

    @Override
    public void setStatusBar(Activity activity) {

    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        file = new File(Environment.getExternalStorageDirectory(), "ldz.apk");
        getVersionsUpdate();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }

    private void getVersionsUpdate() {
        RequestUtils.create(ApiService.class)
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
                                doNewVersionUpdate();
                            } else if (Integer.valueOf(version[1]) > Integer.valueOf(versionName[1])) {
                                doNewVersionUpdate();
                            } else if (Integer.valueOf(version[2]) > Integer.valueOf(versionName[2])) {
                                doNewVersionUpdate();
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

    private void doNewVersionUpdate() {
        final CustomDialog dialog = new CustomDialog(this, R.style.DialogActivity);
        dialog.setContentView(R.layout.dialog_update);
        dialog.setCancelable(false);
        dialog.show();
        mConfirm = dialog.findViewById(R.id.positiveButton);
        mCancel = dialog.findViewById(R.id.negativeButton);
        mVersion = dialog.findViewById(R.id.version);
        mVersion.setText(upDateBean.getVersion().getVersion());
        if (!upDateBean.getVersion().isIs_force_update()) {
            mCancel.setVisibility(View.VISIBLE);
        }
        mConfirm.setOnClickListener(arg0 -> {
            pBar = new ProgressDialog(UpdateActivity.this);
            pBar.setTitle("正在下载...");
            pBar.setCancelable(false);
            pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pBar.setButton(DialogInterface.BUTTON_POSITIVE, "取消", (dialog1, which) -> {
                try {
                    if (fos != null) {
                        fos.close();
                        file.delete();
                    }
                    dialog1.dismiss();
                    if (upDateBean.getVersion().isIs_force_update()) {
                        finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            downFile();
        });
        mCancel.setOnClickListener(arg0 -> dialog.dismiss());
    }

    private void downFile() {
        pBar.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(upDateBean.getVersion().getDown_url());
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

    /**
     * 防止Handler泄露 使用静态
     * Ggz
     */
    static class MyHandler extends Handler {
        WeakReference<UpdateActivity> mWeakReference;

        public MyHandler(UpdateActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final UpdateActivity activity = mWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
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
     *
     * @param activity
     */
    public static void install(UpdateActivity activity) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是Android N以及更高版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "ldz.apk");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUrl = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.setDataAndType(contentUrl, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(activity.file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        activity.startActivity(intent);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "ldz.apk");
//            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
//            Uri apkUri = FileProvider.getUriForFile(activity, "com.example.gab.babylove.fileprovider", file);
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            // 由于没有在Activity环境下启动Activity,设置下面的标签
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            //添加这一句表示对目标应用临时授权该Uri所代表的文件
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
//            activity.startActivity(intent);
//        } else {
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setDataAndType(Uri.fromFile(activity.file), "application/vnd.android.package-archive");
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            activity.startActivity(intent);
//            activity.finish();
//        }
    }
}
