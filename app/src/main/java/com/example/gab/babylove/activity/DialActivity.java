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

/**
 * Created by Gab on 2017/12/12 0012.
 * 积分大转盘
 */

public class DialActivity extends AppCompatActivity implements LuckPanLayout.AnimationEndListener {

    private RotatePan rotatePan;
    private LuckPanLayout luckPanLayout;
    private ImageView goBtn;
    private ImageView yunIv;
    private String[] strs;
    private TextView mTextMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);
        mTextMessage = (TextView) findViewById(R.id.message);

        new ExitDialog.Builder()
                .setTitle("做回访 赚积分 抽好礼")
                .setMessage(getString(R.string.context))
                .setRightListener(getString(android.R.string.yes), v -> {

                })
                .setLeftListener("否,我需要更新", v -> Toast.makeText(DialActivity.this, "进入到更新页面", Toast.LENGTH_SHORT).show())
                .create()
                .show(this.getSupportFragmentManager(), "WarningDialog");

        strs = getResources().getStringArray(R.array.names);
        luckPanLayout = (LuckPanLayout) findViewById(R.id.luckpan_layout);
        luckPanLayout.setAnimationEndListener(this);
        goBtn = (ImageView) findViewById(R.id.go);
        yunIv = (ImageView) findViewById(R.id.yun);
    }

    public void rotation(View view) {
        luckPanLayout.rotate(-1, 100);
    }

    @Override
    public void endAnimation(int position) {
        Toast.makeText(this, strs[position], Toast.LENGTH_SHORT).show();
    }
}
