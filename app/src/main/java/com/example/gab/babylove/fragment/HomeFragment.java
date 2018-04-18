package com.example.gab.babylove.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.gab.babylove.R;
import com.example.gab.babylove.activity.AgentWebActivity;
import com.example.gab.babylove.adapter.HomeAdapter;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.ArticleBean;
import com.example.gab.babylove.entity.BannerBean;
import com.example.gab.babylove.view.NetworkImageHolderView;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.retrofit.BeanModule;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RequestUtils;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.JumpUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gab on 2017/12/15 0015.
 * 主页Fragment
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.banner)
    ConvenientBanner mConvenientBanner;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    HomeAdapter mAdapter;
    private int mPageNo = 1;

    @Override
    protected void baseInit() {
        super.baseInit();
        getStandardsToApp();
        getArticleList(mPageNo);
        initRecyle();
        initRefresh();
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        mContext.setSupportActionBar(toolbar);
        //是否显示返回箭头
//        mContext.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(v -> mContext.finish());

    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }

    /**
     * banner 轮播图 加载数据 接口
     */
    private void getStandardsToApp() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.data_loading);
        RequestUtils.create(ApiService.class)
                .getBanner()
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetCallBack<BeanModule<List<BannerBean>>>(progressDialog) {
                    @Override
                    protected void onSuccess(BeanModule<List<BannerBean>> bean) {
                        List<String> pics = new ArrayList<>();
                        List<String> urls = new ArrayList<>();
                        List<String> titles = new ArrayList<>();

                        for (BannerBean bannerBean : bean.getData()) {
                            pics.add(bannerBean.getImagePath());
                            urls.add(bannerBean.getUrl());
                            titles.add(bannerBean.getTitle());
                        }

//                        for (BannerBean.DataBean dataBean : bean.getData()) {
//
//                        }
                        bannerView(pics, urls);
                        //给页面设置工具栏
                        if (mCollapsingToolbarLayout != null) {
                            //设置隐藏图片时候ToolBar的颜色
                            mCollapsingToolbarLayout.setContentScrimColor(Color.parseColor("#20C5FA"));
                            //设置工具栏标题
                            mCollapsingToolbarLayout.setTitle("一起玩Android");
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    /**
     * 轮播图 相关设置
     *
     * @param Pic
     * @param urls
     */
    private void bannerView(List<String> Pic, List<String> urls) {
        mConvenientBanner.setPages(() -> new NetworkImageHolderView(), Pic)
                .setPageIndicator(new int[]{R.drawable.shape_banner_indicator1, R.drawable.shape_banner_indicator2})
                .setPointViewVisible(true)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)//设置指示器的方向
                .setPageTransformer(new AccordionTransformer())
                .setOnItemClickListener(position -> {
                    String Ulr = urls.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("UrlBean", Ulr);
                    JumpUtils.jump(mContext, AgentWebActivity.class, bundle);
                })
                .setcurrentitem(0);
    }

    /**
     * 首页列表数据加载
     *
     * @param mCurPage
     */
    private void getArticleList(int mCurPage) {
        Map<String, Object> param = new HashMap<>();
        param.put("page", mCurPage);
        RequestUtils.create(ApiService.class)
                .getArticleList(mCurPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanModule<ArticleBean>>() {
                    @Override
                    public void accept(BeanModule<ArticleBean> articleBean) throws Exception {
                        if (null != articleBean && null != articleBean.getData()) {
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(articleBean.getData().getDatas());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(articleBean.getData().getDatas());
                                mRefreshLayout.finishLoadmore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(articleBean.getData().getDatas());
                            }
                        }
                    }
                });
    }

    /**
     * 分页加载数据
     */
    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPageNo += 1;
                getArticleList(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPageNo = 1;
                getArticleList(1);
            }
        });
    }

    /**
     * recycleview 相关设置
     */
    private void initRecyle() {
        GridLayoutManager gManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(gManager);
        mAdapter = new HomeAdapter(R.layout.item_home, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ArticleBean.DatasBean bean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("UrlBean", bean.getLink());
            JumpUtils.jump(mContext, AgentWebActivity.class, bundle);// 详情
        });
        mRecyclerView.setAdapter(mAdapter);
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
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
        }
        if (mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadmore();
        }
    }
}
