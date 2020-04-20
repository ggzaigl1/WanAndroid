package com.ggz.baselibrary.retrofit.cookie;

import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 没有网时候的缓存
 */

public class OfflineCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isNetworkAvailable(ConfigUtils.getAppCtx())) {
            int offlineCacheTime = 60;//离线的时候的缓存的过期时间
            request = request.newBuilder()
//                            .cacheControl(new CacheControl
//                                    .Builder()
//                                    .maxStale(60, TimeUnit.SECONDS)
//                                    .onlyIfCached()
//                                    .build()
//                        ) 两种方式结果是一样的，写法不同
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + offlineCacheTime)
                    .build();
        }
        return chain.proceed(request);
    }
};
