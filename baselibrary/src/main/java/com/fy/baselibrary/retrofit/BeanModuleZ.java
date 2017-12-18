package com.fy.baselibrary.retrofit;

import java.io.Serializable;

/**
 * 网络请求 返回数据 格式化对象
 * Created by fangs on 2017/11/6.
 */
public class BeanModuleZ<T> implements Serializable{

    private int error_code;
    private String reason = "";
    private String ResultMes = "测试失败情况";
    private T ResultData;


    public boolean isSuccess(){
        return error_code == 1;
    }

    public int getResultCode() {
        return error_code;
    }

    public void setResultCode(int resultCode) {
        error_code = resultCode;
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
        return reason;
    }

    public void setReason(String reason) {
        reason = reason;
    }

    @Override
    public String toString() {
        return "BeanModule{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", ResultMes='" + ResultMes + '\'' +
                ", ResultData=" + ResultData +
                '}';
    }
}
