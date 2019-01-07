package com.example.gab.wanandroid_kotlin.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bigkoo.convenientbanner.holder.Holder
import com.example.gab.wanandroid_kotlin.R
import com.ggz.baselibrary.utils.imgload.ImgLoadUtils

/**
 * Created by 初夏小溪
 * data :2019/1/7 0007 10:30
 */

class NetworkImageHolderView : Holder<String> {

    lateinit var imageView: ImageView

    override fun createView(context: Context?): View {
        imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        return imageView
    }


    override fun UpdateUI(context: Context?, position: Int, data: String?) {
        imageView.setImageResource(R.drawable.ic_launcher_background)
        ImgLoadUtils.loadImage(context, data, imageView)
    }
}