package com.example.gab.babylove.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.gab.babylove.R;
import com.example.gab.babylove.adapter.NewsAdapter;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.TreeBean;
import com.example.gab.babylove.web.AgentWebActivity;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.retrofit.BeanModule;
import com.fy.baselibrary.retrofit.RequestUtils;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gab on 2017/12/15 0015.
 * 体系 Fragment
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    NewsAdapter mAdapter;


    @Override
    protected int setContentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        toolbar.setTitle("体系");
        initRecyle();
        getArticleList();
        MdStatusBar.setColorBar(getActivity(), R.color.statusBar, R.color.statusBar);
    }

    /**
     *
     * 列表数据加载
     */
    @SuppressLint("CheckResult")
    private void getArticleList() {
        RequestUtils.create(ApiService.class)
                .getTreeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanModule<List<TreeBean>>>() {
                    @Override
                    public void accept(BeanModule<List<TreeBean>> listBeanModule) throws Exception {
                        if (null != listBeanModule && null != listBeanModule.getData()) {
                            mAdapter.setNewData(listBeanModule.getData());
                        }
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
//            TreeBean bean = mAdapter.getData().get(position);
//            Bundle bundle = new Bundle();
//            JumpUtils.jump(mContext, AgentWebActivity.class, bundle);// 详情
        });
        mRecyclerView.setAdapter(mAdapter);
    }

}
