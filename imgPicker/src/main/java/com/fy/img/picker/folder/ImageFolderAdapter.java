package com.fy.img.picker.folder;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fy.baselibrary.base.CommonAdapter;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.fy.img.picker.bean.ImageFolder;
import com.fy.library.imgpicker.R;

import java.util.List;

/**
 * 图片文件夹的适配器
 * Created by fangs on 2017/6/30.
 */
public class ImageFolderAdapter extends CommonAdapter<ImageFolder> {

    private int lastSelected = 0;

    public ImageFolderAdapter(Context context, List<ImageFolder> data) {
        super((AppCompatActivity) context, data);
    }

    @Override
    public View getView(int position, View arg1, ViewGroup arg2) {
        View itemView = getViewCache().get(position);

        if (null == itemView) {
            final ImageFolder item = getData().get(position);
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_imgfolder, null);

            ImageView iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            ImageView iv_folder_check = (ImageView) itemView.findViewById(R.id.iv_folder_check);
            TextView tv_folder_name = (TextView) itemView.findViewById(R.id.tv_folder_name);
            TextView tv_image_count = (TextView) itemView.findViewById(R.id.tv_image_count);

            tv_folder_name.setText(item.name);
            tv_image_count.setText(getContext().getString(R.string.folder_image_count, item.images.size()));
            ImgLoadUtils.loadImage(getContext(), item.cover.path, iv_cover);

            if (lastSelected == position) {
                iv_folder_check.setVisibility(View.VISIBLE);
            } else {
                iv_folder_check.setVisibility(View.INVISIBLE);
            }
            
            itemView.setTag(item);
            getViewCache().put(position, itemView);
        }

        return itemView;
    }

    public void setSelectIndex(int i) {
        lastSelected = i;
        clearCache();
    }
}
