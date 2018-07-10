package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.ui.main.adapter.WebsiteAdapter;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.BookmarkBean;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪 on 2018/4/19 0019.
 * 常用网站
 */

public class WebsiteActivity extends AppCompatActivity implements IBaseActivity {

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
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        initRecyle();
        getBookmarkList();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }

    /**
     * 常用网站 数据加载
     */
    @SuppressLint("CheckResult")
    private void getBookmarkList() {
        RequestUtils.create(ApiService.class)
                .getBookmarkList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listBeanModule -> {
                    if (null != listBeanModule && null != listBeanModule.getData()) {
                        mAdapter.setNewData(listBeanModule.getData());
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

//        new LinearLayoutManager(this)
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new WebsiteAdapter(R.layout.item_website, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            BookmarkBean bean = mAdapter.getData().get(position);
            WebViewActivity.startWebActivity(this, bean.getLink());// 详情
        });
//        mAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.item_website_footer, (ViewGroup) mRecyclerView.getParent(), false));
        mRecyclerView.setAdapter(mAdapter);
    }
}
