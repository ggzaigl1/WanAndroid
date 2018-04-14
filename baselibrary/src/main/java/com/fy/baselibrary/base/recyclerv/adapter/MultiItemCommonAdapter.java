package com.fy.baselibrary.base.recyclerv.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecyclerView多种ItemViewType 的adapter
 * Created by 下载 on 2017/7/31.
 */
public abstract class MultiItemCommonAdapter<Item> extends RecyclerCommonAdapter<Item> {

    protected MultiItemTypeSupport<Item> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<Item> datas,
                                  MultiItemTypeSupport<Item> multiItemTypeSupport) {
        super(context, -1, datas);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        ViewHolder holder = ViewHolder.get(mContext, parent, layoutId);
        return holder;
    }


}
