package com.example.gab.babylove.ui.personal.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/21 0021.
 * 修改个人信息
 */

public class ModifyInfoActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.imgHeadModify)
    AppCompatImageView imgHeadModify;
    @BindView(R.id.tvSexModify)
    TextView tvSexModify;
    @BindView(R.id.tvBirthdayModify)
    TextView tvBirthdayModify;
    @BindView(R.id.tvNationModify)
    TextView tvNationModify;
    @BindView(R.id.tvSchoolModify)
    TextView tvSchoolModify;
    @BindView(R.id.tvAgeModify)
    TextView tvAgeModify;
    @BindView(R.id.tvHeightModify)
    TextView tvHeightModify;
    @BindView(R.id.tvWeightModify)
    TextView tvWeightModify;
    @BindView(R.id.tvParentPhoneModify)
    TextView tvParentPhoneModify;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_modify_info;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        ImgLoadUtils.loadCircleImg("http://img1.3lian.com/2015/w22/87/d/105.jpg", imgHeadModify);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && null != data){
//            switch (requestCode){
//                case ImagePicker.Picture_Selection:
//                    Bundle bundle = data.getExtras();
//                    ImageItem imgItem = (ImageItem) bundle.getSerializable("pickerImgData");
//
//                    ImgLoadUtils.loadCircleImg(imgItem.getPath(), imgHeadModify);
//                    break;
//            }
//        }
//    }

    @OnClick({R.id.llHead, R.id.llSex, R.id.llBirthday, R.id.llNation, R.id.llSchool, R.id.llAge, R.id.llHeight, R.id.llWeight, R.id.llParentPhone})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvMenu://完成

                break;
            case R.id.llHead://修改头像
//                Bundle bundle = new Bundle();
//                bundle.putInt(PickerConfig.KEY_MAX_COUNT, 0);
//                bundle.putBoolean(PickerConfig.KEY_ISTAKE_picture, true);
//                JumpUtils.jump(ModifyInfoActivity.this, bundle, "com.fy.img.picker.multiselect.ImgPickerActivity", ImagePicker.Picture_Selection);
                break;
            case R.id.llSex://修改性别

                break;
            case R.id.llBirthday://修改生日
//                DateSelectUtils.getDatePicker(ModifyInfoActivity.this, date -> tvBirthdayModify.setText(TimeUtils.Data2String(date, "yyyyMMdd"))
//                ).show();
                break;
            case R.id.llNation://修改民族

                break;
            case R.id.llSchool://修改学校

                break;
            case R.id.llAge://修改年龄

                break;
            case R.id.llHeight://修改身高

                break;
            case R.id.llWeight://修改体重

                break;
            case R.id.llParentPhone://电话
                Snackbar.make(view, "想知道我的电话吗?", Snackbar.LENGTH_SHORT)
                        .setActionTextColor(ContextCompat.getColor(ModifyInfoActivity.this, R.color.button_press))
                        .setAction("点击我试试", new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        tvParentPhoneModify.setText("18771002999");
                    }
                }).show();
                break;
        }
    }

    @Override
    public void reTry() {

    }
}
