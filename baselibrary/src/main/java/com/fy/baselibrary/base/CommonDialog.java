package com.fy.baselibrary.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.fy.baselibrary.R;
import com.fy.baselibrary.utils.L;

/**
 * 应用 所有dialog 的父类
 * <p/>
 * Created by fangs on 2017/3/13.
 */
public abstract class CommonDialog extends DialogFragment {

    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String GRAVITY = "show_gravity";
    private static final String CANCEL = "out_cancel";
    private static final String ANIM = "anim_style";
    private static final String LAYOUT = "layout_id";

    protected View mRootView;

    DialogDismissListner dialogList;

    /** dialog显示位置 */
    protected int gravity = Gravity.CENTER;
    /** dialog进出动画 资源ID */
    protected int anim = android.R.style.Animation_Translucent;
    @LayoutRes
    protected int layoutId;

    /** 点击window外的区域 是否消失 */
    protected boolean isHide = false;

    protected float dimAmount = 0.5f;//灰度深浅
    /** 宽度 -1(ViewGroup.LayoutParams.MATCH_PARENT)：撑满；-2(ViewGroup.LayoutParams.WRAP_CONTENT)：自适应； 其他固定数值 */
    protected int widthPixels = -2;
    /** 高度 -1：撑满 -2：自适应 其他固定数值 */
    protected int heightPixels = -2;
    /** 是否 清理背景变暗 */
    protected boolean isBgDarkening = false;
    /** 背景 是否透明 (dialog 边框阴影)*/
    protected boolean isTransparent = false;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /** 设置dialog 布局 */
    protected abstract int getContentLayout();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.commonDialog);
        layoutId = getContentLayout();

        //恢复保存的数据
        if (savedInstanceState != null) {
            widthPixels = savedInstanceState.getInt(WIDTH);
            heightPixels = savedInstanceState.getInt(HEIGHT);
            dimAmount = savedInstanceState.getFloat(DIM);
            gravity = savedInstanceState.getInt(GRAVITY);
            isHide = savedInstanceState.getBoolean(CANCEL);
            anim = savedInstanceState.getInt(ANIM);
            layoutId = savedInstanceState.getInt(LAYOUT);

            instanceState(savedInstanceState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (null == mRootView) {
            mRootView = inflater.inflate(layoutId, container, false);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseInit();
    }

    @Override
    public void onStart() {
        super.onStart();
        baseInit();

        setOnKeyListener();
    }

    /**
     * 初始化 dialog 样式
     */
    protected void baseInit() {
        Window window = getDialog().getWindow();
        if (null != window) {
            // 使用ViewGroup.LayoutParams，以便Dialog 宽度或高度充满整个屏幕
            WindowManager.LayoutParams params = window.getAttributes();
            if (isTransparent) window.setBackgroundDrawableResource(android.R.color.transparent);//背景透明
            //调节灰色背景透明度[0-1]，默认0.5f
            params.dimAmount = dimAmount;

            params.width = widthPixels;
            params.height = heightPixels;

            window.setGravity(gravity);  //此处可以设置dialog显示的位置
            window.setWindowAnimations(anim);  //添加动画

            window.setAttributes(params);

        }

        setCancelable(isHide);
    }

    public void instanceState(Bundle savedInstanceState){}

    /**
     * 屏幕旋转等导致DialogFragment销毁后重建时保存数据
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(WIDTH, widthPixels);
        outState.putInt(HEIGHT, heightPixels);
        outState.putFloat(DIM, dimAmount);
        outState.putInt(GRAVITY, gravity);
        outState.putBoolean(CANCEL, isHide);
        outState.putInt(ANIM, anim);
        outState.putInt(LAYOUT, layoutId);
    }

    /**
     * 设置 dialog显示位置
     * @param gravity
     */
    public CommonDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * 设置 dialog进出动画
     * @param anim
     */
    public CommonDialog setAnim(int anim) {
        this.anim = anim;
        return this;
    }

    /**
     * 点击window外的区域 是否消失
     * @param hide
     */
    public CommonDialog setHide(boolean hide) {
        isHide = hide;
        return this;
    }

    /**
     * 设置背景 是否透明(dialog 边框阴影)
     * @param transparent
     */
    public void setTransparent(boolean transparent) {
        isTransparent = transparent;
    }

    /**
     * 设置 宽度值
     * @param widthPixels
     */
    public CommonDialog setWidthPixels(int widthPixels) {
        this.widthPixels = widthPixels;
        return this;
    }

    /**
     * 设置 高度值
     * @param heightPixels
     */
    public CommonDialog setHeightPixels(int heightPixels) {
        this.heightPixels = heightPixels;
        return this;
    }
    /**
     * 设置 是否 清理背景变暗
     * @param bgDarkening
     */
    public void setBgDarkening(boolean bgDarkening) {
        isBgDarkening = bgDarkening;
    }

    /**
     * 设置 dialog 关闭监听
     * @param dialogList
     */
    public CommonDialog setDialogList(DialogDismissListner dialogList) {
        this.dialogList = dialogList;
        return this;
    }

    /** dialog关闭 监听接口 */
    public interface DialogDismissListner{
        void onDismiss();
    }


    /**
     * 自定义show
     * @param manager
     */
    public void show(FragmentManager manager) {
        FragmentTransaction ft = manager.beginTransaction();
        if (this.isAdded()) {
            ft.remove(this).commit();
        }
        ft.add(this, String.valueOf(System.currentTimeMillis()));
        ft.commitAllowingStateLoss();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (null != dialogList)dialogList.onDismiss();
    }


    protected void setOnKeyListener() {
        this.getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                L.v("dialog onkey", "按下返回键");
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
    }
}
