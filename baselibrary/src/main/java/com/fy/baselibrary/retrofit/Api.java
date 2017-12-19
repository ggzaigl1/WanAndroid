package com.fy.baselibrary.retrofit;

/**
 * api
 * Created by fangs on 2017/5/15.
 */
public interface Api {

    String BASE_URL = "http://192.168.100.30:8099/";//服务器 的 根目录地址
//    String BASE_URL = "http://v.juhe.cn/";//服务器 的 根目录地址

    int DEFAULT_MILLISECONDS = 60000;             //默认的超时时间


    String DocAndNurse = "DocAndNurse";                 //医护人员登入接口
    String GetZyPatient = "GetZyPatient";               //获取住院患者信息
    String GetCyPatient = "GetCyPatient";               //获取出院患者信息
    String uploadPostFile = "uploadPostFile";           //多图片上传

}
