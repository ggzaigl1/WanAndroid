package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.ArticleBean;
import com.example.gab.babylove.entity.OfficialAccountListBean;
import com.example.gab.babylove.ui.main.adapter.HomeAdapter;
import com.example.gab.babylove.ui.main.adapter.OfficialAccountListAdapter;
import com.example.gab.babylove.ui.main.login.LoginActivity;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.statusbar.MdStatusBar;
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

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪 on 2018/5/11 0011.
 * 搜索界面
 */

public class SearchActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    /**
     * 首页搜索 当前显示的页码(从 0 开始)
     * 公众号搜索 当前显示的页码(从 1 开始)
     */
    int mPageNo = 0;
    int mPageOffNo = 1;

    int mId;
    String queryKey;
    HomeAdapter mAdapter;
    OfficialAccountListAdapter mOfficialAccountListAdapter;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_search;
    }

    @Override
    public void setStatusBar(Activity activity) {
//        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        //设置导航图标要在setSupportActionBar方法之后
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 给左上角图标的左边加上一个返回的图标
        mToolbar.setNavigationOnClickListener(v -> JumpUtils.exitActivity(this));
        queryKey = getIntent().getStringExtra("query");
        int type = getIntent().getIntExtra("type", 0);
        mId = getIntent().getIntExtra("id", 0);
        /* type =1 公众号
         *  type =2 首页  */
        if (type == 1) {
            getQuery(type, mPageOffNo, queryKey);
            initRecyle(type);
            initRefresh(type);
        } else if (type == 2) {
            getQuery(type, mPageNo, queryKey);
            initRecyle(type);
            initRefresh(type);
        }
    }

    /**
     * 搜索接口
     * type =1 公众号
     * type =2 首页搜索
     *
     * @param type
     * @param pageNum
     * @param queryKey
     */
    private void getQuery(int type, int pageNum, String queryKey) {
        if (type == 1) {
            mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
            RequestUtils.create(ApiService.class)
                    .getWxarticleQuery(mId, pageNum, queryKey)
                    .compose(RxHelper.handleResult())
                    .doOnSubscribe(RequestUtils::addDispos)
                    .subscribe(new NetCallBack<OfficialAccountListBean>() {
                        @Override
                        protected void onSuccess(OfficialAccountListBean officialAccountListBean) {
                            if (null != officialAccountListBean) {
                                mKProgressHUD.dismiss();
                                if (mRefreshLayout.isRefreshing()) {
                                    mOfficialAccountListAdapter.setNewData(officialAccountListBean.getDatas());
                                    mRefreshLayout.finishRefresh();
                                } else if (mRefreshLayout.isLoading()) {
                                    mOfficialAccountListAdapter.getData().addAll(officialAccountListBean.getDatas());
                                    mRefreshLayout.finishLoadMore();
                                    mOfficialAccountListAdapter.notifyDataSetChanged();
                                } else {
                                    mOfficialAccountListAdapter.setNewData(officialAccountListBean.getDatas());
                                }
                            } else {
                                if (mRefreshLayout.isRefreshing()) {
                                    mRefreshLayout.finishRefresh();
                                } else if (mRefreshLayout.isLoading()) {
                                    mRefreshLayout.finishLoadMore();
                                }
                            }
                        }

                        @Override
                        protected void updataLayout(int flag) {

                        }
                    });
        } else if (type == 2) {
            mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
            RequestUtils.create(ApiService.class)
                    .getQuery(pageNum, queryKey)
                    .compose(RxHelper.handleResult())
                    .doOnSubscribe(RequestUtils::addDispos)
                    .subscribe(new NetCallBack<ArticleBean>() {
                        @Override
                        protected void onSuccess(ArticleBean articleBean) {
                            if (null != articleBean) {
                                mKProgressHUD.dismiss();
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
                            } else {
                                if (mRefreshLayout.isRefreshing()) {
                                    mRefreshLayout.finishRefresh();
                                } else if (mRefreshLayout.isLoading()) {
                                    mRefreshLayout.finishLoadMore();
                                }
                            }
                        }

                        @Override
                        protected void updataLayout(int flag) {

                        }
                    });
        }
    }

    /**
     * 收藏
     *
     * @param id
     */
    @SuppressLint("CheckResult")
    private void collectArticle(int id) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getCollectArticle(id, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(objectBeanModule -> {
                    mKProgressHUD.dismiss();
                    T.showShort(getString(R.string.collection_success));
                });
    }

    /**
     * 取消收藏
     *
     * @param id
     */
    @SuppressLint("CheckResult")
    private void uncollectArticle(int id) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .uncollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(objectBeanModule -> {
                    mKProgressHUD.dismiss();
                    T.showShort(getString(R.string.cancel_collection_success));
                });
    }


    private void initRecyle(int type) {
        if (type == 1) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mOfficialAccountListAdapter = new OfficialAccountListAdapter(new ArrayList<>());
            mOfficialAccountListAdapter.setOnItemClickListener((adapter, view, position) -> {
                WebViewActivity.startWebActivity(this
                        , mOfficialAccountListAdapter.getData().get(position).getLink()
                        , mAdapter.getData().get(position).getId());// 详情
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            });
            mOfficialAccountListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                switch (view.getId()) {
                    case R.id.image_collect:
                        if (SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin)) {
                            if (mOfficialAccountListAdapter.getData().get(position).isCollect()) { //收藏
                                uncollectArticle(mOfficialAccountListAdapter.getData().get(position).getId());
                                mOfficialAccountListAdapter.getData().get(position).setCollect(false);
                                mOfficialAccountListAdapter.notifyItemChanged(position, "");
                            } else {
                                collectArticle(mOfficialAccountListAdapter.getData().get(position).getId());
                                mOfficialAccountListAdapter.getData().get(position).setCollect(true);
                                mOfficialAccountListAdapter.notifyItemChanged(position, "");
                            }
                        } else {
                            JumpUtils.jump(this, LoginActivity.class, null);
                            T.showShort(R.string.collect_login);
                        }
                        break;
                }
            });
            mRecyclerView.setAdapter(mOfficialAccountListAdapter);
            mOfficialAccountListAdapter.setEmptyView(R.layout.activity_null, (ViewGroup) mRecyclerView.getParent());
        } else if (type == 2) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mAdapter = new HomeAdapter(R.layout.item_home, new ArrayList<>());
            mAdapter.setOnItemClickListener((adapter, view, position) -> {
                WebViewActivity.startWebActivity(this
                        , mAdapter.getData().get(position).getLink()
                        , mAdapter.getData().get(position).getId());// 详情
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
                            JumpUtils.jump(this, LoginActivity.class, null);
                            T.showShort(R.string.collect_login);
                        }
                        break;
                }
            });
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setEmptyView(R.layout.activity_null, (ViewGroup) mRecyclerView.getParent());
        }
    }

    /**
     * 分页加载数据
     */
    private void initRefresh(int type) {
        if (type == 1) {
            mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
            mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
            mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    mPageOffNo += 1;
                    getQuery(type, mPageOffNo, queryKey);
                }

                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    mPageOffNo = 1;
                    getQuery(type, mPageOffNo, queryKey);
                }
            });
        } else if (type == 2) {
            mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
            mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
            mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    mPageNo += 1;
                    getQuery(type, mPageNo, queryKey);
                }

                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    mPageNo = 0;
                    getQuery(type, mPageNo, queryKey);
                }
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
        }
        if (mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadMore();
        }
    }
}
