package com.example.gab.babylove.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

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
        mPhotoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                finish();
            }

            @Override
            public void onOutsidePhotoTap() {

            }
        });
    }
}
