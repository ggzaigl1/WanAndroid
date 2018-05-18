package com.example.gab.babylove;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
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
import com.example.gab.babylove.widget.CommonProgressDialog;
import com.fy.baselibrary.application.BaseApp;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.AppUtils;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.SystemUtils;
import com.fy.baselibrary.utils.ToastUtils;
import com.fy.baselibrary.utils.cache.ACache;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;

/**
 * 主方法
 */
public class MainActivity extends AppCompatActivity implements IBaseActivity, BottomNavigationBar.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private NewsFragment mNewsFragment;
    private NavigationViewFragment mNavigationViewFragment;
    private StarFragment mStarFragment;
    private Fragment mCurrentFrag; //当前的fragment
    private long exitTime = 0; //保存点击的时间
    private CommonProgressDialog mProgressDialog;
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
    TextView Tv_Login;
    TextView Tv_Name;
    // 下载存储的文件名
    private static final String DOWNLOAD_NAME = "channelWe";

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

        // 获取本版本号，是否更新
        int vision = AppUtils.getVersionCode();
//        getVersion(vision);

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
        Tv_Login = headerView.findViewById(R.id.tv_login);//点击登录
        Tv_Name = headerView.findViewById(R.id.tv_name);
        imageView.setOnClickListener(v -> JumpUtils.jump(MainActivity.this, PhotoViewActivity.class, null));

        Tv_Login.setOnClickListener(v -> {
            boolean isLogin = SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin);
            if (isLogin) {
                new AlertDialog.Builder(this)
                        .setTitle(R.string.system_title).setMessage(R.string.system_content)
                        .setPositiveButton(R.string.ok, (dialog, which) -> {
                            Tv_Name.setText(R.string.notLogin);
                            Tv_Login.setText(R.string.clickLogin);
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
        Tv_Name.setText(isLogin ? SpfUtils.getSpfSaveStr(ConstantUtils.userName) : ResourceUtils.getStr(R.string.notLogin));
        Tv_Login.setText(isLogin ? R.string.login_exit : R.string.clickLogin);

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
                switchContent(mNavigationViewFragment);
                break;
            case 3:
                switchContent(mStarFragment);
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
        if (id == R.id.nav_belle) {
            //美图欣赏
            JumpUtils.jump(this, BelleActivity.class, null);
        } else if (id == R.id.nav_website) {
            //常用网站
            JumpUtils.jump(this, WebsiteActivity.class, null);
        } else if (id == R.id.nav_collect) {
            //我的收藏
            if (SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin)) {
                JumpUtils.jump(this, MyCollectActivity.class, null);
            } else {
                JumpUtils.jump(this, LoginActivity.class, null);
                ToastUtils.showShort("登录之后才能查看已收藏内容");
            }
        } else if (id == R.id.nav_exit) {
            SystemUtils.ExitSystem();
        } else if (id == R.id.nav_night) {
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
        } else if (id == R.id.nav_ornamental) {
            //强身健体
            JumpUtils.jump(this, OrnamentalListContextActivity.class, null);
        } else if (id == R.id.nav_about) {
            //关于我们
            JumpUtils.jump(this, AboutActivity.class, null);
        } else if (id == R.id.nav_share) {
            Intent textIntent = new Intent(Intent.ACTION_SEND);
            textIntent.setType("text/plain");
            textIntent.putExtra(Intent.EXTRA_TEXT, "分享");
            startActivity(Intent.createChooser(textIntent, "分享"));
        } else if (id == R.id.nav_manage) {
//            工具类
            JumpUtils.jump(this, ToolsActivity.class, null);
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

    /**
     * 从服务器获取最新版本号信息
     *
     * @param vision
     */
    private void getVersion(int vision) {
//        RequestUtils.create(ApiService.class)
//                .getupdata(vision, "")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(RequestUtils::addDispos)
//                .subscribe(new Consumer<BeanModule<UpdateAppInfoBean>>() {
//                    @Override
//                    public void accept(BeanModule<UpdateAppInfoBean> updateAppInfoBeanBeanModule) throws Exception {
//                    }
//                });

        //网络请求获取当前版本号和下载链接
        //实际操作是从服务器获取
        //demo写死了
        String newversion = "2.1";//更新新的版本号
        String content = "\n" +
                "就不告诉你我们更新了什么-。-\n" +
                "\n" +
                "----------万能的分割线-----------\n" +
                "\n" +
                "(ㄒoㄒ) 被老板打了一顿，还是来告诉你吧：\n" +

                "1.下架商品误买了？恩。。。我搞了点小动作就不会出现了\n" +
                "2.侧边栏、弹框优化 —— 这个你自己去探索吧，总得留点悬念嘛-。-\n";//更新内容
        String url = "http://openbox.mobilem.360.cn/index/d/sid/3429345";//安装包下载地址

        double newversioncode = Double.parseDouble(newversion);
        int cc = (int) (newversioncode);

        System.out.println(newversion + "v" + vision + ",," + cc);
        if (cc != vision) {
            if (vision < cc) {
                System.out.println(newversion + "v" + vision);
                // 版本号不同
                ShowDialog(newversion, content, url);
            }
        }
    }

    private void ShowDialog(String newVersion, String content, String url) {

        new MaterialDialog.Builder(this).title("版本更新" + newVersion).content(content).positiveText(R.string.ok).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                mProgressDialog = new CommonProgressDialog(MainActivity.this);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setTitle("正在下载");
                mProgressDialog.setCustomTitle(LayoutInflater.from(MainActivity.this).inflate(R.layout.title_dialog, null));
                mProgressDialog.setMessage("正在下载");
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(true);
                // downFile(URLData.DOWNLOAD_URL);
                final DownloadTask downloadTask = new DownloadTask(MainActivity.this);
                downloadTask.execute(url);
                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        downloadTask.cancel(true);
                    }
                });
                dialog.dismiss();
            }
        }).negativeText(R.string.cancel).onNegative(null).show();
    }

    /**
     * 下载应用
     *
     * @author Administrator
     */
    @SuppressLint("StaticFieldLeak")
    class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            File file = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                // expect HTTP 200 OK, so we don't mistakenly save error
                // report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage();
                }
                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    file = new File(Environment.getExternalStorageDirectory(), DOWNLOAD_NAME);
                    if (!file.exists()) {
                        // 判断父文件夹是否存在
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                    }
                } else {
                    ToastUtils.showShort("sd卡未挂载");
                }
                input = connection.getInputStream();
                output = new FileOutputStream(file);
                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }
                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
            mWakeLock.acquire();
            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            mProgressDialog.dismiss();
            AppUtils.setUpdate(MainActivity.this);
        }
    }


    //退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawer(GravityCompat.START);
            } else if ((System.currentTimeMillis() - exitTime) >= 2000) {
                Snackbar.make(mDrawer, R.string.exit_app, Snackbar.LENGTH_SHORT)
                        .setActionTextColor(ContextCompat.getColor(this, R.color.white))
                        .show();
//                Util.CustomToast.INSTANCE.showToast(MainActivity.this, R.string.exit_app);
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
