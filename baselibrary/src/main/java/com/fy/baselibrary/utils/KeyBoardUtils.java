package com.fy.baselibrary.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 打开或关闭软键盘
 * <p/>
 * Created by fangs on 2017/3/1.
 */
public class KeyBoardUtils {

    private KeyBoardUtils(){
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 隐藏系统软键盘
     * @param mContext
     * @param mEditText
     */
    public static void goneKeyboard(Context mContext, EditText mEditText) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示系统软键盘
     *
     * @param msg_input
     */
    public static void visibleKeyboard(final EditText msg_input) {
        msg_input.setFocusable(true);
        msg_input.setFocusableInTouchMode(true);
        msg_input.requestFocus();

        Observable.timer(900, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程;
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        InputMethodManager inputManager = (InputMethodManager)msg_input.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.showSoftInput(msg_input, 0);
                    }
                });
    }

}
