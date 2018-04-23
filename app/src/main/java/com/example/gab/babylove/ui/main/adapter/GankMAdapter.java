package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.GankBean;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;

import java.util.List;


/**
 * Created by 初夏小溪 on 2018/4/13 0013.
 *
 */

public class GankMAdapter extends BaseQuickAdapter<GankBean.ResultsBean, BaseViewHolder> {

    public GankMAdapter(int layoutResId, @Nullable List<GankBean.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankBean.ResultsBean item) {
        ImgLoadUtils.loadImage(mContext,item.getUrl(),helper.getView(R.id.img_content));
    }
}
