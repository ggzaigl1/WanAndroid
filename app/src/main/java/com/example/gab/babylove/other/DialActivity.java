package com.example.gab.babylove.other;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.example.gab.babylove.utils.ExitDialog;
import com.example.gab.babylove.view.LuckPanLayout;
import com.example.gab.babylove.view.RotatePan;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;

import butterknife.BindView;

/**
 * Created by Gab on 2017/12/12 0012.
 * 积分大转盘
 */
public class DialActivity extends AppCompatActivity implements IBaseActivity, LuckPanLayout.AnimationEndListener {

    @BindView(R.id.luckpan_layout)
    LuckPanLayout luckPanLayout;

    private RotatePan rotatePan;
    private String[] strs;

    public void rotation(View view) {
        luckPanLayout.rotate(-1, 100);
    }

    @Override
    public void endAnimation(int position) {
        Toast.makeText(this, strs[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_dial;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        new ExitDialog.Builder()
                .setTitle("跨年抽豪礼")
                .setMessage(getString(R.string.context))
                .setRightListener(getString(android.R.string.yes), v -> {

                })
                .setLeftListener("否,我需要更新", v -> {
                    JumpUtils.jump(DialActivity.this, MainActivity.class, null);
                    T.showShort("进入到主页面更新");
                })
                .create()
                .show(this.getSupportFragmentManager(), "WarningDialog");

        strs = getResources().getStringArray(R.array.names);
        luckPanLayout.setAnimationEndListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }
}
