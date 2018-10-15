package com.example.gab.babylove.ui.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.OfficialAccountListBean;
import com.ggz.baselibrary.utils.ResourceUtils;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/4/19 0019.
 * 公众号详情
 */

public class OfficialAccountListAdapter extends BaseQuickAdapter<OfficialAccountListBean.DatasBean, BaseViewHolder> {

    public OfficialAccountListAdapter(@Nullable List<OfficialAccountListBean.DatasBean> data) {
        super(R.layout.item_official_account_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OfficialAccountListBean.DatasBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_author_name, "作者：" + item.getAuthor()).setTextColor(R.id.tv_author_name, ResourceUtils.getRandomColor())
                .setText(R.id.tv_date, item.getNiceDate())
                .setText(R.id.tv_chapterName, "分类：" + item.getSuperChapterName()).setTextColor(R.id.tv_chapterName, ResourceUtils.getRandomColor());
        helper.addOnClickListener(R.id.image_collect);

    }
}
