package com.fy.baselibrary.retrofit.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.fy.baselibrary.R;
import com.fy.baselibrary.base.CommonDialog;

/**
 * 加载 dialog
 * Created by fangs on 2017/3/10.
 */
public class DialogLoad extends CommonDialog {

    private String msg = "";

    public DialogLoad() {
    }

    public static DialogLoad init() {
        return new DialogLoad();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.dialog_loading;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState) {
            savedInstanceState.getParcelable("listener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // 加载动画
        Animation loadAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_loading);
        // 使用ImageView显示动画
        ImageView imgLoadAnim = (ImageView) mRootView.findViewById(R.id.imgLoadAnim);
        imgLoadAnim.setAnimation(loadAnim);

        TextView txtLoadHint = (TextView) mRootView.findViewById(R.id.txtLoadHint);
        if (!TextUtils.isEmpty(msg)){
            txtLoadHint.setText(msg);
        }
    }

    public DialogLoad setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
