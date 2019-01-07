package com.example.gab.wanandroid_kotlin.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Gab
 * @date 2018/3/14 0014
 */

public class OrnamentalListBean implements Serializable {

    private List<CourseDetails.DataBean.GroupsBean.ActionsBean> data;

    public List<CourseDetails.DataBean.GroupsBean.ActionsBean> getData() {
        return data;
    }

    public void setData(List<CourseDetails.DataBean.GroupsBean.ActionsBean> data) {
        this.data = data;
    }

    public OrnamentalListBean(List<CourseDetails.DataBean.GroupsBean.ActionsBean> data) {
        this.data = data;
    }
}
