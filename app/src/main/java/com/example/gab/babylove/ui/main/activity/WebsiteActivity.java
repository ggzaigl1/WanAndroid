package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.ui.main.adapter.WebsiteAdapter;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.BookmarkBean;
import com.example.gab.babylove.web.AgentWebActivity;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.retrofit.RequestUtils;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.JumpUtils;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
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
 * Created by 初夏小溪 on 2018/4/19 0019.
 * 常用网站
 */

public class WebsiteActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    WebsiteAdapter mAdapter;

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
        getBookmarkList();
        initRefresh();
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
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);

        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new WebsiteAdapter(R.layout.item_website, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            BookmarkBean bean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("UrlBean", bean.getLink());
            JumpUtils.jump(this, AgentWebActivity.class, bundle);// 详情
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 常用网站 数据加载
     */
    @SuppressLint("CheckResult")
    private void getBookmarkList() {
        RequestUtils.create(ApiService.class)
                .getBookmarkList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listBeanModule -> {
                    if (null != listBeanModule) {
                        if (mRefreshLayout.isRefreshing()) {
                            mAdapter.setNewData(listBeanModule.getData());
                            mRefreshLayout.finishRefresh();
                        } else if (mRefreshLayout.isLoading()) {
                            mAdapter.getData().addAll(listBeanModule.getData());
                            mRefreshLayout.finishLoadMore();
                            mAdapter.notifyDataSetChanged();
                        } else {
                            mAdapter.setNewData(listBeanModule.getData());
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
                getBookmarkList();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getBookmarkList();
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
