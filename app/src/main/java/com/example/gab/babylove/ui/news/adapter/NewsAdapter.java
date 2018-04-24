package com.example.gab.babylove.ui.news.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.TreeBean;
import com.fy.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/4/19 0019.
 * 知识体系数据实体类
 */

public class NewsAdapter extends BaseQuickAdapter<TreeBean, BaseViewHolder> {

    public NewsAdapter(int layoutResId, @Nullable List<TreeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeBean item) {
        StringBuilder strBuilder = new StringBuilder();
        List<TreeBean.ChildrenBean> childrenBeans = item.getChildren();
        if (null != childrenBeans){
            for (TreeBean.ChildrenBean childrenBean : childrenBeans){
                String str = ResourceUtils.getReplaceStr(R.string.space, childrenBean.getName());
                strBuilder.append(str);
            }
        }
        helper.setText(R.id.tv_title, item.getName())
                .setText(R.id.tv_context,strBuilder.toString())
                .setTextColor(R.id.tv_context, ResourceUtils.getRandomColor());
    }

}
