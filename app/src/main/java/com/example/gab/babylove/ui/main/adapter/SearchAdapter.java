package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.HotKeyBean;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/11/8 0008.
 */
public class SearchAdapter extends BaseQuickAdapter<HotKeyBean, BaseViewHolder> {

    public SearchAdapter(@Nullable List<HotKeyBean> data) {
        super(R.layout.item_hotkey_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotKeyBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.addOnClickListener(R.id.tv_name);
    }
}
