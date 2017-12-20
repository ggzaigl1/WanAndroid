package com.example.gab.babylove;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.gab.babylove.activity.PersonalCenterActivity;
import com.example.gab.babylove.activity.PhotoViewActivity;
import com.example.gab.babylove.activity.ToolsActivity;
import com.example.gab.babylove.fragment.HomeFragment;
import com.example.gab.babylove.fragment.NewsFragment;
import com.example.gab.babylove.fragment.OtherFragment;
import com.example.gab.babylove.fragment.WifeFragment;
import com.example.gab.babylove.login.LoginActivity;
import com.example.gab.babylove.tbs.FileBrowsingActivity;
import com.example.gab.babylove.utils.Util;
import com.example.gab.babylove.widget.AppLoading;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.NetRequest;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.statusbar.StatusBarUtils;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 主方法
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private NewsFragment mNewsFragment;
    private WifeFragment mWifeFragment;
    private OtherFragment mOtherFragment;
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
    @BindView(R.id.rl_content)
    LinearLayout rl_content;

    @Override
    protected int getHeadView() {
        return -1;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        StatusBarUtils.with(this).setDrawerLayoutContentId(true, R.id.rl_content).init();
        mFragmentManager = getSupportFragmentManager();
        //初始化 主要的fragment 的
        mHomeFragment = new HomeFragment();
        mNewsFragment = new NewsFragment();
        mWifeFragment = new WifeFragment();
        mOtherFragment = new OtherFragment();
        initBottomNavigation();
        switchContent(mHomeFragment);
        mNavigation.setNavigationItemSelectedListener(this);//NavigationView 设置条目点击事前
        View headerView = mNavigation.getHeaderView(0);
        ImageView imageView = headerView.findViewById(R.id.headerView);
        imageView.setOnClickListener(v -> JumpUtils.jump(mContext, PhotoViewActivity.class,null));

    }

    @Override
    protected void setStatusBarType() {
        MdStatusBarCompat.setImageTransparent(this);
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
                .addItem(new BottomNavigationItem(R.mipmap.icon_find, getString(R.string.find)).setActiveColorResource(R.color.colorPrimary))
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
                switchContent(mWifeFragment);
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
            JumpUtils.jump(mContext, PersonalCenterActivity.class,null);
            // Handle the camera action
        } else if (id == R.id.nav_personal_center) {
            JumpUtils.jump(mContext, FileBrowsingActivity.class,null);
        } else if (id == R.id.nav_share) {
            T.showShort("nav_share");
        } else if (id == R.id.nav_send) {
            T.showShort("nav_send");
        } else if (id == R.id.nav_exit) {
            AlertDialog();
        }else if (id ==R.id.nav_manage){
            JumpUtils.jump(mContext, ToolsActivity.class,null);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 退出登录对话框
     */
    private void AlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setTitle(R.string.system_title).setMessage(R.string.system_content)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> LogOut())
                .setNegativeButton(android.R.string.no, (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }
    /**
     * 退出登录
     */
    private void LogOut() {
//        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.exit_loading);
        AppLoading.show(mContext);
        Map<String, Object> params = new HashMap<>();
        params.put("Token", ConstantUtils.token);//身份验证Token
        params.put("UserID", ConstantUtils.userId);//当前登录ID
        new NetRequest.Builder().create().requestDate(mConnService.LogOut(params).compose(RxHelper.handleResult()),
                new NetCallBack<ArrayList<LoginBean>>() {
                    @Override
                    public void onSuccess(ArrayList<LoginBean> bean) {
                        JumpUtils.jump(mContext, LoginActivity.class, null);
                        finish();
                        T.showShort("退出登录成功");
                    }

                    @Override
                    public void updataLayout(int flag) {
                        L.e("login_失败");
                    }
                });
    }

    //退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawer(GravityCompat.START);
            } else if ((System.currentTimeMillis() - exitTime) >= 2000) {
                Util.CustomToast.INSTANCE.showToast(mContext,R.string.exit_app);
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
