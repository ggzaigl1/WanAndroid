package com.example.gab.babylove.fragment;

import android.support.v4.view.ViewPager;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.gab.babylove.R;
import com.example.gab.babylove.view.NetworkImageHolderView;
import com.fy.baselibrary.base.BaseFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Gab on 2017/12/15 0015.
 * 主页Fragment
 */

public class HomeFragment extends BaseFragment {

    private String[] images = {
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513314151355&di=3c0d90cff5a9c95b5696720a1282adcf&imgtype=0&src=http%3A%2F%2Fi2.17173cdn.com%2Fz0og4j%2FYWxqaGBf%2Fnewgame%2F20160729%2FSNzFzpbkyAkFEwk.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg"};
    @BindView(R.id.banner)
    ConvenientBanner mConvenientBanner;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        initBanner();
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
                .setcurrentitem(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        mConvenientBanner.startTurning(2000);//开始翻页
    }

    @Override
    public void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();//停止翻页
    }
}
