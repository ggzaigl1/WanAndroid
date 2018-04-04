package com.example.gab.babylove.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.JumpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/3/30 0030.
 */

public class AnimationActivity extends BaseActivity {

    @BindView(R.id.image)
    ImageView mImage;
    Animation loadAnimation;

    @Override
    protected int getContentView() {
        return R.layout.activity_animation_new;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
    }

    @OnClick({R.id.scale, R.id.alpha, R.id.rotate, R.id.translate, R.id.continue_btn, R.id.continue_btn2, R.id.flash, R.id.move, R.id.change,
            R.id.layout, R.id.frame})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.scale: {
//                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
//                mImage.startAnimation(loadAnimation);

                // 整个屏幕就0.0到1.0的大小//缩放
                Animation mScaleAnimation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.INFINITE, 0.5f, Animation.INFINITE, 0.5f);
                mScaleAnimation.setDuration(1000);
                mScaleAnimation.setFillAfter(true);// #动画结束后，保持结束时的状态
                //旋转动画
                Animation mTranslateAnimation = new TranslateAnimation(-100 * 50, 0, -15 * 50, 0);// 移动
                mTranslateAnimation.setDuration(1000);

                AnimationSet mAnimationSet = new AnimationSet(false);
                mAnimationSet.addAnimation(mScaleAnimation);
                mAnimationSet.setFillAfter(true);
                mAnimationSet.addAnimation(mTranslateAnimation);
                mImage.startAnimation(mAnimationSet);
                mScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mImage.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            }

            case R.id.rotate: {
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
                mImage.startAnimation(loadAnimation);
                break;
            }

            case R.id.translate: {
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
                mImage.startAnimation(loadAnimation);
                break;
            }

            case R.id.continue_btn: {
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
                mImage.startAnimation(loadAnimation);
                final Animation loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.rotate);
                loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation arg0) {
                        // TODO Auto-generated method stub
                        mImage.startAnimation(loadAnimation2);
                    }
                });
                break;
            }

            case R.id.continue_btn2: {
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.continue_anim);
                mImage.startAnimation(loadAnimation);
                break;
            }

            case R.id.alpha: {
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                mImage.startAnimation(loadAnimation);
                break;
            }
            case R.id.move: {
                TranslateAnimation translate = new TranslateAnimation(-50, 50, 0, 0);
                translate.setDuration(1000);
                translate.setRepeatCount(Animation.INFINITE);
                translate.setRepeatMode(Animation.REVERSE);
                mImage.startAnimation(translate);
                break;
            }

            case R.id.flash: {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                alphaAnimation.setDuration(100);
                alphaAnimation.setRepeatCount(10);
                //倒序重复REVERSE  正序重复RESTART
                alphaAnimation.setRepeatMode(Animation.REVERSE);
                mImage.startAnimation(alphaAnimation);

                break;
            }

            case R.id.change: {
                jumps(mContext, ListActivity.class, null);
                break;
            }

            case R.id.layout: {
                JumpUtils.jump(mContext, ListActivity.class, null);
                break;
            }

            case R.id.frame: {
                mImage.setImageResource(R.drawable.anim_list);
                break;

            }
        }
    }

    /**
     * 跳转到指定 activity
     *
     * @param actClass
     * @param bundle
     */
    public void jumps(AppCompatActivity act, Class actClass, Bundle bundle) {
        Intent intent = new Intent(act, actClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }

        act.startActivity(intent);
        act.overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
