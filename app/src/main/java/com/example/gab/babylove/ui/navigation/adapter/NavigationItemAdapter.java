package com.example.gab.babylove.ui.navigation.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.NavigationBean;
import com.ggz.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 * @author chenbo
 */
public class NavigationItemAdapter extends BaseQuickAdapter<NavigationBean.ArticlesBean, BaseViewHolder> {

    NavigationItemAdapter(List<NavigationBean.ArticlesBean> data) {
        super(R.layout.item_navigation_cid, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean.ArticlesBean item) {
        helper.setText(R.id.tv_date, item.getTitle())
                .setBackgroundColor(R.id.tv_date, ResourceUtils.getRandomColor());
    }
}
