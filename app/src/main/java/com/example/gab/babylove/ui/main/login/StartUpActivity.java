package com.example.gab.babylove.ui.main.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.example.gab.babylove.base.BaseActivity;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.base.ViewHolder;
import com.ggz.baselibrary.base.dialog.CommonDialog;
import com.ggz.baselibrary.base.dialog.DialogConvertListener;
import com.ggz.baselibrary.base.dialog.NiceDialog;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.permission.PermissionChecker;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 欢迎页
 *
 * @author fangs
 * @date 2017/12/12
 */
public class StartUpActivity extends BaseActivity implements IBaseActivity {

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

        permissionChecker = new PermissionChecker(this);
        permissionChecker.setTitle(getString(R.string.check_info_title));
        permissionChecker.setMessage(getString(R.string.check_info_message));

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


//        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
//            new MaterialDialog.Builder(this).title(R.string.require_acquisition)
//                    .cancelable(false)
//                    .content(R.string.check_info_message)
//                    .positiveText(R.string.next).onPositive((dialog, which) -> onPermission()).show();
//        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }

    /**
     * 结束动画
     */
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

    /**
     * 延迟200 毫秒 隐藏 加载图片控件
     * <p>
     * 该权限只能在activity里面回调成功,fragment 无法走回调
     * 首先判断是否有需要的权限,没有就检查需要的权限,申请权限
     * 有权限就进入主页
     */
    @SuppressLint("CheckResult")
    private void hideLoadView() {
        //显示欢迎页，并设置点击事件（但是不设置点击回调）
//        loadImg.setVisibility(View.VISIBLE);
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            Observable.timer(2500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            isLackPermissions();
                        }
                    });
        } else {
            Observable.timer(2500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            JumpUtils.jump(StartUpActivity.this, MainActivity.class, null);
                            StartUpActivity.this.finish();
                            StartUpActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                    });
        }
    }

    /**
     * 检查权限
     */
    public void onPermission() {
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            permissionChecker.requestPermissions();
        }
    }

    /**
     * 是否缺少权限
     */
    private void isLackPermissions() {
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            NiceDialog.init()
                    .setLayoutId(R.layout.dialog_permission)
                    .setDialogConvertListener(new DialogConvertListener() {
                        @Override
                        protected void convertView(ViewHolder holder, CommonDialog dialog) {
                            holder.setOnClickListener(R.id.positiveButton, v -> {
                                onPermission();
                                dialog.dismiss(false);
                            });
                        }
                    })
                    .setWidthPixels(30)
                    .setWidthPercent(CommonDialog.WidthPercent)
                    .show(getSupportFragmentManager());
        }
    }

    /**
     * 请求权限返回结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionChecker.PERMISSION_REQUEST_CODE:
                //权限获取成功
                if (permissionChecker.hasAllPermissionsGranted(grantResults)) {
                    JumpUtils.jump(StartUpActivity.this, MainActivity.class, null);
                    StartUpActivity.this.finish();
                    StartUpActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                } else {
                    //权限获取失败
                    permissionChecker.showDialog();
                }
                break;
        }
    }

    /**
     * 当拒绝跳转设置页面 返回到当前activity 生命周期走onRestart方法
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        isLackPermissions();
    }

    @Override
    protected void onDestroy() {
        cancelAnimation();
        super.onDestroy();
    }
}
