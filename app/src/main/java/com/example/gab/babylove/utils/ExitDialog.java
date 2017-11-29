package com.example.gab.babylove.utils;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gab.babylove.R;


/**
 * 给予 用户 重要信息 提示 退出登录 Dialog
 * <p/>公共dialog
 * Created by fangs on 2017/6/7.
 */
public class ExitDialog extends CommonDialog {

    DialogParams params;

    //    TextView tvDialogTitle;
    TextView tvInfo;
    TextView tvTitle;
    Button tvLeft;
    Button tvRight;

    @Override
    protected int getContentLayout() {
        return R.layout.dialog_exit_comment;
    }

    @Override
    public void instanceState(Bundle savedInstanceState) {
        super.instanceState(savedInstanceState);
        //恢复保存的数据
        if (savedInstanceState != null) {
            params = (DialogParams) savedInstanceState.getSerializable("DialogParams");
        }
    }

    @Override
    protected void baseInit() {
//        tvDialogTitle = mRootView.findViewById(R.id.tvDialogTitle);
        tvInfo = (TextView) mRootView.findViewById(R.id.tvInfo);
        tvTitle = (TextView) mRootView.findViewById(R.id.tvTitle);
        tvLeft = (Button) mRootView.findViewById(R.id.tvLeft);
        tvRight = (Button) mRootView.findViewById(R.id.tvRight);
        tvInfo.setText(params.getInfo());
        tvTitle.setText(params.getTitle());

        if (params.isShowLeft()) {
            tvLeft.setVisibility(View.VISIBLE);
            tvLeft.setText(params.getLeftStr());
            tvLeft.setOnClickListener(v -> {
                dismiss();
                if (null != params.getLeftListener()) {
                    params.getLeftListener().onClick(v);
                }
            });
        }


        if (params.isShowRight()) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(params.getRightStr());
            tvRight.setOnClickListener(v -> {
                dismiss();
                if (null != params.getRightListener()) {

                    params.getRightListener().onClick(v);
                }
            });
        }

        setWidthPixels(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeightPixels(ViewGroup.LayoutParams.WRAP_CONTENT);
        setTransparent(true);

        super.baseInit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("DialogParams", params);
    }

    public void setParams(DialogParams params) {
        this.params = params;
    }

    public static class Builder {
        static DialogParams params;

        public Builder() {
            params = new DialogParams();
        }

        /**
         * 设置dialog 标题
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            params.setTitle(title);
            return this;
        }

        /**
         * 设置dialog 显示内容
         *
         * @param msg
         * @return
         */
        public Builder setMessage(String msg) {
            params.setInfo(msg);
            return this;
        }

        /**
         * 设置dialog 左边按钮 显示文本和点击事件
         *
         * @param leftText
         * @param leftListener
         * @return
         */
        public Builder setLeftListener(String leftText, View.OnClickListener leftListener) {
            params.setShowLeft(true);
            params.setLeftStr(leftText);
            params.setLeftListener(leftListener);
            return this;
        }

        /**
         * 设置dialog 右边按钮 显示文本和点击事件
         *
         * @param rightText
         * @param rightListener
         * @return
         */
        public Builder setRightListener(String rightText, View.OnClickListener rightListener) {
            params.setShowRight(true);
            params.setRightStr(rightText);
            params.setRightListener(rightListener);
            return this;
        }

        /**
         * 创建dialog
         *
         * @return
         */
        public ExitDialog create() {
            ExitDialog dialog = new ExitDialog();
            dialog.setParams(params);

            return dialog;
        }
    }
}
