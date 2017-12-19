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

    @OnClick({R.id.night_switch,R.id.bt_exit})
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
            case R.id.bt_exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setTitle(R.string.system_title).setMessage(R.string.system_content)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogOut();
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                    builder.create().show();
//                MaterialDialog.Builder builder = new MaterialDialog.Builder(mContext).title(R.string.system_title)
//                        .content(R.string.system_content).negativeText(android.R.string.yes)
//                        .onNeutral((dialog, which) -> LogOut()).positiveText(android.R.string.no)
//                        .onPositive((dialog, which) -> dialog.dismiss());
//                MaterialDialog dialog = builder.build();
//                dialog.show();
                break;
        }
    }

    /**
     * 退出登录
     */
    private void LogOut() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.exit_loading);
        Map<String, Object> params = new HashMap<>();
        params.put("Token", ConstantUtils.token);//身份验证Token
        params.put("UserID", ConstantUtils.userId);//当前登录ID
        new NetRequest.Builder().create().requestDate(mConnService.LogOut(params).compose(RxHelper.handleResult()),
                new NetCallBack<ArrayList<LoginBean>>(progressDialog) {
                    @Override
                    public void onSuccess(ArrayList<LoginBean> bean) {
                        JumpUtils.jump(mContext, LoginActivity.class, null);
                        T.showShort("退出登录成功");
                    }

                    @Override
                    public void updataLayout(int flag) {
                        L.e("login_失败");
                    }
                });
    }
}
