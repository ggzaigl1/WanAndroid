package com.ggz.baselibrary.utils.imgload;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.ggz.baselibrary.R;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 图片加载工具类(目前使用 Glide)
 * <p>
 *
 * @author fangs
 * @date 2017/5/5
 */
public class ImgLoadUtils {

    private ImgLoadUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 加载指定URL的图片
     *
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fallback(R.mipmap.icon_load_error)
                .error(R.mipmap.icon_load_error)
                .placeholder(R.mipmap.icon_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context)
                .load(url)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);
    }

    /**
     * 加载指定URL的图片 不要缓存
     *
     * @param url
     * @param imageView
     */
    public static void loadImages(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fallback(R.mipmap.icon_load_error)
                .error(R.mipmap.icon_load_error)
                .placeholder(R.mipmap.icon_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载圆形 图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircularBead(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fallback(R.mipmap.icon_load_error)
                .error(R.mipmap.icon_load_error)
                .placeholder(R.mipmap.icon_placeholder)
                .circleCrop();

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 预加载 （把指定URL地址的图片 的原始尺寸保存到缓存中）
     *
     * @param url
     */
    public static void preloadImg(Context context, String url) {
        Glide.with(context)
                .load(url)
                .preload();
    }

    /**
     * 异步获取 glide 缓存在磁盘的图片
     *
     * @param context
     * @param url
     * @param consumer
     */
    @SuppressLint("CheckResult")
    public static void getImgCachePath(Context context, String url, Consumer<File> consumer) {
        Observable.just(url)
                .map(url1 -> {
                    FutureTarget<File> target = Glide.with(context)
                            .asFile()
                            .load(url1)
                            .submit();

                    return target.get();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }
}
