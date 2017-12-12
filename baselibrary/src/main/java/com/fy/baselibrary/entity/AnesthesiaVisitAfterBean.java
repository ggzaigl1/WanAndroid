package com.fy.baselibrary.entity;

import java.io.Serializable;

/**
 * Created by QKun on 2017/11/29.
 * 患者麻醉访视记录 麻醉术后访视
 */

public class AnesthesiaVisitAfterBean implements Serializable {

    /**
     * PA_ID : 610595
     * Token : 5E3C6BE70201E56CA94459C4763A8B27
     * OrderType : 2
     * VitalSign_R :
     * VitalSign_BP :
     * Consciousness : 清醒
     * VisitDocID :
     * VisitDoc :
     * VisitDocDate : 2017/11/2 16:47:07
     * VisitNurseID : 610
     * VisitNurse : 庄松鑫
     * VisitNurseDate : 2017/11/2 16:47:07
     * AnesthesiaBefore : null
     * AnesthesiaAfter : {"SurgeryEndDate":"2017年11月01日 16:46","Postoperative":"病房","VitalSign_HR":"","VitalSign_SPO2":"","BreathingStatus":"","GA":"","GA_BLReason":"","SA":"","OffGoingPerson":"","OnComingPerson":"","VitalSign":"","RecConsciousness":"","MentalStatus":"","GA_KP":"","GA_JZL":"","GA_EO":"","SA_TT":"","SA_NZL":"","SA_SXGY":"","NB_SS":"","NB_HGY":"","OtherAf":"","Exceptional":"","OrderOtherAf":""}
     * DateKey : 1509612426267
     */

    private String PA_ID = "";
    private String Token = "";
    private String OrderType = "";
    private String VitalSign_R = "";
    private String VitalSign_BP = "";
    private String Consciousness = "";
    private String VisitDocID = "";
    private String VisitDoc = "";
    private String VisitDocDate = "";
    private String VisitNurseID = "";
    private String VisitNurse = "";
    private String VisitNurseDate = "";
    private Object AnesthesiaBefore = "";
    private AnesthesiaAfterBean AnesthesiaAfter;
    private String DateKey = "";

    public String getPA_ID() {
        return PA_ID;
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

    public Object getAnesthesiaBefore() {
        return AnesthesiaBefore;
    }

    public void setAnesthesiaBefore(Object AnesthesiaBefore) {
        this.AnesthesiaBefore = AnesthesiaBefore;
    }

    public AnesthesiaAfterBean getAnesthesiaAfter() {
        return AnesthesiaAfter;
    }

    public void setAnesthesiaAfter(AnesthesiaAfterBean AnesthesiaAfter) {
        this.AnesthesiaAfter = AnesthesiaAfter;
    }

    public String getDateKey() {
        return DateKey;
    }

    public void setDateKey(String DateKey) {
        this.DateKey = DateKey;
    }

    public static class AnesthesiaAfterBean implements Serializable {
        /**
         * SurgeryEndDate : 2017年11月01日 16:46
         * Postoperative : 病房
         * VitalSign_HR :
         * VitalSign_SPO2 :
         * BreathingStatus :
         * GA :
         * GA_BLReason :
         * SA :
         * OffGoingPerson :
         * OnComingPerson :
         * VitalSign :
         * RecConsciousness :
         * MentalStatus :
         * GA_KP :
         * GA_JZL :
         * GA_EO :
         * SA_TT :
         * SA_NZL :
         * SA_SXGY :
         * NB_SS :
         * NB_HGY :
         * OtherAf :
         * Exceptional :
         * OrderOtherAf :
         */

        private String SurgeryEndDate = "";
        private String Postoperative = "";
        private String VitalSign_HR = "";
        private String VitalSign_SPO2 = "";
        private String BreathingStatus = "";
        private String GA = "";
        private String GA_BLReason = "";
        private String SA = "";
        private String OffGoingPerson = "";
        private String OnComingPerson = "";
        private String VitalSign = "";
        private String RecConsciousness = "";
        private String MentalStatus = "";
        private String GA_KP = "";
        private String GA_JZL = "";
        private String GA_EO = "";
        private String SA_TT = "";
        private String SA_NZL = "";
        private String SA_SXGY = "";
        private String NB_SS = "";
        private String NB_HGY = "";
        private String OtherAf = "";
        private String Exceptional = "";
        private String OrderOtherAf = "";

        public String getSurgeryEndDate() {
            return SurgeryEndDate;
        }

        public void setSurgeryEndDate(String SurgeryEndDate) {
            this.SurgeryEndDate = SurgeryEndDate;
        }

        public String getPostoperative() {
            return Postoperative;
        }

        public void setPostoperative(String Postoperative) {
            this.Postoperative = Postoperative;
        }

        public String getVitalSign_HR() {
            return VitalSign_HR;
        }

        public void setVitalSign_HR(String VitalSign_HR) {
            this.VitalSign_HR = VitalSign_HR;
        }

        public String getVitalSign_SPO2() {
            return VitalSign_SPO2;
        }

        public void setVitalSign_SPO2(String VitalSign_SPO2) {
            this.VitalSign_SPO2 = VitalSign_SPO2;
        }

        public String getBreathingStatus() {
            return BreathingStatus;
        }

        public void setBreathingStatus(String BreathingStatus) {
            this.BreathingStatus = BreathingStatus;
        }

        public String getGA() {
            return GA;
        }

        public void setGA(String GA) {
            this.GA = GA;
        }

        public String getGA_BLReason() {
            return GA_BLReason;
        }

        public void setGA_BLReason(String GA_BLReason) {
            this.GA_BLReason = GA_BLReason;
        }

        public String getSA() {
            return SA;
        }

        public void setSA(String SA) {
            this.SA = SA;
        }

        public String getOffGoingPerson() {
            return OffGoingPerson;
        }

        public void setOffGoingPerson(String OffGoingPerson) {
            this.OffGoingPerson = OffGoingPerson;
        }

        public String getOnComingPerson() {
            return OnComingPerson;
        }

        public void setOnComingPerson(String OnComingPerson) {
            this.OnComingPerson = OnComingPerson;
        }

        public String getVitalSign() {
            return VitalSign;
        }

        public void setVitalSign(String VitalSign) {
            this.VitalSign = VitalSign;
        }

        public String getRecConsciousness() {
            return RecConsciousness;
        }

        public void setRecConsciousness(String RecConsciousness) {
            this.RecConsciousness = RecConsciousness;
        }

        public String getMentalStatus() {
            return MentalStatus;
        }

        public void setMentalStatus(String MentalStatus) {
            this.MentalStatus = MentalStatus;
        }

        public String getGA_KP() {
            return GA_KP;
        }

        public void setGA_KP(String GA_KP) {
            this.GA_KP = GA_KP;
        }

        public String getGA_JZL() {
            return GA_JZL;
        }

        public void setGA_JZL(String GA_JZL) {
            this.GA_JZL = GA_JZL;
        }

        public String getGA_EO() {
            return GA_EO;
        }

        public void setGA_EO(String GA_EO) {
            this.GA_EO = GA_EO;
        }

        public String getSA_TT() {
            return SA_TT;
        }

        public void setSA_TT(String SA_TT) {
            this.SA_TT = SA_TT;
        }

        public String getSA_NZL() {
            return SA_NZL;
        }

        public void setSA_NZL(String SA_NZL) {
            this.SA_NZL = SA_NZL;
        }

        public String getSA_SXGY() {
            return SA_SXGY;
        }

        public void setSA_SXGY(String SA_SXGY) {
            this.SA_SXGY = SA_SXGY;
        }

        public String getNB_SS() {
            return NB_SS;
        }

        public void setNB_SS(String NB_SS) {
            this.NB_SS = NB_SS;
        }

        public String getNB_HGY() {
            return NB_HGY;
        }

        public void setNB_HGY(String NB_HGY) {
            this.NB_HGY = NB_HGY;
        }

        public String getOtherAf() {
            return OtherAf;
        }

        public void setOtherAf(String OtherAf) {
            this.OtherAf = OtherAf;
        }

        public String getExceptional() {
            return Exceptional;
        }

        public void setExceptional(String Exceptional) {
            this.Exceptional = Exceptional;
        }

        public String getOrderOtherAf() {
            return OrderOtherAf;
        }

        public void setOrderOtherAf(String OrderOtherAf) {
            this.OrderOtherAf = OrderOtherAf;
        }
    }
}

