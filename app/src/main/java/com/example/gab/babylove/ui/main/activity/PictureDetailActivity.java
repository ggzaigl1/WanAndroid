package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.ui.main.adapter.PhotoAdapter;
import com.example.gab.babylove.entity.OrListBean;
import com.example.gab.babylove.widget.PhotoViewPager;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.ToastUtils;

/**
 * Created by 初夏小溪 on 2018/4/16 0016.
 * 描述：图片详情页
 */

public class PictureDetailActivity extends AppCompatActivity {

    private int mCurrentPosition;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MdStatusBar.setColorBar(this, R.color.black, R.color.black);
        setContentView(R.layout.activity_picture_detail);
        PhotoViewPager viewPager = findViewById(R.id.view_page);
        TextView tv_image_count = findViewById(R.id.tv_image_count);

        OrListBean actionsBean = (OrListBean) getIntent().getExtras().getSerializable("orListBean");
        mCurrentPosition = getIntent().getExtras().getInt("position");

        tv_image_count.setText((mCurrentPosition + 1) + "/" + actionsBean.getData().size());
        PhotoAdapter adapter = new PhotoAdapter(actionsBean, PictureDetailActivity.this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(mCurrentPosition, false);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mCurrentPosition = position;
                tv_image_count.setText((mCurrentPosition + 1) + "/" + actionsBean.getData().size());
                if (mCurrentPosition == actionsBean.getData().size() - 1) {
                    ToastUtils.showShortToast("已经是最后一张图片了");
                }
            }
        });
    }
}
