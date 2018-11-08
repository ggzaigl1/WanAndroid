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
public class TestAdapter extends BaseQuickAdapter<HotKeyBean, BaseViewHolder> {

    public TestAdapter(@Nullable List<HotKeyBean> data) {
        super(R.layout.item_official_account, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotKeyBean item) {
        helper.setText(R.id.tv_name,item.getName());
    }
}
