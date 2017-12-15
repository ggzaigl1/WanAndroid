package com.fy.img.picker.multiselect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fy.baselibrary.base.recyclerv.adapter.MultiItemCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.MultiItemTypeSupport;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.utils.ScreenUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.fy.img.picker.ImagePicker;
import com.fy.img.picker.bean.ImageItem;
import com.fy.library.imgpicker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片选择（单选、多选） RecyclerView 适配器
 * Created by fangs on 2017/8/7.
 */
public class ImgPickersAdapter extends MultiItemCommonAdapter<ImageItem> {

    private List<ImageItem> selectedData;           //全局保存的已经选中的图片数据
    private int mImageSize;

    private OnImageItemClickListener listener;   //图片被点击的监听
    private OnCheckClickListener clickListener;  //checkBox点击监听
    private ImagePicker imagePicker;

    private ImgPickersAdapter(Context context, List<ImageItem> datas) {
        super((AppCompatActivity) context, datas, new MultiItemTypeSupport<ImageItem>(){
            @Override
            public int getLayoutId(int itemType) {
                if (itemType == 1) {
                    return R.layout.adapter_image;
                } else {
                    return R.layout.adapter_camera;
                }
            }

            @Override
            public int getItemViewType(int position, ImageItem imageItem) {
                return imageItem.isShowCamera();
            }
        });

        selectedData = new ArrayList<>();
        mImageSize = ScreenUtils.getImageItemWidth(context);
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void convert(ViewHolder holder, ImageItem imgItem, int position) {
        if (imgItem.isShowCamera() == 1) {
            ImageView ivThumb = holder.getView(R.id.iv_thumb);
            ivThumb.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mImageSize)); //让图片是个正方形
            ImgLoadUtils.loadImage(mContext, imgItem.path, ivThumb);
            ivThumb.setOnClickListener(v -> {
                if (listener != null) listener.onImageItemClick(imgItem, position);
            });

            CheckBox cbCheck = holder.getView(R.id.cb_check);
            boolean checked = selectedData.contains(imgItem);
            if (checked) {
                cbCheck.setChecked(true);
            } else {
                cbCheck.setChecked(false);
            }

            cbCheck.setOnClickListener(v -> {
                int selectLimit = imagePicker.getSelectLimit();
                if (cbCheck.isChecked() && selectedData.size() >= selectLimit) {
                    T.showLong(mContext.getString(R.string.select_limit, selectLimit));
                    cbCheck.setChecked(false);
                } else {
                    if (cbCheck.isChecked()) selectedData.add(imgItem);
                    else selectedData.remove(imgItem);
                }

                if (null != clickListener)clickListener.onClick(selectedData.size());
            });
        } else {
            holder.setOnClickListener(R.id.camera, v -> {
                T.showLong("开始拍照");
            });
        }
    }


    public void refreshData(List<ImageItem> images) {
        if (null == images || images.size() == 0){
            setmDatas(new ArrayList<>());
        } else {
            setmDatas(images);
        }
        notifyDataSetChanged();
    }

    public void setOnImageItemClickListener(OnImageItemClickListener listener) {
        this.listener = listener;
    }

    public void setClickListener(OnCheckClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setImagePicker(ImagePicker imagePicker) {
        this.imagePicker = imagePicker;
    }

    public List<ImageItem> getSelectedData() {
        return selectedData;
    }

    /**
     * 定义item 图片点击事件回调接口
     */
    public interface OnImageItemClickListener {
        void onImageItemClick(ImageItem imageItem, int position);
    }

    /**
     * 定义 item checkBox点击事件回调接口
     */
    interface OnCheckClickListener{
        void onClick(int num);
    }

    static class Bulder{
        public ImagePicker parames;
        OnImageItemClickListener listener;
        OnCheckClickListener clickListener;

        public Bulder() {
            this.parames = new ImagePicker();
        }

        public Bulder setSelectLimit(int selectLimit) {
            this.parames.setSelectLimit(selectLimit);
            return this;
        }

        public Bulder setOnImageItemClickListener(OnImageItemClickListener listener){
            this.listener = listener;
            return this;
        }

        public Bulder setClickListener(OnCheckClickListener clickListener) {
            this.clickListener = clickListener;
            return this;
        }

        public ImgPickersAdapter create(Context context, List<ImageItem> datas){
            ImgPickersAdapter adapter = new ImgPickersAdapter(context, datas);
            adapter.setImagePicker(parames);
            adapter.setOnImageItemClickListener(listener);
            adapter.setClickListener(clickListener);

            return adapter;
        }
    }
}
