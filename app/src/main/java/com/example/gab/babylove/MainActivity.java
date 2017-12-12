package com.example.gab.babylove;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gab.babylove.activity.DialActivity;
import com.example.gab.babylove.utils.ExitDialog;
import com.example.gab.babylove.view.LuckPanLayout;
import com.example.gab.babylove.view.RotatePan;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.JumpUtils;

import butterknife.BindView;

/**
 *  主方法
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.bt_star)
    Button bt_star;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        bt_star.setOnClickListener(v -> JumpUtils.jump(mContext, DialActivity.class,null));
    }
}
