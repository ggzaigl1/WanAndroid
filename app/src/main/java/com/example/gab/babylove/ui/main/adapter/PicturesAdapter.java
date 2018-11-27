package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.GanBean;
import com.ggz.baselibrary.utils.imgload.ImgLoadUtils;

import java.util.List;


/**
 * @author 初夏小溪
 * @date 2018/4/13 0013
 * 美图欣赏
 */

public class PicturesAdapter extends BaseQuickAdapter<GanBean.ResultsBean, BaseViewHolder> {

    public PicturesAdapter(@Nullable List<GanBean.ResultsBean> data) {
        super(R.layout.item_gank_list_context, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GanBean.ResultsBean item) {
        if (TextUtils.isEmpty(item.getUrl())) {
            helper.setVisible(R.id.img_content, false);
        } else {
            ImgLoadUtils.loadImage(mContext, item.getUrl(), helper.getView(R.id.img_content));
        }
    }
}
