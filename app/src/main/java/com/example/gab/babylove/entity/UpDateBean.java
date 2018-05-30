package com.example.gab.babylove.entity;

/**
 * Created by 初夏小溪 on 2018/5/30 0030.
 */
public class UpDateBean {

    /**
     * result : 操作成功
     * lesprint_uuid_api : fc20854d-ebd4-4e46-9bbd-6fed15397ef4
     * resource : https://api.lccfd.51sprint.com/lesprint/
     * CONTEXT_PATH : /lesprint
     * version : {"content":"","down_url":"http://sprintfd.oss-cn-hangzhou.aliyuncs.com/apk/ldz_2.3.2.apk","is_force_update":false,"version":"2.3.2"}
     * status : 0
     */

    private String result;
    private String lesprint_uuid_api;
    private String resource;
    private String CONTEXT_PATH;
    private VersionBean version;
    private int status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLesprint_uuid_api() {
        return lesprint_uuid_api;
    }

    public void setLesprint_uuid_api(String lesprint_uuid_api) {
        this.lesprint_uuid_api = lesprint_uuid_api;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getCONTEXT_PATH() {
        return CONTEXT_PATH;
    }

    public void setCONTEXT_PATH(String CONTEXT_PATH) {
        this.CONTEXT_PATH = CONTEXT_PATH;
    }

    public VersionBean getVersion() {
        return version;
    }

    public void setVersion(VersionBean version) {
        this.version = version;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class VersionBean {
        /**
         * content :
         * down_url : http://sprintfd.oss-cn-hangzhou.aliyuncs.com/apk/ldz_2.3.2.apk
         * is_force_update : false
         * version : 2.3.2
         */

        private String content;
        private String down_url;
        private boolean is_force_update;
        private String version;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDown_url() {
            return down_url;
        }

        public void setDown_url(String down_url) {
            this.down_url = down_url;
        }

        public boolean isIs_force_update() {
            return is_force_update;
        }

        public void setIs_force_update(boolean is_force_update) {
            this.is_force_update = is_force_update;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
