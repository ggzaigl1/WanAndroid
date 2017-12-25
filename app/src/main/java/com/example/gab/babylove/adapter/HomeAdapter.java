package com.example.gab.babylove.adapter;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.recyclerv.OnItemClickListner;
import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.entity.HomeBean;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;

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
