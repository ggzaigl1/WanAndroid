package com.example.gab.babylove.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gab on 2018/3/14 0014.
 */

public class OrListBean implements Serializable {

    List<GankBean.ResultsBean> data;

    public List<GankBean.ResultsBean> getData() {
        return data;
    }

    public void setData(List<GankBean.ResultsBean> data) {
        this.data = data;
    }

    public OrListBean(List<GankBean.ResultsBean> data) {
        this.data = data;
    }
}
