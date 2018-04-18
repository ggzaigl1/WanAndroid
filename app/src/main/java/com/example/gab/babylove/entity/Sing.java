package com.example.gab.babylove.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/14.
 */
public class Sing implements Serializable{


    /**
     * pwd : ssss
     * key : kkk
     * sign : sign
     */

    private String pwd;
    private String key;
    private String sign;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
