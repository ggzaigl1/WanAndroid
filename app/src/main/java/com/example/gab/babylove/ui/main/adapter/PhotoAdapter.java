package com.example.gab.babylove.ui.main.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.bigkoo.alertview.AlertView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.OrListBean;
import com.example.gab.babylove.utils.ImgUtils;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.ToastUtils;
import com.ggz.baselibrary.utils.imgload.ImgLoadUtils;
import com.ggz.baselibrary.utils.media.UpdateMedia;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by QKun on 2017/12/28.
 * 滑动查看图片
 */

public class PhotoAdapter extends PagerAdapter {

    private OrListBean imageUrls;
    private AppCompatActivity activity;

    public PhotoAdapter(OrListBean imageUrls, AppCompatActivity activity) {
        this.imageUrls = imageUrls;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        String url = imageUrls.getData().get(position).getUrl();
        PhotoView photoView = new PhotoView(activity);
        ImgLoadUtils.loadImage(activity, url, photoView);
        container.addView(photoView);
        //点击图片关闭
        photoView.setOnPhotoTapListener((view, x, y) -> JumpUtils.exitActivity(activity));
        //长按图片保存
        photoView.setOnLongClickListener(v -> {
            new AlertView.Builder().setContext(activity)
                    .setStyle(AlertView.Style.ActionSheet)
                    .setMessage(null)
                    .setCancelText(activity.getString(R.string.cancel))
                    .setDestructive("保存到相册中")
                    .setOthers(null)
                    .setOnItemClickListener((o, position1) -> {
                        switch (position1) {
                            case 0:
                                GetPic(url);
                                break;
                        }
                    })
                    .build()
                    .show();
            return false;
        });
        return photoView;
    }

    @SuppressLint("CheckResult")
    private void GetPic(String url) {
        Observable.just(1)
                .map(integer ->
                        Glide.with(activity)
                                .load(url)
                                .asBitmap()
                                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                .get())
                .map(ImgUtils::saveImageToGallery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(file -> {
                    if (null != file) {
                        //保存图片后发送广播通知更新数据库
                        UpdateMedia.scanMedia(activity, Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, file);
                        ToastUtils.showShort("保存成功!");
                    } else {
                        ToastUtils.showShort("保存失败");
                    }
                });
    }

    @Override
    public int getCount() {
        return imageUrls != null ? imageUrls.getData().size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

}
