package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gab on 2017/12/18 0018.
 *
 */

public class HomeBean implements Serializable {

    /**
     * stat : 1
     */

    private String stat;
    private List<DataBean> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * uniquekey : f82c0a8547409b2d165ea77ace72f8e3
         * title : 中国长安网独家专访找回生母死刑犯：请亲人们等我回来！
         * date : 2017-12-18 14:02
         * category : 头条
         * author_name : 环球网
         * url : http://mini.eastday.com/mobile/171218140236672.html
         * thumbnail_pic_s : http://01.imgmini.eastday.com/mobile/20171218/20171218140236_49a29dc870994ec85e4337eba229468a_3_mwpm_03200403.jpg
         * thumbnail_pic_s02 : http://01.imgmini.eastday.com/mobile/20171218/20171218140236_49a29dc870994ec85e4337eba229468a_2_mwpm_03200403.jpg
         * thumbnail_pic_s03 : http://01.imgmini.eastday.com/mobile/20171218/20171218140236_49a29dc870994ec85e4337eba229468a_1_mwpm_03200403.jpg
         */

        private String uniquekey;
        private String title;
        private String date;
        private String category;
        private String author_name;
        private String url;
        private String thumbnail_pic_s;
        private String thumbnail_pic_s02;
        private String thumbnail_pic_s03;

        public String getUniquekey() {
            return uniquekey;
        }

        public void setUniquekey(String uniquekey) {
            this.uniquekey = uniquekey;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
        }

        public String getThumbnail_pic_s02() {
            return thumbnail_pic_s02;
        }

        public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
            this.thumbnail_pic_s02 = thumbnail_pic_s02;
        }

        public String getThumbnail_pic_s03() {
            return thumbnail_pic_s03;
        }

        public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
            this.thumbnail_pic_s03 = thumbnail_pic_s03;
        }
    }
}
