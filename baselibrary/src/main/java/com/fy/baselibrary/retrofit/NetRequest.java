package com.fy.baselibrary.retrofit;

import com.fy.baselibrary.application.BaseApplication;
import com.fy.baselibrary.statuslayout.RootFrameLayout;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.NetUtils;
import com.fy.baselibrary.utils.SpfUtils;
import com.fy.baselibrary.utils.T;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 网络请求入口
 * Created by fangs on 2017/8/28.
 */
public class NetRequest {

    private Builder builder;

    private volatile static NetRequest netRequest;

    private NetRequest() {
        super();
        ConstantUtils.custom_Url = SpfUtils.getSpfSaveStr("ServiceAddress");
    }

    public static NetRequest getInstens(){
        if (null == netRequest) {
            synchronized (NetRequest.class) {
                if (null == netRequest) {
                    netRequest = new NetRequest();
                }
            }
        }

        return netRequest;
    }

    /**
     * 采用 dagger2 依赖注入 框架 + retorfit 请求 后台 数据
     * @param callBack
     * @param <V>
     */
    public <V> void requestDate(Observable<V> fromNetwrok, NetCallBack<V> callBack) {

        RxRetrofitCache.load(ConstantUtils.userId + builder.getApi(),
                1000 * 60 * 10,fromNetwrok, builder.isRefresh())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {

                        if (!NetUtils.isConnected(BaseApplication.getApplication())){
                            T.showShort("似乎没有网络哦!!!");
                            callBack.updataLayout(RootFrameLayout.LAYOUT_NETWORK_ERROR_ID);
                            callBack.onComplete();
                        } else {
                            callBack.updataLayout(RootFrameLayout.LAYOUT_CONTENT_ID);
                        }
                    }
                })
                .subscribe(callBack);
    }

    public NetRequest setBuilder(Builder builder) {
        this.builder = builder;

        return this;
    }

    public static class Builder {
        String api = "";
        boolean Refresh;

        public Builder() {
        }

        public String getApi() {
            return api;
        }

        public Builder setApi(String api) {
            this.api = api;
            return this;
        }

        public boolean isRefresh() {
            return Refresh;
        }

        public Builder setRefresh(boolean refresh) {
            Refresh = refresh;
            return this;
        }

        public NetRequest create(){
            return getInstens().setBuilder(this);
        }
    }
}
