package com.example.gab.babylove.ui.main.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.CourseDetails;
import com.example.gab.babylove.entity.OrnamentalListBean;
import com.example.gab.babylove.ui.main.adapter.OrnamentalContextAdapter;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RequestUtils;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gab on 2018/3/13 0013.
 * 运动课程内容
 */

public class OrnamentalContextActivity extends AppCompatActivity implements IBaseActivity {

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
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        int id = getIntent().getExtras().getInt("id");
        getCourseDetails(id);
        initRecycle();
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> JumpUtils.exitActivity(this));
    }

    private void getCourseDetails(int mPageNo) {
        SpotsDialog dialog = new SpotsDialog(this);dialog.show();
//        IProgressDialog progressDialog = new IProgressDialog().init(this).setDialogMsg(R.string.data_loading);
        RequestUtils.create(ApiService.class)
                .getCourseDetails(mPageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(new NetCallBack<CourseDetails>() {
                    @Override
                    protected void onSuccess(CourseDetails t) {
                        if (t.getResult() == 1) {
                            CourseDetails.DataBean data = t.getData();
                            if (null != data) {
                                mTvTitle.setText(data.getTitle());
                                mTvContext.setText(data.getSubTitle());
                                tv_announcements.setText(data.getNotes());
                                ImgLoadUtils.loadImage(getApplicationContext(), data.getPic(), mClubDetailsBg);
                                mAdapter.setNewData(data.getGroups().get(0).getActions());
                                mTvPoint.setText("注意事项");
                                mTvDo.setText("训练动作");
                                if (mCollapsingToolbarLayout != null) {
                                    //设置隐藏图片时候ToolBar的颜色
                                    mCollapsingToolbarLayout.setContentScrimColor(ResourceUtils.getRandomColor());
                                    //设置工具栏标题
                                    mCollapsingToolbarLayout.setTitle(data.getTitle());
                                }
                            }
                        }
                        dialog.dismiss();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


    @OnClick({})
    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.tv_back:
//                JumpUtils.exitActivity(this);
//                break;
//        }
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
        mAdapter = new OrnamentalContextAdapter(R.layout.ornamental_context_recycle_item, new ArrayList<>());
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
