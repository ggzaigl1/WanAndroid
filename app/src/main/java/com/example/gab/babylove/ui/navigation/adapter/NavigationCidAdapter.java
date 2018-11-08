package com.example.gab.babylove.ui.navigation.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.NavigationBean;
import com.ggz.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/4/19 0019.
 * 视图导航
 */

public class NavigationCidAdapter extends BaseQuickAdapter<NavigationBean.ArticlesBean, BaseViewHolder> {

    private List<NavigationBean.ArticlesBean> mList;

    public NavigationCidAdapter(@Nullable List<NavigationBean.ArticlesBean> data) {
        super(R.layout.item_navigation_cid, data);
        this.mList = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean.ArticlesBean item) {
        TextView tv_date = helper.getView(R.id.tv_date);
        tv_date.setText(item.getTitle());
        tv_date.setTextColor(ResourceUtils.getRandomColor());
//        tv_date.setBackground(SelectUtils.getTagSelector(R.drawable.selector_item_bg));
        if (helper.getLayoutPosition() == mList.size() - 1) {
            //最后一条数据，隐藏时间轴的竖线和水平的分割线
        }
    }
}