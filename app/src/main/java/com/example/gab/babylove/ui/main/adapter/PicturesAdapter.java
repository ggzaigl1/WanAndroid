package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.GanBean;
import com.ggz.baselibrary.utils.imgload.ImgLoadUtils;

import java.util.List;


/**
 *
 * @author 初夏小溪
 * @date 2018/4/13 0013
 * 美图欣赏
 *
 */

public class PicturesAdapter extends BaseQuickAdapter<GanBean.ResultsBean, BaseViewHolder> {

    public PicturesAdapter(int layoutResId, @Nullable List<GanBean.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GanBean.ResultsBean item) {
        ImgLoadUtils.loadImage(mContext,item.getUrl(),helper.getView(R.id.img_content));
    }
}
