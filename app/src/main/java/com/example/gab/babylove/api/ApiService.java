package com.example.gab.babylove.api;

import com.example.gab.babylove.entity.ArticleBean;
import com.example.gab.babylove.entity.BannerBean;
import com.example.gab.babylove.entity.BookmarkBean;
import com.example.gab.babylove.entity.CollectBean;
import com.example.gab.babylove.entity.CourseDetails;
import com.example.gab.babylove.entity.CourseList;
import com.example.gab.babylove.entity.GankBean;
import com.example.gab.babylove.entity.HomeBean;
import com.example.gab.babylove.entity.LoginBean;
import com.example.gab.babylove.entity.NavigationBean;
import com.example.gab.babylove.entity.OfficialAccountBean;
import com.example.gab.babylove.entity.OfficialAccountListBean;
import com.example.gab.babylove.entity.TreeBean;
import com.example.gab.babylove.entity.ProjectBean;
import com.example.gab.babylove.entity.UpDateBean;
import com.example.gab.babylove.entity.UpdateAppInfoBean;
import com.ggz.baselibrary.retrofit.BeanModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 通用的 api接口 </p>
 * Created by fangs on 2017/8/28.
 */
public interface ApiService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("user/login")
    Observable<BeanModule<LoginBean>> getLogin(@FieldMap Map<String, Object> options);

    /**
     * 注册接口
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("user/register")
    Observable<BeanModule<LoginBean>> getRegister(@FieldMap Map<String, Object> options);

    /**
     * 首页 banner
     */
    @Headers({"url_name:user"})
    @GET("banner/json")
    Observable<BeanModule<List<BannerBean>>> getBanner();

    /**
     * 美图图片
     */
    @Headers({"url_name:user"})
    @GET("http://gank.io/api/data/福利/{count}/{page}")
    Observable<GankBean> getCourseDetails(@Path("count") int count, @Path("page") int page);

    /**
     * 首页文章列表
     */
    @Headers({"url_name:user"})
    @GET("article/list/{id}/json")
    Observable<BeanModule<ArticleBean>> getArticleHomeList(@Path("id") int page);

    /**
     * 体系数据
     *
     * @return
     */
    @Headers({"url_name:user"})
    @GET("tree/json")
    Observable<BeanModule<List<TreeBean>>> getTreeList();

    /**
     * 体系数据 详情
     *
     * @return
     */
    @Headers({"url_name:user"})
    @GET("article/list/{id}/json")
    Observable<BeanModule<ArticleBean>> getArticleList(@Path("id") int page, @Query("cid") int cid);

    /**
     * 常用网站
     */
    @Headers({"url_name:user"})
    @GET("friend/json")
    Observable<BeanModule<List<BookmarkBean>>> getBookmarkList();

    /**
     * 搜索热词
     */
    @Headers({"url_name:user"})
    @GET("hotkey/json")
    Observable<BeanModule<List<BookmarkBean>>> getHotkeykList();

    /**
     * 视图导航
     */
    @Headers({"url_name:user"})
    @GET("navi/json")
    Observable<BeanModule<List<NavigationBean>>> getNaviList();

    /**
     * 项目
     */
    @Headers({"url_name:user"})
    @GET("project/tree/json")
    Observable<BeanModule<List<ProjectBean>>> getProjectList();


    /**
     * 收藏文章列表
     */
    @Headers({"url_name:user"})
    @GET("lg/collect/list/{id}/json")
    Observable<BeanModule<CollectBean>> getCollectList(@Path("id") int page);

    /**
     * 更新
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("lg/collect/{id}/json")
    Observable<BeanModule<UpdateAppInfoBean>> getupdata(@Path("id") int articleId, @Field("reason") String reason);

    /**
     * 收藏站内文章
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("lg/collect/{id}/json")
    Observable<BeanModule<Object>> getCollectArticle(@Path("id") int articleId, @Field("reason") String reason);

    /**
     * 站内文章  取消收藏 (文章列表)
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BeanModule<Object>> uncollectArticle(@Path("id") int articleId,
                                                    @Field("reason") String reason);

    /**
     * 站内文章 取消收藏 [我的收藏页面（该页面包含自己录入的内容）]
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("lg/uncollect/{id}/json")
    Observable<BeanModule<Object>> unMyCollectArticle(@Path("id") int articleId, @Field("originId") int originId);

    /**
     * 搜索
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("article/query/{pageNum}/json")
    Observable<BeanModule<ArticleBean>> getQuery(@Path("pageNum") int pageNum, @Field("k") String queryKey);

    /**
     * 获取公众号列表
     */
    @Headers({"url_name:user"})
    @GET("wxarticle/chapters/json")
    Observable<List<OfficialAccountBean>> getChapters();


    /**
     * 查看某个公众号历史数据
     */
    @Headers({"url_name:user"})
    @GET("wxarticle/list/{ID}/{pageNo}/json")
    Observable<BeanModule<OfficialAccountListBean>> getWxarticle(@Path("ID") int Id, @Path("pageNo") int pageNo);

    /**
     * 在某个公众号中搜索历史文章
     */
    @Headers({"url_name:user"})
    @GET("wxarticle/list/{ID}/{pageNo}/json")
    Observable<BeanModule<OfficialAccountListBean>> getWxarticleQuery(@Path("ID") int Id, @Path("pageNo") int pageNo
            , @Query("k") String queryKey);

    /**
     * 运动课程 ---运动列表
     */
    @Headers({"url_name:user"})
    @GET("http://api.fithub.cc/api/trainitem/trainitemlist")
    Observable<CourseList> getCourseList(@Query("pageNo") int page);

    /**
     * 运动课程 ---运动详情
     */
    @Headers({"url_name:user"})
    @GET("http://api.fithub.cc/api/v44/train/course")
    Observable<CourseDetails> getCourseDetails(@Query("id") int id);


    /**
     * 版本更新
     */
    @Headers({"url_name:user"})
    @GET("http://118.31.218.69/lesprint/api/version/versionInfo")
    Observable<UpDateBean> getVersionsUpdate(@Query("deviceId") String deviceId, @Query("code") String android);


    /**
     * 多图片上传
     *
     * @param token
     * @param PatID
     * @param UserID
     * @param parts
     * @return
     */
    @Multipart
    @Headers({"url_name:user"})
    @POST("/app/ydys/UploadPostFile")
    Observable<BeanModule<ArrayList<HomeBean>>> uploadPostFile(@Part("Token") RequestBody token,
                                                               @Part("PatID") RequestBody PatID,
                                                               @Part("UserID") RequestBody UserID,
                                                               @Part("UploadType") RequestBody UploadType,
                                                               @Part List<MultipartBody.Part> parts);
}
