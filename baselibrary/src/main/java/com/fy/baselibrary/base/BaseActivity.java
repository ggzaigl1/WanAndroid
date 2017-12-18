package com.fy.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.fy.baselibrary.R;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.retrofit.DaggerRequestComponent;
import com.fy.baselibrary.retrofit.RequestComponent;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.statuslayout.OnRetryListener;
import com.fy.baselibrary.statuslayout.OnShowHideViewListener;
import com.fy.baselibrary.statuslayout.RootFrameLayout;
import com.fy.baselibrary.statuslayout.StatusLayoutManager;
import com.fy.baselibrary.utils.FileUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.cache.ACache;

import javax.inject.Inject;

/**
 * Activity 基类，统一处理activity界面样式，多状态视图切换
 * <p/>
 * Created by fangs on 2017/4/1.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected BaseActivity mContext;

    /** 使用默认头部布局 */
    protected static final int USE_SON_LAYOUT = 0;
    protected ACache mCache;
    protected StatusLayoutManager slManager;
    protected TextView tvTitle;
    protected TextView tvBack;
//    protected TextView tvClose;
    protected TextView tvMenu;
    protected ImageView imgDropDown;

    private static final int MIN_CLICK_DELAY_TIME = 3000;
    private static long lastClickTime;
    public static String path = FileUtils.getSDCardPath()+ "01.png";//查房界面 图片保存地址

    @Inject
    protected ApiService mConnService;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        RequestComponent component = DaggerRequestComponent.builder().build();
        component.inJect(this);
        mCache = ACache.get(this);
        mContext = this;

        if (getContentView() != 0) {
            setContentView(getContentView());

//            RootFrameLayout viewContent = findViewById(R.id.viewContent);
            //将继承 TopBarBaseActivity 的布局解析到 FrameLayout 里面
//            initSLManager(viewContent);

//            ViewStub vStubTitleBar = findViewById(R.id.vStubTitleBar);
//            if (getHeadView() == USE_SON_LAYOUT) {
//                vStubTitleBar.inflate();
//                initTitleBar();
//            } else if (getHeadView() > USE_SON_LAYOUT) {
//                vStubTitleBar.setLayoutResource(getHeadView());
//                vStubTitleBar.inflate();
//            }
        }

        super.onCreate(savedInstanceState);

        init(savedInstanceState);

        setStatusBarType();

        mConnService.toString();
    }

    /**
     * 获取自定义 ContentView
     * @return
     */
    protected abstract int getContentView();

    /**
     * 初始化
     * @param savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 头部布局
     * @return
     */
    protected int getHeadView() {
        return USE_SON_LAYOUT;
    }

    /**
     * 设置状态栏类型
     * @return
     */
    protected void setStatusBarType(){
        MdStatusBarCompat.setOrdinaryToolBar(this, R.color.alphaHeadBg);
//        MdStatusBarCompat.setImageTransparent(this);
    }

    /**
     * 重试
     */
    protected void reTry(){}

    /**
     * 设置 不同情况下 界面显示内容
     * @param flag
     */
    protected void setStatusLayout(int flag){
        switch (flag){
            case RootFrameLayout.LAYOUT_LOADING_ID:
                slManager.showLoading();
                break;
            case RootFrameLayout.LAYOUT_CONTENT_ID:
                slManager.showContent();
                break;
            case RootFrameLayout.LAYOUT_ERROR_ID:
                slManager.showError();
                break;
            case RootFrameLayout.LAYOUT_NETWORK_ERROR_ID:
                slManager.showNetWorkError();
                break;
            case RootFrameLayout.LAYOUT_EMPTYDATA_ID:
                slManager.showEmptyData();
                break;
        }
    }

    /**
     * 设置 多状态视图 管理器
     * @param viewContent
     */
    protected void initSLManager(RootFrameLayout viewContent) {
        slManager = StatusLayoutManager.newBuilder(this)
                .setRootLayout(viewContent)
                .contentView(getContentView())
                .loadingView(R.layout.activity_loading)
                .errorView(R.layout.activity_error)
                .netWorkErrorView(R.layout.activity_networkerror)
                .emptyDataView(R.layout.activity_emptydata)
                .retryViewId(R.id.tvTry)
                .onShowHideViewListener(new OnShowHideViewListener() {
                    @Override
                    public void onShowView(View view, int id) {
                    }
                    @Override
                    public void onHideView(View view, int id) {
                    }
                }).onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry() {
                        reTry();
                    }
                }).build();

        setStatusLayout(RootFrameLayout.LAYOUT_CONTENT_ID);
    }

    /**
     * 初始化标题栏
     */
    protected void initTitleBar() {
        tvTitle = findViewById(R.id.tvTitle);
        tvBack = findViewById(R.id.tvBack);
        tvMenu = findViewById(R.id.tvMenu);
        imgDropDown = findViewById(R.id.imgDropDown);
//        tvClose = findViewById(R.id.tvClose);
        tvBack.setOnClickListener(this);
//        tvClose.setOnClickListener(this);
        tvMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tvBack) {
            JumpUtils.exitActivity(this);
        }
//        else if (i == R.id.tvClose){
//            JumpUtils.jump(this, "com.hjy.mobiledoctor.patientlist.PatientListActivity", null);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isSaveInstanceState = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean isSaveInstanceState = false;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isSaveInstanceState = true;
        L.e("Activity", "onSaveInstanceState");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            JumpUtils.exitActivity(this);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置activity标题
     *
     * @param resouseId
     */
    protected void setActTitle(int resouseId) {
        if (null != tvTitle) {
            tvTitle.setText(resouseId);
        }
    }

    /**
     * 设置activity 标题栏 左边按钮 文本
     *
     * @param resouseId
     */
    protected void setActBack(int resouseId) {
        if (null != tvBack) {
            tvBack.setText(resouseId);
        }
    }

    /** 隐藏activity 标题栏 左边按钮 */
    protected void hideBack(){
        if (null != tvBack) {
            tvBack.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置activity 标题栏 右边按钮 文本
     *
     * @param resouseId
     */
    protected void setActMenu(int resouseId) {
        if (null != tvMenu) {
            tvMenu.setText(resouseId);
        }
    }

    /**
     * 隐藏activity 标题栏 右边按钮
     */
    protected void hideMenu() {
        if (null != tvMenu) {
            tvMenu.setVisibility(View.INVISIBLE);
        }
    }

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

}
