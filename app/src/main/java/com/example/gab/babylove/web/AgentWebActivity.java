package com.example.gab.babylove.web;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.example.gab.babylove.R;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;

import butterknife.BindView;

/**
 * Created by Gab on 2018/2/24 0024.
 * AgentWeb
 */

public class AgentWebActivity extends AppCompatActivity implements IBaseActivity {

    AgentWeb mAgentWeb;
    LinearLayout mLinearLayout;
    private String mUrl;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_agentwebview;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        mLinearLayout = findViewById(R.id.mLinearLayout);
        mUrl = getIntent().getStringExtra("UrlBean");
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(new ChromeClientCallbackManager.ReceivedTitleCallback() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        toolbar.setTitle(title);
                    }
                })//设置 Web 页面的 title 回调
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setSecurityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()//
                .ready()
                .go(mUrl);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }


    //WebViewClient
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    };
    //WebChromeClient
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
        }
    };

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause(); //暂停应用内所有 WebView ， 需谨慎。
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
