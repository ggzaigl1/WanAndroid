package com.example.gab.babylove.api;

import com.example.gab.babylove.entity.ArticleBean;
import com.example.gab.babylove.entity.BannerBean;
import com.example.gab.babylove.entity.BookmarkBean;
import com.example.gab.babylove.entity.GankBean;
import com.example.gab.babylove.entity.HomeBean;
import com.example.gab.babylove.entity.LoginBean;
import com.example.gab.babylove.entity.NavigationBean;
import com.example.gab.babylove.entity.TreeBean;
import com.fy.baselibrary.retrofit.BeanModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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
