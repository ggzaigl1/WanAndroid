package com.ggz.baselibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * @author 初夏小溪
 * @date 2018/10/18 0018
 */
public class AndroidShareUtils {

    /**
     * 文本类型
     */
    public static int TEXT = 0;

    /**
     * 图片类型
     */
    public static int DRAWABLE = 1;


    /**
     * 点击分享的代码
     *
     * @param context  Activity的名字
     * @param msgTitle 消息标题
     * @param msgText  消息内容
     * @param type     (发送类型：text or pic 微信朋友圈只支持pic)
     * @param drawable 图片路径，不分享图片则传null
     */
    public static void shareAllMsg(Context context, String msgTitle, String msgText, int type, Bitmap drawable) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (type == AndroidShareUtils.TEXT) {
            intent.setType("text/plain");
        } else if (type == AndroidShareUtils.DRAWABLE) {
            intent.setType("image/*");
            Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), drawable, null, null));
            intent.putExtra(Intent.EXTRA_STREAM, uri);
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, msgTitle));
    }
}
