package com.example.gab.babylove.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.ui.main.login.LoginActivity;
import com.example.gab.babylove.utils.AndroidShareUtils;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.SpfUtils;
import com.ggz.baselibrary.utils.T;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gab on 2018/1/15 0015.
 * WebView 相关内容
 */

public class WebViewActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.showError)
    TextView showError;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String mURl;
    private int mId;
    private static final String WEB_URL = "web_url";
    private static final String WEB_ID = "web_id";

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_webview;
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
            default:
                break;
        }
    }

    /**
     * 瞅啥瞅,没见过帅哥阿!
     *
     * @param context
     * @param url
     */
    public static void startWebActivity(Context context, String url, int Id) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WEB_URL, url);
        intent.putExtra(WEB_ID, Id);
        context.startActivity(intent);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
//        url = getIntent().getStringExtra("UrlBean");
        String tags = getIntent().getStringExtra("tags");//首页tag 需要带前缀网址
        int tag = getIntent().getIntExtra("tag", 1);
        mURl = getIntent().getStringExtra(WEB_URL);
        mId = getIntent().getIntExtra(WEB_ID, 0);
        //加快HTML网页加载完成的速度，等页面finish再加载图片
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.setHorizontalScrollBarEnabled(false);//水平不显示
        mWebView.setVerticalScrollBarEnabled(false); //垂直不显示
        if (tag == 1) {
            mWebView.loadUrl(ConfigUtils.getBaseUrl() + tags);
        } else {
            mWebView.loadUrl(mURl);
        }
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
                } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (url.contains("platformapi") && url.contains("startapp")) {
                        startAlipayActivity(url);
                    }
                } else {
                    mWebView.loadUrl(url);
                }
                return true;
//                if (url.contains("alipays://platformapi/startApp?")) {
//                    startAlipayActivity(url);
//                    // android  6.0 两种方式获取intent都可以跳转支付宝成功,7.1测试不成功
//                } else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M) && (url.contains("platformapi") && url.contains("startapp"))) {
//                    startAlipayActivity(url);
//                } else {
//                    mWebView.loadUrl(url);
//                }
//                return true;
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
            if (null == result) {
                return false;
            }
            int type = result.getType();
            if (type == WebView.HitTestResult.UNKNOWN_TYPE) {
                return false;
            }
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

    /**
     * 阿里支付
     *
     * @param url
     */
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
     * 收藏
     *
     * @param id
     */
    @SuppressLint("CheckResult")
    private void collectArticle(int id) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getCollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        mKProgressHUD.dismiss();
                        T.showShort(getString(R.string.collection_success));
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    /**
     * 创建菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_web_more, menu);
        return true;
    }

    /**
     * 菜单条目选择器
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.web_share:
                AndroidShareUtils.shareWeChatFriend(this, "WanAndroid", mURl, AndroidShareUtils.TEXT, null);
                break;
            case R.id.web_collection:
                if (SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin)) {
                    collectArticle(mId);
                } else {
                    JumpUtils.jumpFade(this, LoginActivity.class, null);
                    T.showShort(R.string.collect_login);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 通过反射 让 menu 显示 icon
     *
     * @param featureId
     * @param menu
     * @return
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    /**
     * 重写 Activity 的 finish ⽅法, 并调⽤ overridePendingTransition ⽅法，解决退出动画⽆效
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    @Override
    public void onPause() {
        super.onPause();
        //CacheUtils是我自己封装的SharedPreferences保存工具类
        //记录上次访问的位置，这里的mArticleContent.aid是我的文章的id，
        //当然你可用你的文章url作为key，value为你的webview滑动位置即可
        if (mWebView != null) {
            int scrollY = mWebView.getScrollY();
            SpfUtils.putInt(this, mURl, scrollY);//保存访问的位置
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
