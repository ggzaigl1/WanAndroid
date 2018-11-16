package com.example.gab.babylove.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.LinearLayout;

import com.example.gab.babylove.MainActivity;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.ui.main.login.StartUpActivity;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.base.ViewHolder;
import com.ggz.baselibrary.base.dialog.CommonDialog;
import com.ggz.baselibrary.base.dialog.DialogConvertListener;
import com.ggz.baselibrary.base.dialog.NiceDialog;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.KeyBoardUtils;
import com.ggz.baselibrary.utils.SnackbarUtil;
import com.ggz.baselibrary.utils.T;
import com.ggz.baselibrary.utils.permission.PermissionChecker;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * @author 初夏小溪
 * @date 2018/10/15 0015
 */
public class BaseActivity extends AppCompatActivity implements IBaseActivity {

    protected KProgressHUD mKProgressHUD;
    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return 0;
    }

    @Override
    public void setStatusBar(Activity activity) {
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }


    /**
     * 收藏
     * @param view
     * @param id
     */
    @SuppressLint("CheckResult")
    protected void collectArticle(View view, int id) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getCollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        mKProgressHUD.dismiss();
                        Snackbar.make(view, R.string.collection_success, Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
//                        T.showShort(getString(R.string.collection_success));
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


    /**
     * 取消收藏
     *
     * @param id
     */
    @SuppressLint("CheckResult")
    protected void unCollectArticle(int id) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .unCollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        T.showShort(getString(R.string.cancel_collection_success));
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mKProgressHUD != null) {
            mKProgressHUD.dismiss();
        }
    }

    @Override
    public void finish() {
        super.finish();
        //重写 Activity 的 finish ⽅法, 并调⽤ overridePendingTransition ⽅法，解决退出动画⽆效
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        KeyBoardUtils.closeKeyBoard(this);
    }
}
