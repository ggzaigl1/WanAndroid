package com.example.gab.babylove.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.GsonUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.cache.ACache;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gab on 2018/2/7 0007.
 *
 */
public class StoreWebViewActivity extends AppCompatActivity {

    WebView mWebView;
    ProgressBar progressBar1;
    TextView tvMenu, tvBack, tvTitle, showError;

//    public static final String URL = "http://kmjkzx.kmwlyy.com/shopApi/o2o/index/userAuthenticationGet";//正式
    public static final String URL = "www.baidu,com";//正式
//    public static final String URL = "http://testkmjkzx.kmwlyy.com/web/shop/o2o/index/userAuthenticationGet";//测试

    public static final String TIME_OUT_URL = "http://testkmjkzx.kmwlyy.com/web/shop/wx/" +
            "userAuthentication?orgId=WHXS&openId=test2&" +
            "sex=1&" +
            "tel=13751096562&" +
            "groupId=test2&" +
            "email=1810293364@0qq.com&" +
            "birthday=1984-09-15&" +
            "trueName=maituwang&" +
            "nickName=maituwang&" +
            "city=深圳&" +
            "address=广东湛江&" +
            "sign=xxxx";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_three);

        mWebView = findViewById(R.id.web_view);
        progressBar1 = findViewById(R.id.progressBar1);
        showError = findViewById(R.id.showError);
        tvMenu = findViewById(R.id.tvMenu);
        tvBack = findViewById(R.id.tvBack);
        tvTitle = findViewById(R.id.tvTitle);
        tvMenu.setVisibility(View.GONE);
        tvBack.setVisibility(View.GONE);
//         Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        //设置WebView支持javascript脚本
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setSupportZoom(true);          //允许缩放
        webSettings.setBuiltInZoomControls(true);  //原网页基础上缩放
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        init();
    }

    private Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("orgId", "WHXS");
        params.put("time", System.currentTimeMillis() + "");

//        LoginBean.StudentBean studentInfo = (LoginBean.StudentBean) ACache.get(this).getAsObject(ConstantUtils.student);
//        if (null != studentInfo) {
//            params.put("openId", studentInfo.getStudentid());
//            params.put("sex", studentInfo.getSex());
//            params.put("telPhone", studentInfo.getJiazhangphone());
//            params.put("trueName", studentInfo.getName());
////            params.put("email", "1810293364@0qq.com");
////            params.put("birthday", "1984-09-15");
////            params.put("trueName", "maituwang");
////            params.put("nickName", "maituwang");
////            params.put("city", "深圳");
////            params.put("address", "广东湛江");
////            params.put("sign", "xxxx");
//        }

        return params;
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        showError.setOnClickListener(v -> {
            mWebView.setVisibility(View.VISIBLE);
            showError.setVisibility(View.GONE);
            mWebView.reload();
        });

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar1.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar1.setProgress(newProgress);//设置进度值
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (null != title) {
                    tvTitle.setText(title);
                } else {
                    tvTitle.setText("");
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("alipays://platformapi/startApp?")) {
                    startAlipayActivity(url);
                    // android  6.0 两种方式获取intent都可以跳转支付宝成功,7.1测试不成功
                } else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                        && (url.contains("platformapi") && url.contains("startapp"))) {
                    startAlipayActivity(url);
                } else {
                    mWebView.loadUrl(url);
                }
                return true;

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                mWebView.setVisibility(View.GONE);
                showError.setVisibility(View.VISIBLE);
                super.onReceivedError(view, request, error);
            }
        });

//        String params = GsonUtils.mapToJsonStr(getParams());

//        try { //get请求
//            mWebView.loadUrl(URL + "?data=" + URLEncoder.encode(SecurityUtils.aesEncrypt(params), "UTF-8"));//get
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        mWebView.loadUrl(URL);//get
    }

    // 调起支付宝并跳转到指定页面
    private void startAlipayActivity(String url) {
        Intent intent;
        try {
            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setComponent(null);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回键返回上一网页
     */
    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            if (mWebView.getUrl().equals(URL)){
                JumpUtils.jump(this, MainActivity.class, null);
                SpfUtils.saveBooleanToSpf("loginShowHome", true);
            }else {
                mWebView.goBack();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            //在5.1上如果不加上这句话就会出现内存泄露。这是5.1的bug
            // mComponentCallbacks导致的内存泄漏
            mWebView.setTag(null);
            mWebView.clearHistory();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
