package com.example.gab.babylove.ui.main.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.example.gab.babylove.R;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.DeviceUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/4/17 0017.
 * 手機相關信息
 */

public class PhoneUtilsActivity extends AppCompatActivity implements IBaseActivity, View.OnTouchListener {

    @BindView(R.id.tv_baby)
    TextView tv_baby;
    @BindView(R.id.tv_Os)
    TextView tv_Os;
    @BindView(R.id.horizontal_view)
    HorizontalScrollView mHorizontalScrollView;
    private GestureDetector mGestureDetector;
    private TranslateAnimation mRigthToLeftAnim;
    private final static float SCOLL_V = 0.1f;
    private int BatteryN;       //目前电量
    private int BatteryV;       //电池电压
    private double BatteryT;        //电池温度
    private String BatteryStatus;   //电池状态
    private String BatteryTemp;     //电池使用情况


    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_phone_utils;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        tv_baby.post(() -> {
            mRigthToLeftAnim = new TranslateAnimation(mHorizontalScrollView.getWidth(), -tv_baby.getWidth(), 0, 0);
            mRigthToLeftAnim.setRepeatCount(Animation.INFINITE);
            mRigthToLeftAnim.setInterpolator(new LinearInterpolator());
            mRigthToLeftAnim.setDuration((long) ((mHorizontalScrollView.getWidth() + tv_baby.getWidth()) / SCOLL_V));
            tv_baby.startAnimation(mRigthToLeftAnim);
        });
        this.registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        tv_Os.setText("手機廠商:" + DeviceUtils.getDeviceMake()
                + "\n" + "手機型號:" + DeviceUtils.getSystemModel()
                + "\n" + "Android版本號:" + DeviceUtils.getDeviceVersion()
                + "\n" + "手機IMEI:" + DeviceUtils.getIMEI(this)
                + "\n" + "手機MEID：" + PhoneUtils.getMEID()
                + "\n" + "目前電量為" + BatteryN + "% --- " + BatteryStatus
                + "\n" + "電壓為" + BatteryV + "mV --- " + BatteryTemp
                + "\n" + "溫度為" + (BatteryT * 0.1) + "℃");
        mGestureDetector = new GestureDetector(new gestureListener()); //使用派生自OnGestureListener
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }

    /* 创建广播接收器 */
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            /*
             * 如果捕捉到的action是ACTION_BATTERY_CHANGED， 就运行onBatteryInfoReceiver()
             */
            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                BatteryN = intent.getIntExtra("level", 0);    //目前电量
                BatteryV = intent.getIntExtra("voltage", 0);  //电池电压
                BatteryT = intent.getIntExtra("temperature", 0);  //电池温度

                switch (intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN)) {
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        BatteryStatus = "充电状态";
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        BatteryStatus = "放电状态";
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        BatteryStatus = "未充电";
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        BatteryStatus = "充满电";
                        break;
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                        BatteryStatus = "未知道状态";
                        break;
                }

                switch (intent.getIntExtra("health", BatteryManager.BATTERY_HEALTH_UNKNOWN)) {
                    case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                        BatteryTemp = "未知错误";
                        break;
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        BatteryTemp = "状态良好";
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        BatteryTemp = "电池没有电";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        BatteryTemp = "电池电压过高";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        BatteryTemp = "电池过热";
                        break;
                }
            }
        }
    };

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
}
