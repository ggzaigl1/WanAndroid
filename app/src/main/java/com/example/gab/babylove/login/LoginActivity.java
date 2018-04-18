package com.example.gab.babylove.login;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.example.gab.babylove.utils.Util;
import com.fy.baselibrary.application.BaseApplication;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.ArticleBean;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.retrofit.BeanModule;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.startactivity.StartActivity;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.cache.ACache;
import com.fy.baselibrary.utils.permission.PermissionChecker;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录
 * Created by fangs on 2017/12/12.
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.cBoxPass)
    CheckBox cBoxPass;
    @BindView(R.id.extInputLayout)
    TextInputLayout iLayoutPass;

    @BindView(R.id.editName)
    TextInputEditText editName;
    @BindView(R.id.editPass)
    TextInputEditText editPass;

    private long exitTime = 0;
    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    protected void setStatusBarType() {
        MdStatusBarCompat.setImageTransparent(this);
    }

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        permissionChecker = new PermissionChecker(mContext);
        permissionChecker.setTitle(getString(R.string.check_info_title));
        permissionChecker.setMessage(getString(R.string.check_info_message));
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            new MaterialDialog.Builder(this).title("需要获取以下权限")
                    .content("通过相机权限和电话权限来进行拍照和确定本机设备ID,已保证为您个性化推荐内容;")
                    .positiveText(R.string.next).onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    onPermission();
                }
            }).show();
        } else {

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
                    iLayoutPass.setError("密码不符合规则!!!");
                } else {
                    if (null != iLayoutPass.getError()) {
                        iLayoutPass.setError(null);
                    }
                }
            }
        });
    }


    /**
     * 检查权限
     */
    private void onPermission() {
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            permissionChecker.requestPermissions();
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        onPermission();
    }

    /**
     * 请求权限返回结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @android.support.annotation.NonNull String[] permissions, @android.support.annotation.NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionChecker.PERMISSION_REQUEST_CODE:
                //权限获取成功
                if (permissionChecker.hasAllPermissionsGranted(grantResults)) {
                } else {
                    //权限获取失败
                    permissionChecker.showDialog();
                }
                break;
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }


    @OnClick({R.id.tvLogin})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvLogin:
                login();
//                JumpUtils.jump(mContext, MainActivity.class, null);
                break;
        }
    }

    private void login() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.user_login);

        String mUserName = editName.getText().toString().trim();//"ggzaigl1"
        String mPassWord = editPass.getText().toString().trim();//"tmdligen"

        Map<String, Object> param = new HashMap<>();
        param.put("username", mUserName);
        param.put("password", mPassWord);

        mConnService.getLogin(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetCallBack<BeanModule<LoginBean>>(progressDialog) {
                    @Override
                    protected void onSuccess(BeanModule<LoginBean> login) {
                        if (login.isSuccess()) {
                            ACache mCache = ACache.get(BaseApplication.getApplication());
                            mCache.put(ConstantUtils.userName, login);

                            SpfUtils.saveBooleanToSpf(ConstantUtils.isLogin, true);
                            SpfUtils.saveStrToSpf(ConstantUtils.userName, login.getData().getUsername());
                            Bundle bundle = new Bundle();
                            bundle.putString("LoginBean", mCache.getAsString("User_Name"));
                            JumpUtils.jump(mContext, MainActivity.class, null);
                            finish();
                        } else {
                            T.showShort(login.getErrorMsg());
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
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

//    //退出程序
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if ((System.currentTimeMillis() - exitTime) >= 2000) {
//                Util.CustomToast.INSTANCE.showToast(mContext, R.string.exit_app);
//                exitTime = System.currentTimeMillis();
//            } else {
//                finish();
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
