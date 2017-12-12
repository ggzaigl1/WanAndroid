package com.fy.baselibrary.retrofit;

import com.fy.baselibrary.application.BaseApplication;
import com.fy.baselibrary.base.CommonDialog;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.statuslayout.RootFrameLayout;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.NetUtils;
import com.fy.baselibrary.utils.T;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 自定义Subscribe
 * Created by fangs on 2017/8/28.
 */
public abstract class NetCallBack<V> implements Observer<V> {

    private Disposable disposed;
    private IProgressDialog progressDialog;
    private CommonDialog dialog;

    public NetCallBack() {
    }

    public NetCallBack(IProgressDialog pDialog) {
        this.progressDialog = pDialog;
        init();
    }

    private void init(){
        if (null == progressDialog) return;
        dialog = progressDialog.getDialog();
        if (null == dialog) return;
        dialog.setDialogList(() -> {
            if (null != disposed && !disposed.isDisposed()) disposed.dispose();
        });
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        L.e("test---onSubscribe ", "onSubscribe()");

        this.disposed = d;
        if (null != progressDialog)progressDialog.show();
    }

    @Override
    public void onNext(V t) {
        L.e("test---onNext ", "onNext()");

        updataLayout(RootFrameLayout.LAYOUT_CONTENT_ID);
        if (null != t) {
            onSuccess(t);
        } else {
            actionResponseError("请求失败");
        }
    }

    @Override
    public void onError(Throwable e) {
        L.e("test---onError ", "onError()");
        e.printStackTrace();
        if (!NetUtils.isConnected(BaseApplication.getApplication())) {
            actionResponseError("网络不可用");
            updataLayout(RootFrameLayout.LAYOUT_NETWORK_ERROR_ID);
        } else if (e instanceof ServerException) {
            actionResponseError(e.getMessage());
            updataLayout(RootFrameLayout.REQUEST_FAIL);
        } else if (e instanceof ConnectException){
            actionResponseError("请求超时，请稍后再试...");
            updataLayout(RootFrameLayout.REQUEST_FAIL);
        } else if (e instanceof SocketTimeoutException){
            actionResponseError("服务器响应超时，请稍后再试...");
            updataLayout(RootFrameLayout.REQUEST_FAIL);
        } else {
            actionResponseError("请求失败，请稍后再试...");
            updataLayout(RootFrameLayout.REQUEST_FAIL);
        }
        dismissProgress();
    }

    @Override
    public void onComplete() {
        L.e("test---onComplete ", "onComplete()");
        dismissProgress();
    }

    /**
     * 显示提示信息
     *
     * @param msg
     */
    private void actionResponseError(String msg) {
        T.showShort(msg);
    }

    /**
     * 取消进度框
     */
    private void dismissProgress() {
        if (null != progressDialog) {
            progressDialog.close();
        }
    }


    /**
     * 请求成功 回调
     *
     * @param t 请求返回的数据
     */
    protected abstract void onSuccess(V t);

    /**
     * 更新activity 界面（多状态视图）
     * 可根据flag 判断请求失败
     *
     * @param flag
     */
    protected abstract void updataLayout(int flag);
}
