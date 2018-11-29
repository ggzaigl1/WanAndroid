package com.example.gab.babylove.api;

import com.example.gab.babylove.entity.BaseBean;
import com.example.gab.babylove.entity.BannerBean;
import com.example.gab.babylove.entity.BookmarkBean;
import com.example.gab.babylove.entity.CollectBean;
import com.example.gab.babylove.entity.CourseDetails;
import com.example.gab.babylove.entity.CourseList;
import com.example.gab.babylove.entity.GanBean;
import com.example.gab.babylove.entity.HotKeyBean;
import com.example.gab.babylove.entity.LoginBean;
import com.example.gab.babylove.entity.NavigationBean;
import com.example.gab.babylove.entity.OfficialAccountBean;
import com.example.gab.babylove.entity.ProjectBean;
import com.example.gab.babylove.entity.ViewBean;
import com.example.gab.babylove.entity.UpDateBean;
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
 *
 * @author 初夏小溪
 * @date 2017/8/28
 */
public interface ApiService {


    /**
     * 登录
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("user/login")
    Observable<BeanModule<LoginBean>> getLogin(@FieldMap Map<String, Object> options);

    /**
     * 注册接口
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("user/register")
    Observable<BeanModule<LoginBean>> getRegister(@FieldMap Map<String, Object> options);

    /**
     * 退出登录
     *
     * @return
     */
    @Headers({"url_name:user"})
    @GET("user/logout/json")
    Observable<BeanModule<Object>> getLogout();

    /**
     * 首页 banner
     *
     * @return
     */
    @Headers({"url_name:user"})
    @GET("banner/json")
    Observable<BeanModule<List<BannerBean>>> getBanner();

    /**
     * 首页文章列表
     *
     * @param page
     * @return
     */
    @Headers({"url_name:user"})
    @GET("article/list/{id}/json")
    Observable<BeanModule<BaseBean>> getArticleHomeList(@Path("id") int page);

    /**
     * 体系数据
     *
     * @return
     */
    @Headers({"url_name:user"})
    @GET("tree/json")
    Observable<BeanModule<List<ViewBean>>> getTreeList();

    /**
     * 体系数据 详情
     *
     * @return
     */
    @Headers({"url_name:user"})
    @GET("article/list/{id}/json")
    Observable<BeanModule<BaseBean>> getArticleList(@Path("id") int page, @Query("cid") int cid);

    /**
     * 常用网站
     *
     * @return
     */
    @Headers({"url_name:user"})
    @GET("friend/json")
    Observable<BeanModule<List<BookmarkBean>>> getBookmarkList();

    /**
     * 搜索热词
     *
     * @return
     */
    @Headers({"url_name:user"})
    @GET("hotkey/json")
    Observable<BeanModule<List<HotKeyBean>>> getHotKeyList();

    /**
     * 视图导航
     *
     * @return
     */
    @Headers({"url_name:user"})
    @GET("navi/json")
    Observable<BeanModule<List<NavigationBean>>> getNavigationList();

    /**
     * 项目
     *
     * @return
     */
    @Headers({"url_name:user"})
    @GET("project/tree/json")
    Observable<BeanModule<List<ProjectBean>>> getProject();

    /**
     * 项目列表数据
     *
     * @param page
     * @param cid
     * @return
     */
    @Headers({"url_name:user"})
    @GET("project/list/{pageNum}/json")
    Observable<BeanModule<BaseBean>> getProjectList(@Path("pageNum") int page, @Query("cid") int cid);

    /**
     * 最新项目tab (首页的第二个tab)
     *
     * @param pageNum
     * @return
     */
    @Headers({"url_name:user"})
    @GET("article/listproject/{pageNum}/json")
    Observable<BeanModule<BaseBean>> getListProject(@Path("pageNum") int pageNum);

    /**
     * 收藏文章列表
     *
     * @param page
     * @return
     */
    @Headers({"url_name:user"})
    @GET("lg/collect/list/{id}/json")
    Observable<BeanModule<CollectBean>> getCollectList(@Path("id") int page);

    /**
     * 更新
     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("lg/collect/{id}/json")
//    Observable<BeanModule<UpdateAppInfoBean>> getupdata(@Path("id") int articleId, @Field("reason") String reason);

    /**
     * 收藏站内文章
     *
     * @param articleId
     * @param reason
     * @return
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("lg/collect/{id}/json")
    Observable<BeanModule<Object>> getCollectArticle(@Path("id") int articleId, @Field("reason") String reason);

    /**
     * 站内文章  取消收藏 (文章列表)
     *
     * @param articleId
     * @param reason
     * @return
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BeanModule<Object>> unCollectArticle(@Path("id") int articleId,
                                                    @Field("reason") String reason);

    /**
     * 站内文章 取消收藏 我的收藏页面（该页面包含自己录入的内容）
     *
     * @param articleId
     * @param originId
     * @return
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("lg/uncollect/{id}/json")
    Observable<BeanModule<Object>> unMyCollectArticle(@Path("id") int articleId, @Field("originId") int originId);

    /**
     * 搜索
     *
     * @param pageNum
     * @param queryKey
     * @return
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("article/query/{pageNum}/json")
    Observable<BeanModule<BaseBean>> getQuery(@Path("pageNum") int pageNum, @Field("k") String queryKey);

    /**
     * 获取公众号列表
     *
     * @return
     */
    @Headers({"url_name:user"})
    @GET("wxarticle/chapters/json")
    Observable<BeanModule<List<OfficialAccountBean>>> getChapters();

    /**
     * 查看某个公众号历史数据
     *
     * @param id
     * @param pageNo
     * @return
     */
    @Headers({"url_name:user"})
    @GET("wxarticle/list/{ID}/{pageNo}/json")
    Observable<BeanModule<BaseBean>> getWxarticle(@Path("ID") int id, @Path("pageNo") int pageNo);


    /**
     * 在某个公众号中搜索历史文章
     *
     * @param id
     * @param pageNo
     * @param queryKey
     * @return
     */
    @Headers({"url_name:user"})
    @GET("wxarticle/list/{ID}/{pageNo}/json")
    Observable<BeanModule<BaseBean>> getWxarticleQuery(@Path("ID") int id, @Path("pageNo") int pageNo
            , @Query("k") String queryKey);

    /**
     * 美图图片
     *
     * @param count
     * @param page
     * @return
     */
    @Headers({"url_name:user"})
    @GET("http://gank.io/api/data/福利/{count}/{page}")
    Observable<GanBean> getCourseDetails(@Path("count") int count, @Path("page") int page);

    /**
     * 运动课程 ---运动列表
     *
     * @param page
     * @return
     */
    @Headers({"url_name:user"})
    @GET("http://api.fithub.cc/api/trainitem/trainitemlist")
    Observable<CourseList> getCourseList(@Query("pageNo") int page);


    /**
     * 运动课程 ---运动详情
     *
     * @param id
     * @return
     */
    @Headers({"url_name:user"})
    @GET("http://api.fithub.cc/api/v44/train/course")
    Observable<CourseDetails> getCourseDetails(@Query("id") int id);


    /**
     * 版本更新
     * @param deviceid
     * @param android
     * @return
     */
    @Headers({"url_name:user"})
    @GET("http://118.31.218.69/lesprint/api/version/versionInfo")
    Observable<UpDateBean> getVersionsUpdate(@Query("deviceId") String deviceid, @Query("code") String android);


    /**
     * 多图片上传
     *
     * @param token
     * @param patID
     * @param userID
     * @param uploadType
     * @param parts
     * @return
     */
    @Multipart
    @Headers({"url_name:user"})
    @POST("/app/ydys/UploadPostFile")
    Observable<BeanModule<ArrayList<Object>>> uploadPostFile(@Part("Token") RequestBody token,
                                                             @Part("PatID") RequestBody patID,
                                                             @Part("UserID") RequestBody userID,
                                                             @Part("UploadType") RequestBody uploadType,
                                                             @Part List<MultipartBody.Part> parts);
}
