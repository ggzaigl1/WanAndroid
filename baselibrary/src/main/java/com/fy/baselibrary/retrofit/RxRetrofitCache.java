package com.fy.baselibrary.retrofit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jam on 16-7-6
 * Description:
 * RxJava + Retrofit 的缓存机制
 */
public class RxRetrofitCache {

    /**
     * @param cacheKey     缓存key
     * @param expireTime   过期时间 0 表示有缓存就读，没有就从网络获取
     * @param fromNetwork  从网络获取的Observable
     * @param forceRefresh 是否强制刷新
     * @param <T>
     * @return
     */
    public static <T> Observable<T> load(final String cacheKey, final long expireTime,
                                         Observable<T> fromNetwork, boolean forceRefresh) {

        Observable<T> fromCache = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> subscriber) throws Exception {
//                T cache = (T) CacheManager.readObject(cacheKey, expireTime);
//                if (cache != null) {
//                    Log.e("缓存", "Activity _ onNext");
//                    subscriber.onNext(cache);
//                }
                subscriber.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        /**
         * 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
         */
        fromNetwork = fromNetwork.map(new Function<T, T>() {
            @Override
            public T apply(@NonNull T result) throws Exception {
//                CacheManager.saveObject((Serializable) result, cacheKey);
                return result;
            }
        });

        if (forceRefresh) {
            return Observable.concat(fromCache, fromNetwork);
//            return Observable.concat(fromCache, fromNetwork).first();
        } else {
            return fromNetwork;
        }
    }
}
