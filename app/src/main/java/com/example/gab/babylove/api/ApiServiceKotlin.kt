package com.example.gab.babylove.api

import com.example.gab.babylove.entity.*
import com.ggz.baselibrary.retrofit.BeanModule
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.util.ArrayList

/**
 * Created by 初夏小溪
 * data :2019/1/4 0004 11:28
 */
interface ApiServiceKotlin {

    /**
     * 登录
     *
     * @return
     */
    @FormUrlEncoded
    @Headers("url_name:user")
    @POST("user/login")
    fun getLogin(@Path("username") username: String, @Path("password") password: String): Observable<BeanModule<LoginBean>>

    /**
     * 注册接口
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @Headers("url_name:user")
    @POST("user/register")
    fun getRegister(@FieldMap options: Map<String, Any>): Observable<BeanModule<LoginBean>>

    /**
     * 退出登录
     *
     * @return
     */
    @Headers("url_name:user")
    @GET("user/logout/json")
    fun getLogout(): Observable<BeanModule<Any>>

    /**
     * 首页 banner
     *
     * @return
     */
    @Headers("url_name:user")
    @GET("banner/json")
    fun getBanner(): Observable<BeanModule<List<BannerBean>>>

    /**
     * 首页文章列表
     *
     * @param page
     * @return
     */
    @Headers("url_name:user")
    @GET("article/list/{id}/json")
    fun getArticleHomeList(@Path("id") page: Int): Observable<BeanModule<BaseBean>>

    /**
     * 体系数据
     *
     * @return
     */
    @Headers("url_name:user")
    @GET("tree/json")
    fun getTreeList(): Observable<BeanModule<List<ViewBean>>>

    /**
     * 体系数据 详情
     *
     * @return
     */
    @Headers("url_name:user")
    @GET("article/list/{id}/json")
    fun getArticleList(@Path("id") page: Int, @Query("cid") cid: Int): Observable<BeanModule<BaseBean>>

    /**
     * 常用网站
     *
     * @return
     */
    @Headers("url_name:user")
    @GET("friend/json")
    fun getBookmarkList(): Observable<BeanModule<List<BookmarkBean>>>

    /**
     * 搜索热词
     *
     * @return
     */
    @Headers("url_name:user")
    @GET("hotkey/json")
    fun getHotKeyList(): Observable<BeanModule<List<HotKeyBean>>>

    /**
     * 视图导航
     *
     * @return
     */
    @Headers("url_name:user")
    @GET("navi/json")
    fun getNavigationList(): Observable<BeanModule<List<NavigationBean>>>

    /**
     * 项目
     *
     * @return
     */
    @Headers("url_name:user")
    @GET("project/tree/json")
    fun getProject(): Observable<BeanModule<List<ProjectBean>>>

    /**
     * 项目列表数据
     *
     * @param page
     * @param cid
     * @return
     */
    @Headers("url_name:user")
    @GET("project/list/{pageNum}/json")
    fun getProjectList(@Path("pageNum") page: Int, @Query("cid") cid: Int): Observable<BeanModule<BaseBean>>

    /**
     * 最新项目tab (首页的第二个tab)
     *
     * @param pageNum
     * @return
     */
    @Headers("url_name:user")
    @GET("article/listproject/{pageNum}/json")
    fun getListProject(@Path("pageNum") pageNum: Int): Observable<BeanModule<BaseBean>>

    /**
     * 收藏文章列表
     *
     * @param page
     * @return
     */
    @Headers("url_name:user")
    @GET("lg/collect/list/{id}/json")
    fun getCollectList(@Path("id") page: Int): Observable<BeanModule<CollectBean>>

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
    @Headers("url_name:user")
    @POST("lg/collect/{id}/json")
    fun getCollectArticle(@Path("id") articleId: Int, @Field("reason") reason: String): Observable<BeanModule<Any>>

    /**
     * 站内文章  取消收藏 (文章列表)
     *
     * @param articleId
     * @param reason
     * @return
     */
    @FormUrlEncoded
    @Headers("url_name:user")
    @POST("lg/uncollect_originId/{id}/json")
    fun unCollectArticle(@Path("id") articleId: Int,
                         @Field("reason") reason: String): Observable<BeanModule<Any>>

    /**
     * 站内文章 取消收藏 我的收藏页面（该页面包含自己录入的内容）
     *
     * @param articleId
     * @param originId
     * @return
     */
    @FormUrlEncoded
    @Headers("url_name:user")
    @POST("lg/uncollect/{id}/json")
    fun unMyCollectArticle(@Path("id") articleId: Int, @Field("originId") originId: Int): Observable<BeanModule<Any>>

    /**
     * 搜索
     *
     * @param pageNum
     * @param queryKey
     * @return
     */
    @FormUrlEncoded
    @Headers("url_name:user")
    @POST("article/query/{pageNum}/json")
    fun getQuery(@Path("pageNum") pageNum: Int, @Field("k") queryKey: String): Observable<BeanModule<BaseBean>>

    /**
     * 获取公众号列表
     *
     * @return
     */
    @Headers("url_name:user")
    @GET("wxarticle/chapters/json")
    fun getChapters(): Observable<BeanModule<List<OfficialAccountBean>>>

    /**
     * 查看某个公众号历史数据
     *
     * @param id
     * @param pageNo
     * @return
     */
    @Headers("url_name:user")
    @GET("wxarticle/list/{ID}/{pageNo}/json")
    fun getWxarticle(@Path("ID") id: Int, @Path("pageNo") pageNo: Int): Observable<BeanModule<BaseBean>>


    /**
     * 在某个公众号中搜索历史文章
     *
     * @param id
     * @param pageNo
     * @param queryKey
     * @return
     */
    @Headers("url_name:user")
    @GET("wxarticle/list/{ID}/{pageNo}/json")
    fun getWxarticleQuery(@Path("ID") id: Int, @Path("pageNo") pageNo: Int, @Query("k") queryKey: String): Observable<BeanModule<BaseBean>>

    /**
     * 美图图片
     *
     * @param count
     * @param page
     * @return
     */
    @Headers("url_name:user")
    @GET("http://gank.io/api/data/福利/{count}/{page}")
    fun getCourseDetails(@Path("count") count: Int, @Path("page") page: Int): Observable<GanBean>

    /**
     * 运动课程 ---运动列表
     *
     * @param page
     * @return
     */
    @Headers("url_name:user")
    @GET("http://api.fithub.cc/api/trainitem/trainitemlist")
    fun getCourseList(@Query("pageNo") page: Int): Observable<CourseList>


    /**
     * 运动课程 ---运动详情
     *
     * @param id
     * @return
     */
    @Headers("url_name:user")
    @GET("http://api.fithub.cc/api/v44/train/course")
    fun getCourseDetails(@Query("id") id: Int): Observable<CourseDetails>


    /**
     * 版本更新
     *
     * @param deviceid
     * @param android
     * @return
     */
    @Headers("url_name:user")
    @GET("http://118.31.218.69/lesprint/api/version/versionInfo")
    fun getVersionsUpdate(@Query("deviceId") deviceid: String, @Query("code") android: String): Observable<UpDateBean>


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
    @Headers("url_name:user")
    @POST("/app/ydys/UploadPostFile")
    fun uploadPostFile(@Part("Token") token: RequestBody,
                       @Part("PatID") patID: RequestBody,
                       @Part("UserID") userID: RequestBody,
                       @Part("UploadType") uploadType: RequestBody,
                       @Part parts: List<MultipartBody.Part>): Observable<BeanModule<ArrayList<Any>>>
}