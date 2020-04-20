package com.ggz.baselibrary.retrofit;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;

import com.ggz.baselibrary.application.BaseActivityLifecycleCallbacks;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.LogUtils;
import com.ggz.baselibrary.utils.SpfUtils;
import com.ggz.baselibrary.utils.cache.ACache;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * RxJava + Retrofit 实现缓存机制 (目前仅适用于 不含界面跳转的数据展示，比如加载个人详情界面；见谅 ^_^)
 *
 * @author fangs
 * @date 2017/12/12
 */
public class RxNetCache {

    private volatile static RxNetCache rxNetCache;
    private Builder builder;
    private Context context;

    private RxNetCache() {
        ConstantUtils.custom_Url = SpfUtils.getSpfSaveStr("ServiceAddress");
    }

    private static RxNetCache getInstens() {
        if (null == rxNetCache) {
            synchronized (RxNetCache.class) {
                if (null == rxNetCache) {
                    rxNetCache = new RxNetCache();
                }
            }
        }

        return rxNetCache;
    }

    /**
     * 同时从缓存和网络获取请求结果
     *
     * @param fromNetwork 从网络获取的 Observable
     * @return
     */
    public <T> Observable<T> request(Observable<T> fromNetwork) {

        Observable<T> fromCache = Observable.create((ObservableOnSubscribe<T>) subscriber -> {
            ACache mCache = ACache.get(ConfigUtils.getAppCtx());
            T cache = (T) mCache.getAsObject(builder.getApi());
            if (null != cache) {
                LogUtils.e("net cache", cache.toString());
                subscriber.onNext(cache);
            }
            subscriber.onComplete();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        fromNetwork = fromNetwork.doOnNext(result -> {
            LogUtils.e("net doOnNext", result.toString());
            ACache mCache = ACache.get(ConfigUtils.getAppCtx());
            mCache.put(builder.getApi(), (Serializable) result, builder.getExpireTime());
        });

        return Observable.concat(fromCache, fromNetwork);
    }



    public RxNetCache setBuilder(Builder builder) {
        this.builder = builder;

        return this;
    }

    public static class Builder {
        //缓存key
        String api = "";
        //默认一天超时时间(单位：秒；-1：表示没有过期时间)
        int expireTime = 86400;

        public Builder() {
        }

        public String getApi() {
            return api;
        }

        public Builder setApi(String api) {
            this.api = ConstantUtils.userId + api;
            return this;
        }

        int getExpireTime() {
            return expireTime;
        }

        public Builder setExpireTime(int expireTime) {
            this.expireTime = expireTime;
            return this;
        }

        public RxNetCache create() {
            return getInstens().setBuilder(this);
        }
    }
}
