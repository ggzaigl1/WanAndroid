package com.example.gab.wanandroid_kotlin.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 初夏小溪
 * @date 2018/3/13
 * 健身 实体对象
 */

public class CourseDetails implements Serializable {

    /**
     * result : 1
     * message : 请求处理成功!
     * data : {"id":"59","diffLevel":2,"hardLevel":0,"module":10,"k":140,"title":"挖掘人鱼线","subTitle":"该课程针对想进一步加强腹部的训练者。","groups":[{"id":"199","title":"挖掘人鱼线","actions":[{"id":"2106","title":"单腿两头起","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/2df8aed66d3946d7918f7c8a1633d0f3.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/2df8aed66d3946d7918f7c8a1633d0f3.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/7701fab58bdb4b21a72bf6744044b7a0.mp3","seconds":3,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·双手伸直，腹肌发力起身\r\n·起身时腿部伸直，下背部贴地，手触摸到小腿，另一侧腿不能离开地面"},{"name":"呼吸节奏","desc":"·起身时呼气，还原时吸气"},{"name":"动作感觉","desc":"·起身的瞬间腹肌突然绷紧收缩\r\n·腰部始终放松，不应有紧绷感"}],"k":1,"size":867298,"totalK":15,"totalSeconds":50,"sort":9436995,"rules":[{"args":"","rest":5,"n":15}]},{"id":"2109","title":"仰卧屈膝转体","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/59/199/71879f9a0640452c98e267b1620bd8b1.jpg","link":"http://ws-video.fithub.cc/trainaction/59/199/71879f9a0640452c98e267b1620bd8b1.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/341e9f94374b4ce887f7d793655d53c0.mp3","seconds":3,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·挺直后背，两肘带动腰向身体左右方转动"},{"name":"动作感觉","desc":"·腹部两侧有微微发热的感觉"}],"k":1,"size":239157,"totalK":15,"totalSeconds":50,"sort":9437228,"rules":[{"args":"","rest":5,"n":15}]},{"id":"652","title":"倒蹬车","basis":"2","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/f2c7a67f11d743938c4d08e7cdd9a913.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/f2c7a67f11d743938c4d08e7cdd9a913.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/371ca319428f40bebbc49f84249f36f8.mp3","seconds":30,"tags":"","args":"","notes":[{"name":"动作要点","desc":"\u2022 动作尽量缓慢，不可用力蹬腿\u2022下背部贴紧地面"},{"name":"呼吸节奏","desc":"·全程保持均匀呼吸"},{"name":"动作感觉","desc":"\u2022 每一次蹬直腿时腹部都会产生剧烈的紧绷感，但腰部不应该有紧绷疼痛感"}],"k":1,"size":428993,"totalK":30,"totalSeconds":50,"sort":9437357,"rules":[{"args":"","rest":20,"n":30}]},{"id":"624","title":"左侧卷腹抬腿","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/08341622b57648a7920ee1ed6d14fc2c.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/08341622b57648a7920ee1ed6d14fc2c.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/5a10b602a586494093df672196391149.mp3","seconds":3,"tags":"","args":"","notes":[{"name":"动作要点","desc":"\u2022 右手贴紧地面稳定身体\r\n\u2022用力拉近手肘与大腿的距离"},{"name":"呼吸节奏","desc":"\u2022 卷腹时呼气，下落时吸气"},{"name":"动作感觉","desc":"\u2022 抬腿时腹部左侧有挤压感"}],"k":1,"size":758805,"totalK":20,"totalSeconds":60,"sort":9437457,"rules":[{"args":"","rest":0,"n":20}]},{"id":"626","title":"右侧卷腹抬腿","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/8883e8453a9944f89b8bc778cefdf813.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/8883e8453a9944f89b8bc778cefdf813.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/ca47330123d24b3a96536973afe63b5b.mp3","seconds":3,"tags":"","args":"","notes":[{"name":"动作要点","desc":"\u2022 左手贴紧地面稳定身体\r\n\u2022 用力拉近手肘与大腿的距离"},{"name":"呼吸节奏","desc":"\u2022 卷腹时呼气，下落时吸气"},{"name":"动作感觉","desc":"\u2022 抬腿时腹部左侧有挤压感"}],"k":1,"size":723571,"totalK":20,"totalSeconds":60,"sort":9437538,"rules":[{"args":"","rest":0,"n":20}]},{"id":"2113","title":"仰卧交替抬腿","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/59/199/baf3ebe9c2164f72bd5e1489920dce5e.jpg","link":"http://ws-video.fithub.cc/trainaction/59/199/baf3ebe9c2164f72bd5e1489920dce5e.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/812dc2ce27c84b5bace46d810fb3d520.mp3","seconds":2,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·肩部稍抬离地面，双手在胸前用力并紧或垫于腰下做支撑\r\n·下背部用力贴紧地面，双腿伸直，勾起脚尖"},{"name":"呼吸节奏","desc":"·全程保持均匀呼吸"},{"name":"动作感觉","desc":"·整个腹肌始终保持紧绷感，动作持续越久，腹肌灼烧感越强"},{"name":"常见错误","desc":"·错误：为追求动作快而摆动身体，导致腿部肌肉感觉变强\r\n·解决：减少动作幅度，保证上身固定"}],"k":1,"size":0,"totalK":15,"totalSeconds":35,"sort":9437691,"rules":[{"args":"","rest":5,"n":15}]},{"id":"2114","title":"仰卧髋部旋转","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/29facd02cbc24963916904e00fd12132.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/29facd02cbc24963916904e00fd12132.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/6115be732ebb4344b24e6f7b9749101e.mp3","seconds":2,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·仰卧垫上，双手向两侧打开成一直线\r\n·双腿抬起屈膝90°，髋部带动双腿向两侧转动至45°\r\n·腹部收紧，保持肩膀贴紧地面，眼睛目视上方\r\n·注意均匀呼吸"},{"name":"呼吸节奏","desc":"·全程保持均匀呼吸"},{"name":"动作感觉","desc":"·尽量不要憋气，感觉腹部两侧有收缩感，保持动作流畅"}],"k":1,"size":822330,"totalK":10,"totalSeconds":25,"sort":9437774,"rules":[{"args":"","rest":5,"n":10}]},{"id":"2089","title":"腹部拉伸","basis":"2","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/dd3b785ba0874e3cbd123504d5286bca.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/dd3b785ba0874e3cbd123504d5286bca.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/0e994425368241b6b05059a087a626ec.mp3","seconds":15,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·臀部夹紧，肩膀下沉 手肘微屈\r\n·腿部完全贴紧地面，手将身体撑起用力拉伸腹部\r\n·下巴上扬，挺胸"},{"name":"呼吸节奏","desc":"·全程保持均匀呼吸"},{"name":"动作感觉","desc":"·上半身向上延展产生舒展感，整个腹部有牵拉感\r\n·同时避免幅度过大，引起腰部不适"}],"k":1,"size":0,"totalK":15,"totalSeconds":15,"sort":9437855,"rules":[{"args":"","rest":0,"n":15}]}],"seconds":345,"finished":0,"k":140,"size":7472079,"diffLevel":2,"brief":"","downloadAction":false}],"seconds":345,"tags":"腰腹","notes":"1、学习动作要点，动作规范的基础上跟随课程节奏进行训练\r\n2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成\r\n3、如果训练当中出现\u201c身体不适\u201d或\u201c关节疼痛\u201d的现象，应立即停止训练或降低训练强度\r\n4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象","size":7472079,"pic":"http://ws-ugc.fithub.cc/jinj/train/item/3b61a7a08cc94952b8bb6d59ed3a8c4a.jpg","footer":""}
     * timestamp : 1520992177338
     * success : true
     */

    private int result;
    private String message;
    private DataBean data;
    private long timestamp;
    private boolean success;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 59
         * diffLevel : 2
         * hardLevel : 0
         * module : 10
         * k : 140
         * title : 挖掘人鱼线
         * subTitle : 该课程针对想进一步加强腹部的训练者。
         * groups : [{"id":"199","title":"挖掘人鱼线","actions":[{"id":"2106","title":"单腿两头起","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/2df8aed66d3946d7918f7c8a1633d0f3.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/2df8aed66d3946d7918f7c8a1633d0f3.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/7701fab58bdb4b21a72bf6744044b7a0.mp3","seconds":3,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·双手伸直，腹肌发力起身\r\n·起身时腿部伸直，下背部贴地，手触摸到小腿，另一侧腿不能离开地面"},{"name":"呼吸节奏","desc":"·起身时呼气，还原时吸气"},{"name":"动作感觉","desc":"·起身的瞬间腹肌突然绷紧收缩\r\n·腰部始终放松，不应有紧绷感"}],"k":1,"size":867298,"totalK":15,"totalSeconds":50,"sort":9436995,"rules":[{"args":"","rest":5,"n":15}]},{"id":"2109","title":"仰卧屈膝转体","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/59/199/71879f9a0640452c98e267b1620bd8b1.jpg","link":"http://ws-video.fithub.cc/trainaction/59/199/71879f9a0640452c98e267b1620bd8b1.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/341e9f94374b4ce887f7d793655d53c0.mp3","seconds":3,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·挺直后背，两肘带动腰向身体左右方转动"},{"name":"动作感觉","desc":"·腹部两侧有微微发热的感觉"}],"k":1,"size":239157,"totalK":15,"totalSeconds":50,"sort":9437228,"rules":[{"args":"","rest":5,"n":15}]},{"id":"652","title":"倒蹬车","basis":"2","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/f2c7a67f11d743938c4d08e7cdd9a913.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/f2c7a67f11d743938c4d08e7cdd9a913.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/371ca319428f40bebbc49f84249f36f8.mp3","seconds":30,"tags":"","args":"","notes":[{"name":"动作要点","desc":"\u2022 动作尽量缓慢，不可用力蹬腿\u2022下背部贴紧地面"},{"name":"呼吸节奏","desc":"·全程保持均匀呼吸"},{"name":"动作感觉","desc":"\u2022 每一次蹬直腿时腹部都会产生剧烈的紧绷感，但腰部不应该有紧绷疼痛感"}],"k":1,"size":428993,"totalK":30,"totalSeconds":50,"sort":9437357,"rules":[{"args":"","rest":20,"n":30}]},{"id":"624","title":"左侧卷腹抬腿","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/08341622b57648a7920ee1ed6d14fc2c.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/08341622b57648a7920ee1ed6d14fc2c.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/5a10b602a586494093df672196391149.mp3","seconds":3,"tags":"","args":"","notes":[{"name":"动作要点","desc":"\u2022 右手贴紧地面稳定身体\r\n\u2022用力拉近手肘与大腿的距离"},{"name":"呼吸节奏","desc":"\u2022 卷腹时呼气，下落时吸气"},{"name":"动作感觉","desc":"\u2022 抬腿时腹部左侧有挤压感"}],"k":1,"size":758805,"totalK":20,"totalSeconds":60,"sort":9437457,"rules":[{"args":"","rest":0,"n":20}]},{"id":"626","title":"右侧卷腹抬腿","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/8883e8453a9944f89b8bc778cefdf813.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/8883e8453a9944f89b8bc778cefdf813.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/ca47330123d24b3a96536973afe63b5b.mp3","seconds":3,"tags":"","args":"","notes":[{"name":"动作要点","desc":"\u2022 左手贴紧地面稳定身体\r\n\u2022 用力拉近手肘与大腿的距离"},{"name":"呼吸节奏","desc":"\u2022 卷腹时呼气，下落时吸气"},{"name":"动作感觉","desc":"\u2022 抬腿时腹部左侧有挤压感"}],"k":1,"size":723571,"totalK":20,"totalSeconds":60,"sort":9437538,"rules":[{"args":"","rest":0,"n":20}]},{"id":"2113","title":"仰卧交替抬腿","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/59/199/baf3ebe9c2164f72bd5e1489920dce5e.jpg","link":"http://ws-video.fithub.cc/trainaction/59/199/baf3ebe9c2164f72bd5e1489920dce5e.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/812dc2ce27c84b5bace46d810fb3d520.mp3","seconds":2,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·肩部稍抬离地面，双手在胸前用力并紧或垫于腰下做支撑\r\n·下背部用力贴紧地面，双腿伸直，勾起脚尖"},{"name":"呼吸节奏","desc":"·全程保持均匀呼吸"},{"name":"动作感觉","desc":"·整个腹肌始终保持紧绷感，动作持续越久，腹肌灼烧感越强"},{"name":"常见错误","desc":"·错误：为追求动作快而摆动身体，导致腿部肌肉感觉变强\r\n·解决：减少动作幅度，保证上身固定"}],"k":1,"size":0,"totalK":15,"totalSeconds":35,"sort":9437691,"rules":[{"args":"","rest":5,"n":15}]},{"id":"2114","title":"仰卧髋部旋转","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/29facd02cbc24963916904e00fd12132.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/29facd02cbc24963916904e00fd12132.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/6115be732ebb4344b24e6f7b9749101e.mp3","seconds":2,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·仰卧垫上，双手向两侧打开成一直线\r\n·双腿抬起屈膝90°，髋部带动双腿向两侧转动至45°\r\n·腹部收紧，保持肩膀贴紧地面，眼睛目视上方\r\n·注意均匀呼吸"},{"name":"呼吸节奏","desc":"·全程保持均匀呼吸"},{"name":"动作感觉","desc":"·尽量不要憋气，感觉腹部两侧有收缩感，保持动作流畅"}],"k":1,"size":822330,"totalK":10,"totalSeconds":25,"sort":9437774,"rules":[{"args":"","rest":5,"n":10}]},{"id":"2089","title":"腹部拉伸","basis":"2","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/dd3b785ba0874e3cbd123504d5286bca.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/dd3b785ba0874e3cbd123504d5286bca.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/0e994425368241b6b05059a087a626ec.mp3","seconds":15,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·臀部夹紧，肩膀下沉 手肘微屈\r\n·腿部完全贴紧地面，手将身体撑起用力拉伸腹部\r\n·下巴上扬，挺胸"},{"name":"呼吸节奏","desc":"·全程保持均匀呼吸"},{"name":"动作感觉","desc":"·上半身向上延展产生舒展感，整个腹部有牵拉感\r\n·同时避免幅度过大，引起腰部不适"}],"k":1,"size":0,"totalK":15,"totalSeconds":15,"sort":9437855,"rules":[{"args":"","rest":0,"n":15}]}],"seconds":345,"finished":0,"k":140,"size":7472079,"diffLevel":2,"brief":"","downloadAction":false}]
         * seconds : 345
         * tags : 腰腹
         * notes : 1、学习动作要点，动作规范的基础上跟随课程节奏进行训练
         2、无法完成整节课程时，可以降低训练强度，比如最后几个动作辅助完成
         3、如果训练当中出现“身体不适”或“关节疼痛”的现象，应立即停止训练或降低训练强度
         4、训练中可能会出现气喘，肌肉酸痛的情况，这均属于正常现象
         * size : 7472079
         * pic : http://ws-ugc.fithub.cc/jinj/train/item/3b61a7a08cc94952b8bb6d59ed3a8c4a.jpg
         * footer :
         */

        private String id;
        private int diffLevel;
        private int hardLevel;
        private int module;
        private int k;
        private String title;
        private String subTitle;
        private int seconds;
        private String tags;
        private String notes;
        private int size;
        private String pic;
        private String footer;
        private List<GroupsBean> groups;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getDiffLevel() {
            return diffLevel;
        }

        public void setDiffLevel(int diffLevel) {
            this.diffLevel = diffLevel;
        }

        public int getHardLevel() {
            return hardLevel;
        }

        public void setHardLevel(int hardLevel) {
            this.hardLevel = hardLevel;
        }

        public int getModule() {
            return module;
        }

        public void setModule(int module) {
            this.module = module;
        }

        public int getK() {
            return k;
        }

        public void setK(int k) {
            this.k = k;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getFooter() {
            return footer;
        }

        public void setFooter(String footer) {
            this.footer = footer;
        }

        public List<GroupsBean> getGroups() {
            return groups;
        }

        public void setGroups(List<GroupsBean> groups) {
            this.groups = groups;
        }

        public static class GroupsBean implements Serializable{
            /**
             * id : 199
             * title : 挖掘人鱼线
             * actions : [{"id":"2106","title":"单腿两头起","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/2df8aed66d3946d7918f7c8a1633d0f3.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/2df8aed66d3946d7918f7c8a1633d0f3.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/7701fab58bdb4b21a72bf6744044b7a0.mp3","seconds":3,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·双手伸直，腹肌发力起身\r\n·起身时腿部伸直，下背部贴地，手触摸到小腿，另一侧腿不能离开地面"},{"name":"呼吸节奏","desc":"·起身时呼气，还原时吸气"},{"name":"动作感觉","desc":"·起身的瞬间腹肌突然绷紧收缩\r\n·腰部始终放松，不应有紧绷感"}],"k":1,"size":867298,"totalK":15,"totalSeconds":50,"sort":9436995,"rules":[{"args":"","rest":5,"n":15}]},{"id":"2109","title":"仰卧屈膝转体","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/59/199/71879f9a0640452c98e267b1620bd8b1.jpg","link":"http://ws-video.fithub.cc/trainaction/59/199/71879f9a0640452c98e267b1620bd8b1.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/341e9f94374b4ce887f7d793655d53c0.mp3","seconds":3,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·挺直后背，两肘带动腰向身体左右方转动"},{"name":"动作感觉","desc":"·腹部两侧有微微发热的感觉"}],"k":1,"size":239157,"totalK":15,"totalSeconds":50,"sort":9437228,"rules":[{"args":"","rest":5,"n":15}]},{"id":"652","title":"倒蹬车","basis":"2","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/f2c7a67f11d743938c4d08e7cdd9a913.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/f2c7a67f11d743938c4d08e7cdd9a913.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/371ca319428f40bebbc49f84249f36f8.mp3","seconds":30,"tags":"","args":"","notes":[{"name":"动作要点","desc":"\u2022 动作尽量缓慢，不可用力蹬腿\u2022下背部贴紧地面"},{"name":"呼吸节奏","desc":"·全程保持均匀呼吸"},{"name":"动作感觉","desc":"\u2022 每一次蹬直腿时腹部都会产生剧烈的紧绷感，但腰部不应该有紧绷疼痛感"}],"k":1,"size":428993,"totalK":30,"totalSeconds":50,"sort":9437357,"rules":[{"args":"","rest":20,"n":30}]},{"id":"624","title":"左侧卷腹抬腿","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/08341622b57648a7920ee1ed6d14fc2c.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/08341622b57648a7920ee1ed6d14fc2c.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/5a10b602a586494093df672196391149.mp3","seconds":3,"tags":"","args":"","notes":[{"name":"动作要点","desc":"\u2022 右手贴紧地面稳定身体\r\n\u2022用力拉近手肘与大腿的距离"},{"name":"呼吸节奏","desc":"\u2022 卷腹时呼气，下落时吸气"},{"name":"动作感觉","desc":"\u2022 抬腿时腹部左侧有挤压感"}],"k":1,"size":758805,"totalK":20,"totalSeconds":60,"sort":9437457,"rules":[{"args":"","rest":0,"n":20}]},{"id":"626","title":"右侧卷腹抬腿","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/8883e8453a9944f89b8bc778cefdf813.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/8883e8453a9944f89b8bc778cefdf813.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/ca47330123d24b3a96536973afe63b5b.mp3","seconds":3,"tags":"","args":"","notes":[{"name":"动作要点","desc":"\u2022 左手贴紧地面稳定身体\r\n\u2022 用力拉近手肘与大腿的距离"},{"name":"呼吸节奏","desc":"\u2022 卷腹时呼气，下落时吸气"},{"name":"动作感觉","desc":"\u2022 抬腿时腹部左侧有挤压感"}],"k":1,"size":723571,"totalK":20,"totalSeconds":60,"sort":9437538,"rules":[{"args":"","rest":0,"n":20}]},{"id":"2113","title":"仰卧交替抬腿","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/59/199/baf3ebe9c2164f72bd5e1489920dce5e.jpg","link":"http://ws-video.fithub.cc/trainaction/59/199/baf3ebe9c2164f72bd5e1489920dce5e.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/812dc2ce27c84b5bace46d810fb3d520.mp3","seconds":2,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·肩部稍抬离地面，双手在胸前用力并紧或垫于腰下做支撑\r\n·下背部用力贴紧地面，双腿伸直，勾起脚尖"},{"name":"呼吸节奏","desc":"·全程保持均匀呼吸"},{"name":"动作感觉","desc":"·整个腹肌始终保持紧绷感，动作持续越久，腹肌灼烧感越强"},{"name":"常见错误","desc":"·错误：为追求动作快而摆动身体，导致腿部肌肉感觉变强\r\n·解决：减少动作幅度，保证上身固定"}],"k":1,"size":0,"totalK":15,"totalSeconds":35,"sort":9437691,"rules":[{"args":"","rest":5,"n":15}]},{"id":"2114","title":"仰卧髋部旋转","basis":"1","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/29facd02cbc24963916904e00fd12132.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/29facd02cbc24963916904e00fd12132.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/6115be732ebb4344b24e6f7b9749101e.mp3","seconds":2,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·仰卧垫上，双手向两侧打开成一直线\r\n·双腿抬起屈膝90°，髋部带动双腿向两侧转动至45°\r\n·腹部收紧，保持肩膀贴紧地面，眼睛目视上方\r\n·注意均匀呼吸"},{"name":"呼吸节奏","desc":"·全程保持均匀呼吸"},{"name":"动作感觉","desc":"·尽量不要憋气，感觉腹部两侧有收缩感，保持动作流畅"}],"k":1,"size":822330,"totalK":10,"totalSeconds":25,"sort":9437774,"rules":[{"args":"","rest":5,"n":10}]},{"id":"2089","title":"腹部拉伸","basis":"2","pic":"http://ws-ugc.fithub.cc/trainaction/1/1/dd3b785ba0874e3cbd123504d5286bca.jpg","link":"http://ws-video.fithub.cc/trainaction/1/1/dd3b785ba0874e3cbd123504d5286bca.mp4","audio":"http://ws-video.fithub.cc/trainaction/1/1/0e994425368241b6b05059a087a626ec.mp3","seconds":15,"tags":"","args":"","notes":[{"name":"动作要点","desc":"·臀部夹紧，肩膀下沉 手肘微屈\r\n·腿部完全贴紧地面，手将身体撑起用力拉伸腹部\r\n·下巴上扬，挺胸"},{"name":"呼吸节奏","desc":"·全程保持均匀呼吸"},{"name":"动作感觉","desc":"·上半身向上延展产生舒展感，整个腹部有牵拉感\r\n·同时避免幅度过大，引起腰部不适"}],"k":1,"size":0,"totalK":15,"totalSeconds":15,"sort":9437855,"rules":[{"args":"","rest":0,"n":15}]}]
             * seconds : 345
             * finished : 0
             * k : 140
             * size : 7472079
             * diffLevel : 2
             * brief :
             * downloadAction : false
             */

            private String id;
            private String title;
            private int seconds;
            private int finished;
            private int k;
            private int size;
            private int diffLevel;
            private String brief;
            private boolean downloadAction;
            private List<ActionsBean> actions;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public int getFinished() {
                return finished;
            }

            public void setFinished(int finished) {
                this.finished = finished;
            }

            public int getK() {
                return k;
            }

            public void setK(int k) {
                this.k = k;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getDiffLevel() {
                return diffLevel;
            }

            public void setDiffLevel(int diffLevel) {
                this.diffLevel = diffLevel;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public boolean isDownloadAction() {
                return downloadAction;
            }

            public void setDownloadAction(boolean downloadAction) {
                this.downloadAction = downloadAction;
            }

            public List<ActionsBean> getActions() {
                return actions;
            }

            public void setActions(List<ActionsBean> actions) {
                this.actions = actions;
            }

            public static class ActionsBean implements Serializable{
                /**
                 * id : 2106
                 * title : 单腿两头起
                 * basis : 1
                 * pic : http://ws-ugc.fithub.cc/trainaction/1/1/2df8aed66d3946d7918f7c8a1633d0f3.jpg
                 * link : http://ws-video.fithub.cc/trainaction/1/1/2df8aed66d3946d7918f7c8a1633d0f3.mp4
                 * audio : http://ws-video.fithub.cc/trainaction/1/1/7701fab58bdb4b21a72bf6744044b7a0.mp3
                 * seconds : 3
                 * tags :
                 * args :
                 * notes : [{"name":"动作要点","desc":"·双手伸直，腹肌发力起身\r\n·起身时腿部伸直，下背部贴地，手触摸到小腿，另一侧腿不能离开地面"},{"name":"呼吸节奏","desc":"·起身时呼气，还原时吸气"},{"name":"动作感觉","desc":"·起身的瞬间腹肌突然绷紧收缩\r\n·腰部始终放松，不应有紧绷感"}]
                 * k : 1
                 * size : 867298
                 * totalK : 15
                 * totalSeconds : 50
                 * sort : 9436995
                 * rules : [{"args":"","rest":5,"n":15}]
                 */

                private String id;
                private String title;
                private String basis;
                private String pic;
                private String link;
                private String audio;
                private int seconds;
                private String tags;
                private String args;
                private int k;
                private int size;
                private int totalK;
                private int totalSeconds;
                private int sort;
                private List<NotesBean> notes;
                private List<RulesBean> rules;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getBasis() {
                    return basis;
                }

                public void setBasis(String basis) {
                    this.basis = basis;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getAudio() {
                    return audio;
                }

                public void setAudio(String audio) {
                    this.audio = audio;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public String getTags() {
                    return tags;
                }

                public void setTags(String tags) {
                    this.tags = tags;
                }

                public String getArgs() {
                    return args;
                }

                public void setArgs(String args) {
                    this.args = args;
                }

                public int getK() {
                    return k;
                }

                public void setK(int k) {
                    this.k = k;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public int getTotalK() {
                    return totalK;
                }

                public void setTotalK(int totalK) {
                    this.totalK = totalK;
                }

                public int getTotalSeconds() {
                    return totalSeconds;
                }

                public void setTotalSeconds(int totalSeconds) {
                    this.totalSeconds = totalSeconds;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public List<NotesBean> getNotes() {
                    return notes;
                }

                public void setNotes(List<NotesBean> notes) {
                    this.notes = notes;
                }

                public List<RulesBean> getRules() {
                    return rules;
                }

                public void setRules(List<RulesBean> rules) {
                    this.rules = rules;
                }

                public static class NotesBean implements Serializable{
                    /**
                     * name : 动作要点
                     * desc : ·双手伸直，腹肌发力起身
                     ·起身时腿部伸直，下背部贴地，手触摸到小腿，另一侧腿不能离开地面
                     */

                    private String name;
                    private String desc;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }

                public static class RulesBean implements Serializable{
                    /**
                     * args :
                     * rest : 5
                     * n : 15
                     */

                    private String args;
                    private int rest;
                    private int n;

                    public String getArgs() {
                        return args;
                    }

                    public void setArgs(String args) {
                        this.args = args;
                    }

                    public int getRest() {
                        return rest;
                    }

                    public void setRest(int rest) {
                        this.rest = rest;
                    }

                    public int getN() {
                        return n;
                    }

                    public void setN(int n) {
                        this.n = n;
                    }
                }
            }
        }
    }
}
