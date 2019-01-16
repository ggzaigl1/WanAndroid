package com.example.gab.babylove.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.KeyBoardUtils;
import com.ggz.baselibrary.utils.NetworkUtils;
import com.ggz.baselibrary.utils.T;
import com.ggz.baselibrary.utils.permission.PermissionChecker;
import com.kaopiz.kprogresshud.KProgressHUD;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * @author 初夏小溪
 * @date 2018/10/15 0015
 */
public class BaseActivity extends AppCompatActivity implements IBaseActivity {

    protected KProgressHUD mKProgressHUD;
    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE
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
        if (!NetworkUtils.isNetworkAvailable(ConfigUtils.getAppCtx())) {
            T.showShort("好像没有网络耶~");
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }


    /**
     * 收藏
     *
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
                        Snackbar.make(view, R.string.collection_success, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
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
