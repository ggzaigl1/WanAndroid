package com.example.gab.babylove.ui.main.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.BannerBean;
import com.example.gab.babylove.entity.BaseBean;
import com.example.gab.babylove.ui.main.adapter.BaseAdapter;
import com.example.gab.babylove.ui.main.login.LoginActivity;
import com.example.gab.babylove.view.NetworkImageHolderView;
import com.example.gab.babylove.web.AgentWebActivity;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.base.BaseFragment;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.SpfUtils;
import com.ggz.baselibrary.utils.T;
import com.kaopiz.kprogresshud.KProgressHUD;
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
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Gab
 * @date 2017/12/15 0015
 * 主页Fragment
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.banner)
    ConvenientBanner mConvenientBanner;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    public SmartRefreshLayout mRefreshLayout;
    ConvenientBanner<BannerBean> bannerView;
    BaseAdapter mAdapter;
    int mPageNo = 0;


    @Override
    protected void baseInit() {
        super.baseInit();
        getData();
        initRecyle();
        initRefresh();

        //给页面设置工具栏
//        if (mCollapsingToolbarLayout != null) {
//            //设置隐藏图片时候ToolBar的颜色
//            mCollapsingToolbarLayout.setContentScrimColor(Color.parseColor("#20C5FA"));
//            //设置工具栏标题
//            mCollapsingToolbarLayout.setTitle("一起玩Android");
//        }
        //通过CollapsingToolbarLayout修改字体颜色
//        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
//        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
//        mContext.setSupportActionBar(toolbar);
//        mRefreshLayout.autoRefresh();//自动刷新
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }


    /**
     * 轮播图 相关设置
     *
     * @param pic
     * @param urls
     */
    private void bannerView(List<String> pic, List<String> urls) {
        mConvenientBanner.setPages(() -> new NetworkImageHolderView(), pic)
                .startTurning(2000)
                .setPageIndicator(new int[]{R.drawable.shape_banner_indicator1, R.drawable.shape_banner_indicator2})
                .setPointViewVisible(true)
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setPageTransformer(new AccordionTransformer())
                .setOnItemClickListener(position -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("UrlBean", urls.get(position));
                    JumpUtils.jumpFade((AppCompatActivity) getActivity(), AgentWebActivity.class, bundle);
//                        WebViewActivity.startWebActivity(getActivity(), urls.get(position), 0);// 详情
                    mContext.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                })
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);
//                .setcurrentitem(0);
    }

    /**
     * banner 轮播图 加载数据 接口
     */
    private void getData() {
        Observable<List<BannerBean>> observable1 = RequestUtils.create(ApiService.class)
                .getBanner()
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()));

        Observable<BaseBean> observable2 = RequestUtils.create(ApiService.class)
                .getArticleHomeList(mPageNo)
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()));
        Observable.zip(observable1, observable2, (bannerBean, articleBean) -> {
            Map<String, Object> map = new HashMap<>(16);
            map.put("banner", bannerBean);
            map.put("article", articleBean);
            return map;
        }).doOnSubscribe(RequestUtils::addDisposable)
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

                        BaseBean baseBean = (BaseBean) map.get("article");
                        if (null != baseBean && null != baseBean.getDatas()) {
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(baseBean.getDatas());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(baseBean.getDatas());
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(baseBean.getDatas());
                            }
                        }
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
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getArticleHomeList(mCurPage)
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<BaseBean>() {
                    @Override
                    protected void onSuccess(BaseBean baseBean) {
                        if (null != baseBean) {
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(baseBean.getDatas());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(baseBean.getDatas());
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(baseBean.getDatas());
                            }
                        } else {
                            if (mRefreshLayout.isRefreshing()) {
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mRefreshLayout.finishLoadMore();
                            }
                        }
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    /**
     * 收藏
     *
     * @param id
     */
    @SuppressLint("CheckResult")
    private void collectArticle(View view, int id) {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getCollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        Snackbar.make(view, R.string.collection_success, Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    /**
     * 取消收藏
     *
     * @param id
     */
    @SuppressLint("CheckResult")
    private void unCollectArticle(int id) {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .unCollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
//                        T.showShort(getString(R.string.cancel_collection_success));
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    /**
     * recycleview 相关设置
     */
    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BaseAdapter(new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            // 详情
            WebViewActivity.startWebActivity(getActivity()
                    , mAdapter.getData().get(position).getLink()
                    , mAdapter.getData().get(position).getId());
            mContext.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        mAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.item_list_footer, (ViewGroup) mRecyclerView.getParent(), false));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.image_collect:
                    if (SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin)) {
                        //收藏
                        if (mAdapter.getData().get(position).isCollect()) {
                            unCollectArticle(mAdapter.getData().get(position).getId());
                            mAdapter.getData().get(position).setCollect(false);
                            mAdapter.notifyItemChanged(position, "");
                        } else {
                            collectArticle(view, mAdapter.getData().get(position).getId());
                            mAdapter.getData().get(position).setCollect(true);
                            mAdapter.notifyItemChanged(position, "");
                        }
                    } else {
                        JumpUtils.jumpFade(mContext, LoginActivity.class, null);
                        T.showShort(R.string.collect_login);
                    }
                    break;
                default:
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
                getData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != bannerView) {
            //开始翻页
            bannerView.startTurning(2000);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != bannerView) {
            bannerView.stopTurning();//停止翻
        }
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
        }
        if (mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadMore();
        }
    }
}
