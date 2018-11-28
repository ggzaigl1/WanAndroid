package com.example.gab.babylove.ui.navigation.fragment;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.NavigationBean;
import com.example.gab.babylove.ui.navigation.adapter.NavigationAdapter;
import com.example.gab.babylove.base.BaseFragment;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * @author Gab
 * @date 2017/12/15 0015
 * 导航数据
 */

public class NavigationFragment extends BaseFragment {

    @BindView(R.id.vtl_navigation)
    VerticalTabLayout vtlNavigation;
    @BindView(R.id.rv_navigation)
    RecyclerView mRecyclerView;

    NavigationAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean needScroll;
    private int index;
    private boolean isClickTab;

    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    protected void initView(View view) {
        initRecyclerRight();
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_navigation_view;
    }

    @Override
    protected void initData() {
        getNavigationList();
    }

    /**
     * 列表数据加载
     */
    @SuppressLint("CheckResult")
    private void getNavigationList() {
        mKProgressHUD = KProgressHUD
                .create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        RequestUtils.create(ApiService.class)
                .getNavigationList()
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<List<NavigationBean>>() {
                    @Override
                    protected void onSuccess(List<NavigationBean> navigationBeans) {
                        vtlNavigation.setTabAdapter(new TabAdapter() {
                            @Override
                            public int getCount() {
                                return navigationBeans == null ? 0 : navigationBeans.size();
                            }

                            @Override
                            public ITabView.TabBadge getBadge(int position) {
                                return null;
                            }

                            @Override
                            public ITabView.TabIcon getIcon(int position) {
                                return null;
                            }

                            @Override
                            public ITabView.TabTitle getTitle(int position) {
                                return new TabView.TabTitle.Builder()
                                        .setContent(navigationBeans.get(position).getName())
                                        .setTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent),
                                                ContextCompat.getColor(getActivity(), R.color.colorAutoDismissToast))
                                        .build();
                            }

                            @Override
                            public int getBackground(int position) {
                                return -1;
                            }
                        });
                        mAdapter.setNewData(navigationBeans);
                        leftRightLinkage();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    private void initRecyclerRight() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new NavigationAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void leftRightLinkage() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView();
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (isClickTab) {
                        isClickTab = false;
                        return;
                    }
                    int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                    if (index != firstPosition) {
                        index = firstPosition;
                        setChecked(index);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (needScroll) {
                    scrollRecyclerView();
                }
            }
        });

        vtlNavigation.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tabView, int i) {
                isClickTab = true;
                index = i;
                mRecyclerView.stopScroll();
                smoothScrollToPosition(i);
            }

            @Override
            public void onTabReselected(TabView tabView, int i) {
            }
        });
    }

    private void scrollRecyclerView() {
        needScroll = false;
//        int indexDistance = index - mLinearLayoutManager.findFirstVisibleItemPosition();
//        if (indexDistance >= 0 && indexDistance < mRecyclerView.getChildCount()) {
//            int top = mRecyclerView.getChildAt(indexDistance).getTop();
//            mRecyclerView.smoothScrollBy(0, top);
//        }

        View childAt = mRecyclerView.getChildAt(index - mLinearLayoutManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = childAt.getTop() - mRecyclerView.getHeight() / 2;
            mRecyclerView.smoothScrollBy(0, y);
        }
    }

    private void setChecked(int position) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (vtlNavigation == null) {
                return;
            }
            vtlNavigation.setTabSelected(index);
        }
        index = position;
    }

    private void smoothScrollToPosition(int currentPosition) {
        int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLinearLayoutManager.findLastVisibleItemPosition();
        if (currentPosition <= firstPosition) {
            mRecyclerView.smoothScrollToPosition(currentPosition);
        } else if (currentPosition <= lastPosition) {
            int top = mRecyclerView.getChildAt(currentPosition - firstPosition).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            mRecyclerView.smoothScrollToPosition(currentPosition);
            needScroll = true;
        }
    }
}
