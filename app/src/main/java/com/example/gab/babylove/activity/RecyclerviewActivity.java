package com.example.gab.babylove.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.adapter.ListAdapter;
import com.example.gab.babylove.fragment.NewsFragment;
import com.example.gab.babylove.widget.FastScrollLinearLayoutManager;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2018/1/5 0005.
 *
 */

public class RecyclerviewActivity extends BaseActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recycleview)
    RecyclerView rvPatientList;
    ListAdapter adapter;
    private List<String> dummyData;

    @Override
    protected int getContentView() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setDummyData();
        GetRecordData();
    }

    private void GetRecordData() {
        LinearLayoutManager manager = new FastScrollLinearLayoutManager(mContext,LinearLayoutManager.VERTICAL, false);
        rvPatientList.setLayoutManager(manager);
        adapter = new ListAdapter(mContext, dummyData);
        rvPatientList.setAdapter(adapter);
        rvPatientList.addOnScrollListener(new MyRecyclerViewScrollListener());
    }


    private void setDummyData() {
        dummyData = new ArrayList();
        for (int i = 0; i < 55; i++) {
            dummyData.add("");
        }
    }

    @OnClick({R.id.fab})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                rvPatientList.scrollToPosition(0);
                fab.setVisibility(View.GONE);
                break;
        }
    }

    //滑动监听
    private class MyRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
            //当不滚动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //  判断是否滚动超过一屏
                if (firstVisibleItemPosition == 0) {
                    fab.setVisibility(View.INVISIBLE);
                } else {
                    fab.setVisibility(View.VISIBLE);
                }

            } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {//拖动中
                fab.setVisibility(View.INVISIBLE);
            }
        }
    }
}
