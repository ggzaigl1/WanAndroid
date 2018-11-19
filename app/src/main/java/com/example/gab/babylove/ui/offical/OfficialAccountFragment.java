package com.example.gab.babylove.ui.offical;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.OfficialAccountBean;
import com.example.gab.babylove.ui.offical.activity.OfficialAccountListActivity;
import com.example.gab.babylove.ui.offical.adapter.OfficialAccountAdapter;
import com.ggz.baselibrary.base.BaseFragment;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.utils.JumpUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 初夏小溪
 * data :2018/11/19 0019 11:17
 * @author 55204
 */
public class OfficialAccountFragment extends BaseFragment {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    OfficialAccountAdapter mAdapter;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_official_account;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        initRecyle();
        getChaptersList();
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);
    }

    /**
     * 公众号 数据加载
     */
    @SuppressLint("CheckResult")
    private void getChaptersList() {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getChapters()
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<List<OfficialAccountBean>>() {
                    @Override
                    protected void onSuccess(List<OfficialAccountBean> officialAccountBeans) {
                        if (null != officialAccountBeans) {
                            mAdapter.setNewData(officialAccountBeans);
                            mKProgressHUD.dismiss();
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new OfficialAccountAdapter(new ArrayList<>());
        mAdapter.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.item_list_footer, (ViewGroup) mRecyclerView.getParent(), false));
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", mAdapter.getData().get(position).getId());
            JumpUtils.jumpFade((AppCompatActivity) getActivity(), OfficialAccountListActivity.class, bundle);
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
