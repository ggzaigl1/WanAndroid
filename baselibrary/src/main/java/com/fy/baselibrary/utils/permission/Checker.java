package com.fy.baselibrary.utils.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by 初夏小溪 on 2018/3/26 0026.
 */

public class Checker {

    private final Context mContext;

    public Checker(Context context) {
        mContext = context.getApplicationContext();
    }

    // Check permissions
    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    // Check lack Permissions
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED;
    }
}
