package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.BookmarkBean;
import com.ggz.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/4/19 0019.
 */

public class WebsiteAdapter extends BaseQuickAdapter<BookmarkBean, BaseViewHolder> {

    public WebsiteAdapter(int layoutResId, @Nullable List<BookmarkBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookmarkBean item) {
        TextView name = helper.getView(R.id.tv_name);
        name.setText(item.getName());
        name.setTextColor(ResourceUtils.getRandomColor());
//        name.setBackground(SelectUtils.getTagSelector(R.drawable.selector_item_bg));
    }
}
