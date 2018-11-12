package com.example.gab.babylove.ui.main.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Explode;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.LoginBean;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.SpfUtils;
import com.ggz.baselibrary.utils.cache.ACache;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 *
 * @author 初夏小溪
 * @date 2017/12/12
 * https://github.com/fanrunqi/MaterialLogin
 */
public class LoginActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.extInputLayout)
    TextInputLayout iLayoutPass;
    @BindView(R.id.editName)
    TextInputEditText editName;
    @BindView(R.id.editPass)
    TextInputEditText editPass;
    @BindView(R.id.fab_register)
    FloatingActionButton mFloatingActionButton;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_login_material;
    }

    @Override
    public void setStatusBar(Activity activity) {
//        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(500);
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
        }
        editPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = editPass.getText().toString().trim();
                if (!TextUtils.isEmpty(text) && text.length() > 12) {
                    iLayoutPass.setError(getString(R.string.correct_password));
                } else {
                    if (null != iLayoutPass.getError()) {
                        iLayoutPass.setError(null);
                    }
                }
            }
        });
    }

    @OnClick({R.id.bt_Login, R.id.fab_register})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_Login:
                login();
                break;
            case R.id.fab_register:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(null);
                    getWindow().setEnterTransition(null);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, mFloatingActionButton, mFloatingActionButton.getTransitionName());
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class), options.toBundle());
                }
                break;
            default:
                break;
        }
    }

    private void login() {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        String mUserName = editName.getText().toString().trim();//"ggzaigl1"
        String mPassWord = editPass.getText().toString().trim();//"tmdligen"
        Map<String, Object> param = new HashMap<>();
        param.put("username", mUserName);
        param.put("password", mPassWord);
        RequestUtils.create(ApiService.class)
                .getLogin(param)
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<LoginBean>() {
                    @Override
                    protected void onSuccess(LoginBean login) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ACache mCache = ACache.get(ConfigUtils.getAppCtx());
                            mCache.put(ConstantUtils.userName, login);
                            SpfUtils.saveBooleanToSpf(ConstantUtils.isLogin, true);
                            SpfUtils.saveStrToSpf(ConstantUtils.userName, login.getUsername());
                            Bundle bundle = new Bundle();
                            bundle.putString("LoginBean", mCache.getAsString("User_Name"));
                            Explode explode = new Explode();
                            explode.setDuration(500);
                            getWindow().setExitTransition(explode);
                            getWindow().setEnterTransition(explode);
                            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
                            JumpUtils.jumpFade(LoginActivity.this, MainActivity.class, activityOptionsCompat.toBundle());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent, activityOptionsCompat.toBundle());
                            mKProgressHUD.dismiss();
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {
                        mKProgressHUD.dismiss();
                    }
                });
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onRestart() {
        super.onRestart();
        mFloatingActionButton.setVisibility(View.GONE);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onResume() {
        super.onResume();
        mFloatingActionButton.setVisibility(View.VISIBLE);
    }

    /**
     * 点击空白位置 隐藏软键盘
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
