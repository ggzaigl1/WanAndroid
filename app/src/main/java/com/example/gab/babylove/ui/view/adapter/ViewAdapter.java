package com.example.gab.babylove.ui.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.ViewBean;
import com.ggz.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 *
 * @author 初夏小溪
 * @date 2018/4/19 0019
 * 知识体系数据实体类
 */

public class ViewAdapter extends BaseQuickAdapter<ViewBean, BaseViewHolder> {

    public ViewAdapter(int layoutResId, @Nullable List<ViewBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ViewBean item) {
        StringBuilder strBuilder = new StringBuilder();
        List<ViewBean.ChildrenBean> childrenBeans = item.getChildren();
        if (null != childrenBeans){
            for (ViewBean.ChildrenBean childrenBean : childrenBeans){
                String str = ResourceUtils.getReplaceStr(R.string.news_space, childrenBean.getName());
                strBuilder.append(str);
            }
        }
        helper.setText(R.id.tv_title, item.getName())
                .setText(R.id.tv_context,strBuilder.toString())
                .setTextColor(R.id.tv_context, ResourceUtils.getRandomColor());
    }
}
