package com.example.gab.babylove.ui.offical.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.OfficialAccountBean;
import com.ggz.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 * @author 初夏小溪
 * @date 2018/4/19 0019
 * 公众号
 */

public class OfficialAccountAdapter extends BaseQuickAdapter<OfficialAccountBean, BaseViewHolder> {

    public OfficialAccountAdapter(@Nullable List<OfficialAccountBean> data) {
        super(R.layout.item_official_account, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OfficialAccountBean item) {
        helper.setText(R.id.tv_name, item.getName())
                .setTextColor(R.id.tv_name, ResourceUtils.getRandomColor());
    }
}
