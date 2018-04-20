package com.example.gab.babylove.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gab.babylove.R;
import com.example.gab.babylove.adapter.NavigationCidAdapter;
import com.example.gab.babylove.adapter.NavigationViewAdapter;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.NavigationBean;
import com.example.gab.babylove.web.AgentWebActivity;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.retrofit.BeanModule;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RequestUtils;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.JumpUtils;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gab on 2017/12/15 0015.
 * 导航数据
 */

public class NavigationViewFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView_Title;
    @BindView(R.id.rv_context)
    RecyclerView mRecyclerView_Context;

    NavigationViewAdapter mAdapter;
    NavigationCidAdapter mNavigationViewAdpater;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_bookmarks;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        toolbar.setTitle("视图导航");
        MdStatusBar.setColorBar(getActivity(), R.color.statusBar, R.color.statusBar);
        initRecyle();
        initRecyleCid();
        getNaviList();

    }

    /**
     * 列表数据加载
     */
    private void getNaviList() {
        IProgressDialog progressDialog = new IProgressDialog().init((AppCompatActivity) getActivity()).setDialogMsg(R.string.loading_get);
        RequestUtils.create(ApiService.class)
                .getNaviList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetCallBack<BeanModule<List<NavigationBean>>>(progressDialog) {
                    @Override
                    protected void onSuccess(BeanModule<List<NavigationBean>> navigationBeanBeanModule) {
                        if (null != navigationBeanBeanModule && null != navigationBeanBeanModule.getData()) {
                            mAdapter.setNewData(navigationBeanBeanModule.getData());
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


    private void initRecyle() {
        mRecyclerView_Title.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NavigationViewAdapter(R.layout.item_navigation, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NavigationBean navigationBean = mAdapter.getData().get(position);
                mNavigationViewAdpater.setNewData(navigationBean.getArticles());

            }
        });
        mRecyclerView_Title.setAdapter(mAdapter);
    }

    private void initRecyleCid() {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getActivity());
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);

        mRecyclerView_Context.setLayoutManager(layoutManager);
        mNavigationViewAdpater = new NavigationCidAdapter(R.layout.item_navigation_cid, new ArrayList<>());
        mNavigationViewAdpater.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NavigationBean.ArticlesBean navigationBean = mNavigationViewAdpater.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("UrlBean", navigationBean.getLink());
                JumpUtils.jump(mContext, AgentWebActivity.class, bundle);// 详情

            }
        });
        mRecyclerView_Context.setAdapter(mNavigationViewAdpater);
    }
}
