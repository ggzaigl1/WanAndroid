package com.fy.baselibrary.entity;

import java.io.Serializable;

/**
 * 个人信息图片实体类
 * Created by Gab on 2017/8/31 0031.
 */

public class ImgBean implements Serializable {

        /**
         * FileNo : 1
         * FileName : 20171206024253_a0e5c289-f4b6-4145-9e0b-21d679a2e277.jpg
         * FilePath : D:/MoveSystem/YDYS\20171206\20171206024253_a0e5c289-f4b6-4145-9e0b-21d679a2e277.jpg
         * FileSize : 1338.00
         * FileUrl : http://192.168.100.30:8098/YDYS/20171206/20171206024253_a0e5c289-f4b6-4145-9e0b-21d679a2e277.jpg
         * FileType : 4
         * PatID : 516
         * CreateUserID : 516
         */

        private String FileNo="";
        private String FileName="";
        private String FilePath="";
        private String FileSize="";
        private String FileUrl="";
        private String FileType="";
        private String PatID="";
        private String CreateUserID="";

        public String getFileNo() {
            return FileNo;
        }

        public void setFileNo(String FileNo) {
            this.FileNo = FileNo;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public String getFileSize() {
            return FileSize;
        }

        public void setFileSize(String FileSize) {
            this.FileSize = FileSize;
        }

        public String getFileUrl() {
            return FileUrl;
        }

        public void setFileUrl(String FileUrl) {
            this.FileUrl = FileUrl;
        }

        public String getFileType() {
            return FileType;
        }

        public void setFileType(String FileType) {
            this.FileType = FileType;
        }

        public String getPatID() {
            return PatID;
        }

        public void setPatID(String PatID) {
            this.PatID = PatID;
        }

        public String getCreateUserID() {
            return CreateUserID;
        }

        public void setCreateUserID(String CreateUserID) {
            this.CreateUserID = CreateUserID;
        }
    }
