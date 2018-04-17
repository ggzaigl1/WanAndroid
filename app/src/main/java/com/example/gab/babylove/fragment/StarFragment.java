package com.example.gab.babylove.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gab.babylove.R;
import com.example.gab.babylove.modify.ModifyInfoActivity;
import com.example.gab.babylove.utils.CleanMessageUtil;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/15 0015.
 * 其他
 */

public class StarFragment extends BaseFragment {

    @BindView(R.id.imgHead)
    AppCompatImageView imgHead;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvHeight)
    TextView tvHeight;
    @BindView(R.id.tvWeight)
    TextView tvWeight;

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
        ImgLoadUtils.loadCircleImg("http://img1.3lian.com/2015/w22/87/d/102.jpg", imgHead);
    }

    /**
     * 使用 Spannable 动态设置 textview 显示内容
     *
     * @param id
     * @param replaceStr
     * @return
     */
    private Spannable getSpann(int id, String replaceStr) {
        Spannable sp = new SpannableString(ResourceUtils.getText(id, replaceStr));
        sp.setSpan(new AbsoluteSizeSpan(21, true), 0, replaceStr.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.login)), 0, replaceStr.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(14, true), replaceStr.length(), sp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return sp;
    }

    @OnClick({R.id.tvEdit})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvEdit:
                JumpUtils.jump(mContext, ModifyInfoActivity.class, null);
                break;
        }
    }
}
