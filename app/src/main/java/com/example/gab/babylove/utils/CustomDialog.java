package com.example.gab.babylove.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by 初夏小溪
 * data :2018/11/15 001515:12
 * @author 初夏小溪
 */
public class CustomDialog extends Dialog {

    Context context;

    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
    }
}
