package com.fy.baselibrary.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.lzy.ninegrid.NineGridView;

/**
 * Created by xcy on 2018/2/27.
 */

public class GlideImageLoader implements NineGridView.ImageLoader {


    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
        ImgLoadUtils.loadImage(context,url,imageView);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
