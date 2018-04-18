package com.example.gab.babylove.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.gab.babylove.R;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;


/**
 * Created by Gab on 2017/12/12 0012.
 * 照片放大
 */

public class PhotoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        PhotoView mPhotoView = findViewById(R.id.mPhotoView);
        mPhotoView.setImageResource(R.mipmap.wechat_image);
        //本地设置
//        Glide.with(this).load(R.mipmap.wechat_image).into(mPhotoView);
        //网络加载
//        ImgLoadUtils.loadImage(mContext,"http://img2.3lian.com/2014/f2/37/d/40.jpg",mPhotoView);
//        PhotoViewAttacher attacher = new PhotoViewAttacher(mPhotoView); //阻止点击返回
        mPhotoView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                finish();
            }
        });
    }
}
