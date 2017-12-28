package com.example.gab.babylove.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.activity.DialActivity;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.DeviceUtils;
import com.fy.baselibrary.utils.JumpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/15 0015.
 * 大宝贝的专用类
 */

public class WifeFragment extends BaseFragment {

    @BindView(R.id.tv_baby)
    TextView tv_baby;
    @BindView(R.id.tv_Os)
    TextView tv_Os;
    @BindView(R.id.statusView)
    View statusView;

    @BindView(R.id.horizontal_view)
    HorizontalScrollView mHorizontalScrollView;

    private TranslateAnimation mRigthToLeftAnim;
    private final static float SCOLL_V = 0.2f;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_wife;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void baseInit() {
        super.baseInit();
        tv_baby.post(new Runnable() {
            @Override
            public void run() {
                mRigthToLeftAnim = new TranslateAnimation(mHorizontalScrollView.getWidth(), -tv_baby.getWidth(), 0, 0);
                mRigthToLeftAnim.setRepeatCount(Animation.INFINITE);
                mRigthToLeftAnim.setInterpolator(new LinearInterpolator());
                mRigthToLeftAnim.setDuration((long) ((mHorizontalScrollView.getWidth() + tv_baby.getWidth()) / SCOLL_V));
                tv_baby.startAnimation(mRigthToLeftAnim);
            }
        });
        tv_Os.setText("手机厂商:" + DeviceUtils.getDeviceMake() + "\n" + "手机型号:" + DeviceUtils.getSystemModel() + "\n"
                + "Android版本号:" + DeviceUtils.getDeviceVersion() + "\n" + "手机IMEI:" + DeviceUtils.getIMEI(mContext));
        MdStatusBarCompat.setStatusView(mContext, statusView);
    }

    @OnClick({R.id.bt_Dial})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_Dial://头部左边按钮
                JumpUtils.jump(mContext, DialActivity.class, null);
                break;
        }
    }
}
