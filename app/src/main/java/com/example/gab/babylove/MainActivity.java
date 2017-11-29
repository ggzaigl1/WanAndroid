package com.example.gab.babylove;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gab.babylove.utils.ExitDialog;
import com.example.gab.babylove.view.LuckPanLayout;
import com.example.gab.babylove.view.RotatePan;

/**
 *  啊啊啊
 */
public class MainActivity extends AppCompatActivity implements LuckPanLayout.AnimationEndListener {

    private RotatePan rotatePan;
    private LuckPanLayout luckPanLayout;
    private ImageView goBtn;
    private ImageView yunIv;
    private String[] strs;
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);

        new ExitDialog.Builder()
                .setTitle("做回访 赚积分 抽好礼")
                .setMessage(getString(R.string.context))
                .setRightListener(getString(android.R.string.yes), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setLeftListener("否,我需要更新", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "进入到更新页面", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show(this.getSupportFragmentManager(), "WarningDialog");

//        new AlertDialog.Builder(this).setTitle("做回访 赚积分 抽好礼").setMessage(getString(R.string.context))
//                .setPositiveButton("是", null).setNegativeButton("否,我需要更新",null)
//                .show();
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
