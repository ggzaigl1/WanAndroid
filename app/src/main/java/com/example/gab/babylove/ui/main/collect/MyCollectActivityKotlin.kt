package com.example.gab.babylove.ui.main.collect

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.gab.babylove.R
import com.example.gab.babylove.api.ApiServiceKotlin
import com.example.gab.babylove.entity.CollectBean
import com.example.gab.babylove.ui.main.collect.adapter.MyCollectKotlinAdapter
import com.example.gab.babylove.web.WebViewActivity
import com.ggz.baselibrary.retrofit.NetCallBack
import com.ggz.baselibrary.retrofit.RequestUtils
import com.ggz.baselibrary.retrofit.RxHelper
import com.ggz.baselibrary.utils.JumpUtils
import com.ggz.baselibrary.utils.T
import com.kaopiz.kprogresshud.KProgressHUD
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_collect_kotlin.*
import java.util.*

/**
 * Created by 初夏小溪
 * data :2019/1/4 0004 14:44
 */
class MyCollectActivityKotlin : AppCompatActivity() {

    var mPageNo = 0
    val mAdapter = MyCollectKotlinAdapter(ArrayList())
    lateinit var mKProgressHUD: KProgressHUD //延迟初始化 lateinit

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect_kotlin)
        setSupportActionBar(toolbar_kotlin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_kotlin.setNavigationOnClickListener {
            JumpUtils.exitActivity(this)
        }
        initData()
    }

    fun initData() {
        initRecyle()
        initRefresh()
        getArticleList(mPageNo)
    }

    fun initRecyle() {
        rv_collect_title.layoutManager = LinearLayoutManager(this)
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            WebViewActivity.startWebActivity(this,
                    mAdapter.data[position].link,
                    mAdapter.data[position].id,
                    mAdapter.data[position].isCollect)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        mAdapter.onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.image_collect ->
                    unCollectArticle(mAdapter.data[position].id,
                            mAdapter.data[position].originId,
                            position)
            }
        }
        mAdapter.setEmptyView(R.layout.activity_null_data, rv_collect_title.parent as ViewGroup)
        rv_collect_title.adapter = mAdapter
    }

    private fun toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    /**
     * 我的收藏 数据加载
     */
    private fun getArticleList(mPageNo: Int) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show()
        RequestUtils.create(ApiServiceKotlin::class.java)
                .getCollectList(mPageNo)
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(object : NetCallBack<CollectBean>() {
                    override fun updataLayout(flag: Int) {
                    }

                    override fun onSuccess(collectBean: CollectBean?) {
                        if (collectBean != null) {
                            mKProgressHUD.dismiss()
                            when {
                                refreshLayout_collect.isRefreshing -> {
                                    mAdapter.setNewData(collectBean.datas)
                                    refreshLayout_collect.finishRefresh()
                                }
                                refreshLayout_collect.isLoading -> {
                                    mAdapter.data.addAll(collectBean.datas)
                                    refreshLayout_collect.finishLoadMore()
                                    mAdapter.notifyDataSetChanged()
                                }
                                else -> mAdapter.setNewData(collectBean.datas)
                            }
                        }
                    }
                })
    }

    /**
     * 我的收藏页面, 取消收藏
     *
     * @param id
     * @param originId
     * @param position
     */
    @SuppressLint("CheckResult")
    private fun unCollectArticle(id: Int, originId: Int, position: Int) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show()
        RequestUtils.create(ApiServiceKotlin::class.java)
                .unMyCollectArticle(id, originId)
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(object : NetCallBack<Any>() {
                    override fun onSuccess(t: Any) {
                        mKProgressHUD.dismiss()
                        mAdapter.remove(position)
                        mAdapter.notifyDataSetChanged()
                        T.showShort(getString(R.string.cancel_collection_success))
                    }

                    override fun updataLayout(flag: Int) {

                    }
                })
    }

    /**
     * 分页加载数据
     */
    private fun initRefresh() {
        refreshLayout_collect.setRefreshHeader(ClassicsHeader(this))
        refreshLayout_collect.setRefreshFooter(ClassicsFooter(this))
        refreshLayout_collect.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mPageNo += 1
                getArticleList(mPageNo)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                mPageNo = 0
                getArticleList(mPageNo)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        when {
            refreshLayout_collect.isRefreshing -> {
                refreshLayout_collect.finishRefresh()
            }
            refreshLayout_collect.isLoading -> {
                refreshLayout_collect.finishLoadMore()
            }
        }
    }
}