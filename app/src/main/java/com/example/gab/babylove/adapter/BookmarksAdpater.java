package com.example.gab.babylove.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gab.babylove.entity.BookmarkBean;
import com.example.gab.babylove.entity.TreeBean;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/4/19 0019.
 */

public class BookmarksAdpater extends BaseQuickAdapter<BookmarkBean, BaseViewHolder>   {
    public BookmarksAdpater(int layoutResId, @Nullable List<BookmarkBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookmarkBean item) {

    }
}
