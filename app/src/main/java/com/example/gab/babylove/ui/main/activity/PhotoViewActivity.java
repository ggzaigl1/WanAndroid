package com.example.gab.babylove.ui.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.gab.babylove.R;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Gab on 2017/12/12 0012.
 * PhotoView
 */

public class PhotoViewActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.mPhotoView)
    PhotoView mPhotoView;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_photo;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
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

    @OnClick
    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }
}
