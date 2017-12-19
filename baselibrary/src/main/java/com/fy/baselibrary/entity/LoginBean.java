package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 登录实体类
 * Created by Gab on 2017/8/31 0031.
 */

public class LoginBean implements Serializable {

    /**
     * UserID : 112
     * UserName : 钟隆欣
     * DeptID : 929
     * DeptName : 血透室
     * TelPhone :
     * JobCode :
     * OP_Limit : 0
     * Recipe_Pot : 1
     * DM_Flag : 1
     * Status : 0
     * Token : 5A77522C219B6F326030AAAA60D31C03
     * IsDoc : 1
     * UserImg :
     * Docs : [{"UserID":"039","UserName":"钟隆欣"},{"UserID":"658","UserName":"辜若玲"},{"UserID":"570","UserName":"方宝龙"},{"UserID":"564","UserName":"陈佳"},{"UserID":"112","UserName":"钟隆欣"}]
     */

    private String UserID = "";
    private String UserName = "";
    private String DeptID = "";
    private String DeptName = "";
    private String TelPhone = "";
    private String JobCode = "";
    private String OP_Limit = "";
    private String Recipe_Pot = "";
    private String DM_Flag = "";
    private String Status = "";
    private String Token = "";
    private String IsDoc = "";
    private String UserImg = "";
    private List<DocsBean> Docs;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getDeptID() {
        return DeptID;
    }

    public void setDeptID(String DeptID) {
        this.DeptID = DeptID;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String DeptName) {
        this.DeptName = DeptName;
    }

    public String getTelPhone() {
        return TelPhone;
    }

    public void setTelPhone(String TelPhone) {
        this.TelPhone = TelPhone;
    }

    public String getJobCode() {
        return JobCode;
    }

    public void setJobCode(String JobCode) {
        this.JobCode = JobCode;
    }

    public String getOP_Limit() {
        return OP_Limit;
    }

    public void setOP_Limit(String OP_Limit) {
        this.OP_Limit = OP_Limit;
    }

    public String getRecipe_Pot() {
        return Recipe_Pot;
    }

    public void setRecipe_Pot(String Recipe_Pot) {
        this.Recipe_Pot = Recipe_Pot;
    }

    public String getDM_Flag() {
        return DM_Flag;
    }

    public void setDM_Flag(String DM_Flag) {
        this.DM_Flag = DM_Flag;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getIsDoc() {
        return IsDoc;
    }

    public void setIsDoc(String IsDoc) {
        this.IsDoc = IsDoc;
    }

    public String getUserImg() {
        return UserImg;
    }

    public void setUserImg(String UserImg) {
        this.UserImg = UserImg;
    }

    public List<DocsBean> getDocs() {
        return Docs;
    }

    public void setDocs(List<DocsBean> Docs) {
        this.Docs = Docs;
    }

    public static class DocsBean implements Serializable {
        /**
         * UserID : 039
         * UserName : 钟隆欣
         */

        private String UserID = "";
        private String UserName = "";

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "UserID='" + UserID + '\'' +
                ", UserName='" + UserName + '\'' +
                ", DeptID='" + DeptID + '\'' +
                ", DeptName='" + DeptName + '\'' +
                ", TelPhone='" + TelPhone + '\'' +
                ", JobCode='" + JobCode + '\'' +
                ", OP_Limit='" + OP_Limit + '\'' +
                ", Recipe_Pot='" + Recipe_Pot + '\'' +
                ", DM_Flag='" + DM_Flag + '\'' +
                ", Status='" + Status + '\'' +
                ", Token='" + Token + '\'' +
                ", IsDoc='" + IsDoc + '\'' +
                ", UserImg='" + UserImg + '\'' +
                ", Docs=" + Docs +
                '}';
    }
}
