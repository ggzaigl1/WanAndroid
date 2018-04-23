package com.example.gab.babylove.about;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.widget.interpolator.ElasticOutInterpolator;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.AppUtils;
import com.fy.baselibrary.utils.ResourceUtils;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.flyrefresh.FlyView;
import com.scwang.smartrefresh.header.flyrefresh.MountainSceneView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import butterknife.BindView;

/**
 * 关于
 * Created by fangs on 2018/4/8.
 */
public class AboutActivity extends AppCompatActivity {

    MountainSceneView mAboutUsMountain;
    Toolbar mToolbar;
    CollapsingToolbarLayout mAboutUsToolbarLayout;
    AppBarLayout mAboutUsAppBar;
    FlyRefreshHeader mFlyRefreshHeader;
    SmartRefreshLayout mAboutUsRefreshLayout;
    FloatingActionButton mAboutUsFab;
    FlyView mAboutUsFlyView;
    NestedScrollView mScrollView;
    TextView tvAbout, tvAppInformation;
    private View.OnClickListener mThemeListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        MdStatusBar.setColorBar(this, R.color.statusBar, R.color.statusBar);
        tvAbout = findViewById(R.id.tvAbout);
        tvAppInformation = findViewById(R.id.tvAppInformation);
        mScrollView = findViewById(R.id.about_us_content);
        mAboutUsFlyView = findViewById(R.id.about_us_fly_view);
        mAboutUsFab = findViewById(R.id.about_us_fab);
        mAboutUsRefreshLayout = findViewById(R.id.about_us_refresh_layout);
        mFlyRefreshHeader = findViewById(R.id.about_us_fly_refresh);
        mAboutUsAppBar = findViewById(R.id.about_us_app_bar);
        mAboutUsToolbarLayout = findViewById(R.id.about_us_toolbar_layout);
        mToolbar = findViewById(R.id.about_us_toolbar);
        mAboutUsMountain = findViewById(R.id.about_us_mountain);
        initData();
    }

    private void initData() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        //设置内容
        try {
            String versionStr = getString(R.string.awesome_wan_android) + " V" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            tvAppInformation.setText(versionStr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        tvAbout.setText(Html.fromHtml(ResourceUtils.getStr(R.string.about_content)));
        tvAbout.setMovementMethod(LinkMovementMethod.getInstance());

        //绑定场景和纸飞机
        mFlyRefreshHeader.setUp(mAboutUsMountain, mAboutUsFlyView);
        mAboutUsRefreshLayout.setReboundInterpolator(new ElasticOutInterpolator());
        mAboutUsRefreshLayout.setReboundDuration(800);
        mAboutUsRefreshLayout.setOnRefreshListener(refreshLayout -> {
            updateTheme();
            refreshLayout.finishRefresh(1000);
        });

        //设置让Toolbar和AppBarLayout的滚动同步
        mAboutUsRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                if (mAboutUsAppBar == null || mToolbar == null) {
                    return;
                }
                mAboutUsAppBar.setTranslationY(offset);
                mToolbar.setTranslationY(-offset);
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int footerHeight, int extendHeight) {
                if (mAboutUsAppBar == null || mToolbar == null) {
                    return;
                }
                mAboutUsAppBar.setTranslationY(offset);
                mToolbar.setTranslationY(-offset);
            }
        });

        //进入界面时自动刷新
        mAboutUsRefreshLayout.autoRefresh();

        //点击悬浮按钮时自动刷新
        mAboutUsFab.setOnClickListener(v -> mAboutUsRefreshLayout.autoRefresh());

        /*
         * 监听 AppBarLayout 的关闭和开启 给 FlyView（纸飞机） 和 ActionButton 设置关闭隐藏动画
         */
        mAboutUsAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean misAppbarExpand = true;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int scrollRange = appBarLayout.getTotalScrollRange();
                float fraction = 1f * (scrollRange + verticalOffset) / scrollRange;
                double minFraction = 0.1;
                double maxFraction = 0.8;
                if (fraction < minFraction && misAppbarExpand) {
                    misAppbarExpand = false;
                    mAboutUsFab.animate().scaleX(0).scaleY(0);
                    mAboutUsFlyView.animate().scaleX(0).scaleY(0);
                    ValueAnimator animator = ValueAnimator.ofInt(mScrollView.getPaddingTop(), 0);
                    animator.setDuration(300);
                    animator.addUpdateListener(animation ->
                            mScrollView.setPadding(0, (int) animation.getAnimatedValue(), 0, 0));
                    animator.start();
                }
                if (fraction > maxFraction && !misAppbarExpand) {
                    misAppbarExpand = true;
                    mAboutUsFab.animate().scaleX(1).scaleY(1);
                    mAboutUsFlyView.animate().scaleX(1).scaleY(1);
                    ValueAnimator animator = ValueAnimator.ofInt(mScrollView.getPaddingTop(), DensityUtil.dp2px(25));
                    animator.setDuration(300);
                    animator.addUpdateListener(animation ->
                            mScrollView.setPadding(0, (int) animation.getAnimatedValue(), 0, 0));
                    animator.start();
                }
            }
        });
    }


    /**
     * Update appbar theme
     */
    private void updateTheme() {
        if (mThemeListener == null) {
            mThemeListener = new View.OnClickListener() {
                int index = 0;
                int[] ids = new int[]{
                        android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_red_light,
                        android.R.color.holo_orange_light,
                        R.color.colorPrimary,

                };

                @Override
                public void onClick(View v) {
                    int color = ContextCompat.getColor(getApplication(), ids[index % ids.length]);
                    mAboutUsRefreshLayout.setPrimaryColors(color);
                    mAboutUsFab.setBackgroundColor(color);
                    mAboutUsFab.setBackgroundTintList(ColorStateList.valueOf(color));
                    mAboutUsToolbarLayout.setContentScrimColor(color);
                    index++;
                }
            };
        }
        mThemeListener.onClick(null);
    }
}