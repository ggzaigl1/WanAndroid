package com.example.gab.babylove.activity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Gab on 2017/12/12 0012.
 *  low 动画
 */

public class AnimationActivity extends BaseActivity {

    @BindView(R.id.ll_record)
    LinearLayout ll_record;
    @BindView(R.id.video_delete)
    ImageView video_delete;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_voice)
    AppCompatImageView iv_voice;
    @BindView(R.id.record_duration)
    TextView record_duration;
    private List<String> dummyData;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected int getContentView() {
        return R.layout.activity_animation;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //显示录音时间
        int duration = mediaPlayer.getDuration();
        String timeLong = duration / 1000 + "s";
        record_duration.setText(timeLong);

        //点击播放音频
        ll_record.setOnClickListener(view -> {
//            initMediaPlayer();
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                AnimationDrawable animationDrawable = (AnimationDrawable) iv_voice.getDrawable();
                animationDrawable.start();
            }
        });
        //播放完毕
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                AnimationDrawable animationDrawable = (AnimationDrawable) iv_voice.getDrawable();
                animationDrawable.stop();
            }
        });
    }

    private void initMediaPlayer(String t) {
        try {
            mediaPlayer.reset();
            File file = new File(t);
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDummyData() {
        dummyData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dummyData.add("");
        }
    }
}