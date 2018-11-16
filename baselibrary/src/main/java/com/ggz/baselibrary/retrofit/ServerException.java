package com.ggz.baselibrary.retrofit;

/**
 * 自定义 网络请求 异常
 *
 * @author fangs
 * @date 2017/12/12
 */
public class ServerException extends Exception{

    public int code;

    ServerException(String msg, int code){
        super(msg);
        this.code = code;
    }
}
