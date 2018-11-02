package com.example.gab.babylove.ui.news.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.ui.news.activity.SystemActivity;
import com.example.gab.babylove.ui.news.adapter.NewsAdapter;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.TreeBean;
import com.ggz.baselibrary.base.BaseFragment;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.ggz.baselibrary.utils.JumpUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Gab on 2017/12/15 0015.
 * 体系 主Fragment
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    NewsAdapter mAdapter;


    @Override
    protected int setContentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        initRecyle();
        getArticleList();
    }

    /**
     * 列表数据加载
     */
    @SuppressLint("CheckResult")
    private void getArticleList() {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getTreeList()
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<List<TreeBean>>() {
                    @Override
                    protected void onSuccess(List<TreeBean> listBeanModule) {
                        if (null != listBeanModule) {
                            mKProgressHUD.dismiss();
                            mAdapter.setNewData(listBeanModule);
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    /**
     * recycleview 相关设置
     */
    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NewsAdapter(R.layout.item_news, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            TreeBean bean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("bean", bean);
            JumpUtils.jump(mContext, SystemActivity.class, bundle);// 详情
        });
        mRecyclerView.setAdapter(mAdapter);
    }

}
