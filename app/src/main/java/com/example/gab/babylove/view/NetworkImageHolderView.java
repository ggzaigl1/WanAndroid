package com.example.gab.babylove.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.gab.babylove.R;
import com.ggz.baselibrary.utils.imgload.ImgLoadUtils;

/**
 *
 * @author Sai
 * @date 15/8/4
 * 轮播图 网络图片加载例子
 */
public class NetworkImageHolderView implements Holder<String> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        imageView.setImageResource(R.drawable.ic_launcher_background);
        ImgLoadUtils.loadImage(context, data, imageView);
    }
}
