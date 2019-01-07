package com.example.gab.wanandroid_kotlin.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 初夏小溪
 * @date 2018/4/9 0009
 */

public class CourseList implements Serializable{


    /**
     * result : 1
     * message :
     * data : [{"id":"8","name":"地狱Burpees挑战","trainlevel":5,"duration":0,"sections":0,"calories":0,"icon":"http://ws-ugc.fithub.cc/jinj/train/item/a395d36fb4ed414d97586ab28dbae703.jpg","description":"1、学习动作要点，动作规范的基础上跟随课程节奏进行训练\r\n2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成\r\n3、如果训练当中出现\u201c身体不适\u201d或\u201c关节疼痛\u201d的现象，应立即停止训练或降低训练强度\r\n4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象","brief":"Burpees是单位时间内燃烧热量最高的健身动作之一，将会全方位轰炸你的心肺。","footer":"","type":"减脂,腰腹,零基础","status":0,"recommend":"1","isJoin":"0","curchapter":0,"totalchapter":0,"joinnum":49814,"func":"","trainpart":"全身","instrument":"无器械","functxt":null,"trainparttxt":"全身","instrumenttxt":"无器械","filesize":null},{"id":"150","name":"家庭增肌特训","trainlevel":4,"duration":0,"sections":0,"calories":0,"icon":"http://ws-ugc.fithub.cc/jinj/train/item/2e6a29f8733b419291e7dbcc8149f057.jpg","description":"1、学习动作要点，动作规范的基础上跟随课程节奏进行训练\r\n2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成\r\n3、如果训练当中出现\u201c身体不适\u201d或\u201c关节疼痛\u201d的现象，应立即停止训练或降低训练强度\r\n4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象","brief":"增肌训练并不是只能在健身房进行，只要方法正确，在家里、宿舍一样可以做到。本套训练会用到单杠、哑铃两个器械，带你练出强壮的身材。","footer":"","type":"零基础","status":0,"recommend":"0","isJoin":"0","curchapter":0,"totalchapter":0,"joinnum":20904,"func":"","trainpart":"全身","instrument":"哑铃","functxt":null,"trainparttxt":"全身","instrumenttxt":"哑铃","filesize":null},{"id":"105","name":"躯干拉伸（女生版）","trainlevel":1,"duration":0,"sections":0,"calories":0,"icon":"http://ws-ugc.fithub.cc/jinj/train/item/d63e449dd0a942ada7878972fee9c553.jpg","description":"1、学习动作要点，动作规范的基础上跟随课程节奏进行训练\r\n2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成\r\n3、如果训练当中出现\u201c身体不适\u201d或\u201c关节疼痛\u201d的现象，应立即停止训练或降低训练强度\r\n4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象","brief":"针对腹外斜肌、胸椎、背阔肌拉伸训练。","footer":"","type":"拉伸","status":0,"recommend":"1","isJoin":"0","curchapter":0,"totalchapter":0,"joinnum":23896,"func":"","trainpart":"全身","instrument":"无器械","functxt":null,"trainparttxt":"全身","instrumenttxt":"无器械","filesize":null},{"id":"77","name":"腹肌撕裂者进阶","trainlevel":2,"duration":0,"sections":0,"calories":0,"icon":"http://ws-ugc.fithub.cc/yany/train/item/0f0b7039ae5946c4ac394ae02ac00168.jpg","description":"1、学习动作要点，动作规范的基础上跟随课程节奏进行训练\r\n2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成\r\n3、如果训练当中出现\u201c身体不适\u201d或\u201c关节疼痛\u201d的现象，应立即停止训练或降低训练强度\r\n4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象","brief":"这是针对上腹、下腹、侧腹全方位刺激的训练方案。采用更新的训练模式，每个动作只用最少的个数，就能达到撕裂般疼痛的感受","footer":"","type":"腰腹","status":0,"recommend":"0","isJoin":"0","curchapter":0,"totalchapter":0,"joinnum":56659,"func":"","trainpart":"腹部","instrument":"无器械","functxt":null,"trainparttxt":"腹部","instrumenttxt":"无器械","filesize":null},{"id":"106","name":"下肢拉伸（女生版）","trainlevel":1,"duration":0,"sections":0,"calories":0,"icon":"http://ws-ugc.fithub.cc/jinj/train/item/0d4efc62522c4ec0afab1454e35e1c1e.jpg","description":"1、学习动作要点，动作规范的基础上跟随课程节奏进行训练\r\n2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成\r\n3、如果训练当中出现\u201c身体不适\u201d或\u201c关节疼痛\u201d的现象，应立即停止训练或降低训练强度\r\n4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象","brief":"适用于跑后、骑行后、运动后的拉伸。","footer":"","type":"拉伸","status":0,"recommend":"1","isJoin":"0","curchapter":0,"totalchapter":0,"joinnum":22044,"func":"","trainpart":"腿部","instrument":"无器械","functxt":null,"trainparttxt":"腿部","instrumenttxt":"无器械","filesize":null},{"id":"135","name":"人鱼线雕刻","trainlevel":2,"duration":0,"sections":0,"calories":0,"icon":"http://ws-ugc.fithub.cc/jinj/train/item/15048b28930c45e58416a7de136ce8b1.jpg","description":"1、学习动作要点，动作规范的基础上跟随课程节奏进行训练\r\n2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成\r\n3、如果训练当中出现\u201c身体不适\u201d或\u201c关节疼痛\u201d的现象，应立即停止训练或降低训练强度\r\n4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象","brief":"人鱼线是男人最性感的肌肉，此训练针对想进一步加强腹部的训练者。","footer":"","type":"腰腹","status":0,"recommend":"0","isJoin":"0","curchapter":0,"totalchapter":0,"joinnum":25410,"func":"","trainpart":"腹部","instrument":"椅子","functxt":null,"trainparttxt":"腹部","instrumenttxt":"椅子","filesize":null},{"id":"140","name":"办公室肩颈放松","trainlevel":1,"duration":0,"sections":0,"calories":0,"icon":"http://ws-ugc.fithub.cc/jinj/train/item/02efda21dc0c4080816faff3854711d7.jpg","description":"1、学习动作要点，动作规范的基础上跟随课程节奏进行训练\r\n2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成\r\n3、如果训练当中出现\u201c身体不适\u201d或\u201c关节疼痛\u201d的现象，应立即停止训练或降低训练强度\r\n4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象","brief":"针对长期久坐人群设计的肩颈放松","footer":"","type":"办公室","status":0,"recommend":"0","isJoin":"0","curchapter":0,"totalchapter":0,"joinnum":24208,"func":"","trainpart":"肩部颈部","instrument":"椅子","functxt":null,"trainparttxt":"肩部颈部","instrumenttxt":"椅子","filesize":null},{"id":"78","name":"睡前拉伸","trainlevel":1,"duration":0,"sections":0,"calories":0,"icon":"http://ws-ugc.fithub.cc/jinj/train/item/4ac47873d12d491d932285504b9e732b.jpg","description":"1、学习动作要点，动作规范的基础上跟随课程节奏进行训练\r\n2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成\r\n3、如果训练当中出现\u201c身体不适\u201d或\u201c关节疼痛\u201d的现象，应立即停止训练或降低训练强度\r\n4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象","brief":"这套睡前拉伸是针对日常容易紧张的肌肉设计的，能让身体更加舒服的姿态入睡，提高睡眠质量。","footer":"","type":"拉伸","status":0,"recommend":"0","isJoin":"0","curchapter":0,"totalchapter":0,"joinnum":45544,"func":"","trainpart":"全身","instrument":"瑜伽垫","functxt":null,"trainparttxt":"全身","instrumenttxt":"瑜伽垫","filesize":null},{"id":"26","name":"腹肌训练入门","trainlevel":1,"duration":0,"sections":0,"calories":0,"icon":"http://ws-ugc.fithub.cc/null/train/item/bb30889ec7d64407abbf25f8fb375e80.jpg","description":"1、学习动作要点，动作规范的基础上跟随课程节奏进行训练\r\n2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成\r\n3、如果训练当中出现\u201c身体不适\u201d或\u201c关节疼痛\u201d的现象，应立即停止训练或降低训练强度\r\n4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象","brief":"最基础的腹肌训练动作，帮助你循序渐进地增强腹肌力量","footer":"","type":"腰腹","status":0,"recommend":"0","isJoin":"0","curchapter":0,"totalchapter":0,"joinnum":61606,"func":"","trainpart":"腰部腹部","instrument":"瑜伽垫","functxt":null,"trainparttxt":"腰部腹部","instrumenttxt":"瑜伽垫","filesize":null},{"id":"76","name":"瘦腿训练","trainlevel":1,"duration":0,"sections":0,"calories":0,"icon":"http://ws-ugc.fithub.cc/yany/train/item/7d6e1342d14a4bb9a292cb175efb25ce.jpeg","description":"1、学习动作要点，动作规范的基础上跟随课程节奏进行训练\r\n2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成\r\n3、如果训练当中出现\u201c身体不适\u201d或\u201c关节疼痛\u201d的现象，应立即停止训练或降低训练强度\r\n4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象","brief":"本课程适合想要瘦腿的人，想要自己腿型更加好看的人。","footer":"","type":"减脂","status":0,"recommend":"0","isJoin":"0","curchapter":0,"totalchapter":0,"joinnum":55074,"func":"","trainpart":"腿部","instrument":"瑜伽垫","functxt":null,"trainparttxt":"腿部","instrumenttxt":"瑜伽垫","filesize":null}]
     * timestamp : 1523259777401
     */

    private int result;
    private String message;
    private long timestamp;
    private List<DataBean> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 8
         * name : 地狱Burpees挑战
         * trainlevel : 5
         * duration : 0
         * sections : 0
         * calories : 0
         * icon : http://ws-ugc.fithub.cc/jinj/train/item/a395d36fb4ed414d97586ab28dbae703.jpg
         * description : 1、学习动作要点，动作规范的基础上跟随课程节奏进行训练
         2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成
         3、如果训练当中出现“身体不适”或“关节疼痛”的现象，应立即停止训练或降低训练强度
         4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象
         * brief : Burpees是单位时间内燃烧热量最高的健身动作之一，将会全方位轰炸你的心肺。
         * footer :
         * type : 减脂,腰腹,零基础
         * status : 0
         * recommend : 1
         * isJoin : 0
         * curchapter : 0
         * totalchapter : 0
         * joinnum : 49814
         * func :
         * trainpart : 全身
         * instrument : 无器械
         * functxt : null
         * trainparttxt : 全身
         * instrumenttxt : 无器械
         * filesize : null
         */

        private int id;
        private String name;
        private int trainlevel;
        private int duration;
        private int sections;
        private int calories;
        private String icon;
        private String description;
        private String brief;
        private String footer;
        private String type;
        private int status;
        private String recommend;
        private String isJoin;
        private int curchapter;
        private int totalchapter;
        private int joinnum;
        private String func;
        private String trainpart;
        private String instrument;
        private Object functxt;
        private String trainparttxt;
        private String instrumenttxt;
        private Object filesize;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTrainlevel() {
            return trainlevel;
        }

        public void setTrainlevel(int trainlevel) {
            this.trainlevel = trainlevel;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getSections() {
            return sections;
        }

        public void setSections(int sections) {
            this.sections = sections;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getFooter() {
            return footer;
        }

        public void setFooter(String footer) {
            this.footer = footer;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getIsJoin() {
            return isJoin;
        }

        public void setIsJoin(String isJoin) {
            this.isJoin = isJoin;
        }

        public int getCurchapter() {
            return curchapter;
        }

        public void setCurchapter(int curchapter) {
            this.curchapter = curchapter;
        }

        public int getTotalchapter() {
            return totalchapter;
        }

        public void setTotalchapter(int totalchapter) {
            this.totalchapter = totalchapter;
        }

        public int getJoinnum() {
            return joinnum;
        }

        public void setJoinnum(int joinnum) {
            this.joinnum = joinnum;
        }

        public String getFunc() {
            return func;
        }

        public void setFunc(String func) {
            this.func = func;
        }

        public String getTrainpart() {
            return trainpart;
        }

        public void setTrainpart(String trainpart) {
            this.trainpart = trainpart;
        }

        public String getInstrument() {
            return instrument;
        }

        public void setInstrument(String instrument) {
            this.instrument = instrument;
        }

        public Object getFunctxt() {
            return functxt;
        }

        public void setFunctxt(Object functxt) {
            this.functxt = functxt;
        }

        public String getTrainparttxt() {
            return trainparttxt;
        }

        public void setTrainparttxt(String trainparttxt) {
            this.trainparttxt = trainparttxt;
        }

        public String getInstrumenttxt() {
            return instrumenttxt;
        }

        public void setInstrumenttxt(String instrumenttxt) {
            this.instrumenttxt = instrumenttxt;
        }

        public Object getFilesize() {
            return filesize;
        }

        public void setFilesize(Object filesize) {
            this.filesize = filesize;
        }
    }
}
