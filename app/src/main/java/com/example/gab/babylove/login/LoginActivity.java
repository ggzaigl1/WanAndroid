package com.example.gab.babylove.login;

import android.content.Context;
import android.os.Bundle;
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

import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.example.gab.babylove.widget.AppLoading;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.NetRequest;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.TransfmtUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Spannable sp = new SpannableString(getString(R.string.loginTitel)) ;
        sp.setSpan(new AbsoluteSizeSpan(20,true),0,11,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.myTxtColor)),
                0,11,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        sp.setSpan(new AbsoluteSizeSpan(14,true),11,sp.length(),Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.myTxtColor)),
                11,sp.length(),Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tvLoginTitle.setText(sp);

        String userName = SpfUtils.getSpfSaveStr("UserID");
        if (!TextUtils.isEmpty(userName)) {
            mUserNameEdt.setText(userName);
            mUserNameEdt.setSelection(userName.length());
            cBoxPass.setChecked(true);
        }

//        cBoxPass.setChecked(true);
    }

    @OnClick({R.id.tvLogin})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.tvLogin:
//                JumpUtils.jump(mContext, MainActivity.class, null);
                user_name = mUserNameEdt.getText().toString().trim();
                password = mPassword.getText().toString().trim();
                runLogin(user_name, password);
                break;
        }
    }

    /**
     * 登陆请求
     *
     * @param UserID
     * @param PassWd
     */
    private void runLogin(String UserID, String PassWd) {
        if (!checkInput()) {
            return;
        }
        AppLoading.show(mContext);
//        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.user_login);
        String key = System.currentTimeMillis() + "";
        String passMd5 = TransfmtUtils.getMD5(PassWd);
        String sign = TransfmtUtils.getMD5(UserID + key + passMd5);

        Map<String, Object> params = new HashMap<>();
        params.put("UserID", UserID);
        params.put("PassWd", passMd5);
        params.put("sign", sign);
        params.put("key", key);

        new NetRequest.Builder()
                .create()
                .requestDate(mConnService.DocAndNurse(params).compose(RxHelper.handleResult()),
                        new NetCallBack<ArrayList<LoginBean>>() {
                            @Override
                            public void onSuccess(ArrayList<LoginBean> bean) {
                                L.e("login_成功");
                                AppLoading.close();
                                if (bean != null && bean.size() > 0) {
                                    if (cBoxPass.isChecked()) {//是否保存账号
                                        SpfUtils.saveStrToSpf("UserID", UserID);
                                    } else {
                                        SpfUtils.saveStrToSpf("UserID", "");
                                    }

                                    LoginBean loginBean = bean.get(0);

                                    //判断是否是 医生登录
                                    if (!loginBean.getIsDoc().equals("1")){
                                        T.showLong("请使用正确的账号登录系统");
                                        return;
                                    }

                                    ConstantUtils.userId = loginBean.getUserID();
                                    ConstantUtils.DeptID = loginBean.getDeptID();
                                    ConstantUtils.UserName = loginBean.getUserName();

                                    mCache.put("token", ConstantUtils.token);
                                    mCache.put("UserName", loginBean);
                                    JumpUtils.jump(mContext, MainActivity.class, null);
                                    finish();
                                }
                            }

                            @Override
                            public void updataLayout(int flag) {
                                L.e("login_失败");
                            }
                        });
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
