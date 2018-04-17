package com.example.gab.babylove.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;

import butterknife.BindView;

/**
 * Created by Gab on 2017/12/15 0015.
 * 新闻类 Fragment
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.statusView)
    View statusView;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvMenu)
    TextView tvMenu;
    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        tvTitle.setText("新闻资讯");
        tvBack.setVisibility(View.INVISIBLE);
        MdStatusBarCompat.setStatusView(mContext, statusView);

    }

//    @OnClick({R.id.bt_OnTraceList})
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.bt_OnTraceList:
//
//                break;
//        }
//    }
}
