package com.example.gab.babylove.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.modify.ModifyInfoActivity;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/15 0015.
 * 其他
 */

public class OtherFragment extends BaseFragment {

    @BindView(R.id.imgHead)
    AppCompatImageView imgHead;
    @BindView(R.id.night_switch)
    Switch night_switch;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvHeight)
    TextView tvHeight;
    @BindView(R.id.tvWeight)
    TextView tvWeight;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvEdit)
    TextView tvEdit;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_other;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        tvAge.setText(getSpann(R.string.age, "25"));
        tvHeight.setText(getSpann(R.string.height, "165"));
        tvWeight.setText(getSpann(R.string.weight, "63"));
        ImgLoadUtils.loadCircleImg("http://img1.3lian.com/2015/w22/87/d/105.jpg", imgHead);
    }

    /**
     * 使用 Spannable 动态设置 textview 显示内容
     * @param id
     * @param replaceStr
     * @return
     */
    private Spannable getSpann(int id, String replaceStr){
        Spannable sp = new SpannableString(ResourceUtils.getText(id, replaceStr)) ;
        sp.setSpan(new AbsoluteSizeSpan(21,true),0,replaceStr.length(),Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.login)),0,replaceStr.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(14,true),replaceStr.length(), sp.length(),Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return sp;
    }

    @OnClick({R.id.night_switch,R.id.tvEdit})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.night_switch:
                Switch sw = (Switch) view;
                boolean isChecked = sw.isChecked();
                if(isChecked){
                    T.showShort("开启");
                }else {
                    T.showShort("关闭");
                }
                break;
            case R.id.tvEdit:
                JumpUtils.jump(mContext, ModifyInfoActivity.class, null);
                break;
        }
    }

}
