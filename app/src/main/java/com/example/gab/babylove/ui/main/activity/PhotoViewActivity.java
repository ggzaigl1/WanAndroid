package com.example.gab.babylove.ui.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.base.BaseActivity;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Gab on 2017/12/12 0012.
 * 头像 PhotoView
 */

public class PhotoViewActivity extends BaseActivity implements IBaseActivity {

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
        mPhotoView.setOnPhotoTapListener((view, x, y) -> finish());
    }
}