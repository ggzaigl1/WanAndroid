package com.example.gab.babylove.entity;

/**
 * Created by Gab on 2017/12/18 0018.
 */

public class TraceBean {

    /** 时间 */
    private String acceptTime;
    /** 描述 */
    private String acceptStation;

    public TraceBean() {
    }

    public TraceBean(String acceptTime, String acceptStation) {
        this.acceptTime = acceptTime;
        this.acceptStation = acceptStation;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getAcceptStation() {
        return acceptStation;
    }

    public void setAcceptStation(String acceptStation) {
        this.acceptStation = acceptStation;
    }

}
