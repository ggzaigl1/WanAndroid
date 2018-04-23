package com.example.gab.babylove.ui.navigation.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.NavigationBean;
import com.fy.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/4/19 0019.
 * 视图导航
 */

public class NavigationCidAdapter extends BaseQuickAdapter<NavigationBean.ArticlesBean, BaseViewHolder> {

    public NavigationCidAdapter(int layoutResId, @Nullable List<NavigationBean.ArticlesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean.ArticlesBean item) {
        helper.setText(R.id.tv_date, item.getTitle()).setTextColor(R.id.tv_date, ResourceUtils.getRandomColor());
    }

}
