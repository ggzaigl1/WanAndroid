package com.example.gab.babylove.fragment;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.gab.babylove.R;
import com.example.gab.babylove.adapter.HomeAdapter;
import com.example.gab.babylove.view.NetworkImageHolderView;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.base.recyclerv.divider.DividerParams;
import com.fy.baselibrary.cutom.swiperefreshlayout.SwipyRefreshLayout;
import com.fy.baselibrary.entity.HomeBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.NetRequest;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.L;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Gab on 2017/12/15 0015.
 * 主页Fragment
 */

public class HomeFragment extends BaseFragment {

    private String[] images = {
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
    private void GetGetDicts() {
        Map<String, Object> params = new HashMap<>();
        params.put("type", "top");  //
        params.put("key", "65a63052634458a9ba2859f95ce6b218");  //

        new NetRequest.Builder().create().requestDate(mConnService.GetHeadline(params).compose(RxHelper.handleResult()),
                new NetCallBack<ArrayList<HomeBean>>() {
                    @Override
                    public void onSuccess(ArrayList<HomeBean> data) {
                        if (null != data) {
                            mAdapter.setmDatas(data);
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void updataLayout(int flag) {
                        L.e("请求失败");
                        L.e(params.toString());
                    }
                });
    }


    @Override
    public void onResume() {
        super.onResume();
        mConvenientBanner.startTurning(2000);//开始翻页
        GetGetDicts();
    }

    @Override
    public void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();//停止翻页
    }
}
