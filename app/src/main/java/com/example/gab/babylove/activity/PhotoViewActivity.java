package com.example.gab.babylove.activity;

import android.os.Bundle;
import android.view.View;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Gab on 2017/12/12 0012.
 *
 */

public class PhotoViewActivity extends BaseActivity{

    @BindView(R.id.pv)
    PhotoView mPhotoView;
    @Override
    protected int getContentView() {
        return R.layout.activity_photo;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        PhotoViewAttacher attacher = new PhotoViewAttacher(photoView); //阻止点击返回
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
