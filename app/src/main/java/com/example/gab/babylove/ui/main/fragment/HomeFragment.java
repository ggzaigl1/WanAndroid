package com.example.gab.babylove.ui.main.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.ArticleBean;
import com.example.gab.babylove.entity.BannerBean;
import com.example.gab.babylove.ui.main.adapter.HomeAdapter;
import com.example.gab.babylove.ui.main.login.LoginActivity;
import com.example.gab.babylove.view.NetworkImageHolderView;
import com.example.gab.babylove.web.AgentWebActivity;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RequestUtils;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import dmax.dialog.SpotsDialog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
    public SmartRefreshLayout mRefreshLayout;
    ConvenientBanner<BannerBean> bannerView;
    HomeAdapter mAdapter;
    int mPageNo = 0;

    @Override
    protected void baseInit() {
        super.baseInit();
        getData();
        initRecyle();
        initRefresh();
        //给页面设置工具栏
        if (mCollapsingToolbarLayout != null) {
            //设置隐藏图片时候ToolBar的颜色
            mCollapsingToolbarLayout.setContentScrimColor(Color.parseColor("#20C5FA"));
            //设置工具栏标题
            mCollapsingToolbarLayout.setTitle("一起玩Android");
        }
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        mContext.setSupportActionBar(toolbar);
//        mRefreshLayout.autoRefresh();//自动刷新
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }


    /**
     * 轮播图 相关设置
     *
     * @param Pic
     * @param urls
     */
    private void bannerView(List<String> Pic, List<String> urls) {
        mConvenientBanner.setPages(() -> new NetworkImageHolderView(), Pic)
                .startTurning(2000)
//                .setPageIndicator(new int[]{R.drawable.shape_banner_indicator1, R.drawable.shape_banner_indicator2})
//                .setPointViewVisible(true)
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)//设置指示器的方向
//                .setPageTransformer(new AccordionTransformer())
                .setOnItemClickListener(position -> {
                    String Ulr = urls.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("UrlBean", Ulr);
                    JumpUtils.jump(mContext, AgentWebActivity.class, bundle);
                })
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);
//                .setcurrentitem(0);
    }

    /**
     * banner 轮播图 加载数据 接口
     */
    private void getData() {
        SpotsDialog dialog = new SpotsDialog(getActivity());
        dialog.show();
//        IProgressDialog progressDialog = new IProgressDialog().init((AppCompatActivity) getActivity()).setDialogMsg(R.string.loading_get);
        Observable<List<BannerBean>> observable1 = RequestUtils.create(ApiService.class)
                .getBanner()
                .compose(RxHelper.handleResult())
                .observeOn(Schedulers.io());

        Observable<ArticleBean> observable2 = RequestUtils.create(ApiService.class)
                .getArticleHomeList(mPageNo)
                .compose(RxHelper.handleResult())
                .observeOn(Schedulers.io());


        Observable.zip(observable1, observable2, (bannerBean, articleBean) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("banner", bannerBean);
            map.put("article", articleBean);
            return map;
        }).doOnSubscribe(RequestUtils::addDispos)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetCallBack<Map<String, Object>>() {
                    @Override
                    protected void onSuccess(Map<String, Object> map) {
                        List<BannerBean> banner = (List<BannerBean>) map.get("banner");
                        if (null != banner && banner.size() > 0) {
                            List<String> pics = new ArrayList<>();
                            List<String> urls = new ArrayList<>();
                            List<String> titles = new ArrayList<>();

                            for (BannerBean bannerBean : banner) {
                                pics.add(bannerBean.getImagePath());
                                urls.add(bannerBean.getUrl());
                                titles.add(bannerBean.getTitle());
                            }
                            bannerView(pics, urls);
                        }

                        ArticleBean articleBean = (ArticleBean) map.get("article");
                        if (null != articleBean && null != articleBean.getDatas()) {
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(articleBean.getDatas());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(articleBean.getDatas());
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(articleBean.getDatas());
                            }
                        }
                        dialog.dismiss();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


    /**
     * 首页列表数据加载
     * <p>
     * //     * @param mCurPage
     */
    @SuppressLint("CheckResult")
    private void getArticleList(int mCurPage) {
        RequestUtils.create(ApiService.class)
                .getArticleHomeList(mCurPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articleBean -> {
                    if (null != articleBean && null != articleBean.getData()) {
                        if (mRefreshLayout.isRefreshing()) {
                            mAdapter.setNewData(articleBean.getData().getDatas());
                            mRefreshLayout.finishRefresh();
                        } else if (mRefreshLayout.isLoading()) {
                            mAdapter.getData().addAll(articleBean.getData().getDatas());
                            mRefreshLayout.finishLoadMore();
                            mAdapter.notifyDataSetChanged();
                        } else {
                            mAdapter.setNewData(articleBean.getData().getDatas());
                        }
                    }
                });
    }

    /**
     * 收藏
     *
     * @param id
     */
    private void collectArticle(int id) {
        RequestUtils.create(ApiService.class)
                .getCollectArticle(id, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(objectBeanModule -> ToastUtils.showShort("收藏成功"));
    }

    //    取消收藏
    private void uncollectArticle(int id) {
        RequestUtils.create(ApiService.class)
                .uncollectArticle(id, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(objectBeanModule -> ToastUtils.showShort("取消收藏成功"));
    }

    /**
     * recycleview 相关设置
     */
    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new HomeAdapter(R.layout.item_home, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ArticleBean.DatasBean bean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("UrlBean", bean.getLink());
            JumpUtils.jump(mContext, AgentWebActivity.class, bundle);// 详情
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.image_collect:
                    if (SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin)) {
                        if (mAdapter.getData().get(position).isCollect()) { //收藏
                            uncollectArticle(mAdapter.getData().get(position).getId());
                            mAdapter.getData().get(position).setCollect(false);
                            mAdapter.notifyItemChanged(position, "");
                        } else {
                            collectArticle(mAdapter.getData().get(position).getId());
                            mAdapter.getData().get(position).setCollect(true);
                            mAdapter.notifyItemChanged(position, "");
                        }
                    } else {
                        JumpUtils.jump(mContext, LoginActivity.class, null);
                        ToastUtils.showShort("登录之后才能查看已收藏内容");
                    }
                    break;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 分页加载数据
     */
    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPageNo += 1;
                getArticleList(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 0;
                getArticleList(0);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != bannerView) bannerView.startTurning(2000);//开始翻页

    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != bannerView) bannerView.stopTurning();//停止翻
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
        }
        if (mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadMore();
        }
    }
}
