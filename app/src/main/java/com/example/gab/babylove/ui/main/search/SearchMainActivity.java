package com.example.gab.babylove.ui.main.search;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.BaseBean;
import com.example.gab.babylove.ui.main.adapter.BaseAdapter;
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
 * @author 初夏小溪
 * @date 2018/5/11 0011
 * 搜索界面 Main主页 配合Toolbar使用
 */

public class SearchMainActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    int mPageNo = 0;
    String queryKey;
    BaseAdapter mAdapter;

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
        // 给左上角图标的左边加上一个返回的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> JumpUtils.exitActivity(this));
        queryKey = getIntent().getStringExtra("query");
        getQuery(mPageNo, queryKey);
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
                .getQuery(pageNum, queryKey)
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<BaseBean>() {
                    @Override
                    protected void onSuccess(BaseBean officialAccountListBean) {
                        if (null != officialAccountListBean) {
                            mKProgressHUD.dismiss();
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(officialAccountListBean.getDatas());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(officialAccountListBean.getDatas());
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(officialAccountListBean.getDatas());
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


    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseAdapter(new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            WebViewActivity.startWebActivity(this
                    , mAdapter.getData().get(position).getLink()
                    , mAdapter.getData().get(position).getId());
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.image_collect:
                    if (SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin)) {
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
                        JumpUtils.jumpFade(this, LoginActivity.class, null);
                        T.showShort(R.string.collect_login);
                    }
                    break;
                default:
                    break;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setEmptyView(R.layout.activity_null, (ViewGroup) mRecyclerView.getParent());
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
                mPageNo += 1;
                getQuery(mPageNo, queryKey);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 0;
                getQuery(mPageNo, queryKey);
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
