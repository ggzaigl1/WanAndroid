package com.fy.baselibrary.base.recyclerv;

/**
 * 自定义 RecycclerView item 点击接口
 * Created by fangs on 2017/11/20.
 */
public interface OnItemClickListner<Item> {

    /**
     * 定义回调方法
     * @param item
     */
    void onItemClick(Item item);
}
