package com.example.gab.babylove.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.gab.babylove.R;
import com.example.gab.babylove.activity.HomeDataActivity;
import com.example.gab.babylove.adapter.HomeAdapter;
import com.example.gab.babylove.view.NetworkImageHolderView;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.base.recyclerv.divider.DividerParams;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Gab on 2017/12/15 0015.
 * 主页Fragment
 */

public class HomeFragment extends BaseFragment {

    private String[] images = {
            "http://192.168.100.30:8098/YDYS/20171219/20171220161832.png",
            "http://img5.duitang.com/uploads/item/201606/01/20160601001315_wC3mU.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513314151355&di=3c0d90cff5a9c95b5696720a1282adcf&imgtype=0&src=http%3A%2F%2Fi2.17173cdn.com%2Fz0og4j%2FYWxqaGBf%2Fnewgame%2F20160729%2FSNzFzpbkyAkFEwk.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg"};

    @BindView(R.id.banner)
    ConvenientBanner mConvenientBanner;
    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    HomeAdapter mAdapter;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        initBanner();
        GetData();
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
                    if (position ==0){
                        T.showShort("这个妹子在祈祷什么呢?");
                    }else if (position ==1){
                        T.showShort("这个妹子在地上听什么歌曲呢?");
                        JumpUtils.jump(mContext,HomeDataActivity.class,null);
                    }else {
                        T.showShort("这个妹子为什么泡浴缸不脱衣服呢?");
                    }
                })
                .setcurrentitem(0);
    }

    private void GetData() {
        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        mRecyclerView.setLayoutManager(gManager);
        mAdapter = new HomeAdapter(mContext, new ArrayList<>());
        mRecyclerView.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
    }


    /**
     * 请求参数
     */

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
}
