package com.example.gab.babylove.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 初夏小溪 on 2018/4/13 0013.
 * 首頁數據源
 */

public class CollectBean implements Serializable {

    /**
     * curPage : 1
     * datas : [{"author":"Shawn_Dut","chapterId":98,"chapterName":"WebView","courseId":13,"desc":"","envelopePic":"","id":9369,"link":"https://juejin.im/post/58a037df86b599006b3fade4","niceDate":"1小时前","origin":"","originId":2837,"publishTime":1524536947000,"title":"android WebView详解，常见漏洞详解和安全源码","userId":3555,"visible":0,"zan":0},{"author":"LiangLuDev","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"注册登录、用户信息、用户密码、用户图像修改、书籍分类、本地书籍扫描、书架、书籍搜索（作者名或书籍名）、书籍阅读（仅txt格式，暂不支持PDF等其他格式）、阅读字体、背景颜色、翻页效果等设置、意见反馈（反馈信息发送到我的邮箱）、应用版本更新","envelopePic":"http://www.wanandroid.com/blogimgs/fab6fb8b-c3aa-495f-b6a9-c007d78751c0.gif","id":9370,"link":"http://www.wanandroid.com/blog/show/2116","niceDate":"1小时前","origin":"","originId":2836,"publishTime":1524536947000,"title":"微Yue电子书阅读 WeYueReader","userId":3555,"visible":0,"zan":0},{"author":"小编","chapterId":352,"chapterName":"资讯","courseId":13,"desc":"","envelopePic":"","id":9368,"link":"https://www.oschina.net/openapi","niceDate":"1小时前","origin":"","originId":2842,"publishTime":1524536914000,"title":"开源中国oschina API","userId":3555,"visible":0,"zan":0},{"author":"滑板上的老砒霜 ","chapterId":135,"chapterName":"二维码","courseId":13,"desc":"","envelopePic":"","id":9367,"link":"https://www.jianshu.com/p/a4ba10da4231","niceDate":"1小时前","origin":"","originId":2867,"publishTime":1524536912000,"title":"Android扫一扫：zxing的集成与优化","userId":3555,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 4
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean implements Serializable {
        /**
         * author : Shawn_Dut
         * chapterId : 98
         * chapterName : WebView
         * courseId : 13
         * desc :
         * envelopePic :
         * id : 9369
         * link : https://juejin.im/post/58a037df86b599006b3fade4
         * niceDate : 1小时前
         * origin :
         * originId : 2837
         * publishTime : 1524536947000
         * title : android WebView详解，常见漏洞详解和安全源码
         * userId : 3555
         * visible : 0
         * zan : 0
         */

        private String author;
        private int chapterId;
        private String chapterName;
        private int courseId;
        private String desc;
        private String envelopePic;
        private int id;
        private boolean collect = true;//设置默认值true （方便 个人收藏界面使用）

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        private String link;
        private String niceDate;
        private String origin;
        private int originId;
        private long publishTime;
        private String title;
        private int userId;
        private int visible;
        private int zan;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public int getOriginId() {
            return originId;
        }

        public void setOriginId(int originId) {
            this.originId = originId;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }
    }
}
