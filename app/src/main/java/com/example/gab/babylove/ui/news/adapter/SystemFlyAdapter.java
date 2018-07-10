package com.example.gab.babylove.ui.news.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.ArticleBean;
import com.ggz.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/4/24 0024.
 * 知识体系
 */

public class SystemFlyAdapter extends BaseQuickAdapter<ArticleBean.DatasBean, BaseViewHolder> {

    public SystemFlyAdapter(int layoutResId, @Nullable List<ArticleBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DatasBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_author_name, "作者：" + item.getAuthor()).setTextColor(R.id.tv_author_name, ResourceUtils.getRandomColor())
                .setText(R.id.tv_date, item.getNiceDate())
                .setText(R.id.tv_chapterName, "分类：" + item.getChapterName()).setTextColor(R.id.tv_chapterName, ResourceUtils.getRandomColor());
        helper.getView(R.id.image_collect).setVisibility(View.GONE);
    }
}
