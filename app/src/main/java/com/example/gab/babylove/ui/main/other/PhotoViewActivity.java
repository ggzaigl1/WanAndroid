package com.example.gab.babylove.ui.main.other;

import android.app.Activity;
import android.os.Bundle;

import com.example.gab.babylove.R;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.base.BaseActivitys;
import com.ggz.baselibrary.application.IBaseActivity;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;


/**
 *
 * @author Gab
 * @date 2017/12/12 0012
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
    public void initData(Activity activity, Bundle savedInstanceState) {
        mPhotoView.setImageResource(R.mipmap.wechat_image);
        mPhotoView.setOnPhotoTapListener((view, x, y) -> finish());
    }
}