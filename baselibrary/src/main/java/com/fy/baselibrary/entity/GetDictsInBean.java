package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Stefan on 2017/11/21.
 * 获取住院患者信息实体类字典
 */

public class GetDictsInBean implements Serializable {

    /**
     * Description :
     * DictOrder : 1
     * DictType : 10066
     * Dict_TypeID : 1006601
     * Dict_TypeName : 患者类型
     * Dicts : [{"Description":"","DictOrder":"1","DictType":"10066","Dict_TypeID":"100660101","Dict_TypeName":"肿瘤病人","Pid":"1006601","Status":"0"},{"Description":"","DictOrder":"2","DictType":"10066","Dict_TypeID":"100660102","Dict_TypeName":"有医嘱","Pid":"1006601","Status":"0"}]
     * Pid : 10066
     * Status : 0
     */

    private String Description;
    private String DictOrder;
    private String DictType;
    private String Dict_TypeID;
    private String Dict_TypeName;
    private String Pid;
    private String Status;
    private List<DictsBean> Dicts;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDictOrder() {
        return DictOrder;
    }

    public void setDictOrder(String DictOrder) {
        this.DictOrder = DictOrder;
    }

    public String getDictType() {
        return DictType;
    }

    public void setDictType(String DictType) {
        this.DictType = DictType;
    }

    public String getDict_TypeID() {
        return Dict_TypeID;
    }

    public void setDict_TypeID(String Dict_TypeID) {
        this.Dict_TypeID = Dict_TypeID;
    }

    public String getDict_TypeName() {
        return Dict_TypeName;
    }

    public void setDict_TypeName(String Dict_TypeName) {
        this.Dict_TypeName = Dict_TypeName;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String Pid) {
        this.Pid = Pid;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public List<DictsBean> getDicts() {
        return Dicts;
    }

    public void setDicts(List<DictsBean> Dicts) {
        this.Dicts = Dicts;
    }

    public static class DictsBean implements Serializable {
        /**
         * Description :
         * DictOrder : 1
         * DictType : 10066
         * Dict_TypeID : 100660101
         * Dict_TypeName : 肿瘤病人
         * Pid : 1006601
         * Status : 0
         */

        private String Description;
        private String DictOrder;
        private String DictType;
        private String Dict_TypeID;
        private String Dict_TypeName;
        private String Pid;
        private String Status;

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getDictOrder() {
            return DictOrder;
        }

        public void setDictOrder(String DictOrder) {
            this.DictOrder = DictOrder;
        }

        public String getDictType() {
            return DictType;
        }

        public void setDictType(String DictType) {
            this.DictType = DictType;
        }

        public String getDict_TypeID() {
            return Dict_TypeID;
        }

        public void setDict_TypeID(String Dict_TypeID) {
            this.Dict_TypeID = Dict_TypeID;
        }

        public String getDict_TypeName() {
            return Dict_TypeName;
        }

        public void setDict_TypeName(String Dict_TypeName) {
            this.Dict_TypeName = Dict_TypeName;
        }

        public String getPid() {
            return Pid;
        }

        public void setPid(String Pid) {
            this.Pid = Pid;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }
    }
}
