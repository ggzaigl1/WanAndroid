package com.example.gab.babylove.ui.personal.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.ui.personal.activity.ModifyInfoActivity;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ResourceUtils;

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
    protected void baseInit() {
        super.baseInit();
        tvAge.setText(getSpann(R.string.age, "25"));
        tvHeight.setText(getSpann(R.string.height, "165"));
        tvWeight.setText(getSpann(R.string.weight, "63"));
        MdStatusBar.setColorBar(getActivity(), R.color.statusBar, R.color.statusBar);
//        ImgLoadUtils.loadCircleImg("http://img1.3lian.com/2015/w22/87/d/102.jpg", imgHead);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_other;
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
