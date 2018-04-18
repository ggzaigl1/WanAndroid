package com.fy.baselibrary.utils.permission;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.fy.baselibrary.R;
import com.fy.baselibrary.utils.SystemUtils;

/**
 * Created by 初夏小溪 on 2018/3/26 0026.
 */

public class PermissionDialog {

    private AlertDialog.Builder builder;
    private Activity activity;
    private String title;
    private String message;
    private static final String PACKAGE_URL_SCHEME = "package:";

    public PermissionDialog(Activity activity) {
        this.activity = activity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String getTitle() {
        if (isEmpty(title)) {
            return activity.getString(R.string.check_info_title);
        } else {
            return title;
        }
    }

    private String getMessage() {
        if (isEmpty(message)) {
            return activity.getString(R.string.check_info_message);
        } else {
            return message;
        }
    }

    public void init() {
        builder = new AlertDialog.Builder(activity)
                .setTitle(getTitle())
                .setMessage(getMessage())
                .setCancelable(false)
                .setNegativeButton(activity.getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SystemUtils.ExitSystem();
                    }
                })
                .setPositiveButton(activity.getString(R.string.check_info_setting), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionDialog.this.startAppSettings();
                    }
                });
    }

    public void show() {
        builder.show();
    }

    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + activity.getPackageName()));
        activity.startActivity(intent);
    }

    public boolean isEmpty(String src) {
        return TextUtils.isEmpty(src);
    }
}
