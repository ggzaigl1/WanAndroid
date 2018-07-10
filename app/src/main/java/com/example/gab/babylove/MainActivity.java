package com.example.gab.babylove;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.gab.babylove.ui.main.activity.AboutActivity;
import com.example.gab.babylove.ui.main.activity.BelleActivity;
import com.example.gab.babylove.ui.main.activity.MyCollectActivity;
import com.example.gab.babylove.ui.main.activity.OrnamentalListContextActivity;
import com.example.gab.babylove.ui.main.activity.PhotoViewActivity;
import com.example.gab.babylove.ui.main.activity.SearchActivity;
import com.example.gab.babylove.ui.main.activity.ToolsActivity;
import com.example.gab.babylove.ui.main.activity.WebsiteActivity;
import com.example.gab.babylove.ui.main.fragment.HomeFragment;
import com.example.gab.babylove.ui.main.login.LoginActivity;
import com.example.gab.babylove.ui.navigation.fragment.NavigationViewFragment;
import com.example.gab.babylove.ui.news.fragment.NewsFragment;
import com.example.gab.babylove.ui.project.fragment.StarFragment;
import com.example.gab.babylove.utils.NightModeConfig;
import com.example.gab.babylove.utils.Util;
import com.ggz.baselibrary.application.BaseApp;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.ResourceUtils;
import com.ggz.baselibrary.utils.SpfUtils;
import com.ggz.baselibrary.utils.SystemUtils;
import com.ggz.baselibrary.utils.ToastUtils;
import com.ggz.baselibrary.utils.cache.ACache;

import butterknife.BindView;

/**
 * 主方法
 * @author 55204
 */
public class MainActivity extends AppCompatActivity implements IBaseActivity, BottomNavigationBar.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private NewsFragment mNewsFragment;
    private NavigationViewFragment mNavigationViewFragment;
    private StarFragment mStarFragment;
    private Fragment mCurrentFrag; //当前的fragment
    private long exitTime = 0; //保存点击的时间
    private long Time = 2000; //保存点击的时间
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.bottom_navigation)
    BottomNavigationBar mBottomNavigation;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.toolbar)

    public Toolbar mToolbar;
    TextView tvLogin;
    TextView tvName;

    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return R.layout.activity_main;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBarForDrawer(this, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {

        mFragmentManager = getSupportFragmentManager();
        //初始化 主要的fragment 的
        mHomeFragment = new HomeFragment();
        mNewsFragment = new NewsFragment();
        mNavigationViewFragment = new NavigationViewFragment();
        mStarFragment = new StarFragment();
        initBottomNavigation();
        switchContent(mHomeFragment);
        setSupportActionBar(mToolbar);
        mNavigation.setNavigationItemSelectedListener(this);//NavigationView 设置条目点击事前

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        View headerView = mNavigation.inflateHeaderView(R.layout.nav_header_main);//
        ImageView imageView = headerView.findViewById(R.id.headerView);
        tvLogin = headerView.findViewById(R.id.tv_login);//点击登录
        tvName = headerView.findViewById(R.id.tv_name);
        imageView.setOnClickListener(v -> JumpUtils.jump(MainActivity.this, PhotoViewActivity.class, null));

        tvLogin.setOnClickListener(v -> {
            boolean isLogin = SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin);
            if (isLogin) {
                new AlertDialog.Builder(this)
                        .setTitle(R.string.system_title).setMessage(R.string.system_content)
                        .setPositiveButton(R.string.ok, (dialog, which) -> {
                            tvName.setText(R.string.notLogin);
                            tvLogin.setText(R.string.clickLogin);
                            ACache mCache = ACache.get(BaseApp.getAppCtx());
                            mCache.clear();
                            SpfUtils.clear();
                            mHomeFragment.mRefreshLayout.autoRefresh();
                        }).setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss()).create().show();
            } else {
                JumpUtils.jump(MainActivity.this, LoginActivity.class, null);
            }
        });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isLogin = SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin);
        tvName.setText(isLogin ? SpfUtils.getSpfSaveStr(ConstantUtils.userName) : ResourceUtils.getStr(R.string.notLogin));
        tvLogin.setText(isLogin ? R.string.login_exit : R.string.clickLogin);

    }

    private void initBottomNavigation() {
        //设置导航栏背景模式 setBackgroundStyle（）
        //设置BottomNavigationItem颜色 setActiveColor, setInActiveColor, setBarBackgroundColor
        mBottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setMode(BottomNavigationBar.MODE_SHIFTING)
                .addItem(new BottomNavigationItem(R.mipmap.ic_home, getString(R.string.nav_home_title)).setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_view_headline, getString(R.string.nav_system_title)).setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_live_tv, getString(R.string.nav_view_title)).setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.icon_find, getString(R.string.nav_project_title)).setActiveColorResource(R.color.colorPrimary))
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
        if (mCurrentFrag != fragment) {
            if (!fragment.isAdded()) {
                if (mCurrentFrag != null) {
                    mFragmentManager.beginTransaction().hide(mCurrentFrag).commit();
                }
                mFragmentManager.beginTransaction().add(R.id.fl_content, fragment).commit();
            } else {
                mFragmentManager.beginTransaction().hide(mCurrentFrag).show(fragment).commit();
            }
            mCurrentFrag = fragment;
        }
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                switchContent(mHomeFragment);
                break;
            case 1:
                switchContent(mNewsFragment);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_belle:
                //美图欣赏
                JumpUtils.jump(this, BelleActivity.class, null);
                break;
            case R.id.nav_website:
                //常用网站
                JumpUtils.jump(this, WebsiteActivity.class, null);
                break;
            case R.id.nav_collect:
                //我的收藏
                if (SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin)) {
                    JumpUtils.jump(this, MyCollectActivity.class, null);
                } else {
                    JumpUtils.jump(this, LoginActivity.class, null);
                    ToastUtils.showShort("登录之后才能查看已收藏内容");
                }
                break;
            case R.id.nav_exit:
                SystemUtils.ExitSystem();
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
                    ToastUtils.showShort("开启夜间模式");
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    NightModeConfig.getInstance().setNightMode(getApplicationContext(), false);
                    ToastUtils.showShort("关闭夜间模式");
                }
                recreate();//需要recreate才能生效

                break;
            case R.id.nav_ornamental:
                //强身健体
                JumpUtils.jump(this, OrnamentalListContextActivity.class, null);
                break;
            case R.id.nav_about:
                //关于我们
            JumpUtils.jump(this, AboutActivity.class, null);
//                JumpUtils.jump(this, CameraActivity.class, null);
//                JumpUtils.jump(this, MyThreadActivity.class, null);
                break;
            case R.id.nav_share:
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, "分享");
                startActivity(Intent.createChooser(textIntent, "分享"));
                break;
            case R.id.nav_manage:
//            工具类
                JumpUtils.jump(this, ToolsActivity.class, null);
                break;
            default:
                break;
        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("搜索知识点");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle = new Bundle();
                bundle.putString("query", query);
                JumpUtils.jump(MainActivity.this, SearchActivity.class, bundle);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;

    }


    //退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawer(GravityCompat.START);
            } else if ((System.currentTimeMillis() - exitTime) >= Time) {
//                Snackbar.make(mDrawer, R.string.exit_app, Snackbar.LENGTH_SHORT)
//                        .setActionTextColor(ContextCompat.getColor(this, R.color.white))
//                        .show();
                Util.CustomToast.INSTANCE.showToast(MainActivity.this, R.string.exit_app);
                ToastUtils.showLong(R.string.exit_app);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
