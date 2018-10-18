package com.example.gab.babylove.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import com.ggz.baselibrary.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 初夏小溪 on 2018/8/20 0020.
 * 图片保存路径
 */

public class ImgUtils {

    //保存文件到指定路径
    public static File saveImageToGallery(Bitmap bmp) {
        // 首先保存图片
        String storePath = getPath();
        String fileName = System.currentTimeMillis() + ".jpg";
        //判断指定路径的 文件夹 是否存在，不存在创建文件夹
        FileUtils.folderIsExists(storePath);
        //判断指定路径的 文件 是否存在，不存在创建文件
        File file = FileUtils.fileIsExists(storePath + "/" + fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();
            if (isSuccess) {
                return file;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 到得文件的放置路径
     *
     * @return
     * @author fangs
     */
    public static String getPath() {
        String sp = File.separator;
        String modulePath = "WanAndroid".replace(".", sp);
        String fDirStr = sp + modulePath + sp;
        File dirPath;
        if (FileUtils.isSDCardEnable())
            dirPath = new File(FileUtils.getSDCardPath(), fDirStr);
        else dirPath = Environment.getDataDirectory();
        return dirPath.getPath();
    }

    /**
     * 将图片存到本地
     */
    public static Uri saveBitmap(Bitmap bm, String picName) {
        try {
            String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/icon_wanandroid/" + picName + ".png";
            File file = new File(dir);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Uri uri = Uri.fromFile(file);
            return uri;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从Assets中读取图片
     */
    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
