package com.fy.baselibrary.utils.imgload;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fy.baselibrary.R;

/**
 * 图片加载工具类(目前使用 Glide)
 *
 * Created by fangs on 2017/5/5.
 */
public class ImgLoadUtils {

    private ImgLoadUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 预加载 （把指定URL地址的图片 的原始尺寸保存到缓存中）
     * @param context
     * @param url
     */
    public static void preloadImg(Context context, String url){
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .preload();
    }

    /**
     * 加载指定URL的图片(从缓存中取得)
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImg(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .error(R.mipmap.img_load_error)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    /**
     * 加载指定URL的图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .fallback(R.mipmap.img_load_default)
                .error(R.mipmap.img_load_error)
                .placeholder(R.mipmap.img_loading)
                .transform(new GlideCircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存最后一次那个image
                .into(imageView);
    }

    /**
     * 加载指定URL的图片 不要缓存
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImages(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .fallback(R.mipmap.img_load_default)
                .error(R.mipmap.img_load_error)
                .placeholder(R.mipmap.img_loading)
                .into(imageView);
    }


    /**
     * 加载指定URL的图片（不做任何缓存）圆形显示
     *  @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImg(Context context, Uri url, ImageView imageView) {

        Glide.with(context).load(url)
                .fallback(R.mipmap.img_load_default)
                .placeholder(R.mipmap.img_loading)
                .error(R.mipmap.img_load_error)
                .transform(new GlideCircleTransform(context))
                .skipMemoryCache(true)//不自动缓存到内存
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 加载指定URL的图片（不做任何缓存）圆角显示
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadRoundImg(Context context, String url, ImageView imageView){
        Glide.with(context).load(url)
                .fallback(R.mipmap.img_load_default)
                .placeholder(R.mipmap.img_loading)
                .error(R.mipmap.img_load_error)
                .transform(new GlideRoundTransform(context))
                .skipMemoryCache(true)//不自动缓存到内存
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
