package com.fy.img.picker.preview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBarCompat;
import com.fy.img.picker.PickerConfig;
import com.fy.img.picker.bean.ImageFolder;
import com.fy.library.imgpicker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片预览activity
 * Created by fangs on 2017/7/6.
 */
public class PicturePreviewActivity extends BaseActivity {

    protected ConstraintLayout rlHead;         //头部布局
    protected ConvenientBanner viewPager;
    protected TextView tvTitle;              //显示当前图片的位置  例如  5/31
    protected TextView tvBack;
    protected TextView tvMenu;

    protected ImageFolder imgFolder;         //跳转进PicturePreviewActivity的图片文件夹
    protected List<String> selectedImages;   //所有已经选中的图片
    protected int mCurrentPosition = 0;      //跳转进PicturePreviewActivity时的序号，第几个图片
    protected int max = 9;                   //最大选择图片数目

    /**
     * 当前屏幕状态 全屏or显示菜单
     */
    private int currentState = PickerConfig.STATE_SHOW_MENU;

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void setStatusBarType() {
        MdStatusBarCompat.setImageTransparent(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.layout_preview);
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("StringFormatMatches")
    @Override
    protected void init(Bundle savedInstanceState) {
        selectedImages = new ArrayList<>();
        rlHead = findViewById(R.id.rlHead);
        rlHead.setBackgroundColor(getResources().getColor(R.color.imgPreviewHeadBg));

        viewPager = findViewById(R.id.bannerViewPager);
        tvTitle = findViewById(R.id.tvTitle);
        tvBack = findViewById(R.id.tvBack);
        tvMenu = findViewById(R.id.tvMenu);

        getTransmitData();
        //初始化当前页面的状态
        tvTitle.setText(getString(R.string.preview_image_count, mCurrentPosition, imgFolder.images.size()));
        tvBack.setOnClickListener(this);
        tvMenu.setVisibility(View.INVISIBLE);

        //本地图片例子
        viewPager.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, imgFolder.images)
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        tvTitle.setText(getString(R.string.preview_image_count, position + 1, imgFolder.images.size()));
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                }).setPageTransformer(new AccordionTransformer())
                .setcurrentitem(mCurrentPosition - 1);
    }

    /**
     * 获取传递的数据
     */
    private void getTransmitData() {
        Bundle bundle = getIntent().getExtras();
        max = bundle.getInt(PickerConfig.KEY_MAX_COUNT, 9);
        mCurrentPosition = bundle.getInt(PickerConfig.KEY_CURRENT_POSITION, 0);
        imgFolder = (ImageFolder) bundle.getSerializable(PickerConfig.KEY_IMG_FOLDER);
        ArrayList<String> selectImg = bundle.getStringArrayList(PickerConfig.KEY_SELECTED);
        if (null != selectImg) {
            selectedImages.addAll(selectImg);
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
    }

    /**
     * 隐藏 或显示 状态栏，标题栏，底部栏
     */
    private void toggleStateChange() {
        if (currentState == PickerConfig.STATE_SHOW_MENU) {

            currentState = PickerConfig.STATE_FULLSCREEN;
        } else {

            currentState = PickerConfig.STATE_SHOW_MENU;
        }
    }

//        各种翻页效果
//        DefaultTransformer.class.getSimpleName();
//        AccordionTransformer.class.getSimpleName();
//        BackgroundToForegroundTransformer.class.getSimpleName();
//        CubeInTransformer.class.getSimpleName();
//        CubeOutTransformer.class.getSimpleName();
//        DepthPageTransformer.class.getSimpleName();
//        FlipHorizontalTransformer.class.getSimpleName();
//        FlipVerticalTransformer.class.getSimpleName();
//        ForegroundToBackgroundTransformer.class.getSimpleName();
//        RotateDownTransformer.class.getSimpleName();
//        RotateUpTransformer.class.getSimpleName();
//        StackTransformer.class.getSimpleName();
//        ZoomInTransformer.class.getSimpleName();
//        ZoomOutTranformer.class.getSimpleName();
}
