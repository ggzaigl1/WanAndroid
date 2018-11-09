package com.example.gab.babylove.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 初夏小溪 on 2018/4/13 0013.
 * 首页数据
 */

public class ArticleBean implements Serializable {

    /**
     * curPage : 2
     * datas : [{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7465,"link":"https://mp.weixin.qq.com/s/quhz5TsboBQPa2BtEB-v6w","niceDate":"2018-11-05","origin":"","projectLink":"","publishTime":1541347200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"基于 opencv 实现人脸检测","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7455,"link":"https://mp.weixin.qq.com/s/cvcu6kbXzR5x5tpLYYEMKw","niceDate":"2018-11-02","origin":"","projectLink":"","publishTime":1541088000000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"总结几种判断RecyclerView到达底部的方法","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7456,"link":"https://mp.weixin.qq.com/s/24AJKGtcHJeRliUGz3PKsg","niceDate":"2018-11-02","origin":"","projectLink":"","publishTime":1541088000000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Android手机无法开机到桌面问题，定位方法","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7457,"link":"https://mp.weixin.qq.com/s/vVJ3IDuGumo9h4XCNgrbAA","niceDate":"2018-11-02","origin":"","projectLink":"","publishTime":1541088000000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"对 Android 未来的发展十分重要的技术 | App Bundles","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"ksballetba","chapterId":100,"chapterName":"RecyclerView","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7443,"link":"https://juejin.im/post/5bd5b96751882526986e744f","niceDate":"2018-11-01","origin":"","projectLink":"","publishTime":1541084418000,"superChapterId":100,"superChapterName":"5.+高新技术","tags":[],"title":"手把手教你用RecyclerView实现猫眼电影选择效果","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7451,"link":"https://mp.weixin.qq.com/s/CWJCb3Eh65dRLJp2dL8eog","niceDate":"2018-11-01","origin":"","projectLink":"","publishTime":1541001600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"Google推荐的后台任务解决方案","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7452,"link":"https://mp.weixin.qq.com/s/0hkCcegxcP43hef3e0HeYA","niceDate":"2018-11-01","origin":"","projectLink":"","publishTime":1541001600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Android开发 - 获取系统输入法高度的正确姿势","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7453,"link":"https://mp.weixin.qq.com/s/ufOjtKURP8QERWw1pn_m1Q","niceDate":"2018-11-01","origin":"","projectLink":"","publishTime":1541001600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"图片占内存公式：分辨率 * 每个像素大小，严谨吗？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"罗占伟David","chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7446,"link":"https://www.jianshu.com/p/c821f1fb90d1","niceDate":"2018-10-31","origin":"","projectLink":"","publishTime":1540987233000,"superChapterId":73,"superChapterName":"热门专题","tags":[],"title":"Android App 启动速度优化实现演练","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"张风捷特烈","chapterId":168,"chapterName":"Drawable","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7445,"link":"https://www.jianshu.com/p/89aa5f9ed17a","niceDate":"2018-10-31","origin":"","projectLink":"","publishTime":1540987192000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"自己写一个svg转化为安卓xml的工具类","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"贤榆的榆","chapterId":418,"chapterName":"AOSP 源码编译","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7444,"link":"https://juejin.im/post/5bd5c42ce51d457a9b6c8387","niceDate":"2018-10-31","origin":"","projectLink":"","publishTime":1540987165000,"superChapterId":178,"superChapterName":"framework","tags":[],"title":"AndroidStudio不用编译，阅读Android源码","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7447,"link":"https://mp.weixin.qq.com/s/N0IFQHVRRSQ65pNMMCvBvg","niceDate":"2018-10-31","origin":"","projectLink":"","publishTime":1540915200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"Android的so文件加载机制详解","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7448,"link":"https://mp.weixin.qq.com/s/VW-NBbvFye4fJ5qSplFA-A","niceDate":"2018-10-31","origin":"","projectLink":"","publishTime":1540915200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"通过JavaScript实现在Android WebView中点击查看图片，长按识别二维码","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7449,"link":"https://mp.weixin.qq.com/s/B7yvicK4vi50JKeEO0NG0Q","niceDate":"2018-10-31","origin":"","projectLink":"","publishTime":1540915200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"玩Android 有你们更精彩！荐开源项目！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"hyzhan43","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"继上次用 kotlin 编写了 一款简单 豆瓣电影 app 后。体验到了kotlin 的魅力。加上这段时间学习了 MVP 模式、MVVM模式，心痒痒，就像做个 app 来练练手，正当犹豫要选择哪一种来练手的时候，无意中看见另一种的模式艺术图片应用 T-MVVM~ 感觉说的挺有道理的。好奇心驱使我去试一下这种模式，说干就干。","envelopePic":"http://www.wanandroid.com/blogimgs/070d9f4a-2ceb-457a-bb12-f7d55b5cf900.png","fresh":false,"id":7441,"link":"http://www.wanandroid.com/blog/show/2411","niceDate":"2018-10-30","origin":"","projectLink":"https://github.com/hyzhan43/PlayAndroid","publishTime":1540908634000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"用心打造&mdash;&mdash;Kotlin 版玩Android","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Ericsongyl","chapterId":367,"chapterName":"资源聚合类","collect":false,"courseId":13,"desc":"OSF：全称为Android Open Source Framework，即Android优秀开源框架汇总。包含：网络请求，图片下载，数据库，链式框架，组件路由，消息传递通信，热更新，文件下载，图片选择，图片滤镜/毛玻璃等特效处理，GIF图片展示控件，图片九宫格控件 等等 应有尽有。","envelopePic":"http://www.wanandroid.com/resources/image/pc/default_project_img.jpg","fresh":false,"id":7440,"link":"http://www.wanandroid.com/blog/show/2410","niceDate":"2018-10-30","origin":"","projectLink":"https://github.com/Ericsongyl/AOSF","publishTime":1540908422000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=367"}],"title":"Android常用优秀开源框架汇总","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"ChanghuiN","chapterId":313,"chapterName":"字节码","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7435,"link":"https://www.hchstudio.cn/article/2018/e8df/","niceDate":"2018-10-30","origin":"","projectLink":"","publishTime":1540883822000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"操作 Java 字节码","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"小编","chapterId":249,"chapterName":"干货资源","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7434,"link":"https://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&amp;mid=2650826531&amp;idx=1&amp;sn=a63c379ca016aa6c07045e20b00e1a1c","niceDate":"2018-10-30","origin":"","projectLink":"","publishTime":1540857996000,"superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"这可能是Java程序员学习大数据工程师最佳之路！另送1024G学习资料！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"玉刚说","chapterId":410,"chapterName":"玉刚说","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7436,"link":"https://mp.weixin.qq.com/s/V0cL9bSTM65HTIJ4CU4Cow","niceDate":"2018-10-30","origin":"","projectLink":"","publishTime":1540828800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/410/1"}],"title":"Flutter学习指南：UI布局和控件","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7437,"link":"https://mp.weixin.qq.com/s/dTR42a_TucdYRl7YTukRCQ","niceDate":"2018-10-30","origin":"","projectLink":"","publishTime":1540828800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"为什么应用程序在Android 7.0之后安装和运行都变得更快？","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 20
     * over : false
     * pageCount : 283
     * size : 20
     * total : 5658
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
         * apkLink :
         * author : code小生
         * chapterId : 414
         * chapterName : code小生
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : false
         * id : 7465
         * link : https://mp.weixin.qq.com/s/quhz5TsboBQPa2BtEB-v6w
         * niceDate : 2018-11-05
         * origin :
         * projectLink :
         * publishTime : 1541347200000
         * superChapterId : 408
         * superChapterName : 公众号
         * tags : [{"name":"公众号","url":"/wxarticle/list/414/1"}]
         * title : 基于 opencv 实现人脸检测
         * type : 0
         * userId : -1
         * visible : 1
         * zan : 0
         */

        private String apkLink;
        private String author;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private String projectLink;
        private long publishTime;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
        private List<TagsBean> tags;

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

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

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
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

        public boolean isFresh() {
            return fresh;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
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

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class TagsBean implements Serializable {
            /**
             * name : 公众号
             * url : /wxarticle/list/414/1
             */

            private String name;
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}