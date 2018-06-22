package com.example.gab.babylove.ui.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.web.AgentWebActivity;
import com.example.gab.babylove.web.WebViewActivity;
import com.example.gab.babylove.widget.StateButton;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.JumpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/4/17 0017.
 * SelectorButton
 */

public class SelectorButtonActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.different_radius_test)
    StateButton radius;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_selector_button;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        //设置四个角不同的圆角
        radius.setRadius(new float[]{0, 0, 20, 20, 40, 40, 60, 60});
    }

    @OnClick({R.id.bt_OnTraceList, R.id.text_webView, R.id.dash_test, R.id.stroke_test, R.id.different_radius_test})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_webView:
                JumpUtils.jump(this, WebViewActivity.class, null);
//                JumpUtils.jump(this, StoreWebViewActivity.class, null);
                break;
            // AgentWeb 封装
            case R.id.dash_test:
                JumpUtils.jump(this, AgentWebActivity.class, null);
                break;
            default:
                break;
        }
    }

    @Override
    public void reTry() {

    }
}
