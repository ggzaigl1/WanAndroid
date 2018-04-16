package com.example.gab.babylove.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.gab.babylove.bean.OrListBean;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.github.chrisbanes.photoview.PhotoView;


/**
 * Created by QKun on 2017/12/28.
 */

public class PhotoAdapter extends PagerAdapter {

    private OrListBean imageUrls;
    private AppCompatActivity activity;

    public PhotoAdapter(OrListBean imageUrls, AppCompatActivity activity) {
        this.imageUrls = imageUrls;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = imageUrls.getData().get(position).getUrl();

        PhotoView photoView = new PhotoView(activity);
        ImgLoadUtils.loadImage(activity, url, photoView);
        container.addView(photoView);

        photoView.setOnPhotoTapListener((view, x, y) -> activity.finish());
        return photoView;
    }

    @Override
    public int getCount() {
        return imageUrls != null ? imageUrls.getData().size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
