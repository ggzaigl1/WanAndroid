package com.example.gab.babylove.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.PermissionActivity;
import com.fy.baselibrary.utils.T;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Gab on 2018/1/26 0026.
 */

public class CalendarViewActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.activity_calendar_view;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //动态判断权限
        startActivityForResult(new Intent(mContext, CalendarViewActivity.class).putExtra(PermissionActivity.KEY_PERMISSIONS_ARRAY,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE})
                , PermissionActivity.CALL_BACK_PERMISSION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionActivity.CALL_BACK_PERMISSION_REQUEST_CODE) {
            switch (resultCode) {
                case PermissionActivity.CALL_BACK_RESULT_CODE_SUCCESS:
                    T.showShort("0");
                    break;
                case PermissionActivity.CALL_BACK_RESULE_CODE_FAILURE:
                    T.showShort("1");
                    break;
            }
        }
    }
}
