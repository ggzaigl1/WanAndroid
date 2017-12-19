package com.example.gab.babylove.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.activity.DialActivity;
import com.example.gab.babylove.activity.TraceListActivity;
import com.example.gab.babylove.adapter.TraceListAdapter;
import com.example.gab.babylove.bean.Trace;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/15 0015.
 * 新闻类 Fragment
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.statusView)
    View statusView;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        MdStatusBarCompat.setStatusView(mContext, statusView);
    }

    @OnClick({R.id.bt_OnTraceList})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_OnTraceList:
                JumpUtils.jump(mContext, TraceListActivity.class, null);
                break;
        }
    }
}
