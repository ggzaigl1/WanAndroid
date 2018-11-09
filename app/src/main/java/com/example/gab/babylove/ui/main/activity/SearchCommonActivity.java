package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.ArticleBean;
import com.example.gab.babylove.ui.main.adapter.SearchParticularsListAdapter;
import com.example.gab.babylove.ui.main.login.LoginActivity;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.application.IBaseActivity;
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

import butterknife.BindView;

/**
 * Created by 初夏小溪 on 2018/5/11 0011.
 * 公众号 搜索界面
 */

public class SearchCommonActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    int mPageOffNo = 1;
    int mId;
    String queryKey;
    SearchParticularsListAdapter mSearchParticularsListAdapter;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_search;
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        //设置导航图标要在setSupportActionBar方法之后
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 给左上角图标的左边加上一个返回的图标
        mToolbar.setNavigationOnClickListener(v -> JumpUtils.exitActivity(this));
        queryKey = getIntent().getStringExtra("query");
        getQuery(mPageOffNo, queryKey);
        initRecyle();
        initRefresh();

    }

    /**
     * 搜索接口
     *
     * @param pageNum
     * @param queryKey
     */
    private void getQuery(int pageNum, String queryKey) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getWxarticleQuery(mId, pageNum, queryKey)
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<ArticleBean>() {
                    @Override
                    protected void onSuccess(ArticleBean officialAccountListBean) {
                        if (null != officialAccountListBean) {
                            mKProgressHUD.dismiss();
                            if (mRefreshLayout.isRefreshing()) {
                                mSearchParticularsListAdapter.setNewData(officialAccountListBean.getDatas());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mSearchParticularsListAdapter.getData().addAll(officialAccountListBean.getDatas());
                                mRefreshLayout.finishLoadMore();
                                mSearchParticularsListAdapter.notifyDataSetChanged();
                            } else {
                                mSearchParticularsListAdapter.setNewData(officialAccountListBean.getDatas());
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
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        mKProgressHUD.dismiss();
                        T.showShort(getString(R.string.collection_success));
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
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .unCollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        T.showShort(getString(R.string.cancel_collection_success));
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    /**
     * 设置
     */
    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSearchParticularsListAdapter = new SearchParticularsListAdapter(new ArrayList<>());
        mSearchParticularsListAdapter.setOnItemClickListener((adapter, view, position) -> {
            WebViewActivity.startWebActivity(this
                    , mSearchParticularsListAdapter.getData().get(position).getLink()
                    , mSearchParticularsListAdapter.getData().get(position).getId());// 详情
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        mSearchParticularsListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.image_collect:
                    if (SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin)) {
                        if (mSearchParticularsListAdapter.getData().get(position).isCollect()) { //收藏
                            unCollectArticle(mSearchParticularsListAdapter.getData().get(position).getId());
                            mSearchParticularsListAdapter.getData().get(position).setCollect(false);
                            mSearchParticularsListAdapter.notifyItemChanged(position, "");
                        } else {
                            collectArticle(mSearchParticularsListAdapter.getData().get(position).getId());
                            mSearchParticularsListAdapter.getData().get(position).setCollect(true);
                            mSearchParticularsListAdapter.notifyItemChanged(position, "");
                        }
                    } else {
                        JumpUtils.jumpFade(this, LoginActivity.class, null);
                        T.showShort(R.string.collect_login);
                    }
                    break;
            }
        });
        mRecyclerView.setAdapter(mSearchParticularsListAdapter);
        mSearchParticularsListAdapter.setEmptyView(R.layout.activity_null, (ViewGroup) mRecyclerView.getParent());
    }

    /**
     * 分页加载数据
     */
    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPageOffNo += 1;
                getQuery(mPageOffNo, queryKey);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageOffNo = 1;
                getQuery(mPageOffNo, queryKey);
            }
        });
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
