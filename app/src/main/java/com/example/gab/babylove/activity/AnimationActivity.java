package com.example.gab.babylove.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gab.babylove.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gab on 2017/12/12 0012.
 *  low 动画
 */

public class AnimationActivity extends AppCompatActivity {

    private List<String> dummyData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }

    private void setDummyData() {
        dummyData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dummyData.add("");
        }
    }
}
