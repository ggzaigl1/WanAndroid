package com.example.gab.babylove.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gab.babylove.R;
import com.example.gab.babylove.adapter.GankMAdapter;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.GankBean;
import com.fy.baselibrary.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪 on 2018/4/13 0013.
 *
 */

public class GankMZActivity extends BaseActivity {

    @BindView(R.id.rec_mz)
    RecyclerView mRecyclerView;
    GankMAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_gank;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initRv();
        getCourseDetails();
    }

    @SuppressLint("CheckResult")
    private void getCourseDetails() {
        mConnService.getCourseDetails(20,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GankBean>() {
                    @Override
                    public void accept(GankBean gankBean) throws Exception {
                        mAdapter.setNewData(gankBean.getResults());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        T.showShort(throwable.getMessage());
                    }
                });
//                .subscribe(new NetCallBack<GankBean>() {
//                    @Override
//                    protected void onSuccess(GankBean t) {
//                        mAdapter.setNewData(t.getResults());
//                    }
//
//                    @Override
//                    protected void updataLayout(int flag) {
//                    }
//                });
    }

    private void initRv() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new GankMAdapter(R.layout.item_gank_list_context, new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<GankBean.ResultsBean> data = mAdapter.getData();
                Bundle bundle = new Bundle();
//                bundle.putString("id", data.get(position).getId());
//                JumpUtils.jump(mContext, OrnamentalContextActivity.class, bundle);
            }
        });
    }
}
