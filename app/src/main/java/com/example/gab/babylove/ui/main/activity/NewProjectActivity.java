package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.ListProjectBean;
import com.example.gab.babylove.ui.main.adapter.NewProjectAdapter;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 初夏小溪 on 2018/10/17 0017.
 * 最新项目
 */
public class NewProjectActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    NewProjectAdapter mAdapter;
    int mPageNo = 1;


    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_new_project;
    }

    @Override
    public void setStatusBar(Activity activity) {
//        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        initRecyle();
        initRefresh();
        getListProject(mPageNo);
    }

    /**
     * 列表数据加载
     */
    @SuppressLint("CheckResult")
    private void getListProject(int mPageNo) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getListProject(mPageNo)
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<ListProjectBean>() {
                    @Override
                    protected void onSuccess(ListProjectBean listProjectBean) {
                        if (null != listProjectBean) {
                            mKProgressHUD.dismiss();
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(listProjectBean.getDatas());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(listProjectBean.getDatas());
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(listProjectBean.getDatas());
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
                        mKProgressHUD.dismiss();
                    }
                });
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
                getListProject(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 1;
                getListProject(mPageNo);
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


    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NewProjectAdapter(R.layout.item_fly, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            WebViewActivity.startWebActivity(this
                    , mAdapter.getData().get(position).getLink()
                    , mAdapter.getData().get(position).getId());// 详情
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
