package com.example.gab.babylove.ui.main.other

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.gab.babylove.R
import com.example.gab.babylove.base.BaseActivity
import com.ggz.baselibrary.application.IBaseActivity
import com.github.chrisbanes.photoview.OnPhotoTapListener
import kotlinx.android.synthetic.main.activity_photo.*

/**
 * Created by 初夏小溪
 * data :2019/1/4 0004 14:22
 */
class PhotoViewActivity : BaseActivity(),IBaseActivity {

    override fun isShowHeadView(): Boolean {
        return true
    }

    override fun setView(): Int {
        return R.layout.activity_photo
    }

    override fun initData(activity: Activity?, savedInstanceState: Bundle?) {
        mPhotoView.setImageResource(R.mipmap.wechat_image)
        mPhotoView.setOnPhotoTapListener { _, _, _ -> finish() }
    }
}