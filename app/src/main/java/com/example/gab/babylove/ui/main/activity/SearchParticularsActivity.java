package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

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
import com.ggz.baselibrary.utils.KeyBoardUtils;
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
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/11/8 0008.
 * 搜索详情
 */
public class SearchParticularsActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.edit_search)
    EditText edit_search;

    int mPageNo = 0;
    private String mQueryKey;
    SearchParticularsListAdapter mSearchParticularsListAdapter;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_search_patriculars;
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        mQueryKey = getIntent().getStringExtra("queryKey");
        getQuery(mPageNo, mQueryKey);
        initRecyle();
        initRefresh();
        //搜索按钮监听
        edit_search.setText(mQueryKey);
        edit_search.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                mQueryKey = edit_search.getText().toString();
                getQuery(mPageNo, mQueryKey);
                initRecyle();
                mKProgressHUD.dismiss();
                KeyBoardUtils.closeKeyBoard(this);
                return true;
            }
            return false;
        });
    }

    /**
     * 搜索接口
     * type =1 公众号
     * type =2 首页搜索
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
                        KeyBoardUtils.closeKeyBoard(SearchParticularsActivity.this);
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    /**
     * 搜索条目 设置
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

    @OnClick({R.id.tv_search})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_search:
                mQueryKey = edit_search.getText().toString();
                getQuery(mPageNo, mQueryKey);
                initRecyle();
                mKProgressHUD.dismiss();
                KeyBoardUtils.closeKeyBoard(this);
                break;
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
     * 分页加载数据
     */
    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPageNo += 1;
                getQuery(mPageNo, mQueryKey);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 0;
                getQuery(mPageNo, mQueryKey);
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
    public void finish() {
        super.finish();
        KeyBoardUtils.closeKeyBoard(SearchParticularsActivity.this);
    }
}
