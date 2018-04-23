package com.example.gab.babylove.ui.navigation.adapter;

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

public class NavigationViewAdapter extends BaseQuickAdapter<NavigationBean, BaseViewHolder> {


    public NavigationViewAdapter(int layoutResId, @Nullable List<NavigationBean> data) {
        super(layoutResId, data);
    }

    /**
     *  定义一个变量 boolean值 判断是否选中改变状态颜色
     * @param helper
     * @param item
     */
    @Override
    protected void convert(BaseViewHolder helper, NavigationBean item) {

        if (item.isSelected()) {
            helper.setText(R.id.tv_date, item.getName()).setBackgroundColor(R.id.tv_date, mContext.getResources().getColor(R.color.white))
                    .setTextColor(R.id.tv_date, mContext.getResources().getColor(R.color.shallow_green));
        } else {
            helper.setText(R.id.tv_date, item.getName()).setBackgroundColor(R.id.tv_date, mContext.getResources().getColor(R.color.deep_grey))
                    .setTextColor(R.id.tv_date, mContext.getResources().getColor(R.color.black));
        }
    }
}
