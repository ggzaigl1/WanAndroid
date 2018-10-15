package com.example.gab.babylove.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 初夏小溪 on 2018/10/15 0015.
 * <p>
 * 公众号详情
 */
public class OfficialAccountListBean implements Serializable {

    /**
     * curPage : 1
     * datas : [{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3539,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650825961&idx=1&sn=fb6bb35fc9e74e258fd9d772a08ff662&chksm=80b7b077b7c03961e900ccb85a00b1cb6749561aa02bcf0a5e53f6fbebcb9f636dbef5e479d8&scene=38#wechat_redirect","niceDate":"2018-08-01","origin":"","projectLink":"","publishTime":1533052800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"超级大牛总结的Java架构师提升路径，拿走不谢","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3488,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650825931&idx=1&sn=b61ed2ad2665cbc3a89351b161223769&chksm=80b7b055b7c03943f10f1e8b3697d3d33bbfc98d38e40c9ecbc6a3ab34f700a065858f8c8ce9&scene=38#wechat_redirect","niceDate":"2018-07-25","origin":"","projectLink":"","publishTime":1532448000000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"一步步带你深入了解神秘的Java反射机制","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3919,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650825861&idx=1&sn=d275300dafba6a85ea11d317f0a115c7&chksm=80b7b01bb7c0390d0ecf0138ad31b3b05933b34b158088998cebee6dc8a5124688700828644d&scene=38#wechat_redirect","niceDate":"2018-07-16","origin":"","projectLink":"","publishTime":1531670400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"架构师之路：从Java码农到年薪八十万的架构师","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3780,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650825652&idx=1&sn=435c0dfcf66395b7c48304c6cccdcfe0&chksm=80b7b72ab7c03e3cacc8719f1659db2b23558f873b75b8fffccaff7b8e1629358c1ff0a43dc1&scene=38#wechat_redirect","niceDate":"2018-06-19","origin":"","projectLink":"","publishTime":1529337600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"荐一个面试必问的Java系列专题","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3633,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650825634&idx=1&sn=7e87bc50c2d6d381f21b6d8d86a4aa0c&chksm=80b7b73cb7c03e2ab0e27fa7943e9eda0cf144ef76cefa550fd003f23878e534807913c0f186&scene=38#wechat_redirect","niceDate":"2018-06-14","origin":"","projectLink":"","publishTime":1528905600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Java体系化学习路线图，拿走不谢！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3756,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650825497&idx=1&sn=fe158b6c38ff2bd70c79711389f557c2&chksm=80b7b787b7c03e910af985aabff5d9b0158c1db44fe93f0e4ba8f8920f910d1d02ccd3244a88&scene=38#wechat_redirect","niceDate":"2018-05-17","origin":"","projectLink":"","publishTime":1526486400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"必须要理清的Java线程池","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3578,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650825107&idx=1&sn=7f0ac639f8b93806de0d3b6ad9762923&chksm=80b7b50db7c03c1b425e6e59459ddbbe6d1dd309b9b1dd0f5cfa01b65ce58c6174e96792e3d1&scene=38#wechat_redirect","niceDate":"2018-03-07","origin":"","projectLink":"","publishTime":1520352000000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"RxJava实际应用案例讲解：使用RxJava的最佳开发场景","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3930,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650824984&idx=1&sn=36a2d6b973fbb383f6d7201c844db9c3&chksm=80b7b586b7c03c9088298ad3f0e138bc388c4497c050ab867288a149f4ea781ad4d40191d435&scene=38#wechat_redirect","niceDate":"2018-02-06","origin":"","projectLink":"","publishTime":1517846400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"手把手带你入门神秘的 RxJava","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3847,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650824891&idx=1&sn=d86b4c2215247d78bfa0b60207ae2fe6&chksm=80b7b425b7c03d336e240c7b885b5a326f3c589f22d40a17561938468963bb5321e8413d480c&scene=38#wechat_redirect","niceDate":"2018-01-22","origin":"","projectLink":"","publishTime":1516550400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"这是一份全面 & 详细 的RxJava操作符 使用攻略","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3738,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650824708&idx=1&sn=1e66667522e5b4602eeb5391276a561f&chksm=80b7b49ab7c03d8c69e4f6ad64939679c830d23a524d900e6263dba20d946c8e68b9e2462898&scene=38#wechat_redirect","niceDate":"2017-12-21","origin":"","projectLink":"","publishTime":1513785600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Java反射以及在Android中的特殊应用","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3987,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650824661&idx=1&sn=48beadd58b5786aac3df033a35119dfd&chksm=80b78b4bb7c0025d69d0bbc7f987bb23f21452dd32e70087f8cd58620d794c223a4442fdc13a&scene=38#wechat_redirect","niceDate":"2017-12-10","origin":"","projectLink":"","publishTime":1512835200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"练手项目 | 基于MVP+RxJava2+Retrofit2的资讯类App","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3815,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650824648&idx=1&sn=e2c4e76adbb9bb74f50915d7280240ec&chksm=80b78b56b7c002404a347f8e9ad6d7e16cd9b2b32434e3100489e6fa9a67fc58af552e680ccb&scene=38#wechat_redirect","niceDate":"2017-12-08","origin":"","projectLink":"","publishTime":1512662400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android 滑动选择控件 & MVP+Retrofit+RxJava资源推荐","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":4051,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650823932&idx=1&sn=198b18f2f9359e2eee1ffc8703d31905&chksm=80b78862b7c001741916c681d070ca3c1a58eef5632ea394797029d0335f312816afecf87e7d&scene=38#wechat_redirect","niceDate":"2017-09-01","origin":"","projectLink":"","publishTime":1504195200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"RxJava2 学习资料推荐","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3849,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650823617&idx=1&sn=09fce6bbdc139f7a29e0abb4a4d954a7&chksm=80b78f5fb7c006490c394d1a44b06ca458109bd5c6b021b409642f921a7eb8d60d4c5f6dddea&scene=38#wechat_redirect","niceDate":"2017-07-27","origin":"","projectLink":"","publishTime":1501084800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"这可能是最好的RxJava 2.x 教程（完结版）","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3752,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650823111&idx=1&sn=e52181bc51a3b2555c395b5731a3a108&chksm=80b78d59b7c0044f7b9e7ec3ca2277ef0c770139783fa1d6d0b01e903e6e67572e647a11e161&scene=38#wechat_redirect","niceDate":"2017-05-23","origin":"","projectLink":"","publishTime":1495468800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"抢鲜下载 | 阿里Java开发手册最新完美版，千锤百炼始出炉！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3862,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650822789&idx=1&sn=aabf8b95e233a6bb19466e58fb90f812&chksm=80b78c1bb7c0050da725cb947e0ae3a657e74dbae210bd3228eeaa1f7a990e466bc31b25c0d0&scene=38#wechat_redirect","niceDate":"2017-05-02","origin":"","projectLink":"","publishTime":1493654400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"写给Android开发者的Java 8简单入门教程","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3610,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650822772&idx=1&sn=7aa2233926f5346cd2035b1d2e16e812&chksm=80b78ceab7c005fca353ade726cb9003dc2962c98b5565414bfd4a1032cce11707fd0fcaf5a0&scene=38#wechat_redirect","niceDate":"2017-04-28","origin":"","projectLink":"","publishTime":1493308800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"MVVM，RxJava和Retrofit的一次实践","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3909,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650822598&idx=1&sn=3e2ab42875cfe3dadba914402f8d2732&chksm=80b78358b7c00a4e011b0f5ca10ba4b4da0d6f100401d5ad4273a2be862d07f20339672fb113&scene=38#wechat_redirect","niceDate":"2017-04-14","origin":"","projectLink":"","publishTime":1492099200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android实战 RxJava2+Retrofit+RxBinding解锁各种新姿势","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3754,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650822524&idx=1&sn=c277c32df29e68ade42c6e15a5b6f6e1&chksm=80b783e2b7c00af4958072dc21e5e749af46b5f6113f15de932b9db6748dfe86ed656369aa84&scene=38#wechat_redirect","niceDate":"2017-04-05","origin":"","projectLink":"","publishTime":1491321600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"我们为什么要把Dagger2,MVP以及Rxjava引入项目中?","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3657,"link":"http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650820734&idx=1&sn=102248144c5e7c51ce96dd5d98dd6482&scene=38#wechat_redirect","niceDate":"2016-07-29","origin":"","projectLink":"","publishTime":1469721600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"RxJava + Retrofit完成网络请求","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 0
     * over : false
     * pageCount : 2
     * size : 20
     * total : 22
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
         * author : 鸿洋
         * chapterId : 408
         * chapterName : 鸿洋
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : false
         * id : 3539
         * link : http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650825961&idx=1&sn=fb6bb35fc9e74e258fd9d772a08ff662&chksm=80b7b077b7c03961e900ccb85a00b1cb6749561aa02bcf0a5e53f6fbebcb9f636dbef5e479d8&scene=38#wechat_redirect
         * niceDate : 2018-08-01
         * origin :
         * projectLink :
         * publishTime : 1533052800000
         * superChapterId : 408
         * superChapterName : 公众号
         * tags : [{"name":"公众号","url":"/wxarticle/list/408/1"}]
         * title : 超级大牛总结的Java架构师提升路径，拿走不谢
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

        public static class TagsBean implements Serializable{
            /**
             * name : 公众号
             * url : /wxarticle/list/408/1
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
