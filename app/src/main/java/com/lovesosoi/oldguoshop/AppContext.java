package com.lovesosoi.oldguoshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.lookbi.baselib.utils.GetTimestamp;
import com.lookbi.baselib.utils.LogUtil;
import com.lookbi.baselib.utils.SPUtil;
import com.lovesosoi.oldguoshop.bean.Member;
import com.lovesosoi.oldguoshop.bean.Token;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.Date;

/**
 * Created by zhangyisheng on 2018/4/26.
 */

public class AppContext extends MultiDexApplication {
    private static AppContext instance;
    private Context mContext;//上下文
    private Token token;
    private String mTokenStr = "";
    private String timestamp;
    private Member member;
    private String BASEURL = "http://gj.ketao.com/";

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull
                    RefreshLayout layout) {
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull
                    RefreshLayout layout) {
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
    }

    public static synchronized AppContext getInstance() {
        if (null == instance) {
            instance = new AppContext();
        }
        return instance;
    }


    public boolean isLogin(Activity activity) {
        if (SPUtil.getString(mContext, "TOKEN", "") != null) {

            if (!TextUtils.isEmpty(getTokenStr())) {
                String timestamp = SPUtil.getString(mContext, "TIMESTAMP", "");
                long loseTimestamp = Long.valueOf(timestamp);
                long nowTimestamp = GetTimestamp.getSecondTimestampTwo(new Date());
                LogUtil.e("时间" + loseTimestamp + "..." + nowTimestamp);
                //token失效
                if (loseTimestamp - nowTimestamp < 0) {
                    return false;
                }
                //已登录
                else if (loseTimestamp - nowTimestamp > 3600) {
                    return true;
                }
                //刷新token
                else {
//                    RefreshToken.refreshToken(activity);
                    return true;
                }
            }
            //没登录
            else {
                return false;
            }

        } else {
            return false;
        }

    }


    /**
     * 重启当前应用
     */
    public void restart() {
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext
                .getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }


    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    /**
     * token
     *
     * @return
     */
    public String getTokenStr() {
        if (TextUtils.isEmpty(mTokenStr)) {
            mTokenStr = SPUtil.getString(mContext, "TOKEN", "");
        }
        return mTokenStr;
    }

    /**
     * 时间戳
     *
     * @return
     */
    public String getTimestamp() {
        if (TextUtils.isEmpty(timestamp)) {
            timestamp = SPUtil.getString(mContext, "TIMESTAMP", "");
        }
        return timestamp;
    }

    public void setToken(Token tokenbean) {
        this.token = tokenbean;
        if (tokenbean != null) {
            this.mTokenStr = tokenbean.getToken();
            SPUtil.setString(mContext, "TOKEN", token.getToken() + "");
            SPUtil.setString(mContext, "TIMESTAMP", token.getFailuretime() + "");
        } else {
            this.mTokenStr = null;
            SPUtil.setString(mContext, "TOKEN", null);
            SPUtil.setString(mContext, "TIMESTAMP", null);
        }
    }

    /**
     * 个人信息
     *
     * @return
     */
    public synchronized Member getMember() {
        if (member == null) {
            member = (Member) SPUtil.getObject(mContext, "MEMBER");
        }
        return member;
    }

    public synchronized void setMember(Member member) {
        this.member = member;
        SPUtil.setObject(mContext, "MEMBER", member);
    }

    public String getBASEURL() {
        return BASEURL;
    }

    public void setBASEURL(String BASEURL) {
        this.BASEURL = BASEURL;
    }

}
