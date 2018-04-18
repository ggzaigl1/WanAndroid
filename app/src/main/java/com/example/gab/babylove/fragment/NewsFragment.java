package com.example.gab.babylove.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;

import butterknife.BindView;

/**
 * Created by Gab on 2017/12/15 0015.
 * 新闻类 Fragment
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        toolbar.setTitle("新闻资讯");
        MdStatusBar.setColorBar(getActivity(), R.color.statusBar, R.color.statusBar);
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
