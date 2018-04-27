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
import com.example.gab.babylove.entity.CourseList;
import com.example.gab.babylove.ui.main.adapter.OrnamentalListContextAdapter;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RequestUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnRefreshLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪 on 2018/4/9 0009.
 * 运动课程列表
 */

public class OrnamentalListContextActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.ry_data)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    OrnamentalListContextAdapter mAdapter;
    private int mPageNo = 1;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_ornamental_list_context;
    }

    @Override
    public void setStatusBar(Activity activity) {

    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        initRefresh();
        initRv();
        getCourseDetails(mPageNo);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }

    private void getCourseDetails(int mPageNo) {
        RequestUtils.create(ApiService.class)
                .getCourseList(mPageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(new NetCallBack<CourseList>() {
                    @Override
                    protected void onSuccess(CourseList t) {
                        if (t.getResult() == 1) {
                            if (!t.getData().isEmpty()) {
                                if (mRefreshLayout.isRefreshing()) {
                                    mAdapter.setNewData(t.getData());
                                    mRefreshLayout.finishRefresh();
                                } else if (mRefreshLayout.isLoading()) {
                                    mAdapter.getData().addAll(t.getData());
                                    mRefreshLayout.finishLoadmore();
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    mAdapter.setNewData(t.getData());
                                }
                            }
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {
                        if (null != mRefreshLayout) {
                            if (mRefreshLayout.isRefreshing()) {
                                mRefreshLayout.finishRefresh();
                            }
                            if (mRefreshLayout.isLoading()) {
                                mRefreshLayout.finishLoadmore();
                            }
                        }
                    }
                });
    }

    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPageNo += 1;
                getCourseDetails(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getCourseDetails(1);
            }
        });
    }

    private void initRv() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OrnamentalListContextAdapter(R.layout.item_ornamental_list_context, new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<CourseList.DataBean> data = mAdapter.getData();
                Bundle bundle = new Bundle();
                bundle.putInt("id", data.get(position).getId());
                JumpUtils.jump(OrnamentalListContextActivity.this, OrnamentalContextActivity.class, bundle);
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
            mRefreshLayout.finishLoadmore();
        }
    }
}
