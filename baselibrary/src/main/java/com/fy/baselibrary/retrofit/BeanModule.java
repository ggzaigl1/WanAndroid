package com.fy.baselibrary.retrofit;

import java.io.Serializable;

/**
 * 网络请求 返回数据 格式化对象
 * Created by fangs on 2017/11/6.
 */
public class BeanModule<T> implements Serializable{

    private int ResultCode;
    private String ResultToken = "";
    private String ResultMes = "测试失败情况";
    private T ResultData;


    public boolean isSuccess(){
        return ResultCode == 1;
    }

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int resultCode) {
        ResultCode = resultCode;
    }

    public String getResultMes() {
        return ResultMes;
    }

    public void setResultMes(String resultMes) {
        ResultMes = resultMes;
    }

    public T getResultData() {
        return ResultData;
    }

    public void setResultData(T resultData) {
        ResultData = resultData;
    }

    public String getResultToken() {
        return ResultToken;
    }

    public void setResultToken(String resultToken) {
        ResultToken = resultToken;
    }

    @Override
    public String toString() {
        return "BeanModule{" +
                "ResultCode=" + ResultCode +
                ", ResultToken='" + ResultToken + '\'' +
                ", ResultMes='" + ResultMes + '\'' +
                ", ResultData=" + ResultData +
                '}';
    }
}
