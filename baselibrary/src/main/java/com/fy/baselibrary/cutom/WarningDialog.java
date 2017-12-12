package com.fy.baselibrary.cutom;


import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.fy.baselibrary.R;
import com.fy.baselibrary.base.CommonDialog;

/**
 * 给予 用户 重要信息 提示
 * <p/>公共dialog
 * Created by fangs on 2017/6/7.
 */
public class WarningDialog extends CommonDialog {

    DialogParams params;

    TextView tvDialogTitle;
    TextView tvInfo;
    TextView tvLeft;
    TextView tvRight;

    @Override
    protected int getContentLayout() {
        return R.layout.dialog_warning_comment;
    }

    @Override
    protected void baseInit() {
        tvDialogTitle = (TextView) mRootView.findViewById(R.id.tvDialogTitle);
        tvInfo = (TextView) mRootView.findViewById(R.id.tvInfo);
        tvLeft = (TextView) mRootView.findViewById(R.id.tvLeft);
        tvRight = (TextView) mRootView.findViewById(R.id.tvRight);

        tvDialogTitle.setText(params.getTitle());
        tvInfo.setText(params.getInfo());
        setHide(true);
        if (params.isShowLeft()){
            tvLeft.setVisibility(View.VISIBLE);
            tvLeft.setText(params.getLeftStr());
            tvLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (null != params.getLeftListener()){
                        params.getLeftListener().onClick(v);
                    }
                }
            });
        }


        if (params.isShowRight()){
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(params.getRightStr());
            tvRight.setOnClickListener(v -> {
                dismiss();
                if (null != params.getRightListener()){
                    params.getRightListener().onClick(v);
                }
            });
        }

//        setWidthPixels(1500);
//        setHeightPixels(200);
        setGravity(Gravity.RIGHT);
        setBgDarkening(true);
        setTransparent(true);

        super.baseInit();
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
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            params.setTitle(title);
            return this;
        }

        /**
         * 设置dialog 显示内容
         * @param msg
         * @return
         */
        public Builder setMessage(String msg) {
            params.setInfo(msg);
            return this;
        }

        /**
         * 设置dialog 左边按钮 显示文本和点击事件
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
         * @return
         */
        public WarningDialog create() {
            WarningDialog dialog = new WarningDialog();
            dialog.setParams(params);

            return dialog;
        }
    }
}
