package com.example.gab.wanandroid_kotlin.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.gab.wanandroid_kotlin.R
import kotlinx.android.synthetic.main.activity_photo.*

/**
 * Created by 初夏小溪
 * data :2019/1/4 0004 14:22
 */
class PhotoViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        mPhotoView.setImageResource(R.mipmap.wechat_image)
        mPhotoView.setOnPhotoTapListener { _, _, _ -> finish() }
    }
}