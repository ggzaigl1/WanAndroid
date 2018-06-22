package com.example.gab.babylove.ui.main.activity;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import com.example.gab.babylove.ui.main.adapter.GuideAdapter;
import com.example.gab.babylove.ui.main.login.LoginActivity;
import com.fy.baselibrary.utils.JumpUtils;

/**
 *
 * @author Gab
 * @date 2018/1/8 0008
 * 引导图
 */

public class GuideActivity extends AppCompatActivity {

    private ImageView[] imgViews;
    private Boolean exit = false;
    private ComponentName mDefault;
    private ComponentName mDoublel1;
    private ComponentName mDoublel2;
    private PackageManager mPackageManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(null);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        mDefault = getComponentName();
        mDoublel1 = new ComponentName(getBaseContext(), "com.example.gab.babylove.Test1");
        mDoublel2 = new ComponentName(getBaseContext(), "com.example.gab.babylove.Test2");
        mPackageManager = getApplicationContext().getPackageManager();
        changeIcon1();
        changeIcon2();
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
            new Handler().postDelayed(() -> {
                if (exit) {
                    finish();
                } else {
                    JumpUtils.jump(GuideActivity.this, LoginActivity.class, null);
                    finish();
                }
            }, 500);
        }
    }

    /**
     * 动态更换icon图标
     **/
    public void changeIcon1() {
        disableComponent(mDefault);
        disableComponent(mDoublel2);
        enableComponent(mDoublel1);
    }

    public void changeIcon2() {
        disableComponent(mDefault);
        disableComponent(mDoublel1);
        enableComponent(mDoublel2);
    }

    private void enableComponent(ComponentName componentName) {
        mPackageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void disableComponent(ComponentName componentName) {
        mPackageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    //显示引导图
    @SuppressWarnings("deprecation")
    private void initLayout() {
        ViewPager viewPager = findViewById(R.id.viewPager);
        int[] img = new int[]{R.mipmap.bg, R.mipmap.bg, R.mipmap.bg};

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
/**
 *  调休半天 做西餐 意面 蜡烛 轻音乐 红酒 薯条 蒜蓉鸡翅
 *
 *  需要购买鸡翅 牛排 薯条 意面 番茄酱
 *
 */
}


