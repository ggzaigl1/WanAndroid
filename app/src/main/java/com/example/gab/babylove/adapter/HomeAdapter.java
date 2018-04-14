package com.example.gab.babylove.adapter;

import android.support.v7.app.AppCompatActivity;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;

import java.util.List;

/**
 * Created by Gab on 2017/12/18 0018.
 *
 */

public class HomeAdapter extends RecyclerCommonAdapter<String> {

    public HomeAdapter(AppCompatActivity context, List<String> datas) {
        super(context, R.layout.item_home, datas);
    }

    @Override
    public void convert(ViewHolder holder, String s, int position) {

    }

}
