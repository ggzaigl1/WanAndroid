package com.example.gab.babylove.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.activity.PictureDetailActivity;
import com.example.gab.babylove.adapter.GankMAdapter;
import com.example.gab.babylove.bean.OrListBean;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.entity.GankBean;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gab on 2017/12/15 0015.
 * 美圖欣賞
 */

public class PrettyPicturesFragment extends BaseFragment {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvMenu)
    TextView tvMenu;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.fab_top)
    FloatingActionButton fab_top;
    GankMAdapter mAdapter;
    private int mCurPage = 1;
    private final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_wife;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        tvBack.setVisibility(View.GONE);
        tvTitle.setText("美圖欣賞");
        initRv();
        initRefresh();
        getCourseDetails(mCurPage);
    }

    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mCurPage += 1;
                getCourseDetails(mCurPage);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mCurPage = 1;
                getCourseDetails(1);
            }
        });
    }

    @SuppressLint("CheckResult")
    private void getCourseDetails(int mCurPage) {
        mConnService.getCourseDetails(20, mCurPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GankBean>() {
                    @Override
                    public void accept(GankBean gankBean) throws Exception {
                        if (null != gankBean && null != gankBean.getResults()) {
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(gankBean.getResults());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(gankBean.getResults());
                                mRefreshLayout.finishLoadmore();
                                mAdapter.notifyDataSetChanged();
                                T.showShort("又加载了" + gankBean.getResults().size() + "位妹子");
                            } else {
                                mAdapter.setNewData(gankBean.getResults());
                                T.showShort("加载了" + gankBean.getResults().size() + "妹子");
                            }
                        }
                    }
                });
    }

    private void initRv() {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new GankMAdapter(R.layout.item_gank_list_context, new ArrayList<>());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager.findFirstVisibleItemPosition() != 0) {
                    fabInAnim();
                } else {
                    fabOutAnim();
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<GankBean.ResultsBean> data = mAdapter.getData();
            OrListBean orListBean = new OrListBean(data);
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            bundle.putSerializable("orListBean", orListBean);
            JumpUtils.jump(mContext, PictureDetailActivity.class, bundle);
        });

        fab_top.setOnClickListener(v -> {
            LinearLayoutManager manager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            //如果超过50项直接跳到开头，不然要滚好久
            if (manager.findFirstVisibleItemPosition() < 50) {
                mRecyclerView.smoothScrollToPosition(0);
            } else {
                mRecyclerView.scrollToPosition(0);
                fabOutAnim();
            }
        });
    }

    /* 悬浮按钮显示动画 */
    private void fabInAnim() {
        if (fab_top.getVisibility() == View.GONE) {
            fab_top.setVisibility(View.VISIBLE);
            ViewCompat.animate(fab_top).scaleX(1.0F).scaleY(1.0F).alpha(1.0F).setInterpolator(INTERPOLATOR).withLayer().setListener(null).start();
        }
    }

    /* 悬浮图标隐藏动画 */
    private void fabOutAnim() {
        if (fab_top.getVisibility() == View.VISIBLE) {
            ViewCompat.animate(fab_top).scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setInterpolator(INTERPOLATOR).withLayer().setListener(new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationStart(View view) {

                }

                @Override
                public void onAnimationEnd(View view) {
                    fab_top.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(View view) {

                }
            }).start();
        }
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
