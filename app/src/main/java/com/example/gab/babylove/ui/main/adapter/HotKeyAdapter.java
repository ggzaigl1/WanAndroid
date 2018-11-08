package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.HotKeyBean;
import com.ggz.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/11/8 0008.
 */
public class HotKeyAdapter extends BaseQuickAdapter<HotKeyBean, BaseViewHolder> {

    public HotKeyAdapter(@Nullable List<HotKeyBean> data) {
        super(R.layout.item_navigation_cid, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotKeyBean item) {
        TextView tv_date = helper.getView(R.id.tv_date);
        tv_date.setText(item.getName());
        tv_date.setTextColor(ResourceUtils.getRandomColor());

    }
}
