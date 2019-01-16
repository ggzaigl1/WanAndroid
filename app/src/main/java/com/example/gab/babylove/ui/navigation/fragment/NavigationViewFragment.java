package com.example.gab.babylove.ui.navigation.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseFragment;
import com.example.gab.babylove.entity.NavigationBean;
import com.example.gab.babylove.ui.navigation.adapter.NavigationLifeAdapter;
import com.example.gab.babylove.ui.navigation.adapter.NavigationRightAdapter;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.NetworkUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Gab
 * @date 2017/12/15 0015
 * 导航数据
 */

public class NavigationViewFragment extends BaseFragment {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerViewLife;
    @BindView(R.id.rv_context)
    RecyclerView mRecyclerViewRight;
    @BindView(R.id.include_nothing_data)
    View includeNothingData;

    NavigationLifeAdapter mAdapterLife;
    NavigationRightAdapter mAdapterRight;
    //实现单选 变量保存当前选中的position
    private int mSelectedPos = 0;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    protected void initView(View view) {
        initRecyclerLife();
        initRecyclerRight();
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_navigation;
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
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getNavigationList()
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<List<NavigationBean>>() {
                    @Override
                    protected void onSuccess(List<NavigationBean> navigationBeans) {
                        if (null != navigationBeans) {
                            mKProgressHUD.dismiss();
                            mAdapterLife.setNewData(navigationBeans);
                            if (mSelectedPos == 0) {
                                mAdapterLife.getData().get(mSelectedPos).setSelected(true);
                                mAdapterRight.setNewData(navigationBeans.get(mSelectedPos).getArticles());
                            }
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


    private void initRecyclerLife() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerViewLife.setLayoutManager( new LinearLayoutManager(mContext));
        mAdapterLife = new NavigationLifeAdapter(new ArrayList<>());
        mAdapterLife.setOnItemClickListener((adapter, view, position) -> {
            //如果item位置不等于0 就不显示
            if (mSelectedPos != position) {
                mAdapterLife.getData().get(mSelectedPos).setSelected(false);
                mAdapterLife.notifyItemChanged(mSelectedPos);
                // position 位置 赋予给mSelectedPos
                mSelectedPos = position;
                mAdapterLife.getData().get(mSelectedPos).setSelected(true);
                mAdapterLife.notifyItemChanged(mSelectedPos);

            }
            NavigationBean navigationBean = mAdapterLife.getData().get(position);
            mAdapterRight.setNewData(navigationBean.getArticles());
            View childAt = mRecyclerViewLife.getChildAt(position - mLinearLayoutManager.findFirstVisibleItemPosition());
            if (childAt != null) {
                int y = childAt.getTop() - mRecyclerViewLife.getHeight() / 2;
                mRecyclerViewLife.smoothScrollBy(0, y);
            }
        });
        mRecyclerViewLife.setAdapter(mAdapterLife);
    }

    private void initRecyclerRight() {
        mRecyclerViewRight.scrollToPosition(0);
        mRecyclerViewRight.setLayoutManager(new GridLayoutManager(mContext, 3));
        mAdapterRight = new NavigationRightAdapter(new ArrayList<>());
        mAdapterRight.setOnItemClickListener((adapter, view, position) -> {
            WebViewActivity.startWebActivity(getActivity()
                    , mAdapterRight.getData().get(position).getLink()
                    , mAdapterRight.getData().get(position).getId()
                    , mAdapterRight.getData().get(position).isCollect());
            mContext.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        mRecyclerViewRight.setAdapter(mAdapterRight);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!NetworkUtils.isConnected(ConfigUtils.getAppCtx())) {
            includeNothingData.setVisibility(View.VISIBLE);
            initRecyclerLife();
            getNavigationList();
            mKProgressHUD.dismiss();
        }
    }
}
