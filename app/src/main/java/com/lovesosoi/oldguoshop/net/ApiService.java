package com.lovesosoi.oldguoshop.net;

import com.lookbi.baselib.bean.BaseBean;
import com.lookbi.baselib.bean.BaseBoolData;
import com.lookbi.baselib.bean.BaseIntBoolData;
import com.lookbi.baselib.bean.BaseList;
import com.lookbi.baselib.bean.NewApkInfo;
import com.lookbi.womenprison.bean.AboutUs;
import com.lookbi.womenprison.bean.BaseIntBoolData2;
import com.lookbi.womenprison.bean.ContractUs;
import com.lookbi.womenprison.bean.DepartmenConsultList;
import com.lookbi.womenprison.bean.DepartmentDetail;
import com.lookbi.womenprison.bean.DepartmentList;
import com.lookbi.womenprison.bean.FamilyDataPeople;
import com.lookbi.womenprison.bean.FamilyOnline;
import com.lookbi.womenprison.bean.FamilyRelationList;
import com.lookbi.womenprison.bean.GetBanner;
import com.lookbi.womenprison.bean.LaywerConsultInfo;
import com.lookbi.womenprison.bean.LaywerConsultList;
import com.lookbi.womenprison.bean.MeetFamilyData;
import com.lookbi.womenprison.bean.MeetPeopleRecord;
import com.lookbi.womenprison.bean.MeetRecord;
import com.lookbi.womenprison.bean.Member;
import com.lookbi.womenprison.bean.NoticeList;
import com.lookbi.womenprison.bean.OrganRelationList;
import com.lookbi.womenprison.bean.OrginData;
import com.lookbi.womenprison.bean.PartnerInfo;
import com.lookbi.womenprison.bean.PersonAuth;
import com.lookbi.womenprison.bean.PrisonCaseInfo;
import com.lookbi.womenprison.bean.PrisonCaseList;
import com.lookbi.womenprison.bean.PrisonDetail;
import com.lookbi.womenprison.bean.PrisonMoney;
import com.lookbi.womenprison.bean.PrisonMoneyList;
import com.lookbi.womenprison.bean.PrisonReduce;
import com.lookbi.womenprison.bean.PrisonReward;
import com.lookbi.womenprison.bean.PrisonScore;
import com.lookbi.womenprison.bean.Token;
import com.lookbi.womenprison.bean.UploadImageList;
import com.lookbi.womenprison.bean.WorkInfo;
import com.lookbi.womenprison.bean.WorkList;

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

    //刷新token
    @FormUrlEncoded
    @POST(HttpUrl.TOKENREFRESH_URL)
    Observable<BaseBean<Token>> refresh(@Field("token") String token,
                                        @Field("platform") String platform);

    //获取Banner
    @GET(HttpUrl.BANNER)
    Observable<BaseBean<GetBanner>> getBanner(@Query("tbid") String tbid);

    //验证手机号
    @GET(HttpUrl.CHECKMOBILE)
    Observable<BaseBean<BaseBoolData>> checkMobile(@Query("mobile") String mobile);

    //验证手机号是否绑定微信
    @FormUrlEncoded
    @POST(HttpUrl.MOBILE_STATUS)
    Observable<BaseBean<BaseIntBoolData>> checkMobileBindWX(@FieldMap HashMap<String, String>
                                                                    hashMap);

    //发送验证码-未登录
//    @FormUrlEncoded
    @GET(HttpUrl.SENDMOBILECAPTCHA)
    Observable<BaseBean<BaseBoolData>> sendmobilecaptcha(@QueryMap HashMap<String, String> hashMap);

    //发送验证码-登录
    @FormUrlEncoded
    @POST(HttpUrl.SENDMEMBERCAPTCHA)
    Observable<BaseBean<BaseBoolData>> sendLogincaptcha(@FieldMap HashMap<String, String> hashMap);

    //未登录验证验证码
    @GET(HttpUrl.CHECKREGISTERCAPTCHA)
    Observable<BaseBean<BaseIntBoolData>> checkCaptcha(@QueryMap HashMap<String, String> hashMap);

    //已登录验证验证码
    @GET(HttpUrl.CHECKMMEBERCAPTCHA)
    Observable<BaseBean<BaseIntBoolData>> memberCaptcha(@QueryMap HashMap<String, String> hashMap);

    //注册
    @FormUrlEncoded
    @POST(HttpUrl.REGISTER)
    Observable<BaseBean<Token>> register(@FieldMap HashMap<String, String> hashMap);

    //获取Member信息
    @GET(HttpUrl.MEMBER_INFO)
    Observable<BaseBean<Member>> getMemberInfo(@Query("token") String token);

    //账户登录
    @FormUrlEncoded
    @POST(HttpUrl.LOGIN)
    Observable<BaseBean<Token>> login(@FieldMap HashMap<String, String> hashMap);

    //快速登录
    @FormUrlEncoded
    @POST(HttpUrl.QUICK_LOGIN)
    Observable<BaseBean<Token>> quickLogin(@FieldMap HashMap<String, String> hashMap);

    //忘记密码
    @FormUrlEncoded
    @POST(HttpUrl.FORGOTPWD)
    Observable<BaseBean<BaseIntBoolData>> forgotPwd(@FieldMap HashMap<String, String> hashMap);

    //修改用户资料
    @FormUrlEncoded
    @POST(HttpUrl.MEMBER_INFO_EDIT)
    Observable<BaseBean<BaseIntBoolData>> changeUserInfo(@FieldMap HashMap<String, String> hashMap);

    //上传图片
    @Multipart
    @POST(HttpUrl.IMGUPLOADS)
    Observable<BaseBean<UploadImageList>> imageUploads(@Part List<MultipartBody.Part> parts);


    //获取亲属关系
    @GET(HttpUrl.MEMBER_FAMILYRELATION)
    Observable<BaseBean<FamilyRelationList>> getFamilyRelation(@Query("token") String token);

    //获取机构关系
    @GET(HttpUrl.ORGANIZATIONRELATION)
    Observable<BaseBean<OrganRelationList>> getOrganRelation(@Query("token") String token);


    //家属身份认证
    @FormUrlEncoded
    @POST(HttpUrl.CERTIFICATION_FAMILY)
    Observable<BaseBean<PersonAuth>> familyAuth(@FieldMap HashMap<String, String> hashMap);

    //机构身份认证
    @FormUrlEncoded
    @POST(HttpUrl.CERTIFICATION_ORGAN)
    Observable<BaseBean<PersonAuth>> organAuth(@FieldMap HashMap<String, String> hashMap);

    //工作列表
    @GET(HttpUrl.WORK_LIST)
    Observable<BaseBean<WorkList>> getWorkList(@QueryMap HashMap<String, String> hashMap);

    //工作详情
    @GET(HttpUrl.WORK_INFO)
    Observable<BaseBean<WorkInfo>> getWorkInfo(@QueryMap HashMap<String, String> hashMap);

    //通知列表
    @GET(HttpUrl.NOTICE_LIST)
    Observable<BaseBean<NoticeList>> getNoticeList(@QueryMap HashMap<String, String> hashMap);

    //通知详情
    @GET(HttpUrl.NOTICE_INFO)
    Observable<BaseBean<WorkInfo>> getNoticeInfo(@QueryMap HashMap<String, String> hashMap);

    //关于我们
    @GET(HttpUrl.ABOUT_US)
    Observable<BaseBean<AboutUs>> getAboutUs();

    //联系我们
    @GET(HttpUrl.CONTRACT_US)
    Observable<BaseBean<ContractUs>> getContractUs();

    //社会监督
    @FormUrlEncoded
    @POST(HttpUrl.POST_SOCIAL_SUPERVISE)
    Observable<BaseBean<BaseIntBoolData>> postSocialSupervise(@FieldMap HashMap<String, String>
                                                                      hashMap);

    //获取认证详情
    @GET(HttpUrl.CERTIFICATION_GET)
    Observable<BaseBean<PersonAuth>> getAuthInfo(@QueryMap HashMap<String, String> hashMap);

    //修改实名认证信息
    @FormUrlEncoded
    @POST(HttpUrl.MEMBERCERTIFICATIONEDIT)
    Observable<BaseBean<BaseBoolData>> editAuth(@FieldMap HashMap<String, String> hashMap);


    //狱务公开
    @GET(HttpUrl.GET_PRISON_MANAGEMENT_DETAILS)
    Observable<BaseBean<PrisonDetail>> getPrisonDetails(@QueryMap HashMap<String, String> hashMap);

    //余额
    @GET(HttpUrl.GET_PRISON_DETAILS_MONEY)
    Observable<BaseBean<PrisonMoney>> getPrisonMoney(@QueryMap HashMap<String, String> hashMap);

    //余额明细
    @GET(HttpUrl.GET_PRISON_MONEY_LIST)
    Observable<BaseBean<PrisonMoneyList>> getPrisonMoneyInfo(@QueryMap HashMap<String, String>
                                                                     hashMap);

    //减刑假释
    @GET(HttpUrl.GET_PRISON_REDUCE_PENALTY)
    Observable<BaseBean<PrisonReduce>> getPrisonReducePenalty(@QueryMap HashMap<String, String>
                                                                      hashMap);

    //考核分
    @GET(HttpUrl.GET_PRISON_ASSESSMENT_SCORE)
    Observable<BaseBean<PrisonScore>> getPrisonAssessmentScore(@QueryMap HashMap<String, String>
                                                                       hashMap);

    //奖惩记录
    @GET(HttpUrl.GET_PRISON_REWARD_PUNISHMENT)
    Observable<BaseBean<PrisonReward>> getPrisonRewardPunishment(@QueryMap HashMap<String,
            String> hashMap);

    //病例列表
    @GET(HttpUrl.GET_PRISON_CASE_LIST)
    Observable<BaseBean<PrisonCaseList>> getPrisonCaseList(@QueryMap HashMap<String, String>
                                                                   hashMap);

    //病例列表
    @GET(HttpUrl.GET_PRISON_CASE_INFO)
    Observable<BaseBean<PrisonCaseInfo>> getPrisonCaseInfo(@QueryMap HashMap<String, String>
                                                                   hashMap);

    //律师咨询列表
    @GET(HttpUrl.GET_LAYWER_LIST)
    Observable<BaseBean<LaywerConsultList>> getLaywerList(@QueryMap HashMap<String, String> hashMap);

    //律师咨询详情
    @GET(HttpUrl.GET_LAYWER_INFO)
    Observable<BaseBean<LaywerConsultInfo>> getLaywerInfo(@QueryMap HashMap<String, String> hashMap);

    //修改实名认证信息
    @FormUrlEncoded
    @POST(HttpUrl.POST_LAYWER)
    Observable<BaseBean<BaseIntBoolData>> postLaywer(@FieldMap HashMap<String, String> hashMap);


    //根据原密码修改密码
    @FormUrlEncoded
    @POST(HttpUrl.CHANGE_MEMBER_PASSWORD)
    Observable<BaseBean<BaseBoolData>> changeMemberPwd(@FieldMap HashMap<String, String> hashMap);

    //科室列表
    @GET(HttpUrl.DEPARTMENT_LIST)
    Observable<BaseBean<DepartmentList>> getDepartmentList(@QueryMap HashMap<String, String> hashMap);

    //提交科室咨询
    @FormUrlEncoded
    @POST(HttpUrl.POST_CONSULT)
    Observable<BaseBean<BaseIntBoolData2>> postConsult(@FieldMap HashMap<String, String> hashMap);

    //科室质询列表
    @GET(HttpUrl.DEPARTMENT_CONSULT_LIST)
    Observable<BaseBean<DepartmenConsultList>> getDepartmenConsultList(@QueryMap HashMap<String, String> hashMap);

    //科室质询详情
    @GET(HttpUrl.DEPARTMENT_DETAIL)
    Observable<BaseBean<DepartmentDetail>> getDepartmentDetail(@QueryMap HashMap<String, String> hashMap);

    //亲情在线列表
    @GET(HttpUrl.THEFAMILYONLINE_LIST)
    Observable<BaseBean<BaseList<FamilyOnline>>> getFamilyOnline(@QueryMap HashMap<String,
            String> hashMap);

    //发布亲情在线
    @FormUrlEncoded
    @POST(HttpUrl.THEFAMILYONLINE_APPLYFOR)
    Observable<BaseBean<BaseBoolData>> releaseFamilyOnline(@FieldMap HashMap<String, String> hashMap);

    //服务协议
    @GET(HttpUrl.NETWORKSERVICESPROTOCOL)
    Observable<BaseBean<AboutUs>> getRegisterXY();

    //文件上传说明
    @GET(HttpUrl.CREDENTIALUPLOADINGEXPLAIN)
    Observable<BaseBean<AboutUs>> getUploadSM();

    //亲情在线文件上传说明
    @GET(HttpUrl.WEBSITETHEFAMILYONLINE)
    Observable<BaseBean<AboutUs>> getFamilyOnlineUploadSM();

    //获取同行人员列表
    @GET(HttpUrl.GET_PARTNER_LIST)
    Observable<BaseBean<MeetPeopleRecord>> getPartnetList(@Query("token") String token);

    //获取预约人数
    @GET(HttpUrl.GET_APPOINT_PEOPLE)
    Observable<BaseBean<FamilyDataPeople>> getAppointPeople(@QueryMap Map<String, String> map);

    //新增随行人员
    @FormUrlEncoded
    @POST(HttpUrl.POST_PARTNER)
    Observable<BaseBean<BaseBoolData>> postPartner(@FieldMap HashMap<String, String> hashMap);

    //编辑同行人员
    @FormUrlEncoded
    @POST(HttpUrl.POST_PARTNER_EDIT)
    Observable<BaseBean<BaseBoolData>> postPartnerEdit(@FieldMap HashMap<String, String> hashMap);

    //申请预约会见（机构）
    @FormUrlEncoded
    @POST(HttpUrl.POST_ORG_MEETING)
    Observable<BaseBean<BaseBoolData>> postOragn(@FieldMap HashMap<String, String> hashMap);

    //申请预约会见（家属）
    @FormUrlEncoded
    @POST(HttpUrl.POST_FAMILY_MEETING)
    Observable<BaseBean<BaseBoolData>> postFamilyMeet(@FieldMap HashMap<String, String> hashMap);

    //获取预约记录
    @GET(HttpUrl.GET_MEETING_RECORD)
    Observable<BaseBean<MeetRecord>> getMeetingRecord(@QueryMap Map<String, String> map);

    //获取同行人员信息
    @GET(HttpUrl.GET_PARTNER_INFO)
    Observable<BaseBean<PartnerInfo>> getPartnerInfo(@QueryMap Map<String, String> map);

    //获取家属日期
    @GET(HttpUrl.GET_FAMILY_PRISONDATA)
    Observable<BaseBean<MeetFamilyData>> getMeetFamilyData();

    //获取机构日期
    @GET(HttpUrl.GET_ORGIN_PRISONDATA)
    Observable<BaseBean<OrginData>> getMeetOrginData();
}