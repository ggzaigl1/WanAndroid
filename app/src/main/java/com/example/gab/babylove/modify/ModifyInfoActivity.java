package com.example.gab.babylove.modify;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.utils.DateSelectUtils;
import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.TimeUtils;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.fy.img.picker.PickerConfig;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/21 0021.
 * 修改个人信息
 */

public class ModifyInfoActivity extends BaseActivity {

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
    protected int getContentView() {
        return R.layout.activity_modify_info;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText(R.string.myData);
        tvMenu.setText(R.string.complete);

        ImgLoadUtils.loadCircleImg("http://img1.3lian.com/2015/w22/87/d/105.jpg", imgHeadModify);
    }

    @OnClick({R.id.llHead, R.id.llSex, R.id.llBirthday, R.id.llNation, R.id.llSchool, R.id.llAge, R.id.llHeight, R.id.llWeight, R.id.llParentPhone})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvMenu://完成

                break;
            case R.id.llHead://修改头像
                Bundle bundle = new Bundle();
                bundle.putInt(PickerConfig.KEY_MAX_COUNT, 0);
                bundle.putBoolean(PickerConfig.KEY_ISTAKE_picture, true);
                JumpUtils.jump(mContext, bundle, "com.fy.img.picker.multiselect.ImgPickerActivity", 10000);
                break;
            case R.id.llSex://修改性别

                break;
            case R.id.llBirthday://修改生日
                DateSelectUtils.getDatePicker(mContext, date -> tvBirthdayModify.setText(TimeUtils.Data2String(date, "yyyyMMdd"))
                ).show();
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
                        .setActionTextColor(ContextCompat.getColor(mContext, R.color.button_press))
                        .setAction("点击我试试", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T.showShort("嘿嘿哈哈");
                    }
                }).show();
                break;
        }
    }
}
