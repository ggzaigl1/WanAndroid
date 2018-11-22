package com.example.gab.babylove.ui.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.BaseBean;
import com.example.gab.babylove.ui.main.activity.NewProjectActivity;
import com.example.gab.babylove.ui.main.adapter.BaseAdapter;
import com.example.gab.babylove.ui.main.login.LoginActivity;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.base.BaseFragment;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
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

/**
 * @author 初夏小溪
 * @date 2018/4/20 0020
 * 项目 TabLayout Fragment
 */

public class SystemStarFragment extends BaseFragment {

    @BindView(R.id.rvLayout)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.fab_top)
    FloatingActionButton mFabTop;

    public static final String ARG_PARAM1 = "id";
    public static final String ARG_PARAM2 = "param2";
    BaseAdapter mAdapter;
    int mPageNo = 1;
    private Bundle mBundle;

    public static SystemStarFragment getInstance(int id, String param2) {
        SystemStarFragment fragment = new SystemStarFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PARAM1, id);
        bundle.putString(ARG_PARAM2, param2);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_system_fly;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        initRecyle();
        initRefresh();

        mBundle = getArguments();
        assert mBundle != null;
        getArticleList(mBundle.getInt(ARG_PARAM1));

        mFabTop.setOnClickListener(v -> JumpUtils.jumpFade(mContext, NewProjectActivity.class, null));
    }

    /**
     * 列表数据加载
     */
    @SuppressLint("CheckResult")
    private void getArticleList(int id) {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getProjectList(mPageNo, id)
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<BaseBean>() {
                    @Override
                    protected void onSuccess(BaseBean baseBean) {
                        if (null != baseBean) {
                            mKProgressHUD.dismiss();
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(baseBean.getDatas());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(baseBean.getDatas());
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(baseBean.getDatas());
                            }
                        }
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
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPageNo += 1;
                getArticleList(mBundle.getInt(ARG_PARAM1));
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 0;
                getArticleList(mBundle.getInt(ARG_PARAM1));
            }
        });
    }



    /**
     * 收藏
     *
     * @param id
     */
    @SuppressLint("CheckResult")
    private void collectArticle(View view, int id) {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getCollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        Snackbar.make(view, R.string.collection_success, Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                        mKProgressHUD.dismiss();
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
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .unCollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
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

    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BaseAdapter(new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WebViewActivity.startWebActivity(mContext
                        , mAdapter.getData().get(position).getLink()
                        , mAdapter.getData().get(position).getId()
                        , mAdapter.getData().get(position).isCollect());
                mContext.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        mAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.activity_null, (ViewGroup) mRecyclerView.getParent(), false));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.image_collect:

                    if (TextUtils.isEmpty(SpfUtils.getSpfSaveStr(ConstantUtils.userName))) {
                        JumpUtils.jumpFade(mContext, LoginActivity.class, null);
                        T.showShort(R.string.collect_login);
                    } else {
                        if (mAdapter.getData().get(position).isCollect()) {
                            unCollectArticle(mAdapter.getData().get(position).getId());
                            mAdapter.getData().get(position).setCollect(false);
                            mAdapter.notifyItemChanged(position, "");
                        } else {
                            collectArticle(view, mAdapter.getData().get(position).getId());
                            mAdapter.getData().get(position).setCollect(true);
                            mAdapter.notifyItemChanged(position, "");
                        }
                    }

                    //Todo 收藏
//                    if (SpfUtils.getSpfSaveBoolean(ConstantUtils.isLogin)) {
//                        if (mAdapter.getData().get(position).isCollect()) {
//                            unCollectArticle(mAdapter.getData().get(position).getId());
//                            mAdapter.getData().get(position).setCollect(false);
//                            mAdapter.notifyItemChanged(position, "");
//                        } else {
//                            collectArticle(mAdapter.getData().get(position).getId());
//                            mAdapter.getData().get(position).setCollect(true);
//                            mAdapter.notifyItemChanged(position, "");
//                        }
//                    } else {
//                        JumpUtils.jumpFade(mContext, LoginActivity.class, null);
//                        T.showShort(R.string.collect_login);
//                    }
                    break;
                default:
                    break;
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(defaultItemAnimator);
    }

    @Override
    public void onStart() {
        super.onStart();
        mKProgressHUD.dismiss();
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
