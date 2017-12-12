package com.fy.baselibrary.base.recyclerv.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView 通用分割线
 * Created by fangs on 2017/7/4.
 */
public class CommonDivider extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{ android.R.attr.listDivider };

    private DividerParams params;

    /** 用于绘制间隔样式 */
    private Drawable mDivider;

    public CommonDivider(Context context, DividerParams params) {
        this.params = params;
        // 获取默认主题的属性
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        // 绘制间隔，每一个item，绘制右边和下方间隔样式
        int spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        int orientation = ((GridLayoutManager) parent.getLayoutManager()).getOrientation();
        boolean isDrawHorizontalDivider;
        boolean isDrawVerticalDivider;

        int childCount = parent.getChildCount();
        int extra = childCount % spanCount;
        extra = extra == 0 ? spanCount : extra;

        for (int i = 0; i < childCount; i++) {
            isDrawVerticalDivider = true;
            isDrawHorizontalDivider = true;

            // 如果是竖直方向
            if (orientation == OrientationHelper.VERTICAL){
                if ((i + 1) % spanCount == 0){//最右边一列不绘制竖直方向的间隔
                    isDrawVerticalDivider = false;
                }

                if (i >= childCount - extra){//最后一行不绘制水平方向间隔
//                    isDrawHorizontalDivider = false;
                }
            } else if (orientation == OrientationHelper.HORIZONTAL){// 如果是水平方向
                if (i >= childCount - extra){//最后一列不绘制竖直方向间隔
                    isDrawVerticalDivider = false;
                }

                if ((i + 1) % spanCount == 0) {//最下面一行不绘制水平方向的间隔
                    isDrawHorizontalDivider = false;
                }
            }

            if (isDrawHorizontalDivider) {
                drawHorizontalDivider(c, parent, i);
            }

            if (isDrawVerticalDivider) {
                drawVerticalDivider(c, parent, i);
            }
        }
    }

    /**
     * 绘制竖直间隔线
     *
     * @param canvas
     * @param parent   父布局，RecyclerView
     * @param position irem在父布局中所在的位置
     */
    private void drawVerticalDivider(Canvas canvas, RecyclerView parent, int position) {
        final View child = parent.getChildAt(position);
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        final int top = child.getTop();
        final int bottom = child.getBottom() + mDivider.getIntrinsicHeight();
        final int left = child.getRight() + params.rightMargin;
        final int right = left + mDivider.getIntrinsicWidth();

        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(canvas);
    }

    /**
     * 绘制水平间隔线
     *
     * @param canvas
     * @param parent   父布局，RecyclerView
     * @param position item在父布局中所在的位置
     */
    private void drawHorizontalDivider(Canvas canvas, RecyclerView parent, int position) {
        final View child = parent.getChildAt(position);
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        final int top = child.getBottom() + params.bottomMargin;
        final int bottom = top + mDivider.getIntrinsicHeight();

        final int left = child.getLeft();
        final int right = child.getRight();

        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(canvas);
    }
}
