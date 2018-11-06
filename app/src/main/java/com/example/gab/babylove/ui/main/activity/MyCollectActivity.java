package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.CollectBean;
import com.example.gab.babylove.ui.main.adapter.CollectAdapter;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.utils.T;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * @author 初夏小溪
 * @date 2018/4/19 0019
 * 我的收藏
 */

public class MyCollectActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    CollectAdapter mAdapter;
    int mPageNo = 0;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_collect;
    }

    @Override
    public void setStatusBar(Activity activity) {
//        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        initRecyle();
        initRefresh();
        getArticleList(mPageNo);
    }

    /**
     * recycleview 相关设置
     */
    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CollectAdapter(R.layout.item_collect_my, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            WebViewActivity.startWebActivity(this
                    , mAdapter.getData().get(position).getLink()
                    , mAdapter.getData().get(position).getId());// 详情
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.image_collect:
                    unMycollectArticle(mAdapter.getData().get(position).getId(), mAdapter.getData().get(position).getOriginId(), position);
                    break;
                default:
                    break;
            }

        });
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 我的收藏 数据加载
     */
    @SuppressLint("CheckResult")
    private void getArticleList(int mPageNo) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getCollectList(mPageNo)
                .compose(RxHelper.handleResult())
                .subscribe(new Consumer<CollectBean>() {
                    @Override
                    public void accept(CollectBean collectBeanBeanModule) throws Exception {
                        if (null != collectBeanBeanModule) {
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(collectBeanBeanModule.getDatas());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(collectBeanBeanModule.getDatas());
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(collectBeanBeanModule.getDatas());
                            }
                            mKProgressHUD.dismiss();
                        }
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
                getArticleList(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 0;
                getArticleList(mPageNo);
            }
        });
    }

    //    我的收藏页面, 取消收藏
    @SuppressLint("CheckResult")
    private void unMycollectArticle(int id, int OriginId, int position) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .unMyCollectArticle(id, OriginId)
                .compose(RxHelper.handleResult())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        mAdapter.remove(position);
                        mAdapter.notifyDataSetChanged();
                        T.showShort(getString(R.string.cancel_collection_success));
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void updataLayout(int flag) {

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
