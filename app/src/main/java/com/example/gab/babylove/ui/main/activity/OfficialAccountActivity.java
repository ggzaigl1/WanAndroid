package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.OfficialAccountBean;
import com.example.gab.babylove.ui.main.adapter.OfficialAccountAdapter;
import com.ggz.baselibrary.application.IBaseActivity;
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
 * Created by 初夏小溪 on 2018/10/15 0015.
 * 公众号 作者
 */
public class OfficialAccountActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    OfficialAccountAdapter mAdapter;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_official_account;
    }

    @Override
    public void setStatusBar(Activity activity) {
//        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
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
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getChapters()
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OfficialAccountAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", mAdapter.getData().get(position).getId());
            JumpUtils.jump(OfficialAccountActivity.this, OfficialAccountListActivity.class, bundle);
        });
    }

}
