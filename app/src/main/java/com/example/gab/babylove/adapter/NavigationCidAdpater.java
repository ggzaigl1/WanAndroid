package com.example.gab.babylove.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.NavigationBean;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/4/19 0019.
 * 视图导航
 */

public class NavigationCidAdpater extends BaseQuickAdapter<NavigationBean.ArticlesBean, BaseViewHolder> {

    public NavigationCidAdpater(int layoutResId, @Nullable List<NavigationBean.ArticlesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean.ArticlesBean item) {
        helper.setText(R.id.tv_date,item.getTitle());
    }

}
