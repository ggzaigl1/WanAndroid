package com.ggz.baselibrary.retrofit;

import android.content.Context;
import android.content.Intent;

import com.ggz.baselibrary.retrofit.dialog.IProgressDialog;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.statuslayout.StatusLayoutManager;
import com.ggz.baselibrary.utils.AppUtils;

/**
 * 应用主 moudle 网络请求观察者
 * 用于适配不同app的服务器 状态码不同
 *
 * @param <T>
 * @author 55204
 */
public abstract class NetCallBack<T> extends RequestBaseObserver<T> {

    public NetCallBack() {
    }

    public NetCallBack(IProgressDialog pDialog) {
        super(pDialog);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();//todo 正式发布时候 注释此打印

        if (e instanceof ServerException) {
            dismissProgress();
            //token 失效 进入登录页面
            if ("请先登录！".equals(e.getMessage())) {
                Context context = ConfigUtils.getAppCtx();
                Intent intent = new Intent();
                intent.setAction(AppUtils.getLocalPackageName() + ".login.LoginActivity");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            if (((ServerException) e).code != 401) {
                actionResponseError(e.getMessage());
            }
            updataLayout(StatusLayoutManager.REQUEST_FAIL);
        } else {
            super.onError(e);
        }
    }
}
