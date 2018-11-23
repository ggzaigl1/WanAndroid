package com.example.gab.babylove.ui.navigation.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.NavigationBean;
import com.example.gab.babylove.ui.navigation.adapter.NavigationLifeAdapter;
import com.example.gab.babylove.ui.navigation.adapter.NavigationRightAdapter;
import com.example.gab.babylove.web.WebViewActivity;
import com.example.gab.babylove.base.BaseFragment;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.NetworkUtils;
import com.ggz.baselibrary.utils.ResourceUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Gab
 * @date 2017/12/15 0015
 * 导航数据
 */

public class NavigationViewFragment extends BaseFragment implements TagFlowLayout.OnTagClickListener {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerViewLife;
    @BindView(R.id.id_tagFlow)
    TagFlowLayout mTagFlowLayout;

    NavigationLifeAdapter mAdapterLife;
    NavigationRightAdapter mAdapterRight;
    //实现单选 变量保存当前选中的position
    private int mSelectedPos = 0;
    private LinearLayoutManager mLinearLayoutManager;
    private List<NavigationBean.ArticlesBean> mArticles;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        initRecyclerLife();
//        initRecyclerRight();
        getNavigationList();
//        initHotKeyData();
        mTagFlowLayout.setOnTagClickListener(this);
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
                                initNavigationData(navigationBeans.get(0).getArticles());
//                                mAdapterRight.setNewData(navigationBeans.get(mSelectedPos).getArticles());
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
        mRecyclerViewLife.setLayoutManager(mLinearLayoutManager);
        mAdapterLife = new NavigationLifeAdapter(new ArrayList<>());
        mAdapterLife.setOnItemClickListener((adapter, view, position) -> {
            NavigationBean navigationBean = mAdapterLife.getData().get(position);
            //如果item位置不等于0 就不显示
            if (mSelectedPos != position) {
                mAdapterLife.getData().get(mSelectedPos).setSelected(false);
                mAdapterLife.notifyItemChanged(mSelectedPos);
                // position 位置 赋予给mSelectedPos
                mSelectedPos = position;
                mAdapterLife.getData().get(mSelectedPos).setSelected(true);
                mAdapterLife.notifyItemChanged(mSelectedPos);

            }
            mArticles = navigationBean.getArticles();
            initNavigationData(mArticles);
//            mAdapterRight.setNewData(navigationBean.getArticles());
            View childAt = mRecyclerViewLife.getChildAt(position - mLinearLayoutManager.findFirstVisibleItemPosition());
            if (childAt != null) {
                int y = childAt.getTop() - mRecyclerViewLife.getHeight() / 2;
                mRecyclerViewLife.smoothScrollBy(0, y);
            }
        });
        mRecyclerViewLife.setAdapter(mAdapterLife);
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        WebViewActivity.startWebActivity(getActivity()
                , mArticles.get(position).getLink()
                , mArticles.get(position).getId()
                , mArticles.get(position).isCollect());
        mContext.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        return true;
    }

    private void initNavigationData(List<NavigationBean.ArticlesBean> articles) {
        mTagFlowLayout.setAdapter(new TagAdapter(articles) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView mTvDate = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.item_navigation_cid, parent, false);
                mTvDate.setText(articles.get(position).getTitle());
                mTvDate.setTextColor(ResourceUtils.getRandomColor());
                return mTvDate;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (NetworkUtils.isConnected(ConfigUtils.getAppCtx())) {
            initRecyclerLife();
            getNavigationList();
            mKProgressHUD.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initRecyclerRight() {
//        mRecyclerViewRight.scrollToPosition(0);
//        mRecyclerViewRight.setLayoutManager(new GridLayoutManager(mContext, 4));
//        mAdapterRight = new NavigationRightAdapter(new ArrayList<>());
//        mAdapterRight.setOnItemClickListener((adapter, view, position) -> {
//            WebViewActivity.startWebActivity(getActivity()
//                    , mAdapterRight.getData().get(position).getLink()
//                    , mAdapterRight.getData().get(position).getId());
//            mContext.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//        });
//        mRecyclerViewRight.setAdapter(mAdapterRight);
    }

}
