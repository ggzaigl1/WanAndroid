package com.example.gab.babylove.widget.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.gab.babylove.R;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.LogUtils;
import com.fy.baselibrary.utils.ToastUtils;
import com.fy.baselibrary.utils.permission.PermissionChecker;

import butterknife.BindView;

/**
 * @author 初夏小溪
 * @date 2018/6/12 0012
 */
public class CameraActivity extends AppCompatActivity implements IBaseActivity {

    private SurfaceHolder mSurfaceHolder;
    private int mCameraId = 0;
    private Camera mCamera;
    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
    };

    @BindView(R.id.surfaceView)
    SurfaceView mSurfaceView;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_camera;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBarForDrawer(this, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        mSurfaceHolder = mSurfaceView.getHolder();
        if (checkCameraHardware(this)) {
            mSurfaceHolder.addCallback(new SurfaceCallback());
            permissionChecker = new PermissionChecker(this);
            permissionChecker.setTitle(getString(R.string.check_info_title));
            permissionChecker.setMessage(getString(R.string.check_info_message));
            if (permissionChecker.isLackPermissions(PERMISSIONS)) {
                new MaterialDialog.Builder(this).title(R.string.require_acquisition)
                        .content(R.string.default_always_message)
                        .positiveText(R.string.next).onPositive((dialog, which) -> onPermission()).show();
            }
        } else {
            ToastUtils.showShort("没有相机硬件");
        }
    }

    /**
     * 检查权限
     */
    private void onPermission() {
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
                if (permissionChecker.hasAllPermissionsGranted(grantResults)) {
                } else {
                    //权限获取失败
                    permissionChecker.showDialog();
                }
                break;
            default:
                break;
        }
    }

    private boolean checkCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private class SurfaceCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                mCamera = Camera.open(mCameraId);
            } catch (Exception e) {
                LogUtils.i("摄像头被占用");
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            try {
                initCamera(mSurfaceView.getWidth(), mSurfaceView.getHeight());
                mCamera.setPreviewDisplay(holder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            CameraUtil.getInstance(mCamera, mCameraId).stopPreview();
        }
    }

    private void initCamera(int width, int height) {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewFormat(ImageFormat.NV21);
        //根据设置的宽高 和手机支持的分辨率对比计算出合适的宽高算法
        Camera.Size optionSize = CameraUtil.getInstance(mCamera, mCameraId).getOptimalPreviewSize(width, height);
        parameters.setPreviewSize(optionSize.width, optionSize.height);
        //设置照片尺寸
        parameters.setPictureSize(optionSize.width, optionSize.height);
        //设置实时对焦 部分手机不支持会crash
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        mCamera.setParameters(parameters);
        CameraUtil.getInstance(mCamera, mCameraId).setCameraDisplayOrientation(this);
        //开启预览
        mCamera.startPreview();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }
}
