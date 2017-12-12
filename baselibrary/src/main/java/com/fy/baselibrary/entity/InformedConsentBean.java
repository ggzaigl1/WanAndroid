package com.fy.baselibrary.entity;

import java.io.Serializable;

/**
 * Created by Gab on 2017/11/30 0030.
 * 查询知情同意书
 */

public class InformedConsentBean implements Serializable {

    /**
     * Token : 2613665883AA7CBE8497832B32112B77
     * OrderType : 3
     * DateKey : 1510556455281
     * PA_ID : 608836
     * PatFamilyURL : http://192.168.100.30:8099/qm_file/20171113030056_95431899-ab43-43de-a507-2af94eaa5bde.png
     * PatFamilyType : BBC
     * EXENurseID : 456
     * EXENurseName : 杜宋耿
     * SignDate : 2017年11月13日
     * Nodes : 100010101;100010102;100010202;100010304;100010406;
     * Opinion :
     * Remark :
     */

    private String Token = "";
    private String OrderType = "";
    private String DateKey = "";
    private String PA_ID = "";
    private String PatFamilyURL = "";
    private String PatFamilyType = "";
    private String EXENurseID = "";
    private String EXENurseName = "";
    private String SignDate = "";
    private String Nodes = "";
    private String Opinion = "";
    private String Remark = "";

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String OrderType) {
        this.OrderType = OrderType;
    }

    public String getDateKey() {
        return DateKey;
    }

    public void setDateKey(String DateKey) {
        this.DateKey = DateKey;
    }

    public String getPA_ID() {
        return PA_ID;
    }

    public void setPA_ID(String PA_ID) {
        this.PA_ID = PA_ID;
    }

    public String getPatFamilyURL() {
        return PatFamilyURL;
    }

    public void setPatFamilyURL(String PatFamilyURL) {
        this.PatFamilyURL = PatFamilyURL;
    }

    public String getPatFamilyType() {
        return PatFamilyType;
    }

    public void setPatFamilyType(String PatFamilyType) {
        this.PatFamilyType = PatFamilyType;
    }

    public String getEXENurseID() {
        return EXENurseID;
    }

    public void setEXENurseID(String EXENurseID) {
        this.EXENurseID = EXENurseID;
    }

    public String getEXENurseName() {
        return EXENurseName;
    }

    public void setEXENurseName(String EXENurseName) {
        this.EXENurseName = EXENurseName;
    }

    public String getSignDate() {
        return SignDate;
    }

    public void setSignDate(String SignDate) {
        this.SignDate = SignDate;
    }

    public String getNodes() {
        return Nodes;
    }

    public void setNodes(String Nodes) {
        this.Nodes = Nodes;
    }

    public String getOpinion() {
        return Opinion;
    }

    public void setOpinion(String Opinion) {
        this.Opinion = Opinion;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    @Override
    public String toString() {
        return "InformedConsentBean{" +
                "Token='" + Token + '\'' +
                ", OrderType='" + OrderType + '\'' +
                ", DateKey='" + DateKey + '\'' +
                ", PA_ID='" + PA_ID + '\'' +
                ", PatFamilyURL='" + PatFamilyURL + '\'' +
                ", PatFamilyType='" + PatFamilyType + '\'' +
                ", EXENurseID='" + EXENurseID + '\'' +
                ", EXENurseName='" + EXENurseName + '\'' +
                ", SignDate='" + SignDate + '\'' +
                ", Nodes='" + Nodes + '\'' +
                ", Opinion='" + Opinion + '\'' +
                ", Remark='" + Remark + '\'' +
                '}';
    }
}
