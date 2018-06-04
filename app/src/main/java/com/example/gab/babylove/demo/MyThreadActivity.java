package com.example.gab.babylove.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.fy.baselibrary.application.IBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/5/30 0030.
 */
public class MyThreadActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.tv_content)
    TextView mTextView;

    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return R.layout.my_thread_activity;
    }

    @Override
    public void setStatusBar(Activity activity) {

    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {

    }

    @OnClick({R.id.button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                //步骤2:创建线程类的实例
                //创建二个线程，模拟二个窗口卖票
                MyThread mt1 = new MyThread("窗口1");
                MyThread mt2 = new MyThread("窗口2");

                //步骤3：调用start()方法开启线程
                //启动二个线程，也即是窗口，开始卖票
                mt1.start();
                mt2.start();
                //总共100张票
                MyThread1 mt = new MyThread1();
                //因为要创建二个线程，模拟二个窗口卖票
                Thread mt11 = new Thread(mt, "窗口1");
                Thread mt12 = new Thread(mt, "窗口2");
                //步骤3：调用start()方法开启线程
                //启动二个线程，也即是窗口，开始卖票
                mt11.start();
                mt12.start();
                break;
        }
    }

    @Override
    public void reTry() {

    }

    private class MyThread extends Thread {
        private int ticket = 100; //定义一个窗口有100张票
        private String name; //窗口名 线程的名字

        public MyThread(String name) {
            this.name = name;
        }

        //在run方法里复写需要进行的操作
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            while (ticket > 0) {
                ticket--;
                mTextView.setText(ticket);
                System.out.println(Thread.currentThread().getName() + name + "卖掉了1张票，剩余票数为:" + ticket);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class MyThread1 implements Runnable {

        private int ticket = 100; //定义一个窗口有100张票

        @Override
        public void run() {
            while (ticket > 100) {
                ticket--;
                System.out.println(Thread.currentThread().getName() + "卖掉了1张票，剩余票数为:"+ticket);
                try {
                    Thread.sleep(1000);//卖票速度是1s一张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
