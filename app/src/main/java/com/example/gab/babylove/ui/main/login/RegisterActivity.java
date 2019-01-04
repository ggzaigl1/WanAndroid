package com.example.gab.babylove.ui.main.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;

import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.base.BaseActivitys;
import com.example.gab.babylove.entity.LoginBean;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.LogUtils;
import com.ggz.baselibrary.utils.SpfUtils;
import com.ggz.baselibrary.utils.T;
import com.ggz.baselibrary.utils.cache.ACache;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 初夏小溪
 * @date 2018/4/18 0018
 * 注册界面
 */

public class RegisterActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.et_username)
    TextInputEditText editRegisterName;
    @BindView(R.id.editRegisterPass)
    TextInputEditText editRegisterPass;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.cv_add)
    CardView mCardView;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_register_material;
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        showEnterAnimation();
    }

    @OnClick({R.id.btn_Register, R.id.fab})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Register:
                register();
                break;
            case R.id.fab:
                animateRevealClose();
                break;
            default:
                break;
        }
    }

    private void showEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.transition_register);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(transition);
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    mCardView.setVisibility(View.GONE);
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    transition.removeListener(this);
                    animateRevealShow();
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }
    }

    /**
     * 显示动画
     */
    public void animateRevealShow() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator mAnimator = ViewAnimationUtils.createCircularReveal(mCardView, mCardView.getWidth() / 2, 0, mFloatingActionButton.getWidth() / 2, mCardView.getHeight());
            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    mCardView.setVisibility(View.VISIBLE);
                    super.onAnimationStart(animation);
                }
            });
            mAnimator.start();
        }
    }

    /**
     * 关闭动画
     */
    public void animateRevealClose() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator mAnimator = ViewAnimationUtils.createCircularReveal(mCardView, mCardView.getWidth() / 2, 0, mCardView.getHeight(), mFloatingActionButton.getWidth() / 2);
            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mCardView.setVisibility(View.INVISIBLE);
                    super.onAnimationEnd(animation);
                    mFloatingActionButton.setImageResource(R.mipmap.plus);
                    RegisterActivity.super.onBackPressed();
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                }
            });
            mAnimator.start();
        }

    }

    /**
     * 注册
     */
    private void register() {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        String mUserName = editRegisterName.getText().toString().trim();
        String mPassWord = editRegisterPass.getText().toString().trim();

        Map<String, Object> param = new HashMap<>(0);
        param.put("username", mUserName);
        param.put("password", mPassWord);
        param.put("repassword", mPassWord);
        RequestUtils.create(ApiService.class)
                .getRegister(param)
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<LoginBean>() {
                    @Override
                    protected void onSuccess(LoginBean login) {
                        ACache mCache = ACache.get(ConfigUtils.getAppCtx());
                        mCache.put(ConstantUtils.userName, login);
                        SpfUtils.saveBooleanToSpf(ConstantUtils.isLogin, true);
                        SpfUtils.saveStrToSpf(ConstantUtils.userName, login.getUsername());
                        JumpUtils.jumpFade(RegisterActivity.this, MainActivity.class, null);
                        mKProgressHUD.dismiss();
                        T.showShort("注册账号成功");
                    }

                    @Override
                    protected void updataLayout(int flag) {
                        mKProgressHUD.dismiss();
                        LogUtils.e("net updataLayout", flag + "-----");
                    }
                });
    }
}
