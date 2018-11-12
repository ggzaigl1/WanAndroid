package com.example.gab.babylove.ui.main.other;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.OrListBean;
import com.example.gab.babylove.ui.main.adapter.PhotoAdapter;
import com.example.gab.babylove.view.PhotoViewPager;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.ggz.baselibrary.utils.T;

import butterknife.BindView;

/**
 * Created by 初夏小溪 on 2018/4/16 0016.
 * 描述：图片详情页
 */

public class PictureDetailActivity extends BaseActivity implements IBaseActivity {

    private int mCurrentPosition;
    @BindView(R.id.view_page)
    PhotoViewPager mPhotoViewPager;
    @BindView(R.id.tv_image_count)
    TextView tv_image_count;

    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return R.layout.activity_picture_detail;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(this, R.color.black, R.color.black);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        OrListBean actionsBean = (OrListBean) getIntent().getSerializableExtra("orListBean");
        mCurrentPosition = getIntent().getIntExtra("position", -1);

        tv_image_count.setText((mCurrentPosition + 1) + "/" + actionsBean.getData().size());
        PhotoAdapter photoAdapter = new PhotoAdapter(actionsBean, PictureDetailActivity.this);
        mPhotoViewPager.setAdapter(photoAdapter);
        mPhotoViewPager.setCurrentItem(mCurrentPosition, false);

        mPhotoViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mCurrentPosition = position;
                tv_image_count.setText((mCurrentPosition + 1) + "/" + actionsBean.getData().size());
                if (mCurrentPosition == actionsBean.getData().size() - 1) {
                    T.showShort("已经是最后一张图片了");
                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        MdStatusBar.setHideBar(this, true);
    }
}
