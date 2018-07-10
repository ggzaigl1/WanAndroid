package com.example.gab.babylove.ui.main.login;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Explode;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.LoginBean;
import com.ggz.baselibrary.application.BaseApp;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.BeanModule;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.statusbar.MdStatusBar;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.SpfUtils;
import com.ggz.baselibrary.utils.ToastUtils;
import com.ggz.baselibrary.utils.cache.ACache;
import com.ggz.baselibrary.utils.permission.PermissionChecker;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录
 *
 * @author 初夏小溪
 * @date 2017/12/12
 * https://github.com/fanrunqi/MaterialLogin
 */
public class LoginActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.extInputLayout)
    TextInputLayout iLayoutPass;
    @BindView(R.id.editName)
    TextInputEditText editName;
    @BindView(R.id.editPass)
    TextInputEditText editPass;
    @BindView(R.id.fab_register)
    FloatingActionButton mFloatingActionButton;

    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

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
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(500);
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
        }
        permissionChecker = new PermissionChecker(this);
        permissionChecker.setTitle(getString(R.string.check_info_title));
        permissionChecker.setMessage(getString(R.string.check_info_message));
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            new MaterialDialog.Builder(this).title(R.string.require_acquisition)
                    .content(R.string.default_always_message)
                    .positiveText(R.string.next).onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    LoginActivity.this.onPermission();
                }
            }).show();
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

    @Override
    public void reTry() {

    }

    /**
     * 检查权限
     */
    private void onPermission() {
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            permissionChecker.requestPermissions();
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
                } else {
                    //权限获取失败
                    permissionChecker.showDialog();
                }
                break;
            default:
                break;
        }
    }

    private void login() {
//        new SpotsDialog(this, R.style.Custom).show();
        SpotsDialog dialog = new SpotsDialog(this);
        dialog.show();
//        IProgressDialog progressDialog = new IProgressDialog().init(this).setDialogMsg(R.string.user_login);

        String mUserName = editName.getText().toString().trim();//"ggzaigl1"
        String mPassWord = editPass.getText().toString().trim();//"tmdligen"

        Map<String, Object> param = new HashMap<>();
        param.put("username", mUserName);
        param.put("password", mPassWord);

        RequestUtils.create(ApiService.class)
                .getLogin(param)
                .subscribeOn(Schedulers.io())//在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())//回到主线程去处理请求结果
                .subscribe(new NetCallBack<BeanModule<LoginBean>>() {
                    @Override
                    protected void onSuccess(BeanModule<LoginBean> login) {
                        if (login.isSuccess()) {
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                ACache mCache = ACache.get(BaseApp.getAppCtx());
                                mCache.put(ConstantUtils.userName, login);

                                SpfUtils.saveBooleanToSpf(ConstantUtils.isLogin, true);
                                SpfUtils.saveStrToSpf(ConstantUtils.userName, login.getData().getUsername());
                                Bundle bundle = new Bundle();
                                bundle.putString("LoginBean", mCache.getAsString("User_Name"));
                                Explode explode = new Explode();
                                explode.setDuration(500);
                                getWindow().setExitTransition(explode);
                                getWindow().setEnterTransition(explode);
                                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
                                JumpUtils.jump(LoginActivity.this, MainActivity.class, activityOptionsCompat.toBundle());
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent, activityOptionsCompat.toBundle());
                                dialog.dismiss();
                            }
                        } else {
                            ToastUtils.showShort(login.getErrorMsg());
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {
                        dialog.dismiss();
                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onPermission();
        mFloatingActionButton.setVisibility(View.GONE);
    }

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
