package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.BaseBean;
import com.ggz.baselibrary.utils.ResourceUtils;
import com.ggz.baselibrary.utils.imgload.ImgLoadUtils;

import java.util.List;

/**
 * Created by Gab on 2017/12/18 0018.
 * 公共数据页面adapter
 */

public class BaseAdapter extends BaseQuickAdapter<BaseBean.DatasBean, BaseViewHolder> {

    public BaseAdapter(@Nullable List<BaseBean.DatasBean> data) {
        super(R.layout.item_base_list, data);
//        super(R.layout.item_fly, data);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull List<Object> payloads) {
//        payloads 对象不会为null，但是它可能是空（empty），这时候需要完整绑定(所以我们在方法里只要判断isEmpty就好，不用重复判空)。
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            BaseBean.DatasBean datasBean = mData.get(position);
            initImage(datasBean, holder.getView(R.id.image_collect));
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean.DatasBean item) {
        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_tag = helper.getView(R.id.tv_tag);
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_author_name, "作者：" + item.getAuthor()).setTextColor(R.id.tv_author_name, ResourceUtils.getRandomColor())
                .setText(R.id.tv_date, item.getNiceDate())
                .setText(R.id.tv_chapterName, "分类：" + item.getChapterName()).setTextColor(R.id.tv_chapterName, ResourceUtils.getRandomColor());

        if (TextUtils.isEmpty(item.getEnvelopePic())) {
            helper.getView(R.id.Ll_item_pic).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.Ll_item_pic).setVisibility(View.VISIBLE);
            ImgLoadUtils.loadImage(mContext, item.getEnvelopePic(), helper.getView(R.id.item_list_iv));
        }

        if (item.getTags().size() != 0 && !item.getTags().isEmpty()) {
            tv_tag.setVisibility(View.VISIBLE);
            tv_tag.setText(item.getTags().get(0).getName());
        } else {
            tv_tag.setVisibility(View.GONE);
        }

        String title = item.getTitle();
        //Kotlin 继续助力 <em class='highlight'>Android</em> 开发，并计划涉足更多领域
        //如果包含 截取返回第一次出现的字符串
        if (title.contains("<em")) {
            int start_pre = title.indexOf("<");
            String pre = title.substring(0, start_pre);
            int start_next = title.indexOf(">");
            //截取返回最后一次出现
            int end_pre = title.lastIndexOf("<");
            String centerStr = title.substring(start_next + 1, end_pre);
            int end = title.lastIndexOf(">");
            //拼接
            String endStr = title.substring(end + 1, title.length());
            tv_title.setText(pre + centerStr + endStr);
        } else {
            tv_title.setText(title);
        }

        helper.addOnClickListener(R.id.image_collect);
        initImage(item, helper.getView(R.id.image_collect));
    }

    /**
     * 判断收藏
     * @param datasBean
     * @param imageView
     */
    private void initImage(BaseBean.DatasBean datasBean, AppCompatImageView imageView) {
        if (datasBean.isCollect()) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.vector_collect));
        } else {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.vector_collect_false));
        }
    }
}
