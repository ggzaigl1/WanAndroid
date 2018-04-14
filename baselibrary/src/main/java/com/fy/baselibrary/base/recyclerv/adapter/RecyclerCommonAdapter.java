package com.fy.baselibrary.base.recyclerv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecyclerView 通用的Adapter
 * Created by fangs on 2017/7/31.
 */
public abstract class RecyclerCommonAdapter<Item> extends RecyclerView.Adapter<ViewHolder> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<Item> mDatas;
    protected LayoutInflater mInflater;

    protected SparseBooleanArray mSelectedPositions;//保存多选 数据

    public RecyclerCommonAdapter(Context context, int layoutId, List<Item> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        mSelectedPositions = new SparseBooleanArray();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.get(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mDatas.get(position), position);
    }

    /**
     * 渲染数据到View中
     * @param holder
     * @param t
     */
    public abstract void convert(ViewHolder holder, Item t, int position);

    @Override
    public int getItemCount() {
        return null == mDatas ? 0 : mDatas.size();
    }

    public List<Item> getmDatas() {
        return this.mDatas;
    }

    public void setmDatas(List<Item> mDatas) {
        this.mDatas = mDatas;
    }


    /**
     * 添加data，从指定location中加入
     * @param location
     * @param item
     */
    public void addData(int location, Item item){
        this.mDatas.add(location, item);
    }

    /**
     * 追加一个集合
     * @param data
     */
    public void addData(List<Item> data){
        this.mDatas.addAll(data);
    }

    /**
     * 插入一个集合
     * @param location
     * @param data
     */
    public void addData(int location, List<Item> data){
        this.mDatas.addAll(location, data);
    }

    /**
     * 替换指定位置的 data
     * @param location
     * @param item
     */
    public void setData(int location, Item item) {
        this.mDatas.set(location, item);
    }

    /**
     * 删除指定 Location 位置的data
     * @param location
     */
    public void removeData(int location){
        if (location < getItemCount()) this.mDatas.remove(location);
    }

    /**
     *  从列表中移除所有元素
     */
    public void cleanData(){
        this.mDatas.clear();
    }

    /**
     * 清理 多选状态
     */
    public void cleanChecked(){
        mSelectedPositions.clear();
    }

    /**
     * 设置给定位置条目的选择状态
     * @param array
     * @param position
     * @param isChecked
     */
    protected void setItemChecked(SparseBooleanArray array, int position, boolean isChecked){
        array.put(position, isChecked);
    }

    /**
     * 根据位置判断条目是否选中
     * @param position
     * @return
     */
    protected boolean isItemChecked(SparseBooleanArray array, int position) {
        return array.get(position);
    }

    /**
     * 设置全选 or 反选
     * @param isAllSelect
     */
    public void setIsAllSelect(boolean isAllSelect){
        for (int i = 0; i < getItemCount(); i++){
            setItemChecked(mSelectedPositions, i, isAllSelect);
        }

        notifyDataSetChanged();
    }

    public SparseBooleanArray getmSelectedPositions() {
        return mSelectedPositions;
    }
}
