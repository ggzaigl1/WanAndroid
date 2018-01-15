package com.example.gab.babylove.activity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.adapter.GuideAdapter;
import com.example.gab.babylove.login.LoginActivity;
import com.fy.baselibrary.utils.JumpUtils;

/**
 * Created by Gab on 2018/1/8 0008.
 * 引导图
 */

public class GuideActivity extends AppCompatActivity {

    private ImageView[] imgViews;
    private Boolean exit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(null);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
//        if (!isTaskRoot()) {
//            Intent intent = getIntent();
//            String action = intent.getAction();
//            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action != null && action.equals(Intent.ACTION_MAIN)) {
//                finish();
//                return;
//            }
//        }
        if (!isTaskRoot()) {
            finish();
            return;
        }
        SharedPreferences mPerferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean showImage = mPerferences.getBoolean("showImage", true);
        if (showImage) {
            initLayout();
            SharedPreferences.Editor mEditor = mPerferences.edit();
            mEditor.putBoolean("showImage", false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                mEditor.apply();
            }
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (exit) {
                        finish();
                    } else {
                        JumpUtils.jump(GuideActivity.this, LoginActivity.class, null);
                        finish();
                    }
                }
            }, 500);
        }
    }

    //显示引导图
    @SuppressWarnings("deprecation")
    private void initLayout() {
        ViewPager viewPager = findViewById(R.id.viewPager);
        int[] img = new int[]{R.mipmap.loading, R.mipmap.loading, R.mipmap.loading};

        imgViews = new ImageView[img.length];
        for (int i = 0; i < imgViews.length; i++) {
            ImageView imageView = new ImageView(this);
            imgViews[i] = imageView;
            imageView.setBackgroundResource(img[i]);
        }
        final Button entry = findViewById(R.id.entry);
        entry.setOnClickListener(arg0 -> {
            JumpUtils.jump(GuideActivity.this, LoginActivity.class, null);
            finish();
        });
        viewPager.setAdapter(new GuideAdapter(imgViews));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                for (int i = 0; i < imgViews.length; i++) {
                    if (i == arg0) {
                        if (i == (imgViews.length - 1)) {
                            entry.setVisibility(View.VISIBLE);
                        } else {
                            entry.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        exit = true;
        super.onBackPressed();
    }

}
