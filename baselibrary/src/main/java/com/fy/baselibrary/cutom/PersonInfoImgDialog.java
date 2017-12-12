package com.fy.baselibrary.cutom;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fy.baselibrary.R;
import com.fy.baselibrary.base.CommonDialog;

/**
 * 给予 用户 重要信息 提示 个人资料上传照片 Dialog
 * <p/>公共dialog
 * Created by fangs on 2017/6/7.
 */
public class PersonInfoImgDialog extends CommonDialog {

    DialogParams params;
    Button tv_phone;
    Button tv_pic;
    Button tv_cancel;

    @Override
    protected int getContentLayout() {
        return R.layout.dialog_person_info_img_comment;
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
        tv_phone = mRootView.findViewById(R.id.tv_phone);
        tv_pic = mRootView.findViewById(R.id.tv_pic);
        tv_cancel = mRootView.findViewById(R.id.tv_cancel);

        if (params.isShowRight()){
            tv_phone.setVisibility(View.VISIBLE);
            tv_phone.setText(params.getLeftStr());
            tv_phone.setOnClickListener(v -> {
                dismiss();
                if (null != params.getLeftListener()){
                    params.getLeftListener().onClick(v);
                }
            });
        }
//
        if (params.isShowLeft()){
            tv_pic.setVisibility(View.VISIBLE);
            tv_pic.setText(params.getRightStr());
            tv_pic.setOnClickListener(v -> {
                dismiss();
                if (null != params.getRightListener()){
                    params.getRightListener().onClick(v);
                }
            });
        }

        if (params.isShowCancel()){
            tv_cancel.setVisibility(View.VISIBLE);
            tv_cancel.setText(params.getCancelStr());
            tv_cancel.setOnClickListener(v -> {
                dismiss();
                if (null != params.getCancelListener()){
                    params.getCancelListener().onClick(v);
                }
            });
        }

        setGravity(Gravity.BOTTOM);
        setWidthPixels(ViewGroup.LayoutParams.MATCH_PARENT);
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
         * 设置dialog 拍照取消按钮 显示文本和点击事件
         * @param rightText
         * @param rightListener
         * @return
         */
        public Builder setCancelListener(String rightText, View.OnClickListener rightListener) {
            params.setShowCancel(true);
            params.setCancelStr(rightText);
            params.setCancelListener(rightListener);
            return this;
        }

        /**
         * 创建dialog
         * @return
         */
        public PersonInfoImgDialog create() {
            PersonInfoImgDialog dialog = new PersonInfoImgDialog();
            dialog.setParams(params);

            return dialog;
        }
    }
}
