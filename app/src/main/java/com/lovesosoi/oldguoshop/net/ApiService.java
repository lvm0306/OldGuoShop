package com.lovesosoi.oldguoshop.net;

import com.lookbi.baselib.bean.BaseBean;
import com.lookbi.baselib.bean.BaseBoolData;
import com.lookbi.baselib.bean.BaseIntBoolData;
import com.lovesosoi.oldguoshop.bean.NewApkInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/*
 * 描述:     TODO 接口类型 参数
 */
public interface ApiService {

    //新版本检测更新
    @GET(HttpUrl.APKINFOJSONURL)
    Observable<BaseBean<NewApkInfo>> getNewApkInfo();

//    //刷新token
//    @FormUrlEncoded
//    @POST(HttpUrl.TOKENREFRESH_URL)
//    Observable<BaseBean<Token>> refresh(@Field("token") String token,
//                                        @Field("platform") String platform);

}