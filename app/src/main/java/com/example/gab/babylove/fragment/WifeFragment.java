package com.example.gab.babylove.fragment;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.activity.DialActivity;
import com.example.gab.babylove.adapter.ListAdapter;
import com.example.gab.babylove.widget.FastScrollLinearLayoutManager;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.DeviceUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/15 0015.
 * 大宝贝的专用类
 */

public class WifeFragment extends BaseFragment implements View.OnTouchListener {

    @BindView(R.id.tv_baby)
    TextView tv_baby;
    @BindView(R.id.tv_Os)
    TextView tv_Os;
    @BindView(R.id.statusView)
    View statusView;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rvPatientList)
    RecyclerView rvPatientList;
    @BindView(R.id.horizontal_view)
    HorizontalScrollView mHorizontalScrollView;
    @BindView(R.id.tv_ss)
    TextView mTvSs;
    @BindView(R.id.flHead)
    FrameLayout flHead;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private GestureDetector mGestureDetector;
    private TranslateAnimation mRigthToLeftAnim;
    private final static float SCOLL_V = 0.1f;
    ListAdapter adapter;
    private List<String> dummyData;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_wife;
    }

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void baseInit() {
        super.baseInit();
        tvTitle.setText("杂乱无章的什么玩意儿");
        tv_baby.post(() -> {
            mRigthToLeftAnim = new TranslateAnimation(mHorizontalScrollView.getWidth(), -tv_baby.getWidth(), 0, 0);
            mRigthToLeftAnim.setRepeatCount(Animation.INFINITE);
            mRigthToLeftAnim.setInterpolator(new LinearInterpolator());
            mRigthToLeftAnim.setDuration((long) ((mHorizontalScrollView.getWidth() + tv_baby.getWidth()) / SCOLL_V));
            tv_baby.startAnimation(mRigthToLeftAnim);
        });
        tv_Os.setText("手机厂商:" + DeviceUtils.getDeviceMake()
                + "\n" + "手机型号:" + DeviceUtils.getSystemModel()
                + "\n" + "Android版本号:" + DeviceUtils.getDeviceVersion()
                + "\n" + "手机IMEI:" + DeviceUtils.getIMEI(mContext));
        MdStatusBarCompat.setStatusView(mContext, statusView);
        mGestureDetector = new GestureDetector(new gestureListener()); //使用派生自OnGestureListener
        mGestureDetector.setOnDoubleTapListener(new doubleTapListener());
        flHead.setOnTouchListener(this);
        flHead.setFocusable(true);
        flHead.setClickable(true);
        flHead.setLongClickable(true);
        setDummyData();
        GetRecordData();

    }


    private void GetRecordData() {
        LinearLayoutManager manager = new FastScrollLinearLayoutManager(mContext);
        rvPatientList.setLayoutManager(manager);
        adapter = new ListAdapter(mContext, dummyData);
        rvPatientList.setNestedScrollingEnabled(false);
        rvPatientList.setAdapter(adapter);
//        rvPatientList.addOnScrollListener(new MyRecyclerViewScrollListener());
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

    @OnClick({R.id.bt_Dial})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_Dial://头部左边按钮
                JumpUtils.jump(mContext, DialActivity.class, null);
                break;
        }
    }

    private void setDummyData() {
        dummyData = new ArrayList();
        for (int i = 0; i < 55; i++) {
            dummyData.add("");
        }
    }

    /*
   * 在onTouch()方法中，我们调用GestureDetector的onTouchEvent()方法，将捕捉到的MotionEvent交给GestureDetector
   * 来分析是否有合适的callback函数来处理用户的手势
   */
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    //OnGestureListener监听
    private class gestureListener implements GestureDetector.OnGestureListener {

        public boolean onDown(MotionEvent e) {
            Log.i("MyGesture", "onDown");
            T.showShort("onDown");
            return false;
        }

        public void onShowPress(MotionEvent e) {
            Log.i("MyGesture", "onShowPress");
            T.showShort("onShowPress");
        }

        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("MyGesture", "onSingleTapUp");
            T.showShort("onSingleTapUp");
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i("MyGesture22", "onScroll:" + (e2.getX() - e1.getX()) + "   " + distanceX);
            T.showShort("onScroll");
            return true;
        }

        public void onLongPress(MotionEvent e) {
            Log.i("MyGesture", "onLongPress");
            T.showShort("onLongPress");
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("MyGesture", "onFling");
            T.showShort("onFling");
            return true;
        }
    }

    //OnDoubleTapListener监听
    private class doubleTapListener implements GestureDetector.OnDoubleTapListener {

        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("MyGesture", "onSingleTapConfirmed");
            T.showShort("onSingleTapConfirmed");
            return true;
        }

        public boolean onDoubleTap(MotionEvent e) {
            Log.i("MyGesture", "onDoubleTap");
            T.showShort("onDoubleTap");
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i("MyGesture", "onDoubleTapEvent");
            T.showShort("onDoubleTapEvent");
            rvPatientList.smoothScrollToPosition(0);
            return true;
        }
    }
}
