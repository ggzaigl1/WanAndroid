package com.fy.baselibrary.entity;

import java.io.Serializable;

/**
 * Created by Stefan on 2017/11/21.
 *  获取出院患者信息实体类
 */

public class CyPatientBean implements Serializable{

    /**
     * Token : 36880FF63EE0DF68ED10D2690CB591B9
     * Version : 1511255967
     * PatID : 607586
     * PatName : 郑加龙
     * Sex : M
     * BirthDay : 19420923
     * Age : Y75
     * Occupation :
     * PainID :
     * CertType :
     * CertNo :
     * Fee_Sour_Type : 5009
     * Fee_Sour_Name : 城乡联网
     * MedicareType :
     * MedicareNo :
     * Married : 3
     * Nation : 汉族
     * Address :
     * TelPhone :
     * Blood :
     * ContName :
     * ContTel :
     * ContAddr :
     * ContRelation :
     * BedStatus :
     * ZyDetail : {"In_Dept_ID":"915","In_Dept_Name":"内一科室","In_Date":"2017-09-23","In_Type":"","Wait_ID":"","In_Room":"","In_Bed":"1551","In_AreaID":"202","ICD":"I66.903","ICD_Name":"脑血栓形成","In_Aim":"","Illness_Status":"","Receive_Date":"","IsMedicare":"","Allergy":"","IsSick":"0","Attend_PSID":"","Attend_PSName":"","DoctorIn_PSID":"","DoctorIn_PSName":"","Pri_NurseID":"","Pri_NurseName":"","Prepay_Banalce":"18000","Total_Fee":"16556.30","Status":"2","Day_Type":"3","BedStatus":"","IsArrears":"0","Arrears":"1443.70","InTime":"1","IsTumour":"0","HaveOrders":"0","Out_Date":"2017-09-23","OutICD":"I62.001","OutICDName":"硬膜下血肿","OutDeptID":"915","VisitCount":"0"}
     */

    private String Token;
    private String Version;
    private String PatID;
    private String PatName;
    private String Sex;
    private String BirthDay;
    private String Age;
    private String Occupation;
    private String PainID;
    private String CertType;
    private String CertNo;
    private String Fee_Sour_Type;
    private String Fee_Sour_Name;
    private String MedicareType;
    private String MedicareNo;
    private String Married;
    private String Nation;
    private String Address;
    private String TelPhone;
    private String Blood;
    private String ContName;
    private String ContTel;
    private String ContAddr;
    private String ContRelation;
    private String BedStatus;
    private ZyDetailBean ZyDetail;

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getPatID() {
        return PatID;
    }

    public void setPatID(String PatID) {
        this.PatID = PatID;
    }

    public String getPatName() {
        return PatName;
    }

    public void setPatName(String PatName) {
        this.PatName = PatName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String BirthDay) {
        this.BirthDay = BirthDay;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String Occupation) {
        this.Occupation = Occupation;
    }

    public String getPainID() {
        return PainID;
    }

    public void setPainID(String PainID) {
        this.PainID = PainID;
    }

    public String getCertType() {
        return CertType;
    }

    public void setCertType(String CertType) {
        this.CertType = CertType;
    }

    public String getCertNo() {
        return CertNo;
    }

    public void setCertNo(String CertNo) {
        this.CertNo = CertNo;
    }

    public String getFee_Sour_Type() {
        return Fee_Sour_Type;
    }

    public void setFee_Sour_Type(String Fee_Sour_Type) {
        this.Fee_Sour_Type = Fee_Sour_Type;
    }

    public String getFee_Sour_Name() {
        return Fee_Sour_Name;
    }

    public void setFee_Sour_Name(String Fee_Sour_Name) {
        this.Fee_Sour_Name = Fee_Sour_Name;
    }

    public String getMedicareType() {
        return MedicareType;
    }

    public void setMedicareType(String MedicareType) {
        this.MedicareType = MedicareType;
    }

    public String getMedicareNo() {
        return MedicareNo;
    }

    public void setMedicareNo(String MedicareNo) {
        this.MedicareNo = MedicareNo;
    }

    public String getMarried() {
        return Married;
    }

    public void setMarried(String Married) {
        this.Married = Married;
    }

    public String getNation() {
        return Nation;
    }

    public void setNation(String Nation) {
        this.Nation = Nation;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getTelPhone() {
        return TelPhone;
    }

    public void setTelPhone(String TelPhone) {
        this.TelPhone = TelPhone;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String Blood) {
        this.Blood = Blood;
    }

    public String getContName() {
        return ContName;
    }

    public void setContName(String ContName) {
        this.ContName = ContName;
    }

    public String getContTel() {
        return ContTel;
    }

    public void setContTel(String ContTel) {
        this.ContTel = ContTel;
    }

    public String getContAddr() {
        return ContAddr;
    }

    public void setContAddr(String ContAddr) {
        this.ContAddr = ContAddr;
    }

    public String getContRelation() {
        return ContRelation;
    }

    public void setContRelation(String ContRelation) {
        this.ContRelation = ContRelation;
    }

    public String getBedStatus() {
        return BedStatus;
    }

    public void setBedStatus(String BedStatus) {
        this.BedStatus = BedStatus;
    }

    public ZyDetailBean getZyDetail() {
        return ZyDetail;
    }

    public void setZyDetail(ZyDetailBean ZyDetail) {
        this.ZyDetail = ZyDetail;
    }

    public static class ZyDetailBean implements Serializable{
        /**
         * In_Dept_ID : 915
         * In_Dept_Name : 内一科室
         * In_Date : 2017-09-23
         * In_Type :
         * Wait_ID :
         * In_Room :
         * In_Bed : 1551
         * In_AreaID : 202
         * ICD : I66.903
         * ICD_Name : 脑血栓形成
         * In_Aim :
         * Illness_Status :
         * Receive_Date :
         * IsMedicare :
         * Allergy :
         * IsSick : 0
         * Attend_PSID :
         * Attend_PSName :
         * DoctorIn_PSID :
         * DoctorIn_PSName :
         * Pri_NurseID :
         * Pri_NurseName :
         * Prepay_Banalce : 18000
         * Total_Fee : 16556.30
         * Status : 2
         * Day_Type : 3
         * BedStatus :
         * IsArrears : 0
         * Arrears : 1443.70
         * InTime : 1
         * IsTumour : 0
         * HaveOrders : 0
         * Out_Date : 2017-09-23
         * OutICD : I62.001
         * OutICDName : 硬膜下血肿
         * OutDeptID : 915
         * VisitCount : 0
         */

        private String In_Dept_ID;
        private String In_Dept_Name;
        private String In_Date;
        private String In_Type;
        private String Wait_ID;
        private String In_Room;
        private String In_Bed;
        private String In_AreaID;
        private String ICD;
        private String ICD_Name;
        private String In_Aim;
        private String Illness_Status;
        private String Receive_Date;
        private String IsMedicare;
        private String Allergy;
        private String IsSick;
        private String Attend_PSID;
        private String Attend_PSName;
        private String DoctorIn_PSID;
        private String DoctorIn_PSName;
        private String Pri_NurseID;
        private String Pri_NurseName;
        private String Prepay_Banalce;
        private String Total_Fee;
        private String Status;
        private String Day_Type;
        private String BedStatus;
        private String IsArrears;
        private String Arrears;
        private String InTime;
        private String IsTumour;
        private String HaveOrders;
        private String Out_Date;
        private String OutICD;
        private String OutICDName;
        private String OutDeptID;
        private String VisitCount;

        public String getIn_Dept_ID() {
            return In_Dept_ID;
        }

        public void setIn_Dept_ID(String In_Dept_ID) {
            this.In_Dept_ID = In_Dept_ID;
        }

        public String getIn_Dept_Name() {
            return In_Dept_Name;
        }

        public void setIn_Dept_Name(String In_Dept_Name) {
            this.In_Dept_Name = In_Dept_Name;
        }

        public String getIn_Date() {
            return In_Date;
        }

        public void setIn_Date(String In_Date) {
            this.In_Date = In_Date;
        }

        public String getIn_Type() {
            return In_Type;
        }

        public void setIn_Type(String In_Type) {
            this.In_Type = In_Type;
        }

        public String getWait_ID() {
            return Wait_ID;
        }

        public void setWait_ID(String Wait_ID) {
            this.Wait_ID = Wait_ID;
        }

        public String getIn_Room() {
            return In_Room;
        }

        public void setIn_Room(String In_Room) {
            this.In_Room = In_Room;
        }

        public String getIn_Bed() {
            return In_Bed;
        }

        public void setIn_Bed(String In_Bed) {
            this.In_Bed = In_Bed;
        }

        public String getIn_AreaID() {
            return In_AreaID;
        }

        public void setIn_AreaID(String In_AreaID) {
            this.In_AreaID = In_AreaID;
        }

        public String getICD() {
            return ICD;
        }

        public void setICD(String ICD) {
            this.ICD = ICD;
        }

        public String getICD_Name() {
            return ICD_Name;
        }

        public void setICD_Name(String ICD_Name) {
            this.ICD_Name = ICD_Name;
        }

        public String getIn_Aim() {
            return In_Aim;
        }

        public void setIn_Aim(String In_Aim) {
            this.In_Aim = In_Aim;
        }

        public String getIllness_Status() {
            return Illness_Status;
        }

        public void setIllness_Status(String Illness_Status) {
            this.Illness_Status = Illness_Status;
        }

        public String getReceive_Date() {
            return Receive_Date;
        }

        public void setReceive_Date(String Receive_Date) {
            this.Receive_Date = Receive_Date;
        }

        public String getIsMedicare() {
            return IsMedicare;
        }

        public void setIsMedicare(String IsMedicare) {
            this.IsMedicare = IsMedicare;
        }

        public String getAllergy() {
            return Allergy;
        }

        public void setAllergy(String Allergy) {
            this.Allergy = Allergy;
        }

        public String getIsSick() {
            return IsSick;
        }

        public void setIsSick(String IsSick) {
            this.IsSick = IsSick;
        }

        public String getAttend_PSID() {
            return Attend_PSID;
        }

        public void setAttend_PSID(String Attend_PSID) {
            this.Attend_PSID = Attend_PSID;
        }

        public String getAttend_PSName() {
            return Attend_PSName;
        }

        public void setAttend_PSName(String Attend_PSName) {
            this.Attend_PSName = Attend_PSName;
        }

        public String getDoctorIn_PSID() {
            return DoctorIn_PSID;
        }

        public void setDoctorIn_PSID(String DoctorIn_PSID) {
            this.DoctorIn_PSID = DoctorIn_PSID;
        }

        public String getDoctorIn_PSName() {
            return DoctorIn_PSName;
        }

        public void setDoctorIn_PSName(String DoctorIn_PSName) {
            this.DoctorIn_PSName = DoctorIn_PSName;
        }

        public String getPri_NurseID() {
            return Pri_NurseID;
        }

        public void setPri_NurseID(String Pri_NurseID) {
            this.Pri_NurseID = Pri_NurseID;
        }

        public String getPri_NurseName() {
            return Pri_NurseName;
        }

        public void setPri_NurseName(String Pri_NurseName) {
            this.Pri_NurseName = Pri_NurseName;
        }

        public String getPrepay_Banalce() {
            return Prepay_Banalce;
        }

        public void setPrepay_Banalce(String Prepay_Banalce) {
            this.Prepay_Banalce = Prepay_Banalce;
        }

        public String getTotal_Fee() {
            return Total_Fee;
        }

        public void setTotal_Fee(String Total_Fee) {
            this.Total_Fee = Total_Fee;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getDay_Type() {
            return Day_Type;
        }

        public void setDay_Type(String Day_Type) {
            this.Day_Type = Day_Type;
        }

        public String getBedStatus() {
            return BedStatus;
        }

        public void setBedStatus(String BedStatus) {
            this.BedStatus = BedStatus;
        }

        public String getIsArrears() {
            return IsArrears;
        }

        public void setIsArrears(String IsArrears) {
            this.IsArrears = IsArrears;
        }

        public String getArrears() {
            return Arrears;
        }

        public void setArrears(String Arrears) {
            this.Arrears = Arrears;
        }

        public String getInTime() {
            return InTime;
        }

        public void setInTime(String InTime) {
            this.InTime = InTime;
        }

        public String getIsTumour() {
            return IsTumour;
        }

        public void setIsTumour(String IsTumour) {
            this.IsTumour = IsTumour;
        }

        public String getHaveOrders() {
            return HaveOrders;
        }

        public void setHaveOrders(String HaveOrders) {
            this.HaveOrders = HaveOrders;
        }

        public String getOut_Date() {
            return Out_Date;
        }

        public void setOut_Date(String Out_Date) {
            this.Out_Date = Out_Date;
        }

        public String getOutICD() {
            return OutICD;
        }

        public void setOutICD(String OutICD) {
            this.OutICD = OutICD;
        }

        public String getOutICDName() {
            return OutICDName;
        }

        public void setOutICDName(String OutICDName) {
            this.OutICDName = OutICDName;
        }

        public String getOutDeptID() {
            return OutDeptID;
        }

        public void setOutDeptID(String OutDeptID) {
            this.OutDeptID = OutDeptID;
        }

        public String getVisitCount() {
            return VisitCount;
        }

        public void setVisitCount(String VisitCount) {
            this.VisitCount = VisitCount;
        }
    }

}
