package com.example.gab.wanandroid_kotlin.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.gab.wanandroid_kotlin.R
import com.example.gab.wanandroid_kotlin.entity.CollectBean
import com.ggz.baselibrary.utils.ResourceUtils

/**
 * Created by 初夏小溪
 * data :2019/1/4 0004 16:45
 */
class MyCollectKotlinAdapter(data: List<CollectBean.DatasBean>) : BaseQuickAdapter<CollectBean.DatasBean, BaseViewHolder>(R.layout.item_collect_my, data) {

    override fun convert(helper: BaseViewHolder?, item: CollectBean.DatasBean?) {
        helper?.setText(R.id.tv_title, item?.title)
                ?.setText(R.id.tv_author_name, "作者：" + item?.author)?.setTextColor(R.id.tv_author_name, ResourceUtils.getRandomColor())
                ?.setText(R.id.tv_date, item?.niceDate)
                ?.setText(R.id.tv_chapterName, "分类：" + item?.chapterName)?.setTextColor(R.id.tv_chapterName, ResourceUtils.getRandomColor())
        helper?.addOnClickListener(R.id.image_collect)
        initImage(item!!, helper?.getView(R.id.image_collect)!!)
    }

    /**
     * 判断收藏
     *
     * @param dataBean
     * @param imageView
     */
    private fun initImage(dataBean: CollectBean.DatasBean, imageView: AppCompatImageView) {
        if (dataBean.isCollect) {
            imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.vector_collect))
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.vector_collect_false))
        }
    }
}