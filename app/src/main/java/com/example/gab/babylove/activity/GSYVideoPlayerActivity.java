package com.example.gab.babylove.activity;

import android.os.Bundle;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Gab on 2018/3/15 0015.
 */

public class GSYVideoPlayerActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_gsy_video;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        IjkMediaPlayer mediaPlayer = new IjkMediaPlayer();
    }
}
