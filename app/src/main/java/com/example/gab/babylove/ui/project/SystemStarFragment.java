package com.example.gab.babylove.ui.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiServiceKotlin;
import com.example.gab.babylove.base.BaseFragment;
import com.example.gab.babylove.entity.BaseBean;
import com.example.gab.babylove.ui.main.adapter.BaseAdapter;
import com.example.gab.babylove.ui.main.login.LoginActivity;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.NetworkUtils;
import com.ggz.baselibrary.utils.SpfUtils;
import com.ggz.baselibrary.utils.T;
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

    public static final String ARG_PARAM1 = "id";
    public static final String ARG_PARAM2 = "param2";
    BaseAdapter mAdapter;
    int mPageNo = 0;
    private int mId;

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
    protected void initData() {
        Bundle bundle = getArguments();
        mId = bundle.getInt(ARG_PARAM1);
        mRefreshLayout.autoRefresh();
    }

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected void initView(View view) {
        initRecyle();
        initRefresh();
    }

    /**
     * 列表数据加载
     */
    @SuppressLint("CheckResult")
    private void getArticleList(int id) {
        RequestUtils.create(ApiServiceKotlin.class)
                .getProjectList(mPageNo, id)
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<BaseBean>() {
                    @Override
                    protected void onSuccess(BaseBean baseBean) {
                        if (null != baseBean) {
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
                getArticleList(mId);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 1;
                getArticleList(mId);
            }
        });
    }

    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BaseAdapter(new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            WebViewActivity.startWebActivity(mContext
                    , mAdapter.getData().get(position).getLink()
                    , mAdapter.getData().get(position).getId()
                    , mAdapter.getData().get(position).isCollect());
            mContext.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.image_collect:
                    if (TextUtils.isEmpty(SpfUtils.getSpfSaveStr(ConstantUtils.userName))) {
                        JumpUtils.jumpFade(mContext, LoginActivity.class, null);
                        T.showShort(R.string.collect_login);
                    } else {
                        if (mAdapter.getData().get(position).isCollect()) {
                            getUnCollectArticle(mAdapter.getData().get(position).getId());
                            mAdapter.getData().get(position).setCollect(false);
                            mAdapter.notifyItemChanged(position, "");
                        } else {
                            getCollectArticle(view, mAdapter.getData().get(position).getId());
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
        if (!NetworkUtils.isConnected(ConfigUtils.getAppCtx())) {
            mAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.activity_null_data, (ViewGroup) mRecyclerView.getParent(), false));
            mRefreshLayout.autoRefresh();
        }
    }
}
