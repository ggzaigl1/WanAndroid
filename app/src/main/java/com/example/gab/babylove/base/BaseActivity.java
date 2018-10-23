package com.example.gab.babylove.base;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gab.babylove.R;
import com.ggz.baselibrary.utils.permission.PermissionChecker;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by 初夏小溪 on 2018/10/15 0015.
 */
public class BaseActivity extends AppCompatActivity {

    protected KProgressHUD mKProgressHUD;
    protected Context mContext;
    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

    }


    /**
     * 检查权限
     */
    public void onPermission() {
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            permissionChecker.requestPermissions();
        }
    }

    /**
     * 请求权限返回结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionChecker.PERMISSION_REQUEST_CODE:
                //权限获取成功
                if (!permissionChecker.hasAllPermissionsGranted(grantResults)) {
                    //权限获取失败
                    permissionChecker.showDialog();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mKProgressHUD != null) {
            mKProgressHUD.dismiss();
        }
    }
}
