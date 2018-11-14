package com.example.gab.babylove.ui.main.collect.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.BaseBean;
import com.example.gab.babylove.entity.CollectBean;
import com.ggz.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 * @author 初夏小溪
 * @date 2018/4/24 0024
 */

public class MyCollectAdapter extends BaseQuickAdapter<CollectBean.DatasBean, BaseViewHolder> {

    public MyCollectAdapter(@Nullable List<CollectBean.DatasBean> data) {
        super(R.layout.item_collect_my, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectBean.DatasBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_author_name, "作者：" + item.getAuthor()).setTextColor(R.id.tv_author_name, ResourceUtils.getRandomColor())
                .setText(R.id.tv_date, item.getNiceDate())
                .setText(R.id.tv_chapterName, "分类：" + item.getChapterName()).setTextColor(R.id.tv_chapterName, ResourceUtils.getRandomColor());
        helper.addOnClickListener(R.id.image_collect);
        initImage(item, helper.getView(R.id.image_collect));
    }

    /**
     * 判断收藏
     *
     * @param dataBean
     * @param imageView
     */
    private void initImage(CollectBean.DatasBean dataBean, AppCompatImageView imageView) {
        if (dataBean.isCollect()) {
            imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.vector_collect));
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.vector_collect_false));
        }
    }
}
