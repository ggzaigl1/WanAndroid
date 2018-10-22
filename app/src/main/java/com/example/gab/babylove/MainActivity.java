package com.example.gab.babylove;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.gab.babylove.ui.main.activity.NewProjectActivity;
import com.example.gab.babylove.ui.main.activity.OfficialAccountActivity;
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
import com.example.gab.babylove.utils.AndroidShareUtils;
import com.example.gab.babylove.utils.NightModeConfig;
import com.ggz.baselibrary.application.BaseApp;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.base.ViewHolder;
import com.ggz.baselibrary.base.dialog.CommonDialog;
import com.ggz.baselibrary.base.dialog.DialogConvertListener;
import com.ggz.baselibrary.base.dialog.NiceDialog;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.ResourceUtils;
import com.ggz.baselibrary.utils.SpfUtils;
import com.ggz.baselibrary.utils.SystemUtils;
import com.ggz.baselibrary.utils.T;
import com.ggz.baselibrary.utils.cache.ACache;
import com.ggz.baselibrary.utils.permission.PermissionChecker;

import butterknife.BindView;

/**
 * 主方法
 *
 * @author 55204
 */
public class MainActivity extends AppCompatActivity implements IBaseActivity, BottomNavigationBar.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {

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

    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private NewsFragment mNewsFragment;
    private NavigationViewFragment mNavigationViewFragment;
    private StarFragment mStarFragment;
    private Fragment mCurrentFrag; //当前的fragment
    private long exitTime = 0; //保存点击的时间

    public TextView nev_header_tv_login;
    public TextView nev_header_tv_title;

    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

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

        permissionChecker = new PermissionChecker(this);
        permissionChecker.setTitle(getString(R.string.check_info_title));
        permissionChecker.setMessage(getString(R.string.check_info_message));

        /**
         *  该权限只能在activity里面回调成功,fragment 无法走回调
         *  首先判断是否有需要的权限,没有就检查需要的权限,申请权限
         */
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            NiceDialog.init()
                    .setLayoutId(R.layout.dialog_permission)
                    .setDialogConvertListener(new DialogConvertListener() {
                        @Override
                        protected void convertView(ViewHolder holder, CommonDialog dialog) {
                            holder.setOnClickListener(R.id.positiveButton, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    onPermission();
                                    dialog.dismiss(false);
                                }
                            });
                        }
                    })
                    .setWidthPixels(30)
                    .setWidthPercent(CommonDialog.WidthPercent)
                    .show(mFragmentManager);
        }


        initBottomNavigation();
        switchContent(mHomeFragment);
        setSupportActionBar(mToolbar);
        mNavigation.setNavigationItemSelectedListener(this);//NavigationView 设置条目点击事前

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //获取Navigation header控件方法1:
        View headerView = mNavigation.getHeaderView(0);
        ImageView nev_header_imageView = headerView.findViewById(R.id.nev_header_imageView);
        nev_header_tv_title = headerView.findViewById(R.id.nev_header_tv_title);
        nev_header_tv_login = headerView.findViewById(R.id.nev_header_tv_login);

        //获取Navigation header控件方法2:
//        View headerView = mNavigation.inflateHeaderView(R.layout.nav_header_main);//
//        ImageView nev_header_imageView = headerView.findViewById(R.id.nev_header_imageView);
//        nev_header_tv_title = headerView.findViewById(R.id.nev_header_tv_title);
//        nev_header_tv_login = headerView.findViewById(R.id.nev_header_tv_login);//点击登录

        nev_header_imageView.setOnClickListener(v -> JumpUtils.jump(MainActivity.this, PhotoViewActivity.class, null));
        nev_header_tv_login.setOnClickListener(v -> {
            boolean isLogin = SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin);
            if (isLogin) {
                new AlertDialog.Builder(this)
                        .setTitle(R.string.system_title).setMessage(R.string.system_content)
                        .setPositiveButton(R.string.ok, (dialog, which) -> {
                            nev_header_tv_title.setText(R.string.notLogin);
                            nev_header_tv_login.setText(R.string.clickLogin);
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
        nev_header_tv_title.setText(isLogin ? SpfUtils.getSpfSaveStr(ConstantUtils.userName) : ResourceUtils.getStr(R.string.notLogin));
        nev_header_tv_login.setText(isLogin ? R.string.login_exit : R.string.clickLogin);


    }

    /**
     * 检查权限
     */
    private void onPermission() {
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            permissionChecker.requestPermissions();
        }
    }

    /**
     * 设置底部导航栏
     */
    private void initBottomNavigation() {
        //设置导航栏背景模式 setBackgroundStyle（）
        //设置BottomNavigationItem颜色 setActiveColor, setInActiveColor, setBarBackgroundColor
        mBottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setMode(BottomNavigationBar.MODE_SHIFTING)
                .setInActiveColor("#2c2c2c")//设置Item未选中颜色方法
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
            case R.id.nav_official_account:
                //公众号
                JumpUtils.jump(this, OfficialAccountActivity.class, null);
                break;
            case R.id.nav_list_project:
                //最新项目
                JumpUtils.jump(this, NewProjectActivity.class, null);
                break;
            case R.id.nav_collect:
                //我的收藏
                if (SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin)) {
                    JumpUtils.jump(this, MyCollectActivity.class, null);
                } else {
                    JumpUtils.jump(this, LoginActivity.class, null);
                    T.showShort(getString(R.string.main_login_view));
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
                JumpUtils.jump(this, OrnamentalListContextActivity.class, null);
                break;
            case R.id.nav_about:
                //关于我们
                JumpUtils.jump(this, AboutActivity.class, null);
//                JumpUtils.jump(this, CameraActivity.class, null);
//                JumpUtils.jump(this, MyThreadActivity.class, null);
                break;
            case R.id.nav_share:
//                AndroidShareUtils.shareWeChatFriend(this, "wanandroid", "https://www.pgyer.com/6osT", AndroidShareUtils.TEXT, null);
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_wanandroid);
                AndroidShareUtils.shareWeChatFriend(this, "一起玩Android", "https://www.pgyer.com/6osT", AndroidShareUtils.DRAWABLE, bmp);
                break;
            case R.id.nav_manage:
//            工具类
                JumpUtils.jump(this, ToolsActivity.class, null);
                break;
            default:
                break;
        }
//        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint(getString(R.string.search_knowledge));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle = new Bundle();
                bundle.putString("query", query);
                bundle.putInt("type", 2);
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

    /**
     * 请求权限返回结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionChecker.PERMISSION_REQUEST_CODE:
                //权限获取成功
                if (!permissionChecker.hasAllPermissionsGranted(grantResults)) {
                    //权限获取失败
                    permissionChecker.showDialog();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //保存点击的时间
            long time = 2000;
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawer(GravityCompat.START);
            } else if ((System.currentTimeMillis() - exitTime) >= time) {
//                Snackbar.make(mDrawer, R.string.exit_app, Snackbar.LENGTH_SHORT)
//                        .setActionTextColor(ContextCompat.getColor(this, R.color.white))
//                        .show();
                T.CustomToast.INSTANCE.showToast(MainActivity.this, R.string.exit_app);
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
