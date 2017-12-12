package com.fy.baselibrary.retrofit;

import android.util.Log;

import com.fy.baselibrary.utils.ConstantUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jam on 16-6-12
 * Description: Rx 一些巧妙的处理
 */
public class RxHelper {
    /**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BeanModule<T>, T> handleResult() {

        return new ObservableTransformer<BeanModule<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<BeanModule<T>> upstream) {
                return upstream.flatMap(new Function<BeanModule<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull BeanModule<T> tBeanModule) throws Exception {
                        //获取最新的token
                        ConstantUtils.token  = tBeanModule.getResultToken();
                        if (tBeanModule.isSuccess()) {
                            return createData(tBeanModule.getResultData());
                        } else {
                            return Observable.error(new ServerException(tBeanModule.getResultMes()));
                        }
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> subscriber) throws Exception {
                try {
                    Log.e("网络", "Activity _ onNext");
                    subscriber.onNext(data);
                    subscriber.onComplete();
                } catch (Exception e) {
                    Log.e("网络", "Activity _ onError");
                    subscriber.onError(e);
                }
            }
        });
    }
}
