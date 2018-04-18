package com.example.gab.babylove;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.gab.babylove.about.AboutActivity;
import com.example.gab.babylove.activity.PersonalCenterActivity;
import com.example.gab.babylove.activity.PhotoViewActivity;
import com.example.gab.babylove.activity.ToolsActivity;
import com.example.gab.babylove.fragment.HomeFragment;
import com.example.gab.babylove.fragment.NewsFragment;
import com.example.gab.babylove.fragment.StarFragment;
import com.example.gab.babylove.fragment.PrettyPicturesFragment;
import com.example.gab.babylove.login.LoginActivity;
import com.example.gab.babylove.tbs.FileBrowsingActivity;
import com.example.gab.babylove.utils.Util;
import com.fy.baselibrary.application.BaseApp;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.SystemUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.cache.ACache;

import butterknife.BindView;

/**
 * 主方法
 */
public class MainActivity extends AppCompatActivity implements IBaseActivity, BottomNavigationBar.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private NewsFragment mNewsFragment;
    private PrettyPicturesFragment mPrettyPicturesFragment;
    private StarFragment mOtherFragment;
    private Fragment mCurrentFrag; //当前的fragment
    private long exitTime = 0; //保存点击的时间
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.bottom_navigation)
    BottomNavigationBar mBottomNavigation;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    TextView Tv_Login;
    TextView Tv_Name;

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
        MdStatusBar.setColorBar(this, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        //初始化 主要的fragment 的
        mHomeFragment = new HomeFragment();
        mNewsFragment = new NewsFragment();
        mPrettyPicturesFragment = new PrettyPicturesFragment();
        mOtherFragment = new StarFragment();
        initBottomNavigation();
        switchContent(mHomeFragment);
        mNavigation.setNavigationItemSelectedListener(this);//NavigationView 设置条目点击事前
        View headerView = mNavigation.inflateHeaderView(R.layout.nav_header_main);//
        ImageView imageView = headerView.findViewById(R.id.headerView);
        Tv_Login = headerView.findViewById(R.id.tv_login);//点击登录
        Tv_Name = headerView.findViewById(R.id.tv_name);
        imageView.setOnClickListener(v -> JumpUtils.jump(MainActivity.this, PhotoViewActivity.class, null));

        Tv_Login.setOnClickListener(v -> {
            boolean isLogin = SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin);
            if (isLogin) {
                Tv_Name.setText(R.string.notLogin);
                Tv_Login.setText(R.string.clickLogin);
                ACache mCache = ACache.get(BaseApp.getAppCtx());
                mCache.clear();
                SpfUtils.clear();
            } else {
                JumpUtils.jump(MainActivity.this, LoginActivity.class, null);
            }
        });
//todo 优化string资源 删除无用的内容,调试1.3 1.4接口 整体项目删除无用资源
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
        Tv_Name.setText(isLogin ? SpfUtils.getSpfSaveStr(ConstantUtils.userName) : ResourceUtils.getStr(R.string.notLogin));
        Tv_Login.setText(isLogin ? R.string.exitLogin : R.string.clickLogin);
    }


    private void initBottomNavigation() {
        //设置导航栏背景模式 setBackgroundStyle（）
        //设置BottomNavigationItem颜色 setActiveColor, setInActiveColor, setBarBackgroundColor
        /**
         * 组合
         * MODE_FIXED+BACKGROUND_STYLE_STATIC 效果
         * MODE_FIXED+BACKGROUND_STYLE_RIPPLE 效果
         * MODE_SHIFTING+BACKGROUND_STYLE_STATIC 效果
         * MODE_SHIFTING+BACKGROUND_STYLE_RIPPLE 效果
         */
        mBottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .addItem(new BottomNavigationItem(R.mipmap.ic_home, getString(R.string.nav_00_title)).setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_view_headline, getString(R.string.nav_01_title)).setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_live_tv, getString(R.string.nav_02_title)).setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.icon_find, getString(R.string.nav_03_title)).setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .setTabSelectedListener(this)
                .initialise();
    }

    /**
     * 动态添加fragment，不会重复创建fragment
     *
     * @param to 将要加载的fragment
     */
    private void switchContent(Fragment to) {
        if (mCurrentFrag != to) {
            if (!to.isAdded()) {
                if (mCurrentFrag != null) {
                    mFragmentManager.beginTransaction().hide(mCurrentFrag).commit();
                }
                mFragmentManager.beginTransaction().add(R.id.fl_content, to).commit();
            } else {
                mFragmentManager.beginTransaction().hide(mCurrentFrag).show(to).commit();
            }
            mCurrentFrag = to;
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
                switchContent(mPrettyPicturesFragment);
                break;
            case 3:
                switchContent(mOtherFragment);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            JumpUtils.jump(this, PersonalCenterActivity.class, null);
            // Handle the camera action
        } else if (id == R.id.nav_personal_center) {
            JumpUtils.jump(this, FileBrowsingActivity.class, null);
        } else if (id == R.id.nav_share) {
            T.showShort("nav_share");
        } else if (id == R.id.nav_send) {
            T.showShort("nav_send");
        } else if (id == R.id.nav_exit) {
            SystemUtils.ExitSystem();
        } else if (id == R.id.nav_manage) {
            JumpUtils.jump(this, ToolsActivity.class, null);
        } else if (id == R.id.nav_about) {
            JumpUtils.jump(this, AboutActivity.class, null);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawer(GravityCompat.START);
            } else if ((System.currentTimeMillis() - exitTime) >= 2000) {
                Util.CustomToast.INSTANCE.showToast(MainActivity.this, R.string.exit_app);
//                T.showLong(R.string.exit_app);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
