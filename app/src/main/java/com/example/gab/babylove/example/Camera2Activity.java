package com.example.gab.babylove.example;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by 初夏小溪 on 2018/3/23 0023.
 *
 */

public class Camera2Activity extends BaseActivity  {


    @BindView(R.id.cameraView)
    CameraPreview cameraView;

    @Override
    protected int getContentView() {
        return R.layout.activity_camera2;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

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
