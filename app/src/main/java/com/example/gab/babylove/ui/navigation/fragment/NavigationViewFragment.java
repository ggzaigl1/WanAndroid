package com.example.gab.babylove.ui.navigation.fragment;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.NavigationBean;
import com.example.gab.babylove.ui.navigation.adapter.NavigationCidAdapter;
import com.example.gab.babylove.ui.navigation.adapter.NavigationViewAdapter;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.base.BaseFragment;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Gab on 2017/12/15 0015.
 * 导航数据
 */

public class NavigationViewFragment extends BaseFragment {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView_Title;
    @BindView(R.id.rv_context)
    RecyclerView mRecyclerView_Context;

    NavigationViewAdapter mAdapter;
    NavigationCidAdapter mNavigationCidAdapter;

    private int mSelectedPos = 0;//实现单选 变量保存当前选中的position
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        initRecycler();
        initRecyclerCid();
        getNavigationList();
    }

    /**
     * 列表数据加载
     */
    @SuppressLint("CheckResult")
    private void getNavigationList() {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getNaviList()
                .compose(RxHelper.handleResult())
                .subscribe(navigationBeanBeanModule -> {
                    if (null != navigationBeanBeanModule) {
                        mKProgressHUD.dismiss();
                        mAdapter.setNewData(navigationBeanBeanModule);
                        if (mSelectedPos == 0) {
                            mAdapter.getData().get(mSelectedPos).setSelected(true);
                            mNavigationCidAdapter.setNewData(navigationBeanBeanModule.get(mSelectedPos).getArticles());
                        }
                    }
                });
    }


    private void initRecycler() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView_Title.setLayoutManager(mLinearLayoutManager);
        mAdapter = new NavigationViewAdapter(new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            NavigationBean navigationBean = mAdapter.getData().get(position);
            //如果item位置不等于0 就不显示
            if (mSelectedPos != position) {
                mAdapter.getData().get(mSelectedPos).setSelected(false);
                mAdapter.notifyItemChanged(mSelectedPos);
                // position 位置 赋予给mSelectedPos
                mSelectedPos = position;
                mAdapter.getData().get(mSelectedPos).setSelected(true);
                mAdapter.notifyItemChanged(mSelectedPos);

            }
            mNavigationCidAdapter.setNewData(navigationBean.getArticles());
            View childAt = mRecyclerView_Title.getChildAt(position - mLinearLayoutManager.findFirstVisibleItemPosition());
            if (childAt != null) {
                int y = childAt.getTop() - mRecyclerView_Title.getHeight() / 2;
                mRecyclerView_Title.smoothScrollBy(0, y);
            }
        });
        mRecyclerView_Title.setAdapter(mAdapter);
    }

    private void initRecyclerCid() {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getActivity());
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);
        mRecyclerView_Context.scrollToPosition(0);
        mRecyclerView_Context.setLayoutManager(layoutManager);
        mNavigationCidAdapter = new NavigationCidAdapter(R.layout.item_navigation_cid, new ArrayList<>());
        mNavigationCidAdapter.setOnItemClickListener((adapter, view, position) -> {
            WebViewActivity.startWebActivity(getActivity()
                    , mNavigationCidAdapter.getData().get(position).getLink()
                    , mNavigationCidAdapter.getData().get(position).getId());
            mContext.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        mRecyclerView_Context.setAdapter(mNavigationCidAdapter);
    }

}
