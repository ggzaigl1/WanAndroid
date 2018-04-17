package com.example.gab.babylove.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.fy.baselibrary.entity.ArticleBean;

import java.util.List;

/**
 * Created by Gab on 2017/12/18 0018.
 * 主页数据adapter
 */

public class HomeAdapter extends BaseQuickAdapter<ArticleBean.DataBean.DatasBean, BaseViewHolder> {

    public HomeAdapter(int layoutResId, @Nullable List<ArticleBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DataBean.DatasBean item) {
        helper.setText(R.id.tv_title, item.getTitle()).setText(R.id.tv_author_name, item.getAuthor())
                .setText(R.id.tv_date, item.getNiceDate()).setText(R.id.tv_chapterName, "分类：" + item.getChapterName());
    }
}
