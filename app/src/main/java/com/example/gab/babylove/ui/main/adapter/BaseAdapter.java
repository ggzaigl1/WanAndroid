package com.example.gab.babylove.ui.main.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.BaseBean;
import com.ggz.baselibrary.utils.ResourceUtils;
import com.ggz.baselibrary.utils.imgload.ImgLoadUtils;

import java.util.List;

/**
 * @author Gab
 * @date 2017/12/18 0018
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
            BaseBean.DatasBean dataBean = mData.get(position);
            initImage(dataBean, holder.getView(R.id.image_collect));
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, BaseBean.DatasBean item) {
        TextView mTitle = helper.getView(R.id.tv_title);
        TextView mTag = helper.getView(R.id.tv_tag);
        TextView mFresh = helper.getView(R.id.tv_fresh);

        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_author_name, "作者：" + item.getAuthor()).setTextColor(R.id.tv_author_name, ResourceUtils.getRandomColor())
                .setText(R.id.tv_date, item.getNiceDate())
                .setText(R.id.tv_chapterName, "分类：" + item.getChapterName()).setTextColor(R.id.tv_chapterName, ResourceUtils.getRandomColor());

        //判断是否有图片
        if (TextUtils.isEmpty(item.getEnvelopePic())) {
            helper.getView(R.id.Ll_item_pic).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.Ll_item_pic).setVisibility(View.VISIBLE);
            RequestOptions options = new RequestOptions()
                    .override(100, 200);
            Glide.with(mContext)
                    .load(item.getEnvelopePic())
                    .apply(options)
                    .into((AppCompatImageView) helper.getView(R.id.item_list_iv));
//            ImgLoadUtils.loadImage(mContext, item.getEnvelopePic(), helper.getView(R.id.item_list_iv));
        }

        //判断tag对象是否有数据
        if (item.getTags().size() != 0 && !item.getTags().isEmpty()) {
            mTag.setVisibility(View.VISIBLE);
            mTag.setText(item.getTags().get(0).getName());
        } else {
            mTag.setVisibility(View.GONE);
        }

        //判断是否是最新的item
        if (item.isFresh()) {
            mFresh.setVisibility(View.VISIBLE);
        } else {
            mFresh.setVisibility(View.GONE);
        }


        String title = item.getTitle();
        //"Android <em class='highlight'>Studio3</em>.0正式版填坑路
        //如果包含 截取返回第一次出现的字符串
        if (title.contains("<em")) {
            //返回指定字符在字符串中第一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1。
            int startPre = title.indexOf("<");
            // substring() 方法返回字符串的子字符串 截取。
            // beginIndex -- 起始索引（包括）, 索引从 0 开始。
            // endIndex -- 结束索引（不包括）。
            String pre = title.substring(0, startPre);
            int startNext = title.indexOf(">");
            //截取返回最后一次出现
            //返回指定字符在此字符串中最后一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1。
            int endPre = title.lastIndexOf("<");
            String centerStr = title.substring(startNext + 1, endPre);
            int end = title.lastIndexOf(">");
            //拼接
            String endStr = title.substring(end + 1, title.length());
            mTitle.setText(pre + centerStr + endStr);
        } else {
            mTitle.setText(title);
        }

        helper.addOnClickListener(R.id.image_collect);
        initImage(item, helper.getView(R.id.image_collect));
    }


    /**
     * 判断收藏
     *
     * @param dataBean
     * @param imageView
     */
    private void initImage(BaseBean.DatasBean dataBean, AppCompatImageView imageView) {

        if (dataBean.isCollect()) {
            imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.vector_collect));
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.vector_collect_false));
        }
    }
}
