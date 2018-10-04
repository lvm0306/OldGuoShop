package com.lovesosoi.oldguoshop.net;


import com.lookbi.womenprison.AppContext;

/**
 * Created by zhangyisheng on 2017/11/17.
 */

public class HttpUrl {
    public final static String BASEURL = AppContext.getInstance().getBASEURL();
    //apk更新信息
    public final static String APKINFOJSONURL = "NewAppApkInfoJson.txt";
    //H5   "h5" 可能会变 "apph5"
    public final static String H5 = BASEURL;
    //刷新token
    public static final String TOKENREFRESH_URL = "api/member/token/refresh";
    //验证手机号
    public static final String CHECKMOBILE = "api/member/mobile/check";
    //未登录发送验证码
    public static final String SENDMOBILECAPTCHA = "api/captcha/unregister/send";
    //已登录发送验证码
    public static final String SENDMEMBERCAPTCHA = "api/captcha/register/send";
    //校验是否绑定微信
    public static final String MOBILE_STATUS = "api/member/mobile_status";
    //未登录验证注册验证码
    public static final String CHECKREGISTERCAPTCHA = "api/captcha/unregister/check";
    //已登录验证注册验证码
    public static final String CHECKMMEBERCAPTCHA = "api/captcha/register/check";
    //注册
    public static final String REGISTER = "api/member/register/mobile";
    //账户登录
    public static final String LOGIN = "api/member/login/password";
    //快速登录
    public static final String QUICK_LOGIN = "api/member/mobile_login";
    //用户信息
    public static final String MEMBER_INFO = "api/member/memberinfo/get";
    //修改用户信息
    public static final String MEMBER_INFO_EDIT = "api/member/info/edit";
    //忘记密码-通过手机
    public static final String FORGOTPWD = "api/member/password/reset/mobile";
    //上传图片
    public static final String IMGUPLOADS = "api/member/upload/img";
    //首页banner
    public static final String BANNER = "api/banner/list";
    //获取亲属关系
    public static final String MEMBER_FAMILYRELATION = "api/member/familyRelation";
    //家属身份认证
    public static final String CERTIFICATION_FAMILY = "api/member/certification/apply/fam";
    //机构身份认证
    public static final String CERTIFICATION_ORGAN = "api/member/certification/apply/org";
    //获取机构关系
    public static final String ORGANIZATIONRELATION = "api/member/OrganizationRelation";
    //修改实名认证信息
    public static final String MEMBERCERTIFICATIONEDIT = "api/member/certification/edit";
    //工作列表
    public static final String WORK_LIST = "api/workTrend/list";
    //工作详情
    public static final String WORK_INFO = "api/workTrend/info";
    //工作列表
    public static final String NOTICE_LIST = "api/announcement/list";
    //工作详情
    public static final String NOTICE_INFO = "api/announcement/info";
    //提交社会监督
    public static final String POST_SOCIAL_SUPERVISE = "api/socialSupervision/add";
    //关于我们
    public static final String ABOUT_US = "api/webSite/about";
    //联系我们
    public static final String CONTRACT_US = "api/webSite/contactUs";
    //获取认证详情
    public static final String CERTIFICATION_GET = "api/member/certification/get";
    //狱务公开
    public static final String GET_PRISON_MANAGEMENT_DETAILS = "api/prisonManagementDetails/information";
    //余额
    public static final String GET_PRISON_DETAILS_MONEY = "api/prisonManagementDetails/money";
    //余额明细
    public static final String GET_PRISON_MONEY_LIST = "api/prisonManagementDetails/moneyList";
    //减刑假释
    public static final String GET_PRISON_REDUCE_PENALTY = "api/prisonManagementDetails/reduceAPenalty";
    //考核分
    public static final String GET_PRISON_ASSESSMENT_SCORE = "api/prisonManagementDetails/assessmentScore";
    //奖惩记录
    public static final String GET_PRISON_REWARD_PUNISHMENT = "api/prisonManagementDetails/rewardsAndPunishment";
    //病例列表
    public static final String GET_PRISON_CASE_LIST = "api/prisonManagementDetails/threeLevelEarlyWarning";
    //病例列表
    public static final String GET_PRISON_CASE_INFO = "api/prisonManagementDetails/threeLevelEarlyWarningImg";
    //律师咨询列表
    public static final String GET_LAYWER_LIST = "api/attorneyConsultation/list";
    //律师咨询详情
    public static final String GET_LAYWER_INFO = "api/attorneyConsultation/info";
    //律师咨询提交
    public static final String POST_LAYWER = "api/attorneyConsultation/add";
    //修改密码
    public static final String CHANGE_MEMBER_PASSWORD = "api/member/password/change";
    //亲情在线列表
    public static final String THEFAMILYONLINE_LIST = "api/theFamilyOnline/list";
    //科室列表
    public static final String DEPARTMENT_LIST = "api/departmentConsultation/department";
    //提交科室咨询
    public static final String POST_CONSULT = "api/departmentConsultation/add";
    //科室质询列表
    public static final String DEPARTMENT_CONSULT_LIST = "api/departmentConsultation/list";
    //科室质询详情
    public static final String DEPARTMENT_DETAIL = "api/departmentConsultation/info";
    //发布亲情在线
    public static final String THEFAMILYONLINE_APPLYFOR = "api/theFamilyOnline/applyFor";
    //服务协议
    public static final String NETWORKSERVICESPROTOCOL = "api/webSite/networkServicesProtocol";
    //文件上传说明
    public static final String CREDENTIALUPLOADINGEXPLAIN = "api/webSite/credentialUploadingExplain";
    //亲情在线文件上传说明
    public static final String WEBSITETHEFAMILYONLINE = "api/webSite/theFamilyOnline";
    //获取同行人员列表
    public static final String GET_PARTNER_LIST = "api/meetWithAssistant/getPartnerList";
    //新增随行人员
    public static final String POST_PARTNER = "api/meetWithAssistant/addPartner";
    //编辑同行人员
    public static final String POST_PARTNER_EDIT = "api/meetWithAssistant/editPartnerList";
    //获取预约人数
    public static final String GET_APPOINT_PEOPLE = "api/meetWithAssistant/getApplyForNum";
    //申请预约会见（机构）
    public static final String POST_ORG_MEETING = "api/meetWithAssistant/applyForOrg";
    //申请预约会见（家属）
    public static final String POST_FAMILY_MEETING = "api/meetWithAssistant/applyForFam";
    //获取会见记录
    public static final String GET_MEETING_RECORD = "api/meetWithAssistant/getMeetWithList";
    //获取同行人员信息
    public static final String GET_PARTNER_INFO = "api/meetWithAssistant/getPartnerInfo";
    //获取家属日期
    public static final String GET_FAMILY_PRISONDATA = "api/meetWithAssistant/meetDayFam";
    //获取机构日期
    public static final String GET_ORGIN_PRISONDATA = "api/meetWithAssistant/meetDayOrg";

}

