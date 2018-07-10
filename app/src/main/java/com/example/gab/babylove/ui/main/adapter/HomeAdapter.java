package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.ArticleBean;
import com.ggz.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 * Created by Gab on 2017/12/18 0018.
 * 主页数据adapter
 */

public class HomeAdapter extends BaseQuickAdapter<ArticleBean.DatasBean, BaseViewHolder> {

    AppCompatImageView imageView;

    public HomeAdapter(int layoutResId, @Nullable List<ArticleBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
//        payloads 对象不会为null，但是它可能是空（empty），这时候需要完整绑定(所以我们在方法里只要判断isEmpty就好，不用重复判空)。
        imageView = holder.getView(R.id.image_collect);
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            ArticleBean.DatasBean datasBean = mData.get(position);
            initImage(datasBean, imageView);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DatasBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_author_name, "作者：" + item.getAuthor()).setTextColor(R.id.tv_author_name, ResourceUtils.getRandomColor())
                .setText(R.id.tv_date, item.getNiceDate())
                .setText(R.id.tv_chapterName, "分类：" + item.getChapterName()).setTextColor(R.id.tv_chapterName, ResourceUtils.getRandomColor());
        helper.addOnClickListener(R.id.image_collect);
        imageView = helper.getView(R.id.image_collect);
        initImage(item, imageView);
    }

    private void initImage(ArticleBean.DatasBean datasBean, AppCompatImageView imageView) {
        if (datasBean.isCollect()) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.vector_collect));
        } else {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.vector_collect_false));
        }
    }
}
