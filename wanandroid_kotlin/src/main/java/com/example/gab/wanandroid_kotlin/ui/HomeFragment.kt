package com.example.gab.wanandroid_kotlin.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.BaseAdapter
import com.ToxicBakery.viewpager.transforms.AccordionTransformer
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.listener.OnItemClickListener
import com.example.gab.wanandroid_kotlin.R
import com.example.kotlinconvenientbanner.utils.Utils
import java.util.*

/**
 * Created by 初夏小溪
 * data :2019/1/7 0007 9:32
 */
class HomeFragment : AppCompatActivity(), OnItemClickListener {

    override fun onItemClick(position: Int) {
    }

    var mPageNo = 0
    internal var mRecyclerView: RecyclerView? = null
    internal var mConvenientBanner: ConvenientBanner<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

    }

    private val pics = arrayListOf<String>()
    var images = arrayOf("http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg", "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg", "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg")


//    private fun initData(urls: List<String>) {
//        mConvenientBanner!!.setPages(CBViewHolderCreator { NetworkImageHolderView() }, Arrays.asList<Any>(*Utils.images))
//                .setPageIndicator(intArrayOf(R.drawable.shape_banner_indicator1, R.drawable.shape_banner_indicator2))
//                .setPointViewVisible(true)
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setPageTransformer(AccordionTransformer())
//                .setOnItemClickListener {
//                    position -> val bundle = Bundle()
//                    bundle.putString("Link", urls[position])
//                }.isManualPageable = true
//    }
}

