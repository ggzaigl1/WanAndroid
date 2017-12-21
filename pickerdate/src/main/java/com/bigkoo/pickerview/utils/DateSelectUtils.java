package com.bigkoo.pickerview.utils;

import android.content.Context;
import android.graphics.Color;

import com.bigkoo.pickerview.DateSelectListener;
import com.bigkoo.pickerview.TimePickerView;

import java.util.Calendar;

/**
 * 日期选择 工具类
 * Created by fangs on 2017/9/27.
 */
public class DateSelectUtils {

    /**
     * 年 月 日 日期选择器 dialog
     * @param mContent
     * @param listener
     * @return
     */
    public static TimePickerView getDatePicker(Context mContent, DateSelectListener listener) {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DATE));
        //时间选择器
        return new TimePickerView.Builder(mContent, (date, v) -> {//选中事件回调
            // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
            /*btn_Time.setText(getTime(date));*/
//                Button btn = (Button) v;
//                btn.setText("a");
            if (null != listener) listener.onTimeSelect(date);
        })
                .setType(new boolean[]{true, true, true, false, false, false})//年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setDividerColor(Color.BLUE)//分割线颜色
                .setTitleBgColor(Color.WHITE)//标题背景色
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .setDecorView(null)
                .isCenterLabel(false)//是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isCyclic(false)//是否循环滚动
                .build();
    }

    /**
     * 年 月 选择器
     * @param mContent
     * @param listener
     * @return
     */
    public static TimePickerView getMonthPicker(Context mContent, DateSelectListener listener){
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DATE));
        //时间选择器
        return new TimePickerView.Builder(mContent, (date, v) -> {//选中事件回调
            // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
            /*btn_Time.setText(getTime(date));*/
//                Button btn = (Button) v;
//                btn.setText("a");
            if (null != listener) listener.onTimeSelect(date);
        })
                .setType(new boolean[]{true, true, false, false, false, false})//年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setDividerColor(Color.BLUE)//分割线颜色
                .setTitleBgColor(Color.WHITE)//标题背景色
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .setDecorView(null)
                .isCenterLabel(false)//是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isCyclic(false)//是否循环滚动
                .build();
    }

    /**
     * 年选择器
     * @param mContent
     * @param listener
     * @return
     */
    public static TimePickerView getYearPicker(Context mContent, DateSelectListener listener){
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DATE));
        //时间选择器
        return new TimePickerView.Builder(mContent, (date, v) -> {//选中事件回调
            // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
            /*btn_Time.setText(getTime(date));*/
//                Button btn = (Button) v;
//                btn.setText("a");
            if (null != listener) listener.onTimeSelect(date);
        })
                .setType(new boolean[]{true, false, false, false, false, false})//年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setDividerColor(Color.BLUE)//分割线颜色
                .setTitleBgColor(Color.WHITE)//标题背景色
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .setDecorView(null)
                .isCenterLabel(false)//是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isCyclic(false)//是否循环滚动
                .build();
    }

    /**
     * 小时 分钟 时间选择器
     * @param mContent
     * @param listener
     * @return
     */
    public static TimePickerView getTimePicker(Context mContent, DateSelectListener listener) {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DATE));
        //时间选择器
        return new TimePickerView.Builder(mContent, (date, v) -> {//选中事件回调
            // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
            /*btn_Time.setText(getTime(date));*/
//                Button btn = (Button) v;
//                btn.setText("a");
            if (null != listener) listener.onTimeSelect(date);
        })
                .setType(new boolean[]{false, false, false, true, true, false})//年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setDividerColor(Color.BLUE)//分割线颜色
                .setTitleBgColor(Color.WHITE)//标题背景色
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .setDecorView(null)
                .isCenterLabel(false)//是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isCyclic(false)//是否循环滚动
                .build();
    }
}
