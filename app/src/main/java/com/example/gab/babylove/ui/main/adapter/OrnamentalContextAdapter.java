package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.CourseDetails;
import com.ggz.baselibrary.utils.imgload.ImgLoadUtils;

import java.util.List;

/**
 * Created by Gab on 2018/3/13 0013.
 * 体育运动 详情
 */

public class OrnamentalContextAdapter extends BaseQuickAdapter<CourseDetails.DataBean.GroupsBean.ActionsBean, BaseViewHolder> {

    public OrnamentalContextAdapter(@Nullable List<CourseDetails.DataBean.GroupsBean.ActionsBean> data) {
        super(R.layout.item_ornamental_context_recycle, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, CourseDetails.DataBean.GroupsBean.ActionsBean item) {
        helper.setText(R.id.tv_name, item.getTitle());
        ImgLoadUtils.loadImage(mContext, item.getPic(), helper.getView(R.id.iv_course_one));
    }
}
