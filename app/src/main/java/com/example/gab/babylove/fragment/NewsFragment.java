package com.example.gab.babylove.fragment;

import android.content.Intent;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.activity.AgentWebActivity;
import com.example.gab.babylove.activity.CustomViewActivity;
import com.example.gab.babylove.activity.FingerprintMainActivity;
import com.example.gab.babylove.activity.GSYVideoPlayerActivity;
import com.example.gab.babylove.activity.GankMZActivity;
import com.example.gab.babylove.activity.TraceListActivity;
import com.example.gab.babylove.activity.WebViewActivity;
import com.example.gab.babylove.animation.AnimationActivity;
import com.example.gab.babylove.channelmanage.ChannelManageActivity;
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
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvMenu)
    TextView tvMenu;
    @BindView(R.id.bt_OnTraceList)
    TextView bt_OnTraceList;

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
        tvMenu.setVisibility(View.GONE);
        tvBack.setVisibility(View.GONE);
        MdStatusBarCompat.setStatusView(mContext, statusView);
        onDisplaySettingButton(bt_OnTrace);

        //设置不同状态下文字变色
//        text.setOnClickListener(view -> text.setEnabled(false));
        //设置四个角不同的圆角
        radius.setRadius(new float[]{0, 0, 20, 20, 40, 40, 60, 60});

//        TapTargetView.showFor(mContext, TapTarget.forView(mRootView.findViewById(R.id.bt_OnTraceList), "This is a target", "We have the best targets, believe me")
//                        // All options below are optional
//                        .outerCircleColor(R.color.red)      // Specify a color for the outer circle
//                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
//                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
//                        .titleTextSize(20)                  // Specify the size (in sp) of the title text
//                        .titleTextColor(R.color.white)      // Specify the color of the title text
//                        .descriptionTextSize(10)            // Specify the size (in sp) of the description text
//                        .descriptionTextColor(R.color.red)  // Specify the color of the description text
//                        .textColor(R.color.blue)            // Specify a color for both the title and description text
//                        .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
//                        .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
//                        .drawShadow(true)                   // Whether to draw a drop shadow or not
//                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
//                        .tintTarget(true)                   // Whether to tint the target view's color
//                        .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
//                        .icon(ContextCompat.getDrawable(mContext, R.mipmap.back))// Specify a custom drawable to draw as the target
//                        .targetRadius(60),                  // Specify the target radius (in dp)
//                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
//                    @Override
//                    public void onTargetClick(TapTargetView view) {
//                        super.onTargetClick(view);
//                        // This call is optional
//                        T.showShort("看");
//                    }
//                });

    }

    @OnClick({R.id.bt_OnTraceList, R.id.bt_OnTrace, R.id.bt_recycleview, R.id.text_webView, R.id.abo_Advice, R.id.dash_test, R.id.stroke_test,
            R.id.different_radius_test, R.id.stroke_background_test, R.id.animation,R.id.GankMZ})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_OnTraceList:
                //模拟快递布局
                JumpUtils.jump(mContext, TraceListActivity.class, null);
                break;
            case R.id.bt_recycleview:
//                JumpUtils.jump(mContext, RecyclerviewActivity.class, null);
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
//                JumpUtils.jump(mContext, StoreWebViewActivity.class, null);
                break;
            case R.id.abo_Advice: //自定义View相关内容
                JumpUtils.jump(mContext, CustomViewActivity.class, null);
                break;
//            case R.id.background_test:
//                T.showShort("这是真的成功测试热更新123");
//                break;
            //空
            case R.id.different_radius_test:
//                JumpUtils.jump(mContext, CalendarViewActivity.class, null);
//                JumpUtils.jump(mContext, Camera2Activity.class, null);
                JumpUtils.jump(mContext, ChannelManageActivity.class, null);
                break;
            // AgentWeb 封装
            case R.id.dash_test:
                JumpUtils.jump(mContext, AgentWebActivity.class, null);
                break;
            //指纹相关
            case R.id.stroke_test:
                JumpUtils.jump(mContext, FingerprintMainActivity.class, null);
                break;
            case R.id.stroke_background_test:
                JumpUtils.jump(mContext, GSYVideoPlayerActivity.class, null);
                break;
            case R.id.animation:
                JumpUtils.jump(mContext, AnimationActivity.class, null);
                break;
            case R.id.GankMZ:
                JumpUtils.jump(mContext, GankMZActivity.class, null);
                break;
        }
    }


    public void onDisplaySettingButton(View view) {
        System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);//把从第二位至最后一位之间的数字复制到第一位至倒数第一位
        mHints[mHints.length - 1] = SystemClock.uptimeMillis();//从开机到现在的时间毫秒数
        if (SystemClock.uptimeMillis() - mHints[0] <= 1000) {//连续点击之间间隔小于一秒，有效
            bt_OnTrace.setVisibility(View.VISIBLE);
        }
    }
    /**
     * 看着相册里面的旧照片,仿佛就像是昨天经历过一样,一些场景历历在目,人总是喜欢念旧,但是却又向往美好的生活,但是当不经意的翻到那些旧照片的时候,心里总还是会有一些莫名的忧伤.
     * 离开故乡算算也有9年的时间了,除了节假日能够回去外,基本上都呆在外地了.很奇怪的是,离开故乡的时候总是会想念故乡的种种美好,怀念着一切.但是,当人站在故乡的土地上时,却又厌倦这里的一点一滴.
     *
     */
}
