package com.fy.baselibrary.retrofit;

import com.fy.baselibrary.entity.AddPatientCallBackBean;
import com.fy.baselibrary.entity.AnesthesiaVisitAfterBean;
import com.fy.baselibrary.entity.AnesthesiaVisitBeforeBean;
import com.fy.baselibrary.entity.CheckoutInfoBean;
import com.fy.baselibrary.entity.ChildFirstNursingBean;
import com.fy.baselibrary.entity.CyPatientBean;
import com.fy.baselibrary.entity.EmergencySalvageBean;
import com.fy.baselibrary.entity.GetDictsInBean;
import com.fy.baselibrary.entity.GetObstetricsBean;
import com.fy.baselibrary.entity.GetObstetricsRecodesBean;
import com.fy.baselibrary.entity.GetRespiratorAuxiliaryBean;
import com.fy.baselibrary.entity.ImgBean;
import com.fy.baselibrary.entity.InformedConsentBean;
import com.fy.baselibrary.entity.InspectionInfoBean;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.entity.NewbornGeneralBean;
import com.fy.baselibrary.entity.NewbornNurseBean;
import com.fy.baselibrary.entity.NurseMessageBean;
import com.fy.baselibrary.entity.NursingRecordBean;
import com.fy.baselibrary.entity.ObstetricalPostpartumRecordBean;
import com.fy.baselibrary.entity.PSEvaluationBean;
import com.fy.baselibrary.entity.PatientCallBackBean;
import com.fy.baselibrary.entity.ZyPatientBean;

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
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

/**
 * 通用的的api接口 </p>
 * Created by fangs on 2017/8/28.
 */
public interface ApiService {

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
     * 获取住院患者信息接口
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetZyPatient")
    Observable<BeanModule<ArrayList<ZyPatientBean>>> GetZyPatient(@QueryMap Map<String, Object> options);

    /**
     * 2.2.3.获取字典信息 10054	住院患者动态检索条件
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetDicts")
    Observable<BeanModule<ArrayList<GetDictsInBean>>> GetDicts10054(@QueryMap Map<String, Object> options);

    /**
     * 2.2.3.获取字典信息 10066	住院患者动态检索条件
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetDicts")
    Observable<BeanModule<ArrayList<GetDictsInBean>>> GetDicts10066(@QueryMap Map<String, Object> options);

    /**
     * 2.2.3.获取字典信息 10067	出院患者动态检索条件
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetDicts")
    Observable<BeanModule<ArrayList<GetDictsInBean>>> GetDicts10067(@QueryMap Map<String, Object> options);
    /**
     * 2.2.3.获取字典信息 10005	住院患者(家属) 拒绝翻身知情同意书
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetDicts")
    Observable<BeanModule<ArrayList<GetDictsInBean>>> GetDicts10005(@QueryMap Map<String, Object> options);

    /**
     * 2.2.2.获取出院患者信息接口
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetCyPatient")
    Observable<BeanModule<ArrayList<CyPatientBean>>> GetCyPatient(@QueryMap Map<String, Object> options);

    /**
     * 2.9.1.病患回访列表接口
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetReturnVisits")
    Observable<BeanModule<ArrayList<PatientCallBackBean>>> GetReturnVisits(@QueryMap Map<String, Object> options);

    /**
     * 2.9.2.新增病患回访记录接口
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydys/SetReturnVisit")
    Observable<BeanModule<ArrayList<AddPatientCallBackBean>>> SetReturnVisit(@Body Map<String, Object> options);


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
    Observable<BeanModule<ArrayList<ImgBean>>> uploadPostFile(@Part("Token") RequestBody token,
                                                              @Part("PatID") RequestBody PatID,
                                                              @Part("UserID") RequestBody UserID,
                                                              @Part("UploadType") RequestBody UploadType,
                                                              @Part List<MultipartBody.Part> parts);


    /**
     * 2.3.1.查询知情同意书
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetInformedConsent")
    Observable<BeanModule<ArrayList<InformedConsentBean>>> GetInformedConsent(@QueryMap Map<String, Object> options);


    /**
     * 2.3.2.1.患者首次护理记录 100540301(首次护理记录单)
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetFirstNursing")
    Observable<BeanModule<ArrayList<NurseMessageBean>>> GetFirstNursing(@QueryMap Map<String, Object> options);

    /**
     * 2.3.2.1.患者首次护理记录 100540302(儿童首次护理)
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetFirstNursing")
    Observable<BeanModule<ArrayList<ChildFirstNursingBean>>> GetChildFirstNursing(@QueryMap Map<String, Object> options);

    /**
     * 2.3.2.2.患者麻醉访视之前记录
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetAnesthesiaVisit")
    Observable<BeanModule<ArrayList<AnesthesiaVisitBeforeBean>>> GetAnesthesiaVisitBefore(@QueryMap Map<String, Object> options);

    /**
     * 2.3.2.2.患者麻醉访视之后记录
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetAnesthesiaVisit")
    Observable<BeanModule<ArrayList<AnesthesiaVisitAfterBean>>> GetAnesthesiaVisitAfter(@QueryMap Map<String, Object> options);

    /**
     * 2.3.2.3.患者压疮风险评估单
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetPSEvaluation")
    Observable<BeanModule<ArrayList<PSEvaluationBean>>> GetPSEvaluation(@QueryMap Map<String, Object> options);

    /**
     * 2.3.2.4.内外科护理记录列表 00540306(外科记录单)
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetNursingRecodes")
    Observable<BeanModule<ArrayList<NursingRecordBean>>> GetNursingRecodes(@QueryMap Map<String, Object> options);
    /**
     * 2.3.2.4.内外科护理记录列表  100540308(急救记录单)
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetNursingRecodes")
    Observable<BeanModule<ArrayList<EmergencySalvageBean>>> GetEmergencySalvage(@QueryMap Map<String, Object> options);

    /**
     * 2.3.2.5.产科护理记录列表 100540309(产前待产)
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetObstetricsRecodes")
    Observable<BeanModule<ArrayList<GetObstetricsBean>>> GetObstetricsRecodes1(@QueryMap Map<String, Object> options);

    /**
     * 2.3.2.5.产科护理记录列表  100540310(产前待产呼吸 呼吸)
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetObstetricsRecodes")
    Observable<BeanModule<ArrayList<GetObstetricsBean>>> GetObstetricsRecodes2(@QueryMap Map<String, Object> options);

    /**
     * 2.3.2.5.产科护理记录列表100540311(产科产后记录)
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetObstetricsRecodes")
    Observable<BeanModule<ArrayList<ObstetricalPostpartumRecordBean>>> GetObstetricalPostpartumRecord(@QueryMap Map<String, Object> options);

    /**
     * 2.3.2.5.产科护理记录列表   100540312(产后观察记录)
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetObstetricsRecodes")
    Observable<BeanModule<ArrayList<GetObstetricsRecodesBean>>> GetObstetricsRecodes(@QueryMap Map<String, Object> options);

    /**
     * 2.3.2.6.新生儿科护理记录列表 100540313(一般儿科护理)
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetNeonatalCareRecodes")
    Observable<BeanModule<ArrayList<NewbornGeneralBean>>> GetNeonatalCareRecodes(@QueryMap Map<String, Object> options);
    /**
     * 2.3.2.6.新生儿科护理记录列表100540314(新生儿科护理)
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetNeonatalCareRecodes")
    Observable<BeanModule<ArrayList<NewbornNurseBean>>> GetNeonatalCareRecodes2(@QueryMap Map<String, Object> options);

    /**
     * 2.3.2.7.新生儿科呼吸辅助护理记录列表
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetNCAssistanceRecodes")
    Observable<BeanModule<ArrayList<GetRespiratorAuxiliaryBean>>> GetNCAssistanceRecodes(@QueryMap Map<String, Object> options);

    /**
     * 2.5.1.检验报告接口
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetReport")
    Observable<BeanModule<ArrayList<InspectionInfoBean>>> GetReport(@QueryMap Map<String, Object> options);
    /**
     * 2.5.1.检查报告接口
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetReport")
    Observable<BeanModule<ArrayList<CheckoutInfoBean>>> GetCheckout(@QueryMap Map<String, Object> options);

}
