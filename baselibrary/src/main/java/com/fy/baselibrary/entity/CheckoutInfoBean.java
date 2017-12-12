package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 */

public class CheckoutInfoBean implements Serializable {

    /**
     * IdNum :
     * CardNo :
     * ReportId : 120938254691
     * TestDate : 2015/7/1 7:03:50
     * PatID : 600429
     * PatName : 黄东勇
     * DeptName : 血透室
     * CheckDeptName :
     * DocName : 方宝龙
     * CheckDocName :
     * FinishDocName :
     * TestName : 腹部（含肝、胆、脾、胰）
     * Status : Y
     * LisDetail : null
     * PacsDetail : [{"StudyName":"腹部（含肝、胆、脾、胰）","Report":"肝形态较饱满，表面光滑，边缘圆钝，内光点分布欠均匀，前区回声增强密\r\n集，深部回声减弱，后缘线清晰，内未能见明显局限性团块回声,\r\n肝内管道显示尚清，门静脉主干内径正常。\r\n胆囊大小、形态正常，壁薄，内未见明显异常回声，总管无扩张，\r\n脾脏大小、形态正常，包膜光滑，实质回声均匀。\r\n胰腺大小、形态正常，内回声均匀，未见明显异常回声，胰管未见扩张。\r\nCDFI：肝脏脾脏彩色织血流信号未见异常。","Conclusion":"轻度脂肪肝\r\n胆囊，脾脏，胰腺未见明显异常","ImageURL":""}]
     * ECGDetail : null
     */

    private String IdNum = "";
    private String CardNo = "";
    private String ReportId = "";
    private String TestDate = "";
    private String PatID = "";
    private String PatName = "";
    private String DeptName = "";
    private String CheckDeptName = "";
    private String DocName = "";
    private String CheckDocName = "";
    private String FinishDocName = "";
    private String TestName = "";
    private String Status = "";
    private Object LisDetail = "";
    private Object ECGDetail = "";
    private List<PacsDetailBean> PacsDetail;

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

    public Object getLisDetail() {
        return LisDetail;
    }

    public void setLisDetail(Object LisDetail) {
        this.LisDetail = LisDetail;
    }

    public Object getECGDetail() {
        return ECGDetail;
    }

    public void setECGDetail(Object ECGDetail) {
        this.ECGDetail = ECGDetail;
    }

    public List<PacsDetailBean> getPacsDetail() {
        return PacsDetail;
    }

    public void setPacsDetail(List<PacsDetailBean> PacsDetail) {
        this.PacsDetail = PacsDetail;
    }

    public static class PacsDetailBean implements Serializable {
        /**
         * StudyName : 腹部（含肝、胆、脾、胰）
         * Report : 肝形态较饱满，表面光滑，边缘圆钝，内光点分布欠均匀，前区回声增强密
         * 集，深部回声减弱，后缘线清晰，内未能见明显局限性团块回声,
         * 肝内管道显示尚清，门静脉主干内径正常。
         * 胆囊大小、形态正常，壁薄，内未见明显异常回声，总管无扩张，
         * 脾脏大小、形态正常，包膜光滑，实质回声均匀。
         * 胰腺大小、形态正常，内回声均匀，未见明显异常回声，胰管未见扩张。
         * CDFI：肝脏脾脏彩色织血流信号未见异常。
         * Conclusion : 轻度脂肪肝
         * 胆囊，脾脏，胰腺未见明显异常
         * ImageURL :
         */

        private String StudyName = "";
        private String Report = "";
        private String Conclusion = "";
        private String ImageURL = "";

        public String getStudyName() {
            return StudyName;
        }

        public void setStudyName(String StudyName) {
            this.StudyName = StudyName;
        }

        public String getReport() {
            return Report;
        }

        public void setReport(String Report) {
            this.Report = Report;
        }

        public String getConclusion() {
            return Conclusion;
        }

        public void setConclusion(String Conclusion) {
            this.Conclusion = Conclusion;
        }

        public String getImageURL() {
            return ImageURL;
        }

        public void setImageURL(String ImageURL) {
            this.ImageURL = ImageURL;
        }
    }
}
