package com.example.gab.babylove;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.ui.main.about.AboutActivity;
import com.example.gab.babylove.ui.main.other.BelleActivity;
import com.example.gab.babylove.ui.main.collect.MyCollectActivity;
import com.example.gab.babylove.ui.main.activity.NewProjectActivity;
import com.example.gab.babylove.ui.main.official.OfficialAccountActivity;
import com.example.gab.babylove.ui.main.ornamental.OrnamentalListContextActivity;
import com.example.gab.babylove.ui.main.other.PhotoViewActivity;
import com.example.gab.babylove.ui.main.search.SearchArticleActivity;
import com.example.gab.babylove.ui.main.other.ToolsActivity;
import com.example.gab.babylove.ui.main.activity.WebsiteActivity;
import com.example.gab.babylove.ui.main.fragment.HomeFragment;
import com.example.gab.babylove.ui.main.login.LoginActivity;
import com.example.gab.babylove.ui.navigation.fragment.NavigationViewFragment;
import com.example.gab.babylove.ui.project.StarFragment;
import com.example.gab.babylove.ui.view.fragment.ViewFragment;
import com.example.gab.babylove.utils.AndroidShareUtils;
import com.example.gab.babylove.utils.NightModeConfig;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.ResourceUtils;
import com.ggz.baselibrary.utils.SpfUtils;
import com.ggz.baselibrary.utils.T;
import com.ggz.baselibrary.utils.cache.ACache;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主方法
 *
 * @author 初夏小溪
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.bottom_navigation)
    BottomNavigationBar mBottomNavigation;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_head_right)
    ImageView mIvHeadRight;

    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private ViewFragment mViewFragment;
    private NavigationViewFragment mNavigationViewFragment;
    private StarFragment mStarFragment;

    //当前的fragment
    Fragment mFragment;

    //保存点击的时间
    private long exitTime = 0;

    public TextView mTvNevHeaderLogin;
    public TextView mTvNevHeaderTitle;


    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return R.layout.activity_main;
    }

    // TODO: 2018/11/9 0009 刘海屏适配;

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        //设置状态栏透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        mIvHeadRight.setVisibility(View.VISIBLE);
        mFragmentManager = getSupportFragmentManager();
        //初始化 主要的fragment 的
        mHomeFragment = new HomeFragment();
        mViewFragment = new ViewFragment();
        mNavigationViewFragment = new NavigationViewFragment();
        mStarFragment = new StarFragment();

        initBottomNavigation();
        switchContent(mHomeFragment);
        setSupportActionBar(mToolbar);
        //NavigationView 设置条目点击事前
        mNavigation.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //获取Navigation header控件方法1:
        View headerView = mNavigation.getHeaderView(0);
        ImageView ivNevHeader = headerView.findViewById(R.id.nev_header_imageView);
        mTvNevHeaderTitle = headerView.findViewById(R.id.nev_header_tv_title);
        mTvNevHeaderLogin = headerView.findViewById(R.id.nev_header_tv_login);

        //获取Navigation header控件方法2:
//        View headerView = mNavigation.inflateHeaderView(R.layout.nav_header_main);//
//        ImageView nev_header_imageView = headerView.findViewById(R.id.nev_header_imageView);
//        mTvNevHeaderTitle = headerView.findViewById(R.id.nev_header_tv_title);
        //点击登录
//        nev_header_tv_login = headerView.findViewById(R.id.nev_header_tv_login);

        ivNevHeader.setOnClickListener(v -> JumpUtils.jumpFade(MainActivity.this, PhotoViewActivity.class, null));
        mTvNevHeaderLogin.setOnClickListener(v -> {
            boolean isLogin = SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin);
            if (isLogin) {
                new MaterialDialog.Builder(this)
                        .cancelable(false)
                        .title(R.string.system_title)
                        .content(R.string.system_content)
                        .positiveText(R.string.ok)
                        .onPositive((dialog, which) -> {
                            mTvNevHeaderTitle.setText(R.string.notLogin);
                            mTvNevHeaderLogin.setText(R.string.clickLogin);
                            ACache mCache = ACache.get(ConfigUtils.getAppCtx());
                            mCache.clear();
                            SpfUtils.clear();
                            mHomeFragment.mRefreshLayout.autoRefresh();
                        }).negativeText(R.string.cancel)
                        .onNegative((dialog, which) -> dialog.dismiss()).show();
            } else {
                JumpUtils.jumpFade(MainActivity.this, LoginActivity.class, null);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isLogin = SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin);
        mTvNevHeaderTitle.setText(isLogin ? SpfUtils.getSpfSaveStr(ConstantUtils.userName) : ResourceUtils.getStr(R.string.notLogin));
        mTvNevHeaderLogin.setText(isLogin ? R.string.login_exit : R.string.clickLogin);
    }


    /**
     * 设置底部导航栏
     */
    private void initBottomNavigation() {
        //设置导航栏背景模式 setBackgroundStyle（）
        //设置BottomNavigationItem颜色 setActiveColor, setInActiveColor, setBarBackgroundColor
        mBottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setMode(BottomNavigationBar.MODE_SHIFTING)
                //设置Item未选中颜色方法
                .setInActiveColor("#2c2c2c")
                .addItem(new BottomNavigationItem(R.drawable.vector_home, getString(R.string.nav_home_title)).setActiveColorResource(R.color.pink))
                .addItem(new BottomNavigationItem(R.drawable.vector_view_headline, getString(R.string.nav_system_title)).setActiveColorResource(R.color.pink))
                .addItem(new BottomNavigationItem(R.drawable.vector_live_tv, getString(R.string.nav_view_title)).setActiveColorResource(R.color.pink))
                .addItem(new BottomNavigationItem(R.drawable.vector_find, getString(R.string.nav_project_title)).setActiveColorResource(R.color.pink))
                .setFirstSelectedPosition(0)
                .setTabSelectedListener(this)
                .initialise();
    }

    /**
     * 动态添加fragment，不会重复创建fragment
     *
     * @param fragment 将要加载的fragment
     */
    private void switchContent(Fragment fragment) {
        if (mFragment != fragment) {
            if (!fragment.isAdded()) {
                if (mFragment != null) {
                    mFragmentManager.beginTransaction().hide(mFragment).commit();
                }
                mFragmentManager.beginTransaction().add(R.id.fl_content, fragment).commit();
            } else {
                mFragmentManager.beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                switchContent(mHomeFragment);
                break;
            case 1:
                switchContent(mViewFragment);
                break;
            case 2:
                switchContent(mNavigationViewFragment);
                break;
            case 3:
                switchContent(mStarFragment);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @OnClick({R.id.iv_head_right})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head_right:
                JumpUtils.jumpFade(MainActivity.this, SearchArticleActivity.class, null);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_belle:
                //美图欣赏
                JumpUtils.jumpFade(this, BelleActivity.class, null);
                break;
            case R.id.nav_website:
                //常用网站
                JumpUtils.jumpFade(this, WebsiteActivity.class, null);
                break;
            case R.id.nav_official_account:
                //公众号
                JumpUtils.jumpFade(this, OfficialAccountActivity.class, null);
                break;
            case R.id.nav_list_project:
                //最新项目
                JumpUtils.jumpFade(this, NewProjectActivity.class, null);
                break;
            case R.id.nav_collect:
                //我的收藏
                if (SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin)) {
                    JumpUtils.jumpFade(this, MyCollectActivity.class, null);
                } else {
                    JumpUtils.jumpFade(this, LoginActivity.class, null);
                    T.showShort(getString(R.string.main_login_view));
                }
                break;
            case R.id.nav_night:
                //夜间模式
                //获取当前的模式，设置相反的模式，这里只使用了，夜间和非夜间模式
                int currentMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (currentMode != Configuration.UI_MODE_NIGHT_YES) {
                    //保存夜间模式状态,Application中可以根据这个值判断是否设置夜间模式
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //ThemeConfig主题配置，这里只是保存了是否是夜间模式的boolean值
                    NightModeConfig.getInstance().setNightMode(getApplicationContext(), true);
                    item.setTitle("夜间模式");
                    item.setIcon(R.drawable.vector_menu_night_mode);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    NightModeConfig.getInstance().setNightMode(getApplicationContext(), false);
                    item.setTitle("日间模式");
                    item.setIcon(R.drawable.vector_menu_daytime_mode);
                }
                recreate();//需要recreate才能生效
                break;
            case R.id.nav_ornamental:
                //强身健体
                JumpUtils.jumpFade(this, OrnamentalListContextActivity.class, null);
                break;
            case R.id.nav_about:
                //关于我们
                JumpUtils.jumpFade(this, AboutActivity.class, null);
                break;
            case R.id.nav_share:
                //分享二维码
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_wanandroid);
                AndroidShareUtils.shareAllMsg(ConfigUtils.getAppCtx(), "一起玩Android", "https://www.pgyer.com/6osT", AndroidShareUtils.DRAWABLE, bitmap);
                break;
            case R.id.nav_manage:
//            工具类
                JumpUtils.jumpFade(this, ToolsActivity.class, null);
                break;
            case R.id.nav_exit:
                JumpUtils.exitApp(this, MainActivity.class);
                break;
            default:
                break;
        }
//        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main_search, menu);
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setQueryHint(getString(R.string.search_knowledge));
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Bundle bundle = new Bundle();
//                bundle.putString("query", query);
//                JumpUtils.jumpFadeFade(MainActivity.this, SearchMainActivity.class, bundle);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        return true;
//    }


    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //保存点击的时间
            long time = 2000;
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawer(GravityCompat.START);
            } else if ((System.currentTimeMillis() - exitTime) >= time) {
                T.CustomToast.INSTANCE.showToast(R.string.exit_app);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
