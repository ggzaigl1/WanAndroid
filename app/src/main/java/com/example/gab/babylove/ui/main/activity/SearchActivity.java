package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.ArticleBean;
import com.example.gab.babylove.ui.main.adapter.HomeAdapter;
import com.example.gab.babylove.ui.main.login.LoginActivity;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.BeanModule;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.SpfUtils;
import com.ggz.baselibrary.utils.ToastUtils;
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

public class SearchActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    /**
     * 当前显示的页码(从 0 开始)
     */
    int mPageNo = 0;
    String queryKey;
    HomeAdapter mAdapter;

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
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        //设置导航图标要在setSupportActionBar方法之后
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 给左上角图标的左边加上一个返回的图标
        mToolbar.setNavigationOnClickListener(v -> JumpUtils.exitActivity(this));
        queryKey = getIntent().getStringExtra("query");
        getQuery(mPageNo, queryKey);
        initRecyle();
        initRefresh();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }

    //   搜索接口
    private void getQuery(int pageNum, String queryKey) {
        RequestUtils.create(ApiService.class)
                .getQuery(pageNum, queryKey)
                .compose(RxHelper.handleResult())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(new NetCallBack<ArticleBean>() {
                    @Override
                    protected void onSuccess(ArticleBean articleBean) {
                        if (null != articleBean) {
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
        RequestUtils.create(ApiService.class)
                .getCollectArticle(id, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(objectBeanModule -> ToastUtils.showShort("收藏成功"));
    }

    //    取消收藏
    @SuppressLint("CheckResult")
    private void uncollectArticle(int id) {
        RequestUtils.create(ApiService.class)
                .uncollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(objectBeanModule -> ToastUtils.showShort("取消收藏成功"));
    }


    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeAdapter(R.layout.item_home, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ArticleBean.DatasBean bean = mAdapter.getData().get(position);
            WebViewActivity.startWebActivity(this, bean.getLink());// 详情
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
                        ToastUtils.showShort(R.string.collect_login);
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
