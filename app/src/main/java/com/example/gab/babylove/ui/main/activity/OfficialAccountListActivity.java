package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.OfficialAccountListBean;
import com.example.gab.babylove.ui.main.adapter.OfficialAccountListAdapter;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.statusbar.MdStatusBar;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 初夏小溪 on 2018/10/15 0015.
 * 公众号 详情
 */
public class OfficialAccountListActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    OfficialAccountListAdapter mAdapter;
    private int mId;

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
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        mId = getIntent().getIntExtra("id", 0);
        initRecyle();
        getChaptersList();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }

    /**
     * 公众号详情 数据加载
     */
    @SuppressLint("CheckResult")
    private void getChaptersList() {
        RequestUtils.create(ApiService.class)
                .getWxarticle(mId, 1)
                .compose(RxHelper.handleResult())
                .subscribe(new NetCallBack<OfficialAccountListBean>() {
                    @Override
                    protected void onSuccess(OfficialAccountListBean officialAccountListBean) {
                        if (null != officialAccountListBean) {
                            mAdapter.setNewData(officialAccountListBean.getDatas());
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OfficialAccountListAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            OfficialAccountListBean.DatasBean dataBean = mAdapter.getData().get(position);
            WebViewActivity.startWebActivity(this, dataBean.getLink());// 详情
        });
    }
}
