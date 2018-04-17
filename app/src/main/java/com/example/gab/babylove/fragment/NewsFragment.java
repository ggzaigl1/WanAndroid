package com.example.gab.babylove.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.activity.AgentWebActivity;
import com.example.gab.babylove.activity.FingerprintMainActivity;
import com.example.gab.babylove.activity.TraceListActivity;
import com.example.gab.babylove.activity.WebViewActivity;
import com.example.gab.babylove.animation.AnimationActivity;
import com.example.gab.babylove.channelmanage.ChannelManageActivity;
import com.example.gab.babylove.widget.StateButton;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.JumpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/15 0015.
 * 新闻类 Fragment
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.statusView)
    View statusView;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvMenu)
    TextView tvMenu;
    @BindView(R.id.bt_OnTraceList)
    TextView bt_OnTraceList;
    @BindView(R.id.different_radius_test)
    StateButton radius;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        tvTitle.setText("杂七杂八鬼名堂");
        tvMenu.setVisibility(View.GONE);
        tvBack.setVisibility(View.GONE);
        MdStatusBarCompat.setStatusView(mContext, statusView);
        //设置四个角不同的圆角
        radius.setRadius(new float[]{0, 0, 20, 20, 40, 40, 60, 60});
    }

    @OnClick({R.id.bt_OnTraceList, R.id.text_webView, R.id.dash_test, R.id.stroke_test,
            R.id.different_radius_test, R.id.animation})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
            //指纹相关
            case R.id.stroke_test:
                JumpUtils.jump(mContext, FingerprintMainActivity.class, null);
                break;
            //动画
            case R.id.animation:
                JumpUtils.jump(mContext, AnimationActivity.class, null);
                break;
        }
    }
}
