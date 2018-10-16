package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.CourseList;
import com.ggz.baselibrary.utils.imgload.ImgLoadUtils;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by 初夏小溪 on 2018/4/9 0009.
 */

public class OrnamentalListContextAdapter extends BaseQuickAdapter<CourseList.DataBean, BaseViewHolder> {

    public OrnamentalListContextAdapter(int layoutResId, @Nullable List<CourseList.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseList.DataBean item) {
        helper.setText(R.id.tv_name, item.getName()).setText(R.id.tv_joinnum, item.getJoinnum() + "人已参加");
        ImgLoadUtils.loadImage(mContext, item.getIcon(), helper.getView(R.id.imgHead));
        MaterialRatingBar materialRatingBar = helper.getView(R.id.Rb_trainlevel);
        materialRatingBar.setNumStars((item.getTrainlevel()));
    }
}
