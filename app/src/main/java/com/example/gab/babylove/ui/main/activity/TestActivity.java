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
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.HotKeyBean;
import com.example.gab.babylove.entity.OfficialAccountBean;
import com.example.gab.babylove.ui.main.adapter.OfficialAccountAdapter;
import com.example.gab.babylove.ui.main.adapter.TestAdapter;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.utils.JumpUtils;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 初夏小溪 on 2018/11/8 0008.
 */
public class TestActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.rv_test)
    RecyclerView mRecyclerView;
    TestAdapter mAdapter;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_test;
    }


    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        getChaptersList();
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new TestAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
    }

    @SuppressLint("CheckResult")
    private void getChaptersList() {
        RequestUtils.create(ApiService.class)
                .getHotKeyList()
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<List<HotKeyBean>>() {
                    @Override
                    protected void onSuccess(List<HotKeyBean> hotKeyBeans) {
                        if (null != hotKeyBeans) {
                            mAdapter.setNewData(hotKeyBeans);
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }
}
