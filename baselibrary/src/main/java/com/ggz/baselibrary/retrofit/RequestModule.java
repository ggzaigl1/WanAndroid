package com.ggz.baselibrary.retrofit;

import android.text.TextUtils;

import com.ggz.baselibrary.retrofit.cookie.AddCookiesInterceptor;
import com.ggz.baselibrary.retrofit.cookie.ReceivedCookiesInterceptor;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.ConstantUtils;
import com.ggz.baselibrary.utils.LogUtils;
import com.ggz.baselibrary.utils.security.SSLUtil;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 提供依赖对象的实例
 *
 * @author fangs
 * @date 2017/5/15
 */
@Module
public class RequestModule {

    @Singleton
    @Provides
    Retrofit getService(RxJava2CallAdapterFactory callAdapterFactory, GsonConverterFactory
            gsonConverterFactory, OkHttpClient client) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(ConfigUtils.getBaseUrl())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    RxJava2CallAdapterFactory getCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    GsonConverterFactory getGsonConvertFactory() {
        return GsonConverterFactory.create(new GsonBuilder()
                .setLenient()// json宽松
                .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                .serializeNulls() //智能null
                .setPrettyPrinting()// 调教格式
                .disableHtmlEscaping() //默认是GSON把HTML 转义的
                .create());
//        return DES3GsonConverterFactory.create();//使用 自定义 GsonConverter
    }

    @Singleton
    @Provides
    OkHttpClient getClient(HttpLoggingInterceptor interceptor, Interceptor header) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(ConstantUtils.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .readTimeout(ConstantUtils.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .writeTimeout(ConstantUtils.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .addInterceptor(new ReceivedCookiesInterceptor())
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(header)
                .addNetworkInterceptor(interceptor)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        //强行返回true 即验证成功
                        return true;
                    }
                }).protocols(Collections.singletonList(Protocol.HTTP_1_1));
        InputStream is = new Buffer().writeUtf8(ConfigUtils.getCer()).inputStream();
        SSLSocketFactory sslSocketFactory = SSLUtil.getSSLSocketFactory(is);
        if (!TextUtils.isEmpty(ConfigUtils.getCer()) && null != sslSocketFactory) {
            builder.sslSocketFactory(sslSocketFactory);
        }

        return builder.build();
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor getResponseIntercept() {
        return new HttpLoggingInterceptor(message -> {
            LogUtils.e("net 请求or响应", message);
//                FileUtils.fileToInputContent("log", "日志.txt", message);
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    protected Interceptor getHeader() {
        return chain -> {
            Response response = null;

            //获取request
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "multipart/form-data;charse=UTF-8")
//                        .addHeader("Accept-Encoding", "gzip, deflate")//根据服务器要求添加（避免重复压缩乱码）
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Accept", "application/json")
                    .addHeader("Cookie", "add cookies here")
                    .build();

            //获取request的创建者builder
            Request.Builder builder = request.newBuilder();

            //从request中获取headers，通过给定的键url_name
            List<String> headerValues = request.headers("url_name");
            if (headerValues != null && headerValues.size() > 0) {
                //如果有这个header，先将配置的header删除，因为 此header仅用作app和okhttp之间使用
                builder.removeHeader("url_name");

                //匹配获得新的BaseUrl
                String headerValue = headerValues.get(0);
                HttpUrl newBaseUrl = null;
                if ("user".equals(headerValue) && !TextUtils.isEmpty(ConstantUtils.custom_Url)) {
                    newBaseUrl = HttpUrl.parse(ConstantUtils.custom_Url);

                    //从request中获取原有的HttpUrl实例oldHttpUrl
                    HttpUrl oldHttpUrl = request.url();
                    //重建新的HttpUrl，修改需要修改的url部分
                    HttpUrl newFullUrl = oldHttpUrl
                            .newBuilder()
                            .scheme(newBaseUrl.scheme())
                            .host(newBaseUrl.host())
                            .port(newBaseUrl.port())
                            .build();

                    //重建这个request，通过builder.url(newFullUrl).build()；
                    //然后返回一个response至此结束修改
                    response = chain.proceed(builder.url(newFullUrl).build());
                }
            }

            if (null == response) {
                Request.Builder requestBuilder = request.newBuilder();

                Request newRequest = requestBuilder.build();
                response = chain.proceed(newRequest);
            }

            return response;
        };
    }
}