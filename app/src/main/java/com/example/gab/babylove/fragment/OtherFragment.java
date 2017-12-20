package com.example.gab.babylove.fragment;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Switch;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.gab.babylove.R;
import com.example.gab.babylove.login.LoginActivity;
import com.example.gab.babylove.widget.AppLoading;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.NetRequest;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/15 0015.
 * 其他
 */

public class OtherFragment extends BaseFragment {

    @BindView(R.id.night_switch)
    Switch night_switch;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_other;
    }

    @Override
    protected void baseInit() {
        super.baseInit();

    }

    @OnClick({R.id.night_switch})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.night_switch:
                Switch sw = (Switch) view;
                boolean isChecked = sw.isChecked();
                if(isChecked){
                    T.showShort("开启");
                }else {
                    T.showShort("关闭");
                }
                break;
        }
    }

}
