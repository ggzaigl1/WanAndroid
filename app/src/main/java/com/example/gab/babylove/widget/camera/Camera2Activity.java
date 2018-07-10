package com.example.gab.babylove.widget.camera;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gab.babylove.R;
import com.ggz.baselibrary.application.IBaseActivity;

import butterknife.BindView;

/**
 * Created by 初夏小溪 on 2018/3/23 0023.
 */

public class Camera2Activity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.cameraView)
    CameraPreview cameraView;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_camera2;
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        cameraView.onResume(this);
    }

    @Override
    protected void onPause() {
        cameraView.onPause();
        super.onPause();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void takePic(View view) {
        cameraView.takePicture();
    }
}
