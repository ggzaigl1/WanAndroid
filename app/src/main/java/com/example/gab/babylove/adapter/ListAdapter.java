package com.example.gab.babylove.adapter;

import android.support.v7.app.AppCompatActivity;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;

import java.util.List;

/**
 * Created by Gab on 2018/1/4 0004.
 */

public class ListAdapter extends RecyclerCommonAdapter<String> {

    public ListAdapter(AppCompatActivity context, List<String> datas ) {
        super(context, R.layout.item_list_data, datas);
    }

    @Override
    public void convert(ViewHolder holder, String s, int position) {
//        TextView tv_title = holder.getView(R.id.tv_title);
//        if (null!=s){
//            tv_title.setText(s);
//        }
    }
}
