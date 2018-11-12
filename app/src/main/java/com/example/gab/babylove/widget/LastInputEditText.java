package com.example.gab.babylove.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by 初夏小溪 on 2018/11/12 0012.
 * EditText 光标始终在最后面
 */
public class LastInputEditText extends android.support.v7.widget.AppCompatEditText {

    public LastInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LastInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LastInputEditText(Context context) {
        super(context);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //保证光标始终在最后面
        if (selStart == selEnd) {//防止不能多选
            setSelection(getText().length());
        }
    }
}
