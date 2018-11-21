package com.example.gab.babylove.ui.navigation.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.NavigationBean;
import com.ggz.baselibrary.utils.ResourceUtils;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * @author 初夏小溪
 * @date 2018/4/19 0019
 * 视图导航
 */

public class NavigationAdapter extends BaseQuickAdapter<NavigationBean, BaseViewHolder> {

    public NavigationAdapter(@Nullable List<NavigationBean> data) {
        super(R.layout.item_navigation_demo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean item) {
        RecyclerView recyclerView = helper.getView(R.id.rv_item_navigation);
        List<NavigationBean.ArticlesBean> articles = item.getArticles();
        helper.setText(R.id.tv_item_navigation, item.getName());

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(mContext);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(layoutManager);

        NavigationItemAdapter navigationItemAdapter = new NavigationItemAdapter(articles);
        navigationItemAdapter.setNewData(articles);

        recyclerView.setAdapter(navigationItemAdapter);

    }
}