package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.TextView;

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

    public HomeAdapter(@Nullable List<ArticleBean.DatasBean> data) {
        super(R.layout.item_home, data);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
//        payloads 对象不会为null，但是它可能是空（empty），这时候需要完整绑定(所以我们在方法里只要判断isEmpty就好，不用重复判空)。
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            ArticleBean.DatasBean datasBean = mData.get(position);
            initImage(datasBean, holder.getView(R.id.image_collect));
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DatasBean item) {
        TextView tv_tag = helper.getView(R.id.tv_tag);
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_author_name, "作者：" + item.getAuthor()).setTextColor(R.id.tv_author_name, ResourceUtils.getRandomColor())
                .setText(R.id.tv_date, item.getNiceDate())
                .setText(R.id.tv_chapterName, "分类：" + item.getChapterName()).setTextColor(R.id.tv_chapterName, ResourceUtils.getRandomColor());
        if (item.getTags().size() != 0 && !item.getTags().isEmpty()) {
            tv_tag.setVisibility(View.VISIBLE);
            tv_tag.setText(item.getTags().get(0).getName());
        } else {
            tv_tag.setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.image_collect);
        helper.addOnClickListener(R.id.tv_tag);
        initImage(item, helper.getView(R.id.image_collect));
    }

    private void initImage(ArticleBean.DatasBean datasBean, AppCompatImageView imageView) {
        if (datasBean.isCollect()) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.vector_collect));
        } else {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.vector_collect_false));
        }
    }
}
