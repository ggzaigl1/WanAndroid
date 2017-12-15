package com.fy.img.picker.multiselect;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.base.popupwindow.CommonPopupWindow;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;
import com.fy.img.picker.PickerConfig;
import com.fy.img.picker.bean.ImageFolder;
import com.fy.img.picker.bean.ImageItem;
import com.fy.img.picker.folder.ImageDataSource;
import com.fy.img.picker.folder.ImageFolderAdapter;
import com.fy.img.picker.preview.PicturePreviewActivity;
import com.fy.library.imgpicker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片选择（单选、多选）
 * Created by fangs on 2017/6/29.
 */
public class ImgPickerActivity extends BaseActivity {

    private Button btn_dir;//全部图片
    private Button btn_preview;//预览

    private ImageFolder imgFolder;//当前界面显示的图片 文件夹
    private RecyclerView recycler;
    private ImgPickersAdapter mImgListAdapter;

    private CommonPopupWindow popupWindow;//图片文件夹的 弹窗
    private ImageFolderAdapter mImageFolderAdapter;

    protected List<String> selectedImages;   //所有已经选中的图片

    @Override
    protected int getContentView() {
        return R.layout.act_img_picker;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        btn_dir = (Button) findViewById(R.id.btn_dir);
        btn_preview = (Button) findViewById(R.id.btn_preview);
        btn_dir.setOnClickListener(this);
        btn_preview.setOnClickListener(this);

        setActTitle(R.string.select_img);
        setActMenu(R.string.complete);
        selectedImages = new ArrayList<>();

        initRV();
        initImgFolder();
    }

    @SuppressLint("StringFormatMatches")
    private void initRV(){
        recycler = (RecyclerView) findViewById(R.id.recycler);

        //设置布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, OrientationHelper.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        //添加分割线
//        recycler.addItemDecoration(new DividerParams().create(this));

        //设置adapter
        mImgListAdapter = new ImgPickersAdapter.Bulder()
                .setSelectLimit(12)
                .setClickListener(num -> {
                    if (num > 0){
                        btn_preview.setText(getString(R.string.preview_count, num));
                        btn_preview.setEnabled(true);
                    } else {
                        btn_preview.setText("预览");
                        btn_preview.setEnabled(false);
                    } })
                .setOnImageItemClickListener((imageItem, position) -> {
                    previewPicture(position, 12, imgFolder);
                        })
                .create(this, new ArrayList<>());

        recycler.setAdapter(mImgListAdapter);
    }

    //初始化图片文件夹 相关
    private void initImgFolder(){
        mImageFolderAdapter = new ImageFolderAdapter(this, new ArrayList<>());

        new ImageDataSource(this, null, new ImageDataSource.OnImagesLoadedListener() {
            @Override
            public void onImagesLoaded(List<ImageFolder> imageFolders) {
                if (imageFolders.size() == 0) {
                    mImgListAdapter.refreshData(null);
                } else{
                    imgFolder = imageFolders.get(0);
                    mImgListAdapter.refreshData(imgFolder.images);
                }
                mImageFolderAdapter.setData(imageFolders);
                mImageFolderAdapter.clearCache();
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.btn_dir) {//全部图片 按钮
            if (popupWindow != null && popupWindow.isShowing()) return;
            //计算popupWindow 高度（listview Item数量  * （Item高度 + 分割线高度））
            int itemCount = mImageFolderAdapter.getCount() > 3 ? 3 : mImageFolderAdapter.getCount();
            int pw_lv_height = 166 * itemCount;

            popupWindow = new CommonPopupWindow.Builder(this)
                    .setView(R.layout.pop_imgfolder)
                    .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, pw_lv_height)
                    .setAnimationStyle(R.style.AnimDown)
                    .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                        @Override
                        public void getChildView(View view, int layoutResId) {
                            if (layoutResId == R.layout.pop_imgfolder) {
                                ListView lvImaFolder = (ListView) view.findViewById(R.id.lvImaFolder);
                                lvImaFolder.setAdapter(mImageFolderAdapter);

                                lvImaFolder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        imgFolder = (ImageFolder) view.getTag();

                                        if (null != imgFolder) {
                                            T.showLong(imgFolder.name);
                                            mImgListAdapter.refreshData(imgFolder.images);
                                            btn_dir.setText(imgFolder.name);
                                            mImageFolderAdapter.setSelectIndex(position);
                                        }

                                        popupWindow.dismiss();
                                    }
                                });
                            }
                        }
                    }).create();

            //得到button的左上角坐标
            int[] positions = new int[2];
            view.getLocationOnScreen(positions);
            popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, positions[0], positions[1] - pw_lv_height);
        } else if (i == R.id.btn_preview) {//预览 按钮
            List<ImageItem> selectedData = mImgListAdapter.getSelectedData();
            ImageFolder imgFolder = new ImageFolder(selectedData);

            previewPicture(1, 0, imgFolder);
        } else if (i == R.id.tvMenu){//完成
            List<ImageItem> selectedData = mImgListAdapter.getSelectedData();
            ImageFolder imgFolder = new ImageFolder(selectedData);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ImageFolder", imgFolder);

            JumpUtils.jumpResult(this, bundle);
        }
    }

    /**
     * 预览大图
     * @param position  首先显示的图片
     * @param maxCount  最多选择图片数目
     * @param imgFolder 要显示的图片文件夹
     */
    private void previewPicture(int position, int maxCount, ImageFolder imgFolder){
        Bundle bundle = new Bundle();
        bundle.putInt(PickerConfig.KEY_MAX_COUNT, maxCount);
        bundle.putInt(PickerConfig.KEY_CURRENT_POSITION, position);
        bundle.putSerializable(PickerConfig.KEY_IMG_FOLDER, imgFolder);
        JumpUtils.jump(mContext, PicturePreviewActivity.class, bundle);
    }
}
