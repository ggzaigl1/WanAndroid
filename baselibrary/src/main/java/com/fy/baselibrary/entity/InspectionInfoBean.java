package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 */

public class InspectionInfoBean {
        /**
         * IdNum :
         * CardNo :
         * ReportId : 7110770168064
         * TestDate : 2017/7/14 13:25:00
         * PatID : 600429
         * PatName : 黄东勇
         * DeptName : 血透室
         * CheckDeptName : 血透中心
         * DocName : 方宝龙
         * CheckDocName : 陈佳
         * FinishDocName : 钟隆欣
         * TestName : 急诊生化
         * Status : Y
         * LisDetail : [{"TestName":"急诊生化","ItemName":"钠离子","ItemValue":"139","ItemUnit":"mmol/L","Hint":"","RefExp":"135--145","Determinatio":"离子选择电极法","ImageURL":null},{"TestName":"急诊生化","ItemName":"尿素氮","ItemValue":"17.20 ↑","ItemUnit":"mmol/L","Hint":"ERROR,H","RefExp":"2.80--8.20","Determinatio":"酶学传导速率法","ImageURL":null},{"TestName":"急诊生化","ItemName":"钾离子","ItemValue":"3.8","ItemUnit":"mmol/L","Hint":"","RefExp":"3.50--5.50","Determinatio":"离子选择电极法","ImageURL":null},{"TestName":"急诊生化","ItemName":"氯离子","ItemValue":"104","ItemUnit":"mmol/L","Hint":"","RefExp":"96--108","Determinatio":"离子选择电极法","ImageURL":null},{"TestName":"急诊生化","ItemName":"总蛋白","ItemValue":"62","ItemUnit":"g/L","Hint":"","RefExp":"60.0--85.0","Determinatio":"双缩脲速率法","ImageURL":null},{"TestName":"急诊生化","ItemName":"钙","ItemValue":"2.31","ItemUnit":"mmol/L","Hint":"","RefExp":"2.08--2.60","Determinatio":"离子选择电极法","ImageURL":null},{"TestName":"急诊生化","ItemName":"葡萄糖","ItemValue":"6.70 ↑","ItemUnit":"mmol/L","Hint":"ERROR,H","RefExp":"3.90--6.10","Determinatio":"酶电极法","ImageURL":null},{"TestName":"急诊生化","ItemName":"肌酐","ItemValue":"914 ↑","ItemUnit":"μmol/L","Hint":"ERROR,H","RefExp":"53--115","Determinatio":"苦味酸速率法","ImageURL":null},{"TestName":"急诊生化","ItemName":"二氧化碳","ItemValue":"23","ItemUnit":"mmol/L","Hint":"","RefExp":"22--29","Determinatio":"离子选择电极法","ImageURL":null}]
         * PacsDetail : null
         * ECGDetail : null
         */

        private String IdNum;
        private String CardNo;
        private String ReportId;
        private String TestDate;
        private String PatID;
        private String PatName;
        private String DeptName;
        private String CheckDeptName;
        private String DocName;
        private String CheckDocName;
        private String FinishDocName;
        private String TestName;
        private String Status;
        private Object PacsDetail;
        private Object ECGDetail;
        private List<LisDetailBean> LisDetail;

        public String getIdNum() {
            return IdNum;
        }

        public void setIdNum(String IdNum) {
            this.IdNum = IdNum;
        }

        public String getCardNo() {
            return CardNo;
        }

        public void setCardNo(String CardNo) {
            this.CardNo = CardNo;
        }

        public String getReportId() {
            return ReportId;
        }

        public void setReportId(String ReportId) {
            this.ReportId = ReportId;
        }

        public String getTestDate() {
            return TestDate;
        }

        public void setTestDate(String TestDate) {
            this.TestDate = TestDate;
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

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getCheckDeptName() {
            return CheckDeptName;
        }

        public void setCheckDeptName(String CheckDeptName) {
            this.CheckDeptName = CheckDeptName;
        }

        public String getDocName() {
            return DocName;
        }

        public void setDocName(String DocName) {
            this.DocName = DocName;
        }

        public String getCheckDocName() {
            return CheckDocName;
        }

        public void setCheckDocName(String CheckDocName) {
            this.CheckDocName = CheckDocName;
        }

        public String getFinishDocName() {
            return FinishDocName;
        }

        public void setFinishDocName(String FinishDocName) {
            this.FinishDocName = FinishDocName;
        }

        public String getTestName() {
            return TestName;
        }

        public void setTestName(String TestName) {
            this.TestName = TestName;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public Object getPacsDetail() {
            return PacsDetail;
        }

        public void setPacsDetail(Object PacsDetail) {
            this.PacsDetail = PacsDetail;
        }

        public Object getECGDetail() {
            return ECGDetail;
        }

        public void setECGDetail(Object ECGDetail) {
            this.ECGDetail = ECGDetail;
        }

        public List<LisDetailBean> getLisDetail() {
            return LisDetail;
        }

        public void setLisDetail(List<LisDetailBean> LisDetail) {
            this.LisDetail = LisDetail;
        }

        public static class LisDetailBean {
            /**
             * TestName : 急诊生化
             * ItemName : 钠离子
             * ItemValue : 139
             * ItemUnit : mmol/L
             * Hint :
             * ResItmEcd:
             * RefExp : 135--145
             * Determinatio : 离子选择电极法
             * ImageURL : null
             */

            private String TestName;
            private String ItemName;
            private String ItemValue;
            private String ItemUnit;
            private String Hint;
            private String RefExp;

            public String getResItmEcd() {
                return ResItmEcd;
            }

            public void setResItmEcd(String resItmEcd) {
                ResItmEcd = resItmEcd;
            }

            private String ResItmEcd;
            private String Determinatio;
            private Object ImageURL;

            public String getTestName() {
                return TestName;
            }

            public void setTestName(String TestName) {
                this.TestName = TestName;
            }

            public String getItemName() {
                return ItemName;
            }

            public void setItemName(String ItemName) {
                this.ItemName = ItemName;
            }

            public String getItemValue() {
                return ItemValue;
            }

            public void setItemValue(String ItemValue) {
                this.ItemValue = ItemValue;
            }

            public String getItemUnit() {
                return ItemUnit;
            }

            public void setItemUnit(String ItemUnit) {
                this.ItemUnit = ItemUnit;
            }

            public String getHint() {
                return Hint;
            }

            public void setHint(String Hint) {
                this.Hint = Hint;
            }

            public String getRefExp() {
                return RefExp;
            }

            public void setRefExp(String RefExp) {
                this.RefExp = RefExp;
            }

            public String getDeterminatio() {
                return Determinatio;
            }

            public void setDeterminatio(String Determinatio) {
                this.Determinatio = Determinatio;
            }

            public Object getImageURL() {
                return ImageURL;
            }

            public void setImageURL(Object ImageURL) {
                this.ImageURL = ImageURL;
            }
        }

}
