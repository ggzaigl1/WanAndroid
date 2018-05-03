package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.CourseDetails;
import com.example.gab.babylove.entity.OrnamentalListBean;
import com.example.gab.babylove.ui.main.adapter.OrnamentalMotionAdapter;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.LogUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 运动课程 使用 SurfaceView+MediaPlayer 自定义播放器
 */
public class OrnamentalMotionActivity extends AppCompatActivity implements IBaseActivity, SurfaceHolder.Callback, View.OnClickListener, MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener {

    SurfaceHolder mHolder;
    //媒体控制 mediaPlayer
    MediaPlayer mediaPlayer;
    //是否正在播放
    boolean isPlay = false;
    //是否是第一次加载视频
    boolean isFirstLoadVideo = true;
    //是否销毁activity
    boolean isOnDestroy = false;
    //是否可见
    private boolean isPause = false;
    OrnamentalMotionAdapter mAdapter;
    private int mPosition;
    List<CourseDetails.DataBean.GroupsBean.ActionsBean> data;
    @BindView(R.id.Rl_Or)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_current)
    TextView mTvCurrent;
    @BindView(R.id.tv_total)
    TextView mTvTotal;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.surface_view)
    SurfaceView surfaceView;
    @BindView(R.id.load_bar)
    ProgressBar progressBar;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_ornamental_motion;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(this, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        initRecycle();
        OrnamentalListBean bean = (OrnamentalListBean) getIntent().getExtras().getSerializable("actionsBean");
        mPosition = getIntent().getExtras().getInt("position");
        data = bean.getData();
        mAdapter.setNewData(data.get(mPosition).getNotes());
        MediaController();
        mTvCurrent.setText(mPosition + 1 + " ");
        mTvTotal.setText(data.size() + "");
        mTvTitle.setText(data.get(mPosition).getTitle());
        if (mPosition == 0) {
            mIvLeft.setVisibility(View.GONE);
        }
        if (mPosition == data.size() - 1) {
            mIvRight.setVisibility(View.GONE);
        }
    }


    private void MediaController() {
        mHolder = surfaceView.getHolder();
        mediaPlayer = new MediaPlayer();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.height = getWidth() / 10 * 6;
        surfaceView.setLayoutParams(params);
        //设置回调参数
        mHolder.addCallback(this);
        //设置SurfaceView自己不管理的缓冲区
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //显示的分辨率,不设置为视频默认
//        mHolder.setFixedSize(320, 220);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
    }


    @SuppressLint("SetTextI18n")
    @OnClick({R.id.iv_left, R.id.iv_right})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                if (mPosition == 1) {
                    mIvLeft.setVisibility(View.GONE);
                }
                mPosition--;
                mIvRight.setVisibility(View.VISIBLE);
                mAdapter.setNewData(data.get(mPosition).getNotes());
                mTvCurrent.setText(mPosition + 1 + "");
                mTvTitle.setText(data.get(mPosition).getTitle());
                playUrl(data.get(mPosition).getLink());
                break;
            case R.id.iv_right:
                if (mPosition == data.size() - 2) {
                    mIvRight.setVisibility(View.GONE);
                }
                mPosition++;
                mIvLeft.setVisibility(View.VISIBLE);
                mAdapter.setNewData(data.get(mPosition).getNotes());
                mTvCurrent.setText(mPosition + 1 + "");
                mTvTitle.setText(data.get(mPosition).getTitle());
                playUrl(data.get(mPosition).getLink());
                break;
        }
    }

    @Override
    public void reTry() {

    }

    /**
     * 列表
     */
    private void initRecycle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new OrnamentalMotionAdapter(R.layout.ornamental_recycle_item, new ArrayList<>());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
    }

    //surfaceView创建完成
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        LogUtils.e("TAG", "surfaceCreated");
        //等surfaceView创建完成再开始播放视频
        if (!isPause) {
            playUrl(data.get(mPosition).getLink());
        } else {
            isPause = false;
            mediaPlayer.setDisplay(holder);
            if (isPlay) mediaPlayer.start();
        }
    }

    private void playUrl(String url) {
        try {
            //使mediaPlayer重新进入ide状态
            mediaPlayer.reset();
            //设置媒体类型
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //将影像输出到surfaceView
            mediaPlayer.setDisplay(mHolder);
            mediaPlayer.setDataSource(url);
            //同步准备
//            mediaPlayer.prepare();
            //因为是网络视频 这里用异步准备
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        WindowManager manager = getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    //surfaceView改变
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        LogUtils.e("TAG", "surfaceChanged");
    }

    //surfaceView销毁
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        LogUtils.e("TAG", "surfaceDestroyed");
    }

    //播放完成
    @Override
    public void onCompletion(MediaPlayer mp) {
        isPlay = false;
        Message message = new Message();
        message.what = mediaPlayer.getDuration();
        mediaPlayer.start();
    }

    //播放出错
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        isPlay = false;
        return false;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        LogUtils.e("TAG", "onBufferingUpdate" + ",percent:" + percent);
    }

    //准备完成
    @Override
    public void onPrepared(MediaPlayer mp) {
        //隐藏加载进度条
        progressBar.setVisibility(View.INVISIBLE);
        //开始播放
        mediaPlayer.start();
        //更改播放状态
        isPlay = true;
        //更改状态
        if (isFirstLoadVideo)
            isFirstLoadVideo = false;
    }

    //seekTo()是异步的方法 在此监听是否执行完毕
    @Override
    public void onSeekComplete(MediaPlayer mp) {
        LogUtils.e("TAG", "onSeekComplete");
        if (!isPlay) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }

    }

    @Override
    protected void onDestroy() {
        LogUtils.e("TAG", "onDestroy");
        isOnDestroy = true;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            isPlay = false;
        }
        mediaPlayer.release();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        LogUtils.e("TAG", "onPause");
        isPause = true;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPause && isPlay && mHolder.getSurface().isValid()) {
            isPause = false;
            mediaPlayer.start();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_left_in, R.anim.anim_slide_left_out);
    }
}