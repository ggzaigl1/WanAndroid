package com.example.gab.babylove.activity;

import android.os.Bundle;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.animation.AnimationActivity;
import com.example.gab.babylove.channelmanage.ChannelManageActivity;
import com.example.gab.babylove.widget.StateButton;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.JumpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/4/17 0017.
 * SelectorButton
 */

public class SelectorButtonActivity extends BaseActivity {

    @BindView(R.id.different_radius_test)
    StateButton radius;

    @Override
    protected int getContentView() {
        return R.layout.activity_selector_button;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText("SelectorButton");
        //设置四个角不同的圆角
        radius.setRadius(new float[]{0, 0, 20, 20, 40, 40, 60, 60});
    }

    @OnClick({R.id.tvBack, R.id.bt_OnTraceList, R.id.text_webView, R.id.dash_test, R.id.stroke_test, R.id.different_radius_test, R.id.animation})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvBack:
                JumpUtils.exitActivity(this);
            case R.id.bt_OnTraceList:
                //模拟快递布局
                JumpUtils.jump(mContext, TraceListActivity.class, null);
                break;
            case R.id.text_webView:
                JumpUtils.jump(mContext, WebViewActivity.class, null);
//                JumpUtils.jump(mContext, StoreWebViewActivity.class, null);
                break;
            case R.id.different_radius_test:
//                JumpUtils.jump(mContext, CalendarViewActivity.class, null);
//                JumpUtils.jump(mContext, Camera2Activity.class, null);
                JumpUtils.jump(mContext, ChannelManageActivity.class, null);
                break;
            // AgentWeb 封装
            case R.id.dash_test:
                JumpUtils.jump(mContext, AgentWebActivity.class, null);
                break;
            //动画
            case R.id.animation:
                JumpUtils.jump(mContext, AnimationActivity.class, null);
                break;
        }
    }
}
