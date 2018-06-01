package com.example.gab.babylove.web;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 初夏小溪 on 2018/6/1 0001.
 */
public class MJavascriptInterface {

    private Context context;
    private String [] imageUrls;

    public MJavascriptInterface(Context context,String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @android.webkit.JavascriptInterface
    public void openImage(String img) {
        Intent intent = new Intent();
        intent.putExtra("imageUrls", imageUrls);
        intent.putExtra("curImageUrl", img);
//        intent.setClass(context, PhotoBrowserActivity.class);
        context.startActivity(intent);
        for (int i = 0; i < imageUrls.length; i++) {
            Log.e("图片地址"+i,imageUrls[i].toString());
        }
    }

}