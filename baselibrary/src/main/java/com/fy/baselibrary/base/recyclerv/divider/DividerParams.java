package com.fy.baselibrary.base.recyclerv.divider;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * RecyclerView 通用分割线 参数类
 * Created by fangs on 2017/7/4.
 */
public class DividerParams {

    /** 列表的方向，水平(LinearLayoutManager.HORIZONTAL)/竖直(LinearLayoutManager.VERTICAL)*/
    public int mOrientation = LinearLayoutManager.VERTICAL;

    /** 布局管理器 类型 （0：LinearLayoutManager，1：GridLayoutManager，2：taggeredGridLayoutManager） */
    public int layoutManager = 0;

    public DividerParams setmOrientation(int mOrientation) {
        this.mOrientation = mOrientation;
        return this;
    }

    public DividerParams setLayoutManager(int layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    public CommonDivider create(Context context){
        return new CommonDivider(context, this);
    }
}
