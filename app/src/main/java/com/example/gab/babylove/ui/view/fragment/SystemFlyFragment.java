package com.example.gab.babylove.ui.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.BaseBean;
import com.example.gab.babylove.ui.main.adapter.BaseAdapter;
import com.example.gab.babylove.ui.main.login.LoginActivity;
import com.example.gab.babylove.web.WebViewActivity;
import com.example.gab.babylove.base.BaseFragment;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.SpfUtils;
import com.ggz.baselibrary.utils.T;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author 初夏小溪
 * @date 2018/4/20 0020
 * 知识体系 TabLayout Fragment
 */

public class SystemFlyFragment extends BaseFragment {

    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";
    int mPageNo = 0;

    @BindView(R.id.rvLayout)
    RecyclerView mRecyclerView;

    BaseAdapter mAdapter;

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
        mAdapter = new BaseAdapter(new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            WebViewActivity.startWebActivity(mContext
                    , mAdapter.getData().get(position).getLink()
                    , mAdapter.getData().get(position).getId()
                    ,mAdapter.getData().get(position).isCollect());
            mContext.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        mAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.activity_null_data, (ViewGroup) mRecyclerView.getParent(), false));
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
                    break;
                default:
                    break;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
