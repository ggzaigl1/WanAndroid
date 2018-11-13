package com.example.gab.babylove.db;

import android.content.Context;
import android.widget.TextView;

import com.example.gab.babylove.R;

import java.util.List;


/**
 * Created by yi.huangxing on 17/12/13.类描述:
 */

public class SearchRecordAdapter extends BaseRecycleAdapter<String> {

    public SearchRecordAdapter(List<String> datas, Context mContext) {
        super(datas, mContext);
    }

    @Override
    protected void bindData(BaseViewHolder holder, final int position) {

        TextView textView = (TextView) holder.getView(R.id.tv_record);
        textView.setText(datas.get(position));

        //
        holder.getView(R.id.tv_delete).setOnClickListener(view -> {
            if (null != mRvItemOnclickListener) {
                mRvItemOnclickListener.RvItemOnclick(position);
            }
        });
        holder.getView(R.id.tv_record).setOnClickListener(v -> {
            if (null != mRvItemOnclickListener) {
                mRvItemOnclickListener.RvDeleteItemOnclick(position);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_search;
    }
}
