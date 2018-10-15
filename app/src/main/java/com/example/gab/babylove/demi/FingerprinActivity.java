package com.example.gab.babylove.demi;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.example.gab.babylove.R;
import com.ggz.baselibrary.application.IBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/8/27 0027.
 */

public class FingerprinActivity extends AppCompatActivity implements IBaseActivity {


    @BindView(R.id.iv_off)
    ImageView mImageViewOff;
    @BindView(R.id.iv_on)
    ImageView mImageViewOn;

    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return R.layout.activity_main2;
    }

    @Override
    public void setStatusBar(Activity activity) {

    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {

    }

    @OnClick({R.id.Ll_off, R.id.Ll_on})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Ll_off://Id 正面
                takePhoto(CameraActivity.TYPE_ID_CARD_FRONT);
                break;
            case R.id.Ll_on://Id 背面
                takePhotoback(CameraActivity.TYPE_ID_CARD_BACK);
                break;
        }

    }

    @Override
    public void reTry() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == CameraActivity.REQUEST_CODE) {
            //获取文件路径，显示图片
            if (data != null) {
                String path = data.getStringExtra("result");
                if (!TextUtils.isEmpty(path)) {
                    mImageViewOff.setImageBitmap(BitmapFactory.decodeFile(path));
                }
            }
        }
    }


    /**
     * 拍摄证件照片
     *
     * @param type 拍摄证件类型
     */
    private void takePhoto(int type) {
        if (ActivityCompat.checkSelfPermission(FingerprinActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(FingerprinActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0x12);
            return;
        }
        CameraActivity.navToCamera(this, type);
    }

    private void takePhotoback(int type) {
        if (ActivityCompat.checkSelfPermission(FingerprinActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(FingerprinActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0x12);
            return;
        }
        CameraActivity.navToCamera(this, type);
    }

    /**
     * 身份证正面
     */
    public void frontIdCard(View view) {
        takePhoto(CameraActivity.TYPE_ID_CARD_FRONT);
    }

    /**
     * 身份证反面
     */
    public void backIdCard(View view) {
        takePhotoback(CameraActivity.TYPE_ID_CARD_BACK);
    }

}
