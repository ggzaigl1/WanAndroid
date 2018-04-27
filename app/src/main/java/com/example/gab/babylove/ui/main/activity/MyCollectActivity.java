package com.example.gab.babylove.ui.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.CollectBean;
import com.example.gab.babylove.ui.main.adapter.CollectAdapter;
import com.example.gab.babylove.web.AgentWebActivity;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.retrofit.BeanModule;
import com.fy.baselibrary.retrofit.RequestUtils;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪 on 2018/4/19 0019.
 * 我的收藏
 */

public class MyCollectActivity extends AppCompatActivity implements IBaseActivity {

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
        return R.layout.activity_website;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        initRecyle();
        initRefresh();
        getArticleList(mPageNo);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }

    /**
     * recycleview 相关设置
     */
    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CollectAdapter(R.layout.item_collect_my, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            CollectBean.DatasBean bean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("UrlBean", bean.getLink());
            JumpUtils.jump(this, AgentWebActivity.class, bundle);// 详情
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.image_collect:
                        unMycollectArticle(mAdapter.getData().get(position).getId(), mAdapter.getData().get(position).getOriginId(), position);
                        break;
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 我的收藏 数据加载
     */
    private void getArticleList(int mPageNo) {
        RequestUtils.create(ApiService.class)
                .getCollectList(mPageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanModule<CollectBean>>() {
                    @Override
                    public void accept(BeanModule<CollectBean> collectBeanBeanModule) throws Exception {
                        if (null != collectBeanBeanModule) {
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(collectBeanBeanModule.getData().getDatas());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(collectBeanBeanModule.getData().getDatas());
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(collectBeanBeanModule.getData().getDatas());
                            }
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
    private void unMycollectArticle(int id, int OriginId, int position) {
        RequestUtils.create(ApiService.class)
                .unMyCollectArticle(id, OriginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mAdapter.remove(position);
                        mAdapter.notifyDataSetChanged();
                        ToastUtils.showShort("取消收藏成功");
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
