package com.example.gab.babylove.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.gab.babylove.R;
import com.example.gab.babylove.adapter.HomeAdapter;
import com.example.gab.babylove.view.NetworkImageHolderView;
import com.fy.baselibrary.base.recyclerv.divider.DividerParams;
import com.fy.baselibrary.utils.T;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Gab on 2017/12/25 0025.
 * CoordinatorLayout +  AppBarLayout + CollapsingToolbarLayout
 */

public class HomeDataActivity extends AppCompatActivity {

    private String[] images = {
            "http://192.168.100.30:8098/YDYS/20171219/20171220161832.png",
            "http://img5.duitang.com/uploads/item/201606/01/20160601001315_wC3mU.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513314151355&di=3c0d90cff5a9c95b5696720a1282adcf&imgtype=0&src=http%3A%2F%2Fi2.17173cdn.com%2Fz0og4j%2FYWxqaGBf%2Fnewgame%2F20160729%2FSNzFzpbkyAkFEwk.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg"};

//    @BindView(R.id.banner)
//    ConvenientBanner mConvenientBanner;
//    @BindView(R.id.recyclerView)
//    RecyclerView mRecyclerView;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.appbar_layout)
//    AppBarLayout mAppBarLayout;//标题部分
//    @BindView(R.id.collapsing_toolbar_layout)
//    CollapsingToolbarLayout mCollapsingToolbarLayout;//折叠式标题栏

    HomeAdapter mAdapter;
    RecyclerView mRecyclerView;
    ConvenientBanner mConvenientBanner;
    private List<String> dummyData;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapsing);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mConvenientBanner = findViewById(R.id.banner);
        mRecyclerView = findViewById(R.id.recyclerView);
        AppBarLayout mAppBarLayout = findViewById(R.id.appbar_layout);
        CollapsingToolbarLayout mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        initBanner();
        setDummyData();
        initRecyle();
        //设置状态栏
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //给页面设置工具栏
        if (mCollapsingToolbarLayout != null) {
            //设置隐藏图片时候ToolBar的颜色
            mCollapsingToolbarLayout.setContentScrimColor(Color.parseColor("#20C5FA"));
            //设置工具栏标题
            mCollapsingToolbarLayout.setTitle("我需要这个CollapsingToolbarLayout");
        }
    }

    private void initBanner() {
        List<String> networkImages = Arrays.asList(images);
        mConvenientBanner.setPages(() -> new NetworkImageHolderView(), networkImages)
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                })
                .setPageIndicator(new int[]{R.drawable.shape_banner_indicator1, R.drawable.shape_banner_indicator2})
                .setPointViewVisible(true)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)//设置指示器的方向
                .setPageTransformer(new AccordionTransformer())
                .setOnItemClickListener(position -> {
                    if (position == 0) {
                        T.showShort("这个妹子在祈祷什么呢?");
                    } else if (position == 1) {
                        T.showShort("这个妹子在地上听什么歌曲呢?");
                    } else {
                        T.showShort("这个妹子为什么泡浴缸不脱衣服呢?");
                    }
                })
                .setcurrentitem(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        mConvenientBanner.startTurning(2000);//开始翻页
//        GetGetDicts();
    }

    @Override
    public void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();//停止翻页
    }

        private void setDummyData() {
            dummyData = new ArrayList();
            for (int i = 0; i < 5; i++) {
                dummyData.add("");
            }
    }

    private void initRecyle(){
        GridLayoutManager gManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(gManager);
        mAdapter = new HomeAdapter(this, dummyData);
        mRecyclerView.addItemDecoration(new DividerParams().setLayoutManager(1).create(this));
        mRecyclerView.setAdapter(mAdapter);
    }

}
