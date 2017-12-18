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

public class HomeAdapter extends RecyclerCommonAdapter<HomeBean> {

    private OnItemClickListner<HomeBean> listner;

    public HomeAdapter(AppCompatActivity context, List<HomeBean> datas) {
        super(context, R.layout.item_home, datas);
    }

    @Override
    public void convert(ViewHolder holder, HomeBean homeBean, int position) {
        LinearLayout home_Ll = holder.getView(R.id.home_Ll);
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_author_name = holder.getView(R.id.tv_author_name);
        TextView tv_date = holder.getView(R.id.tv_date);
        ImageView iv_thumbnail_pic_s = holder.getView(R.id.iv_thumbnail_pic_s);
        ImageView thumbnail_pic_s02 = holder.getView(R.id.thumbnail_pic_s02);
        ImageView thumbnail_pic_s03 = holder.getView(R.id.thumbnail_pic_s03);

        tv_title.setText(homeBean.getData().get(position).getTitle());
        tv_author_name.setText(homeBean.getData().get(position).getAuthor_name());
        tv_date.setText(homeBean.getData().get(position).getDate());
        ImgLoadUtils.loadImage(mContext,homeBean.getData().get(position).getThumbnail_pic_s(), iv_thumbnail_pic_s);
        ImgLoadUtils.loadImage(mContext,homeBean.getData().get(position).getThumbnail_pic_s02(), thumbnail_pic_s02);
        ImgLoadUtils.loadImage(mContext,homeBean.getData().get(position).getThumbnail_pic_s03(), thumbnail_pic_s03);
        //设置点击事件
        home_Ll.setOnClickListener(v -> {
            if (null != listner) {
                listner.onItemClick(homeBean);
            }
        });


    }

    public void setListner(OnItemClickListner<HomeBean> listner) {
        this.listner = listner;
    }
}
