package com.example.gab.babylove.ui.news.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.ArticleBean;
import com.example.gab.babylove.ui.news.adapter.SystemFlyAdapter;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.base.BaseFragment;
import com.ggz.baselibrary.retrofit.BeanModule;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪 on 2018/4/20 0020.
 * 知识体系 TabLayout Fragment
 */

public class SystemFlyFragment extends BaseFragment {

    @BindView(R.id.rvLayout)
    RecyclerView mRecyclerView;
    SystemFlyAdapter mAdapter;
    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";
    int mPageNo = 0;

    public static SystemFlyFragment getInstance(int param1, String param2) {
        SystemFlyFragment fragment = new SystemFlyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_system_fly;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        Bundle arguments = getArguments();
        int id = arguments.getInt(ARG_PARAM1);
        initRecyle();
        getArticleList(id);

    }

    /**
     * 列表数据加载
     */
    @SuppressLint("CheckResult")
    private void getArticleList(int id) {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getArticleList(mPageNo, id)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<ArticleBean>() {
                    @Override
                    protected void onSuccess(ArticleBean articleBean) {
                        if (null != articleBean) {
                            mKProgressHUD.dismiss();
                            mAdapter.setNewData(articleBean.getDatas());
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SystemFlyAdapter(R.layout.item_home, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            WebViewActivity.startWebActivity(mContext, mAdapter.getData().get(position).getLink());// 详情
        });
        mRecyclerView.setAdapter(mAdapter);
    }

}
