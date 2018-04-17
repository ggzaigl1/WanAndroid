package com.example.gab.babylove.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.T;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;

/**
 * Created by Gab on 2018/2/24 0024.
 * AgentWeb
 */

public class AgentWebActivity extends BaseActivity {

    AgentWeb mAgentWeb;
    LinearLayout mLinearLayout;
    private String mUrl;

    @Override
    protected int getContentView() {
        return R.layout.activity_agentwebview;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mLinearLayout = findViewById(R.id.mLinearLayout);
        tvTitle.setText("内容详情");
        mUrl = getIntent().getStringExtra("UrlBean");
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(new ChromeClientCallbackManager.ReceivedTitleCallback() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                            tvTitle.setText("内容详情");
                    }
                })//设置 Web 页面的 title 回调
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setSecurityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()//
                .ready()
                .go(mUrl);
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
