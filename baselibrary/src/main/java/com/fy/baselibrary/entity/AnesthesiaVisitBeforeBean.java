package com.fy.baselibrary.entity;

import java.io.Serializable;

/**
 * Created by QKun on 2017/11/29.
 * 患者麻醉访视记录 麻醉术前访视
 */

public class AnesthesiaVisitBeforeBean implements Serializable {

    /**
     * PA_ID : 610595
     * Token : 5E3C6BE70201E56CA94459C4763A8B27
     * OrderType : 1
     * VitalSign_R :
     * VitalSign_BP :
     * Consciousness :
     * VisitDocID :
     * VisitDoc :
     * VisitDocDate : 2017/11/2 11:00:06
     * VisitNurseID : 610
     * VisitNurse : 庄松鑫
     * VisitNurseDate : 2017/11/2 11:00:06
     * AnesthesiaBefore : {"In_Dis":"","ModusOperandi":"","SurgeryDate":"","IsHealthy":"","HasHXSick":"","HasAnesthesia":"","VitalSign_T":"","VitalSign_P":"","ToothStatus":"","XFFStatus":"","VertebraStatus":"","VertebraStatus_Sick":null,"MallampatiGrade":"","HeartGrade":"","ECG":"","ECG_YC":null,"CXR":"","Lung":"","CBC":"","Coagulation":"","LFT":"","Renal":"","GLU":"","OtherEx":"","ASA":"II","OrderOther":"(1) 术前8小时禁饮禁食","AnesthesiaType":"","AnesthesiaIndication":"","AnesthesiaOther":"5、其他：不能不"}
     * AnesthesiaAfter : null
     * DateKey : 1509591604967
     */

    private String PA_ID="";
    private String Token="";
    private String OrderType="";
    private String VitalSign_R="";
    private String VitalSign_BP="";
    private String Consciousness="";
    private String VisitDocID="";
    private String VisitDoc="";
    private String VisitDocDate="";
    private String VisitNurseID="";
    private String VisitNurse="";
    private String VisitNurseDate="";
    private AnesthesiaBeforeBean AnesthesiaBefore;
    private Object AnesthesiaAfter="";
    private String DateKey="";

    public String getPA_ID() {
        return PA_ID="";
    }

    public void setPA_ID(String PA_ID) {
        this.PA_ID = PA_ID;
    }

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

    public String getVitalSign_R() {
        return VitalSign_R;
    }

    public void setVitalSign_R(String VitalSign_R) {
        this.VitalSign_R = VitalSign_R;
    }

    public String getVitalSign_BP() {
        return VitalSign_BP;
    }

    public void setVitalSign_BP(String VitalSign_BP) {
        this.VitalSign_BP = VitalSign_BP;
    }

    public String getConsciousness() {
        return Consciousness;
    }

    public void setConsciousness(String Consciousness) {
        this.Consciousness = Consciousness;
    }

    public String getVisitDocID() {
        return VisitDocID;
    }

    public void setVisitDocID(String VisitDocID) {
        this.VisitDocID = VisitDocID;
    }

    public String getVisitDoc() {
        return VisitDoc;
    }

    public void setVisitDoc(String VisitDoc) {
        this.VisitDoc = VisitDoc;
    }

    public String getVisitDocDate() {
        return VisitDocDate;
    }

    public void setVisitDocDate(String VisitDocDate) {
        this.VisitDocDate = VisitDocDate;
    }

    public String getVisitNurseID() {
        return VisitNurseID;
    }

    public void setVisitNurseID(String VisitNurseID) {
        this.VisitNurseID = VisitNurseID;
    }

    public String getVisitNurse() {
        return VisitNurse;
    }

    public void setVisitNurse(String VisitNurse) {
        this.VisitNurse = VisitNurse;
    }

    public String getVisitNurseDate() {
        return VisitNurseDate;
    }

    public void setVisitNurseDate(String VisitNurseDate) {
        this.VisitNurseDate = VisitNurseDate;
    }

    public AnesthesiaBeforeBean getAnesthesiaBefore() {
        return AnesthesiaBefore;
    }

    public void setAnesthesiaBefore(AnesthesiaBeforeBean AnesthesiaBefore) {
        this.AnesthesiaBefore = AnesthesiaBefore;
    }

    public Object getAnesthesiaAfter() {
        return AnesthesiaAfter;
    }

    public void setAnesthesiaAfter(Object AnesthesiaAfter) {
        this.AnesthesiaAfter = AnesthesiaAfter;
    }

    public String getDateKey() {
        return DateKey;
    }

    public void setDateKey(String DateKey) {
        this.DateKey = DateKey;
    }

    public static class AnesthesiaBeforeBean implements Serializable{
        /**
         * In_Dis :
         * ModusOperandi :
         * SurgeryDate :
         * IsHealthy :
         * HasHXSick :
         * HasAnesthesia :
         * VitalSign_T :
         * VitalSign_P :
         * ToothStatus :
         * XFFStatus :
         * VertebraStatus :
         * VertebraStatus_Sick : null
         * MallampatiGrade :
         * HeartGrade :
         * ECG :
         * ECG_YC : null
         * CXR :
         * Lung :
         * CBC :
         * Coagulation :
         * LFT :
         * Renal :
         * GLU :
         * OtherEx :
         * ASA : II
         * OrderOther : (1) 术前8小时禁饮禁食
         * AnesthesiaType :
         * AnesthesiaIndication :
         * AnesthesiaOther : 5、其他：不能不
         */

        private String In_Dis="";
        private String ModusOperandi="";
        private String SurgeryDate="";
        private String IsHealthy="";
        private String HasHXSick="";
        private String HasAnesthesia="";
        private String VitalSign_T="";
        private String VitalSign_P="";
        private String ToothStatus="";
        private String XFFStatus="";
        private String VertebraStatus="";
        private Object VertebraStatus_Sick="";
        private String MallampatiGrade="";
        private String HeartGrade="";
        private String ECG="";
        private Object ECG_YC="";
        private String CXR="";
        private String Lung="";
        private String CBC="";
        private String Coagulation="";
        private String LFT="";
        private String Renal="";
        private String GLU="";
        private String OtherEx="";
        private String ASA="";
        private String OrderOther="";
        private String AnesthesiaType="";
        private String AnesthesiaIndication="";
        private String AnesthesiaOther="";

        public String getIn_Dis() {
            return In_Dis;
        }

        public void setIn_Dis(String In_Dis) {
            this.In_Dis = In_Dis;
        }

        public String getModusOperandi() {
            return ModusOperandi;
        }

        public void setModusOperandi(String ModusOperandi) {
            this.ModusOperandi = ModusOperandi;
        }

        public String getSurgeryDate() {
            return SurgeryDate;
        }

        public void setSurgeryDate(String SurgeryDate) {
            this.SurgeryDate = SurgeryDate;
        }

        public String getIsHealthy() {
            return IsHealthy;
        }

        public void setIsHealthy(String IsHealthy) {
            this.IsHealthy = IsHealthy;
        }

        public String getHasHXSick() {
            return HasHXSick;
        }

        public void setHasHXSick(String HasHXSick) {
            this.HasHXSick = HasHXSick;
        }

        public String getHasAnesthesia() {
            return HasAnesthesia;
        }

        public void setHasAnesthesia(String HasAnesthesia) {
            this.HasAnesthesia = HasAnesthesia;
        }

        public String getVitalSign_T() {
            return VitalSign_T;
        }

        public void setVitalSign_T(String VitalSign_T) {
            this.VitalSign_T = VitalSign_T;
        }

        public String getVitalSign_P() {
            return VitalSign_P;
        }

        public void setVitalSign_P(String VitalSign_P) {
            this.VitalSign_P = VitalSign_P;
        }

        public String getToothStatus() {
            return ToothStatus;
        }

        public void setToothStatus(String ToothStatus) {
            this.ToothStatus = ToothStatus;
        }

        public String getXFFStatus() {
            return XFFStatus;
        }

        public void setXFFStatus(String XFFStatus) {
            this.XFFStatus = XFFStatus;
        }

        public String getVertebraStatus() {
            return VertebraStatus;
        }

        public void setVertebraStatus(String VertebraStatus) {
            this.VertebraStatus = VertebraStatus;
        }

        public Object getVertebraStatus_Sick() {
            return VertebraStatus_Sick;
        }

        public void setVertebraStatus_Sick(Object VertebraStatus_Sick) {
            this.VertebraStatus_Sick = VertebraStatus_Sick;
        }

        public String getMallampatiGrade() {
            return MallampatiGrade;
        }

        public void setMallampatiGrade(String MallampatiGrade) {
            this.MallampatiGrade = MallampatiGrade;
        }

        public String getHeartGrade() {
            return HeartGrade;
        }

        public void setHeartGrade(String HeartGrade) {
            this.HeartGrade = HeartGrade;
        }

        public String getECG() {
            return ECG;
        }

        public void setECG(String ECG) {
            this.ECG = ECG;
        }

        public Object getECG_YC() {
            return ECG_YC;
        }

        public void setECG_YC(Object ECG_YC) {
            this.ECG_YC = ECG_YC;
        }

        public String getCXR() {
            return CXR;
        }

        public void setCXR(String CXR) {
            this.CXR = CXR;
        }

        public String getLung() {
            return Lung;
        }

        public void setLung(String Lung) {
            this.Lung = Lung;
        }

        public String getCBC() {
            return CBC;
        }

        public void setCBC(String CBC) {
            this.CBC = CBC;
        }

        public String getCoagulation() {
            return Coagulation;
        }

        public void setCoagulation(String Coagulation) {
            this.Coagulation = Coagulation;
        }

        public String getLFT() {
            return LFT;
        }

        public void setLFT(String LFT) {
            this.LFT = LFT;
        }

        public String getRenal() {
            return Renal;
        }

        public void setRenal(String Renal) {
            this.Renal = Renal;
        }

        public String getGLU() {
            return GLU;
        }

        public void setGLU(String GLU) {
            this.GLU = GLU;
        }

        public String getOtherEx() {
            return OtherEx;
        }

        public void setOtherEx(String OtherEx) {
            this.OtherEx = OtherEx;
        }

        public String getASA() {
            return ASA;
        }

        public void setASA(String ASA) {
            this.ASA = ASA;
        }

        public String getOrderOther() {
            return OrderOther;
        }

        public void setOrderOther(String OrderOther) {
            this.OrderOther = OrderOther;
        }

        public String getAnesthesiaType() {
            return AnesthesiaType;
        }

        public void setAnesthesiaType(String AnesthesiaType) {
            this.AnesthesiaType = AnesthesiaType;
        }

        public String getAnesthesiaIndication() {
            return AnesthesiaIndication;
        }

        public void setAnesthesiaIndication(String AnesthesiaIndication) {
            this.AnesthesiaIndication = AnesthesiaIndication;
        }

        public String getAnesthesiaOther() {
            return AnesthesiaOther;
        }

        public void setAnesthesiaOther(String AnesthesiaOther) {
            this.AnesthesiaOther = AnesthesiaOther;
        }
    }
}
