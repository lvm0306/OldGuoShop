package com.lovesosoi.oldguoshop.bean;

import java.io.Serializable;

/**
 * Created by zhangyisheng on 2017/12/16.
 */

public class Member implements Serializable {
//    headimg	string	用户头像
//    realname	string	真实姓名
//    mobile	string	手机号（新）
//    user_type	string	实名认证类型 可选值:1(家属),2(机构)
//    apply_for_status	int	实名认证申请状态 可选值:0(申请中，请等待申请结果!),1(申请已通过!),2(申请驳回!),3(申请实名认证成功!),4(未申请实名认证),5(申请实名认证未成功!)
    private String headimg;
    private String mobile;
    private String realname;
    private String user_type;
    private int apply_for_status;

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public int getApply_for_status() {
        return apply_for_status;
    }

    public void setApply_for_status(int apply_for_status) {
        this.apply_for_status = apply_for_status;
    }
}
