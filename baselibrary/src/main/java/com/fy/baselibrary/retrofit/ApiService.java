package com.fy.baselibrary.retrofit;

import com.fy.baselibrary.entity.GankBean;
import com.fy.baselibrary.entity.HomeBean;
import com.fy.baselibrary.entity.LoginBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

/**
 * 通用的的api接口 </p>
 * Created by fangs on 2017/8/28.
 */
public interface ApiService {

    int DEFAULT_MILLISECONDS = 60000;             //默认的超时时间

    String BASE_URL = "http://47.104.176.229:80/";    //阿里云正式 地址

    String IMG_BASE_URL = BASE_URL + "image";

    String IMG_BASE_URL_THUM = BASE_URL + "image/thum";

    /**
     * 医护人员登入接口
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydys/DocAndNurse")
    Observable<BeanModule<ArrayList<LoginBean>>> DocAndNurse(@Body Map<String, Object> options);

    /**
     * 医护人员登出接口
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydys/LogOut")
    Observable<BeanModule<ArrayList<LoginBean>>> LogOut(@Body Map<String, Object> options);

    /**
     * 头条
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/toutiao/index")
    Observable<BeanModule<ArrayList<HomeBean>>> GetHeadline(@QueryMap Map<String, Object> options);


    /**
     * 运动课程 ---运动详情
     */
    @Headers({"url_name:user"})
    @GET("http://gank.io/api/data/福利/{count}/{page}")
    Observable<GankBean> getCourseDetails(@Path("count") int count, @Path("page") int page);


    /**
     * 多图片上传
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
