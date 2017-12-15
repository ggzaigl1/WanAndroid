package com.fy.img.picker.preview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.fy.img.picker.bean.ImageItem;
import com.fy.img.subscaleview.ImageSource;
import com.fy.img.subscaleview.SubsamplingScaleImageView;
import com.fy.library.imgpicker.R;

import java.io.File;

/**
 * 本地图片Holder
 * Created by fangs on 2017/7/6.
 */
public class LocalImageHolderView implements Holder<ImageItem> {

    private View view;
    private SubsamplingScaleImageView imageView;
    private ImageView imgGlide;

    @Override
    public View createView(Context context) {
        if (null == view){
            view = LayoutInflater.from(context).inflate(R.layout.viewpager_preview, null);
        }
        imgGlide = (ImageView) view.findViewById(R.id.imgGlide);
        imageView = (SubsamplingScaleImageView) view.findViewById(R.id.subImageView);
        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        imageView.setBackgroundColor(Color.TRANSPARENT);
        imageView.setMaxScale(15);

        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, ImageItem imgData) {

        imageView.setOnImageEventListener(new SubsamplingScaleImageView.OnImageEventListener() {
            @Override
            public void onReady() {}
            @Override
            public void onImageLoaded() {
                imgGlide.setVisibility(View.GONE);
            }
            @Override
            public void onPreviewLoadError(Exception e) {}
            @Override
            public void onImageLoadError(Exception e) {}
            @Override
            public void onTileLoadError(Exception e) {}
            @Override
            public void onPreviewReleased() {}
        });

        File file = new File(imgData.path);
        imageView.setImage(ImageSource.uri(file.getAbsolutePath()));

        ImgLoadUtils.loadImg(context, imgData.path, imgGlide);
    }
}
