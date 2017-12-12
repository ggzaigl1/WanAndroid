package com.example.gab.babylove;

import android.os.Bundle;
import android.widget.Button;

import com.example.gab.babylove.activity.DialActivity;
import com.example.gab.babylove.activity.PhotoViewActivity;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.JumpUtils;

import butterknife.BindView;

/**
 *  主方法
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.bt_star)
    Button bt_star;
    @BindView(R.id.bt_phontview)
    Button bt_phontview;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        bt_star.setOnClickListener(v -> JumpUtils.jump(mContext, DialActivity.class,null));
        bt_phontview.setOnClickListener(v -> JumpUtils.jump(mContext,PhotoViewActivity.class,null));
    }
}
