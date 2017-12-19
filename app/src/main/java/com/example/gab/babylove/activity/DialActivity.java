package com.example.gab.babylove.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.example.gab.babylove.utils.ExitDialog;
import com.example.gab.babylove.view.LuckPanLayout;
import com.example.gab.babylove.view.RotatePan;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;

import butterknife.BindView;

/**
 * Created by Gab on 2017/12/12 0012.
 * 积分大转盘
 */

public class DialActivity extends BaseActivity implements LuckPanLayout.AnimationEndListener {

    @BindView(R.id.luckpan_layout)
    LuckPanLayout luckPanLayout;

    private RotatePan rotatePan;
    private String[] strs;

    @Override
    protected int getContentView() {
        return R.layout.activity_dial;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        new ExitDialog.Builder()
                .setTitle("跨年抽豪礼")
                .setMessage(getString(R.string.context))
                .setRightListener(getString(android.R.string.yes), v -> {

                })
                .setLeftListener("否,我需要更新", v -> {
                    JumpUtils.jump(mContext,MainActivity.class,null);
                    T.showShort("进入到主页面更新");
                })
                .create()
                .show(this.getSupportFragmentManager(), "WarningDialog");

        strs = getResources().getStringArray(R.array.names);
        luckPanLayout.setAnimationEndListener(this);

    }

    public void rotation(View view) {
        luckPanLayout.rotate(-1, 100);
    }

    @Override
    public void endAnimation(int position) {
        Toast.makeText(this, strs[position], Toast.LENGTH_SHORT).show();
    }
}
