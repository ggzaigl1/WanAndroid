package com.ggz.baselibrary.utils.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

/**
 *
 * @author 初夏小溪
 * @date 2018/3/26 0026
 */

public class PermissionChecker {

    public static final int PERMISSION_REQUEST_CODE = 0;
    private String[] permissions;
    private Activity activity;
    private PermissionDialog dialog;

    public PermissionChecker(Activity activity) {
        this.activity = activity;
        this.dialog = new PermissionDialog(activity);
    }

    public boolean isLackPermissions(String[] permissions) {
        this.permissions = permissions;
        Checker checker = new Checker(activity);
        return checker.lacksPermissions(permissions);
    }

    public void requestPermissions() {
        ActivityCompat.requestPermissions(activity, permissions, PERMISSION_REQUEST_CODE);
    }

    public void setTitle(String title) {
        dialog.setTitle(title);
    }

    public void setMessage(String message) {
        dialog.setMessage(message);
    }

    public boolean hasAllPermissionsGranted(@NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    public void showDialog() {
        dialog.init();
        dialog.show();
    }

}
