package com.example.gab.babylove.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.gab.babylove.R;

/**
 * Created by Gab on 2018/1/23 0023.
 *
 */

public class CustomView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿 new Paint() 的时候加上一个 ANTI_ALIAS_FLAG 参数就行
    Path mPath = new Path();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * Paint 相当于画笔 ,  Canvas相当于画布;
     * 比如要画一个红色的圆 那么 paint就需要红色熟悉 paint.setColor(Rad);
     * 画布Canvas 就需要准备圆形的画布 canvas.drawCircle(x,y,半径,画笔);
     * 需要什么样的形状就准备什么样的图形;
     *
     * @param canvas
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.addArc(200, 200, 400, 400, -225, 225);
        mPath.arcTo(400, 200, 600, 400, -180, 225, false);
        mPath.lineTo(400, 542);
        canvas.drawPath(mPath,mPaint);
        //绘制一个圆 Style 具体来说有三种： FILL, STROKE 和  FILL_AND_STROKE 。
        //FILL 是填充模式，STROKE 是画线模式（即勾边模式），FILL_AND_STROKE 是两种模式一并使用：既画线又填充。
        //它的默认值是 FILL，填充模式。
//        mPaint.setStyle(Paint.Style.STROKE);//空心圆
        mPaint.setColor(Color.BLACK);
//        mPaint.setStrokeWidth(100);//设置线条宽度
        mPaint.setTextSize(120);
//        mPaint.setAntiAlias(true);//设置抗锯齿开关

//        canvas.drawCircle(300,300,200,mPaint);//圆形
//        canvas.drawRect(300,300,200,300,mPaint);//正方形
//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(100, 100, 500, 500, mPaint);
//
//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawRect(700, 100, 1100, 500, mPaint);
        //圆形点
//        mPaint.setStrokeWidth(20);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(50, 50, mPaint);
//        //方形点
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(150, 150, mPaint);
//
//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawArc(200, 100, 800, 500, -110, 100, true, mPaint); // 绘制扇形
//        canvas.drawArc(200, 100, 800, 500, 20, 140, false, mPaint); // 绘制弧形
//        mPaint.setStyle(Paint.Style.STROKE); // 画线模式
//        canvas.drawArc(200, 100, 800, 500, 180, 60, false, mPaint); // 绘制不封口的弧形
//
//        canvas.drawColor(Color.parseColor("#88880000"));

    }
}
