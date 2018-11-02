package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.OfficialAccountListBean;
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
 * Created by 初夏小溪 on 2018/10/15 0015.
 * 公众号 内容
 */
public class OfficialAccountListActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    OfficialAccountListAdapter mAdapter;
    private int mId;
    int mPageNo = 1;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_official_account;
    }

    @Override
    public void setStatusBar(Activity activity) {
//        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        mId = getIntent().getIntExtra("id", 0);
        initRecyle();
        initRefresh();
        getChaptersList(mPageNo);
    }

    /**
     * 公众号详情 数据加载
     */
    @SuppressLint("CheckResult")
    private void getChaptersList(int mPageNo) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getWxarticle(mId, mPageNo)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<OfficialAccountListBean>() {
                    @Override
                    protected void onSuccess(OfficialAccountListBean officialAccountListBean) {
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
                        mKProgressHUD.dismiss();
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(objectBeanModule -> {
                    mKProgressHUD.dismiss();
                    T.showShort("收藏成功");
                });
    }

    //    取消收藏
    @SuppressLint("CheckResult")
    private void uncollectArticle(int id) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .uncollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(objectBeanModule -> {
                    mKProgressHUD.dismiss();
                    T.showShort("取消收藏成功");
                });
    }

    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OfficialAccountListAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            WebViewActivity.startWebActivity(this,mAdapter.getData().get(position).getLink());// 详情
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
                getChaptersList(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 1;
                getChaptersList(mPageNo);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("搜索知识点");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putString("query", query);
                bundle.putInt("id",mId);
                JumpUtils.jump(OfficialAccountListActivity.this, SearchActivity.class, bundle);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}
