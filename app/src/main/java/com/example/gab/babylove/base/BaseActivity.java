package com.example.gab.babylove.base;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ggz.baselibrary.utils.permission.PermissionChecker;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by 初夏小溪 on 2018/10/15 0015.
 */
public class BaseActivity extends AppCompatActivity {

    protected KProgressHUD mKProgressHUD;
    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mKProgressHUD != null) {
            mKProgressHUD.dismiss();
        }
    }
}
