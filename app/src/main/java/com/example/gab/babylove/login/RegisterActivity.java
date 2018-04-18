package com.example.gab.babylove.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.LoginBean;
import com.fy.baselibrary.application.BaseApp;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RequestUtils;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.cache.ACache;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/4/18 0018.
 * 注册界面
 */

public class RegisterActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.editRegisterName)
    TextInputEditText editRegisterName;

    @BindView(R.id.tilRegisterPass)
    TextInputLayout tilRegisterPass;
    @BindView(R.id.editRegisterPass)
    TextInputEditText editRegisterPass;

    @BindView(R.id.tilRegisterPass2)
    TextInputLayout tilRegisterPass2;
    @BindView(R.id.editRegisterPass2)
    TextInputEditText editRegisterPass2;

    @BindView(R.id.btn_Register)
    Button btn_Register;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_register;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_Register})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Register:
                register();
                break;
        }
    }

    @Override
    public void reTry() {

    }

    private void register() {
        IProgressDialog progressDialog = new IProgressDialog().init(this)
                .setDialogMsg(R.string.register_loading);

        String mUserName = editRegisterName.getText().toString().trim();
        String mPassWord = editRegisterPass.getText().toString().trim();

        Map<String, Object> param = new HashMap<>();
        param.put("username", mUserName);
        param.put("password", mPassWord);
        param.put("repassword", mPassWord);
        RequestUtils.create(ApiService.class)
        .getRegister(param)
                .compose(RxHelper.handleResult())
//                .doOnSubscribe(RequestUtils::addDispos)
                .subscribe(new NetCallBack<LoginBean>(progressDialog) {
                    @Override
                    protected void onSuccess(LoginBean login) {
                        ACache mCache = ACache.get(BaseApp.getAppCtx());
                        mCache.put(ConstantUtils.userName, login);

                        SpfUtils.saveBooleanToSpf(ConstantUtils.isLogin, true);
                        SpfUtils.saveStrToSpf(ConstantUtils.userName, login.getUsername());

                        Bundle bundle = new Bundle();
                        bundle.putString("LoginBean", mCache.getAsString("User_Name"));
                        JumpUtils.jump(RegisterActivity.this, MainActivity.class, bundle);
                    }

                    @Override
                    protected void updataLayout(int flag) {
                        L.e("net updataLayout", flag + "-----");
                    }
                });
    }
}
