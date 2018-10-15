package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.BookmarkBean;
import com.example.gab.babylove.entity.OfficialAccountBean;
import com.ggz.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/4/19 0019.
 * 公众号
 */

public class OfficialAccountAdapter extends BaseQuickAdapter<OfficialAccountBean, BaseViewHolder> {

    public OfficialAccountAdapter(@Nullable List<OfficialAccountBean> data) {
        super(R.layout.item_official_account, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OfficialAccountBean item) {
        TextView name = helper.getView(R.id.tv_name);
        name.setText(item.getName());
        name.setTextColor(ResourceUtils.getRandomColor());
    }
}
