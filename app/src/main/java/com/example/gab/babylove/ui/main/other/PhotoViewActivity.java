package com.example.gab.babylove.ui.main.other;

import android.app.Activity;
import android.os.Bundle;

import com.example.gab.babylove.R;
import com.example.gab.babylove.base.BaseActivity;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;

/**
 * Created by 初夏小溪
 * data :2019/1/7 0007 11:04
 * @author 55204
 */
public class PhotoViewActivity extends BaseActivity  {

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
    public void initData(Activity activity, Bundle savedInstanceState) {
        mPhotoView.setImageResource(R.mipmap.wechat_image);
        mPhotoView.setOnPhotoTapListener((view, x, y) -> finish());
    }
}
