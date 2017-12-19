package com.example.gab.babylove.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.example.gab.babylove.R;


public class AppLoading {

    private static Dialog dialog;

    public static void show(Context context) {
        if (null != dialog && dialog.isShowing()) {
            return;
        }
        if (dialog == null || dialog.getContext() != context) {
            dialog = new Dialog(context, R.style.app_dialog);
            dialog.setContentView(R.layout.dialog_loading);
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();
    }

    public static void close() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
