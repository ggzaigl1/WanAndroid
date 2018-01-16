package com.example.gab.babylove.fragment;

import android.content.Intent;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.activity.RecyclerviewActivity;
import com.example.gab.babylove.activity.TraceListActivity;
import com.example.gab.babylove.activity.WebViewActivity;
import com.example.gab.babylove.widget.StateButton;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/15 0015.
 * 新闻类 Fragment
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.statusView)
    View statusView;
    @BindView(R.id.bt_OnTrace)
    Button bt_OnTrace;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.different_radius_test)
    StateButton radius;

    long[] mHints = new long[5];
    final static int COUNTS = 5;//点击次数
    final static long DURATION = 3 * 1000;//规定有效时间
    long[] mHits = new long[COUNTS];


    @Override
    protected int getContentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        tvTitle.setText("杂七杂八鬼名堂");
        MdStatusBarCompat.setStatusView(mContext, statusView);
        onDisplaySettingButton(bt_OnTrace);

        //设置不同状态下文字变色
//        text.setOnClickListener(view -> text.setEnabled(false));
        //设置四个角不同的圆角
        radius.setRadius(new float[]{0, 0, 20, 20, 40, 40, 60, 60});
    }


    @OnClick({R.id.bt_OnTraceList, R.id.bt_OnTrace,R.id.bt_recycleview,R.id.text_webView})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_OnTraceList:
                JumpUtils.jump(mContext, TraceListActivity.class, null);
                break;
            case R.id.bt_recycleview:
                JumpUtils.jump(mContext, RecyclerviewActivity.class, null);
                break;
            case R.id.bt_OnTrace:
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                //实现左移，然后最后一个位置更新距离开机的时间，如果最后一个时间和最开始时间小于DURATION，即连续5次点击
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
                    if (mHits.length > 5) {
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 启动相机
                        startActivityForResult(intent1, 1);

                    }
                    String tips = "您已在[" + DURATION + "]ms内连续点击【" + mHits.length + "】次了！！！";
                    T.showShort(tips);
                }
                break;
            case R.id.text_webView:
                JumpUtils.jump(mContext, WebViewActivity.class, null);
                break;
//            case R.id.background_test:
//                T.showShort("这是真的成功测试热更新123");
//                break;
        }
    }


    public void onDisplaySettingButton(View view) {
        System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);//把从第二位至最后一位之间的数字复制到第一位至倒数第一位
        mHints[mHints.length - 1] = SystemClock.uptimeMillis();//从开机到现在的时间毫秒数
        if (SystemClock.uptimeMillis() - mHints[0] <= 1000) {//连续点击之间间隔小于一秒，有效
            bt_OnTrace.setVisibility(View.VISIBLE);
        }
    }

}
