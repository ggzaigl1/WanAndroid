package com.example.gab.babylove.ui.news.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ImageUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.ArticleBean;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;

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
        helper.setText(R.id.tv_title, item.getDesc())
                .setText(R.id.tv_author_name, "作者：" + item.getAuthor()).setTextColor(R.id.tv_author_name, ResourceUtils.getRandomColor())
                .setText(R.id.tv_date, item.getNiceDate())
                .setText(R.id.tv_chapterName, "分类：" + item.getChapterName()).setTextColor(R.id.tv_chapterName, ResourceUtils.getRandomColor());
        ImgLoadUtils.loadImage(mContext,item.getEnvelopePic(),helper.getView(R.id.item_project_list_iv));
    }
}
