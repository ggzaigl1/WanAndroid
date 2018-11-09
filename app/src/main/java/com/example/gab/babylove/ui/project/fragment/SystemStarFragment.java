package com.example.gab.babylove.ui.project.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.BaseBean;
import com.example.gab.babylove.ui.project.adapter.StarAdapter;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.base.BaseFragment;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 初夏小溪 on 2018/4/20 0020.
 * 项目 TabLayout Fragment
 */

public class SystemStarFragment extends BaseFragment {

    @BindView(R.id.rvLayout)
    RecyclerView mRecyclerView;
    StarAdapter mAdapter;
    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";
    int mPageNo = 0;

    public static SystemStarFragment getInstance(int param1, String param2) {
        SystemStarFragment fragment = new SystemStarFragment();
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
        initRecyle();
        assert arguments != null;
        getArticleList(arguments.getInt(ARG_PARAM1));

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
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<BaseBean>() {
                    @Override
                    protected void onSuccess(BaseBean baseBean) {
                        if (null != baseBean) {
                            mKProgressHUD.dismiss();
                            mAdapter.setNewData(baseBean.getDatas());
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new StarAdapter(R.layout.item_fly, new ArrayList<>());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WebViewActivity.startWebActivity(mContext
                        , mAdapter.getData().get(position).getLink()
                        , mAdapter.getData().get(position).getId());// 详情
                mContext.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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

}
