package com.example.gab.babylove.ui.main.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.JumpUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 欢迎页
 * Created by fangs on 2017/12/12.
 */
public class StartUpActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.one_animation)
    LottieAnimationView mOneAnimation;
    @BindView(R.id.two_animation)
    LottieAnimationView mTwoAnimation;
    @BindView(R.id.three_animation)
    LottieAnimationView mThreeAnimation;
    @BindView(R.id.four_animation)
    LottieAnimationView mFourAnimation;
    @BindView(R.id.five_animation)
    LottieAnimationView mFiveAnimation;
    @BindView(R.id.six_animation)
    LottieAnimationView mSixAnimation;
    @BindView(R.id.seven_animation)
    LottieAnimationView mSevenAnimation;
    @BindView(R.id.eight_animation)
    LottieAnimationView mEightAnimation;
    @BindView(R.id.nine_animation)
    LottieAnimationView mNineAnimation;
    @BindView(R.id.ten_animation)
    LottieAnimationView mTenAnimation;


    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return R.layout.activity_start_up;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setTransparentBar(activity, R.color.transparent, R.color.transparent);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {

        mOneAnimation.setAnimation("W.json");
        mOneAnimation.playAnimation();
        mTwoAnimation.setAnimation("A.json");
        mTwoAnimation.playAnimation();
        mThreeAnimation.setAnimation("N.json");
        mThreeAnimation.playAnimation();
        mFourAnimation.setAnimation("A.json");
        mFourAnimation.playAnimation();
        mFiveAnimation.setAnimation("N.json");
        mFiveAnimation.playAnimation();
        mSixAnimation.setAnimation("D.json");
        mSixAnimation.playAnimation();
        mSevenAnimation.setAnimation("R.json");
        mSevenAnimation.playAnimation();
        mEightAnimation.setAnimation("I.json");
        mEightAnimation.playAnimation();
        mNineAnimation.setAnimation("O.json");
        mNineAnimation.playAnimation();
        mTenAnimation.setAnimation("D.json");
        mTenAnimation.playAnimation();

        hideLoadView();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }

    //延迟200 毫秒 隐藏 加载图片控件
    @SuppressLint("CheckResult")
    private void hideLoadView() {
        //显示欢迎页，并设置点击事件（但是不设置点击回调）
//        loadImg.setVisibility(View.VISIBLE);
        Observable.timer(2500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    JumpUtils.jump(StartUpActivity.this, MainActivity.class, null);
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                });
    }

    private void cancelAnimation() {
        if (mOneAnimation != null) {
            mOneAnimation.cancelAnimation();
        }
        if (mTwoAnimation != null) {
            mTwoAnimation.cancelAnimation();
        }
        if (mThreeAnimation != null) {
            mThreeAnimation.cancelAnimation();
        }
        if (mFourAnimation != null) {
            mFourAnimation.cancelAnimation();
        }
        if (mFiveAnimation != null) {
            mFiveAnimation.cancelAnimation();
        }
        if (mSixAnimation != null) {
            mSixAnimation.cancelAnimation();
        }
        if (mSevenAnimation != null) {
            mSevenAnimation.cancelAnimation();
        }
        if (mEightAnimation != null) {
            mEightAnimation.cancelAnimation();
        }
        if (mNineAnimation != null) {
            mNineAnimation.cancelAnimation();
        }
        if (mTenAnimation != null) {
            mTenAnimation.cancelAnimation();
        }
    }

    @Override
    protected void onDestroy() {
        cancelAnimation();
        super.onDestroy();
    }

}
