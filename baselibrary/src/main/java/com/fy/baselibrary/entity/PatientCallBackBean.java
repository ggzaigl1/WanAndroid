package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 病人回访实体类
 * Created by Gab on 2017/8/31 0031.
 */

public class PatientCallBackBean implements Serializable {

        /**
         * Token : CABB55A5906C340A190C049458ED1E80
         * PatID : 606804
         * PatName : 刘妙芳
         * Sex : F
         * Age : Y83
         * Tel : 13531937135
         * Address :
         * ContName :
         * ContTel :
         * ContRelation :
         * InICD : 股骨颈骨折
         * InDate : 2017-09-17
         * OutICD : 左股骨颈骨折 高血压病
         * OutDate : 2017-10-16
         * VisitCount : 1
         * RVDetail : [{"DateKey":"1511396460000","VisitDate":"2017/11/23 16:22:16","VisitUserID":"529","VisitUserName":"郑泽龙","CureCySituation":"1","RVSpecial":"1","Rehabilitation":"1","Advice":"1","Remark":"1","AppointmentDate":"2017/11/23 12:00:00"}]
         */

        private String Token = "";
        private String PatID= "";
        private String PatName= "";
        private String Sex= "";
        private String Age= "";
        private String Tel= "";
        private String Address= "";
        private String ContName= "";
        private String ContTel= "";
        private String ContRelation= "";
        private String InICD= "";
        private String InDate= "";
        private String OutICD= "";
        private String OutDate= "";
        private String VisitCount= "";
        private List<RVDetailBean> RVDetail;

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
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

        public String getAge() {
            return Age;
        }

        public void setAge(String Age) {
            this.Age = Age;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
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

        public String getContRelation() {
            return ContRelation;
        }

        public void setContRelation(String ContRelation) {
            this.ContRelation = ContRelation;
        }

        public String getInICD() {
            return InICD;
        }

        public void setInICD(String InICD) {
            this.InICD = InICD;
        }

        public String getInDate() {
            return InDate;
        }

        public void setInDate(String InDate) {
            this.InDate = InDate;
        }

        public String getOutICD() {
            return OutICD;
        }

        public void setOutICD(String OutICD) {
            this.OutICD = OutICD;
        }

        public String getOutDate() {
            return OutDate;
        }

        public void setOutDate(String OutDate) {
            this.OutDate = OutDate;
        }

        public String getVisitCount() {
            return VisitCount;
        }

        public void setVisitCount(String VisitCount) {
            this.VisitCount = VisitCount;
        }

        public List<RVDetailBean> getRVDetail() {
            return RVDetail;
        }

        public void setRVDetail(List<RVDetailBean> RVDetail) {
            this.RVDetail = RVDetail;
        }

        public static class RVDetailBean implements Serializable{
            /**
             * DateKey : 1511396460000
             * VisitDate : 2017/11/23 16:22:16
             * VisitUserID : 529
             * VisitUserName : 郑泽龙
             * CureCySituation : 1
             * RVSpecial : 1
             * Rehabilitation : 1
             * Advice : 1
             * Remark : 1
             * AppointmentDate : 2017/11/23 12:00:00
             */

            private String DateKey= "";
            private String VisitDate= "";
            private String VisitUserID= "";
            private String VisitUserName= "";
            private String CureCySituation= "";
            private String RVSpecial= "";
            private String Rehabilitation= "";
            private String Advice= "";
            private String Remark= "";
            private String AppointmentDate= "";

            public String getDateKey() {
                return DateKey;
            }

            public void setDateKey(String DateKey) {
                this.DateKey = DateKey;
            }

            public String getVisitDate() {
                return VisitDate;
            }

            public void setVisitDate(String VisitDate) {
                this.VisitDate = VisitDate;
            }

            public String getVisitUserID() {
                return VisitUserID;
            }

            public void setVisitUserID(String VisitUserID) {
                this.VisitUserID = VisitUserID;
            }

            public String getVisitUserName() {
                return VisitUserName;
            }

            public void setVisitUserName(String VisitUserName) {
                this.VisitUserName = VisitUserName;
            }

            public String getCureCySituation() {
                return CureCySituation;
            }

            public void setCureCySituation(String CureCySituation) {
                this.CureCySituation = CureCySituation;
            }

            public String getRVSpecial() {
                return RVSpecial;
            }

            public void setRVSpecial(String RVSpecial) {
                this.RVSpecial = RVSpecial;
            }

            public String getRehabilitation() {
                return Rehabilitation;
            }

            public void setRehabilitation(String Rehabilitation) {
                this.Rehabilitation = Rehabilitation;
            }

            public String getAdvice() {
                return Advice;
            }

            public void setAdvice(String Advice) {
                this.Advice = Advice;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public String getAppointmentDate() {
                return AppointmentDate;
            }

            public void setAppointmentDate(String AppointmentDate) {
                this.AppointmentDate = AppointmentDate;
            }
        }

}