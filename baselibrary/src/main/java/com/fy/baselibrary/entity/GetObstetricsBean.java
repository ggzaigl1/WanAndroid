package com.fy.baselibrary.entity;

import java.io.Serializable;

/**
 * Created by Gab on 2017/11/30 0030.
 * 2.3.2.5.产科护理记录列表 产前待产记录
 */

public class GetObstetricsBean implements Serializable {

    /**
     * Token : 5E3C6BE70201E56CA94459C4763A8B27
     * DateKey : 1508896093188
     * OrderType : 1
     * PA_ID : 610595
     * In_Dis : 支气管肺炎
     * RecodeDate : 2017/10/25 09:48:13
     * VitalSign_BP : 0
     * VitalSign_PAndHR :
     * OC_UCS :
     * In_Project :
     * In_Amount :
     * Out_Project :
     * Out_Amount :
     * OtherSituation :
     * ExecutiveNurseID : 593
     * ExecutiveNurse : 钟山
     * QualityControlNurseID :
     * QualityControlNurse :
     * Antenatal : {"VitalSign_T":"","VitalSign_SP02":"","OC_FHt":"","OC_FM":"","OC_POTF":"","OC_UCI":"","OC_CD":"","OC_CS":"","OC_SO":"","OC_Caul":"","OC_AFI":"","Aerophose":"","KneeJerk":"","VitalSign_R":"","Gravidity":"","Parity":"","GestationalWeeks":""}
     * Postpartum : null
     * PostPartumLook : null
     */

    private String Token = "";
    private String DateKey = "";
    private String OrderType = "";
    private String PA_ID = "";
    private String In_Dis = "";
    private String RecodeDate = "";
    private String VitalSign_BP = "";
    private String VitalSign_PAndHR = "";
    private String OC_UCS = "";
    private String In_Project = "";
    private String In_Amount = "";
    private String Out_Project = "";
    private String Out_Amount = "";
    private String OtherSituation = "";
    private String ExecutiveNurseID = "";
    private String ExecutiveNurse = "";
    private String QualityControlNurseID = "";
    private String QualityControlNurse = "";
    private AntenatalBean Antenatal;
    private Object Postpartum = "";
    private Object PostPartumLook = "";

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getDateKey() {
        return DateKey;
    }

    public void setDateKey(String DateKey) {
        this.DateKey = DateKey;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String OrderType) {
        this.OrderType = OrderType;
    }

    public String getPA_ID() {
        return PA_ID;
    }

    public void setPA_ID(String PA_ID) {
        this.PA_ID = PA_ID;
    }

    public String getIn_Dis() {
        return In_Dis;
    }

    public void setIn_Dis(String In_Dis) {
        this.In_Dis = In_Dis;
    }

    public String getRecodeDate() {
        return RecodeDate;
    }

    public void setRecodeDate(String RecodeDate) {
        this.RecodeDate = RecodeDate;
    }

    public String getVitalSign_BP() {
        return VitalSign_BP;
    }

    public void setVitalSign_BP(String VitalSign_BP) {
        this.VitalSign_BP = VitalSign_BP;
    }

    public String getVitalSign_PAndHR() {
        return VitalSign_PAndHR;
    }

    public void setVitalSign_PAndHR(String VitalSign_PAndHR) {
        this.VitalSign_PAndHR = VitalSign_PAndHR;
    }

    public String getOC_UCS() {
        return OC_UCS;
    }

    public void setOC_UCS(String OC_UCS) {
        this.OC_UCS = OC_UCS;
    }

    public String getIn_Project() {
        return In_Project;
    }

    public void setIn_Project(String In_Project) {
        this.In_Project = In_Project;
    }

    public String getIn_Amount() {
        return In_Amount;
    }

    public void setIn_Amount(String In_Amount) {
        this.In_Amount = In_Amount;
    }

    public String getOut_Project() {
        return Out_Project;
    }

    public void setOut_Project(String Out_Project) {
        this.Out_Project = Out_Project;
    }

    public String getOut_Amount() {
        return Out_Amount;
    }

    public void setOut_Amount(String Out_Amount) {
        this.Out_Amount = Out_Amount;
    }

    public String getOtherSituation() {
        return OtherSituation;
    }

    public void setOtherSituation(String OtherSituation) {
        this.OtherSituation = OtherSituation;
    }

    public String getExecutiveNurseID() {
        return ExecutiveNurseID;
    }

    public void setExecutiveNurseID(String ExecutiveNurseID) {
        this.ExecutiveNurseID = ExecutiveNurseID;
    }

    public String getExecutiveNurse() {
        return ExecutiveNurse;
    }

    public void setExecutiveNurse(String ExecutiveNurse) {
        this.ExecutiveNurse = ExecutiveNurse;
    }

    public String getQualityControlNurseID() {
        return QualityControlNurseID;
    }

    public void setQualityControlNurseID(String QualityControlNurseID) {
        this.QualityControlNurseID = QualityControlNurseID;
    }

    public String getQualityControlNurse() {
        return QualityControlNurse;
    }

    public void setQualityControlNurse(String QualityControlNurse) {
        this.QualityControlNurse = QualityControlNurse;
    }

    public AntenatalBean getAntenatal() {
        return Antenatal;
    }

    public void setAntenatal(AntenatalBean Antenatal) {
        this.Antenatal = Antenatal;
    }

    public Object getPostpartum() {
        return Postpartum;
    }

    public void setPostpartum(Object Postpartum) {
        this.Postpartum = Postpartum;
    }

    public Object getPostPartumLook() {
        return PostPartumLook;
    }

    public void setPostPartumLook(Object PostPartumLook) {
        this.PostPartumLook = PostPartumLook;
    }

    public static class AntenatalBean implements Serializable {
        /**
         * VitalSign_T :
         * VitalSign_SP02 :
         * OC_FHt :
         * OC_FM :
         * OC_POTF :
         * OC_UCI :
         * OC_CD :
         * OC_CS :
         * OC_SO :
         * OC_Caul :
         * OC_AFI :
         * Aerophose :
         * KneeJerk :
         * VitalSign_R :
         * Gravidity :
         * Parity :
         * GestationalWeeks :
         */

        private String VitalSign_T = "";
        private String VitalSign_SP02 = "";
        private String OC_FHt = "";
        private String OC_FM = "";
        private String OC_POTF = "";
        private String OC_UCI = "";
        private String OC_CD = "";
        private String OC_CS = "";
        private String OC_SO = "";
        private String OC_Caul = "";
        private String OC_AFI = "";
        private String Aerophose = "";
        private String KneeJerk = "";
        private String VitalSign_R = "";
        private String Gravidity = "";
        private String Parity = "";
        private String GestationalWeeks = "";

        public String getVitalSign_T() {
            return VitalSign_T;
        }

        public void setVitalSign_T(String VitalSign_T) {
            this.VitalSign_T = VitalSign_T;
        }

        public String getVitalSign_SP02() {
            return VitalSign_SP02;
        }

        public void setVitalSign_SP02(String VitalSign_SP02) {
            this.VitalSign_SP02 = VitalSign_SP02;
        }

        public String getOC_FHt() {
            return OC_FHt;
        }

        public void setOC_FHt(String OC_FHt) {
            this.OC_FHt = OC_FHt;
        }

        public String getOC_FM() {
            return OC_FM;
        }

        public void setOC_FM(String OC_FM) {
            this.OC_FM = OC_FM;
        }

        public String getOC_POTF() {
            return OC_POTF;
        }

        public void setOC_POTF(String OC_POTF) {
            this.OC_POTF = OC_POTF;
        }

        public String getOC_UCI() {
            return OC_UCI;
        }

        public void setOC_UCI(String OC_UCI) {
            this.OC_UCI = OC_UCI;
        }

        public String getOC_CD() {
            return OC_CD;
        }

        public void setOC_CD(String OC_CD) {
            this.OC_CD = OC_CD;
        }

        public String getOC_CS() {
            return OC_CS;
        }

        public void setOC_CS(String OC_CS) {
            this.OC_CS = OC_CS;
        }

        public String getOC_SO() {
            return OC_SO;
        }

        public void setOC_SO(String OC_SO) {
            this.OC_SO = OC_SO;
        }

        public String getOC_Caul() {
            return OC_Caul;
        }

        public void setOC_Caul(String OC_Caul) {
            this.OC_Caul = OC_Caul;
        }

        public String getOC_AFI() {
            return OC_AFI;
        }

        public void setOC_AFI(String OC_AFI) {
            this.OC_AFI = OC_AFI;
        }

        public String getAerophose() {
            return Aerophose;
        }

        public void setAerophose(String Aerophose) {
            this.Aerophose = Aerophose;
        }

        public String getKneeJerk() {
            return KneeJerk;
        }

        public void setKneeJerk(String KneeJerk) {
            this.KneeJerk = KneeJerk;
        }

        public String getVitalSign_R() {
            return VitalSign_R;
        }

        public void setVitalSign_R(String VitalSign_R) {
            this.VitalSign_R = VitalSign_R;
        }

        public String getGravidity() {
            return Gravidity;
        }

        public void setGravidity(String Gravidity) {
            this.Gravidity = Gravidity;
        }

        public String getParity() {
            return Parity;
        }

        public void setParity(String Parity) {
            this.Parity = Parity;
        }

        public String getGestationalWeeks() {
            return GestationalWeeks;
        }

        public void setGestationalWeeks(String GestationalWeeks) {
            this.GestationalWeeks = GestationalWeeks;
        }
    }
}