package com.example.gab.babylove.utils;

import android.view.View;

import java.io.Serializable;

/**
 * 自定义dialog辅助类
 * Created by fangs on 2017/5/24.
 */
public class DialogParams implements Serializable {

    String title = "";
    String info = "";
    String leftStr = "";
    String rightStr = "";
    /** 是否显示 左 边按钮 */
    boolean isShowLeft;
    /** 是否显示 右 边按钮 */
    boolean isShowRight;
    View.OnClickListener leftListener;
    View.OnClickListener rightListener;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLeftStr() {
        return leftStr;
    }

    public void setLeftStr(String leftStr) {
        this.leftStr = leftStr;
    }

    public String getRightStr() {
        return rightStr;
    }

    public void setRightStr(String rightStr) {
        this.rightStr = rightStr;
    }

    public boolean isShowLeft() {
        return isShowLeft;
    }

    public void setShowLeft(boolean showLeft) {
        isShowLeft = showLeft;
    }

    public boolean isShowRight() {
        return isShowRight;
    }

    public void setShowRight(boolean showRight) {
        isShowRight = showRight;
    }

    public View.OnClickListener getLeftListener() {
        return leftListener;
    }

    public void setLeftListener(View.OnClickListener leftListener) {
        this.leftListener = leftListener;
    }

    public View.OnClickListener getRightListener() {
        return rightListener;
    }

    public void setRightListener(View.OnClickListener rightListener) {
        this.rightListener = rightListener;
    }
}
