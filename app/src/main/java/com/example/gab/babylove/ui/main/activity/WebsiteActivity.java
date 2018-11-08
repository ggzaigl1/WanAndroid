package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.BookmarkBean;
import com.example.gab.babylove.ui.main.adapter.WebsiteAdapter;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by 初夏小溪 on 2018/4/19 0019.
 * 常用网站
 */

public class WebsiteActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    WebsiteAdapter mAdapter;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_website;
    }

    @Override
    public void setStatusBar(Activity activity) {
//        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        initRecyle();
        getBookmarkList();
    }

    /**
     * 常用网站 数据加载
     */
    @SuppressLint("CheckResult")
    private void getBookmarkList() {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getBookmarkList()
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<List<BookmarkBean>>() {
                    @Override
                    protected void onSuccess(List<BookmarkBean> listBeanModule) {
                        if (null != listBeanModule) {
                            mAdapter.setNewData(listBeanModule);
                            mKProgressHUD.dismiss();
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


    /**
     * recycleview 相关设置
     */
    private void initRecyle() {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);

        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new WebsiteAdapter(R.layout.item_website, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            WebViewActivity.startWebActivity(this
                    , mAdapter.getData().get(position).getLink()
                    , mAdapter.getData().get(position).getId());// 详情
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
//        mAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.item_website_footer, (ViewGroup) mRecyclerView.getParent(), false));
        mRecyclerView.setAdapter(mAdapter);
    }
}
