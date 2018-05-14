package com.example.gab.babylove.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.SpfUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2018/1/15 0015.
 * WebView 相关内容
 */

public class WebViewActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.showError)
    TextView showError;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    String url;
    public static final String TIME_OUT_URL = "https://www.jianshu.com/";

    private List<String> list;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_webview;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        initView();
    }

    @OnClick({R.id.showError})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showError:
                mWebView.setVisibility(View.VISIBLE);
                showError.setVisibility(View.GONE);
                mWebView.reload();
                break;
        }
    }

    @Override
    public void reTry() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        url = getIntent().getStringExtra("UrlBean");
        //加快HTML网页加载完成的速度，等页面finish再加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWebView.getSettings().setLoadsImagesAutomatically(false);
        }
        mWebView.setHorizontalScrollBarEnabled(false);//水平不显示
        mWebView.setVerticalScrollBarEnabled(false); //垂直不显示
        mWebView.loadUrl(url);
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        //设置WebView支持javascript脚本
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);          //允许缩放
        webSettings.setBuiltInZoomControls(true);  //原网页基础上缩放
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 5.0 以后的WebView加载的链接为Https开头，但是链接里面的内容，比如图片为Http链接，加载图片方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //两者都可以
            webSettings.setMixedContentMode(webSettings.getMixedContentMode());
            //mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            //用网页的标题来设置自己的标题栏
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (null != title) {
                    toolbar.setTitle(title);
                } else {
                    toolbar.setTitle("资讯新闻");
                }
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar1.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar1.setProgress(newProgress);//设置进度值
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

            //在认证证书不被Android所接受的情况下，我们可以通过设置重写WebViewClient的onReceivedSslError方法在其中设置接受所有网站的证书来解决
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //super.onReceivedSslError(view, handler, error);注意一定要去除这行代码，否则设置无效。
                // handler.cancel();// Android默认的处理方式
                handler.proceed();// 接受所有网站的证书
                // handleMessage(Message msg);// 进行其他处理
            }

            //加快HTML网页加载完成的速度，等页面finish再加载图片
            @Override
            public void onPageFinished(WebView view, String url) {
                if (!mWebView.getSettings().getLoadsImagesAutomatically()) {
                    mWebView.getSettings().setLoadsImagesAutomatically(true);
                }
                int position = SpfUtils.getInt(WebViewActivity.this, url, 0);
                view.scrollTo(0, position);
            }

        });

        mWebView.setOnLongClickListener(v -> {
            WebView.HitTestResult result = ((WebView) v).getHitTestResult();
            if (null == result)
                return false;
            int type = result.getType();
            if (type == WebView.HitTestResult.UNKNOWN_TYPE)
                return false;
            // 这里可以拦截很多类型，我们只处理图片类型就可以了
            switch (type) {
                case WebView.HitTestResult.PHONE_TYPE: // 处理拨号
                    break;
                case WebView.HitTestResult.EMAIL_TYPE: // 处理Email
                    break;
                case WebView.HitTestResult.GEO_TYPE: // 地图类型
                    break;
                case WebView.HitTestResult.SRC_ANCHOR_TYPE: // 超链接
                    break;
                case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE:
                    break;
                case WebView.HitTestResult.IMAGE_TYPE: // 处理长按图片的菜单项
                    // 获取图片的路径
                    String saveImgUrl = result.getExtra();
                    // 跳转到图片详情页，显示图片
//                        Intent i = new Intent(MainActivity.this, ImageActivity.class);
//                        i.putExtra("imgUrl", saveImgUrl);
//                        startActivity(i);
                    break;
                default:
                    break;
            }
            return false;
        });
    }

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
     * （1） 为什么WebView打开一个页面，播放一段音乐，退出Activity时音乐还在后台播放？
     */
    //销毁WebView
    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.removeAllViews();
            //在5.1上如果不加上这句话就会出现内存泄露。这是5.1的bug
            // mComponentCallbacks导致的内存泄漏
            mWebView.setTag(null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        //CacheUtils是我自己封装的SharedPreferences保存工具类
        //记录上次访问的位置，这里的mArticleContent.aid是我的文章的id，
        //当然你可用你的文章url作为key，value为你的webview滑动位置即可
        if (mWebView != null) {
            int scrollY = mWebView.getScrollY();
            SpfUtils.putInt(this, url, scrollY);//保存访问的位置
        }
    }


    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();//返回上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出整个应用程序
    }

}
