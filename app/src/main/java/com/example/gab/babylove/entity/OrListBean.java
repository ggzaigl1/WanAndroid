package com.example.gab.babylove.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Gab
 * @date 2018/3/14 0014
 */

public class OrListBean implements Serializable {

    List<GanBean.ResultsBean> data;

    public List<GanBean.ResultsBean> getData() {
        return data;
    }

    public void setData(List<GanBean.ResultsBean> data) {
        this.data = data;
    }

    public OrListBean(List<GanBean.ResultsBean> data) {
        this.data = data;
    }
}
