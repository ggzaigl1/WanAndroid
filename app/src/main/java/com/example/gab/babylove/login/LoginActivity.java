package com.example.gab.babylove.login;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.permission.PermissionChecker;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * Created by fangs on 2017/12/12.
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.cBoxPass)
    CheckBox cBoxPass;
    @BindView(R.id.tvLoginTitle)
    TextView tvLoginTitle;
    @BindView(R.id.editUser)
    EditText mUserNameEdt;
    @BindView(R.id.editPass)
    EditText mPassword;
    String user_name, password;
    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
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
        if (!permissionChecker.isLackPermissions(PERMISSIONS)) {
        } else {
            new MaterialDialog.Builder(this).title("需要获取以下权限")
                    .content("通过相机权限和电话权限来进行拍照和确定本机设备ID,已保证为您个性化推荐内容;")
                    .positiveText(R.string.next).onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    onPermission();
                }
            }).show();
        }
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
//        //动态判断权限
//        startActivityForResult(new Intent(this, PermissionActivity.class).putExtra(PermissionActivity.KEY_PERMISSIONS_ARRAY,
//                new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_PHONE_STATE})
//                ,PermissionActivity.CALL_BACK_PERMISSION_REQUEST_CODE);

        Spannable sp = new SpannableString(getString(R.string.loginTitle));
        sp.setSpan(new AbsoluteSizeSpan(20, true), 0, 11, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.myTxtColor)), 0, 11, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(14, true), 11, sp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.myTxtColor)),
                11, sp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tvLoginTitle.setText(sp);

        String userName = SpfUtils.getSpfSaveStr("UserID");
        if (!TextUtils.isEmpty(userName)) {
            mUserNameEdt.setText(userName);
            mUserNameEdt.setSelection(userName.length());
            cBoxPass.setChecked(true);
        }

//        cBoxPass.setChecked(true);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PermissionActivity.CALL_BACK_PERMISSION_REQUEST_CODE) {
//            switch (resultCode) {
//                case PermissionActivity.CALL_BACK_RESULT_CODE_SUCCESS:
//                    T.showShort("0");
//                    break;
//                case PermissionActivity.CALL_BACK_RESULE_CODE_FAILURE:
//                    T.showShort("1");
//                    break;
//            }
//        }
//    }

    @OnClick({R.id.tvLogin, R.id.tvLoginTitle})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvLogin:
                JumpUtils.jump(mContext, MainActivity.class, null);
                finish();
//                user_name = mUserNameEdt.getText().toString().trim();
//                password = mPassword.getText().toString().trim();
//                runLogin(user_name, password);
                break;
            case R.id.tvLoginTitle:
                new MaterialDialog.Builder(this).title("需要获取以下权限").content("通过相机权限和电话权限来进行拍照和确定本机设备ID,已保证为您个性化推荐内容;")
                        .positiveText("下一步").onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        permissionChecker = new PermissionChecker(mContext);
                        permissionChecker.setTitle(getString(R.string.check_info_title));
                        permissionChecker.setMessage(getString(R.string.check_info_message));
                        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
                            permissionChecker.requestPermissions();
                        } else {
                            android.os.Process.killProcess(android.os.Process.myPid());  //获取PID
                            System.exit(0);
                        }
                    }
                }).show();
                break;
        }
    }

    private boolean checkInput() {
        if (user_name.length() < 1) {
            T.showShort("用户名错误");
            mUserNameEdt.requestFocus();
            InputMethodManager imm = (InputMethodManager) mUserNameEdt.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            return false;
        }
        if (password.isEmpty()) {
            T.showShort("密码不能为空");
            mPassword.requestFocus();
            InputMethodManager imm = (InputMethodManager) mPassword.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            return false;
        }

        return true;
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
