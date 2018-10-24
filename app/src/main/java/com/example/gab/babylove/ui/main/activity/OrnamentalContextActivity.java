package com.example.gab.babylove.ui.main.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.CourseDetails;
import com.example.gab.babylove.entity.OrnamentalListBean;
import com.example.gab.babylove.ui.main.adapter.OrnamentalContextAdapter;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.ggz.baselibrary.statusbar.StatusBarContentColor;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.ResourceUtils;
import com.ggz.baselibrary.utils.imgload.ImgLoadUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gab on 2018/3/13 0013.
 * 运动课程内容
 */

public class OrnamentalContextActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_content)
    TextView mTvContext;
    @BindView(R.id.tv_announcements)
    TextView tv_announcements;//注意事项
    @BindView(R.id.club_details_bg)
    AppCompatImageView mClubDetailsBg;
    @BindView(R.id.tv_do)
    TextView mTvDo;
    @BindView(R.id.tv_point)
    TextView mTvPoint;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private OrnamentalContextAdapter mAdapter;

    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return R.layout.activity_ornamental_context;
    }

    @Override
    public void setStatusBar(Activity activity) {
//        MdStatusBar.statusAlpha = 0;
//        MdStatusBar.navAlpha = 0;
        MdStatusBar.setColorBar(activity, R.color.transparent, R.color.statusBar);
//        StatusBarContentColor.setStatusTextColor(this, true, true);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        int id = getIntent().getIntExtra("id", 0);
        getCourseDetails(id);
        initRecycle();
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> JumpUtils.exitActivity(this));
    }


    private void getCourseDetails(int mPageNo) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getCourseDetails(mPageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(new NetCallBack<CourseDetails>() {
                    @Override
                    protected void onSuccess(CourseDetails courseDetails) {
                        if (courseDetails.getResult() == 1) {
                                mKProgressHUD.dismiss();
                                mTvTitle.setText(courseDetails.getData().getTitle());
                                mTvContext.setText(courseDetails.getData().getSubTitle());
                                tv_announcements.setText(courseDetails.getData().getNotes());
                                ImgLoadUtils.loadImage(getApplicationContext(), courseDetails.getData().getPic(), mClubDetailsBg);
                                mAdapter.setNewData(courseDetails.getData().getGroups().get(0).getActions());
                                mTvPoint.setText(getString(R.string.announcements));
                                mTvDo.setText(getString(R.string.training_action));
                                if (mCollapsingToolbarLayout != null) {
                                    //设置隐藏图片时候ToolBar的颜色
//                                    mCollapsingToolbarLayout.setContentScrimColor(ResourceUtils.getRandomColor());
                                    mCollapsingToolbarLayout.setContentScrimColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
                                    //设置工具栏标题
                                    mCollapsingToolbarLayout.setTitle(courseDetails.getData().getTitle());
                                }
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {
                        mKProgressHUD.dismiss();
                    }
                });
    }


    @OnClick({})
    @Override
    public void onClick(View view) {
    }

    @Override
    public void reTry() {

    }

    /**
     * 训练课程列表
     */
    private void initRecycle() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter = new OrnamentalContextAdapter(new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<CourseDetails.DataBean.GroupsBean.ActionsBean> data = mAdapter.getData();
            OrnamentalListBean orListBean = new OrnamentalListBean(data);
            Bundle bundle = new Bundle();
            bundle.putSerializable("actionsBean", orListBean);
            bundle.putInt("position", position);
            JumpUtils.jump(OrnamentalContextActivity.this, OrnamentalMotionActivity.class, bundle);
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
