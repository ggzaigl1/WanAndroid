package com.example.gab.babylove.base;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.utils.permission.PermissionChecker;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by 初夏小溪 on 2018/10/15 0015.
 */
public class BaseActivity extends AppCompatActivity implements IBaseActivity {

    protected KProgressHUD mKProgressHUD;
    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return 0;
    }

    @Override
    public void setStatusBar(Activity activity) {
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mKProgressHUD != null) {
            mKProgressHUD.dismiss();
        }
    }
}
