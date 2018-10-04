package com.lookbi.baselib.bean;

import java.io.Serializable;


public class BaseIntBoolData implements Serializable {

    private int issuccess;
    private String msg;
    private int istrue;
    private int status;
    private int is_exist;
    private String cause;
    private int employeeid;
    private int isexist;

    public int getIsexist() {
        return isexist;
    }

    public void setIsexist(int isexist) {
        this.isexist = isexist;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public int getIs_exist() {
        return is_exist;
    }

    public void setIs_exist(int is_exist) {
        this.is_exist = is_exist;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIstrue() {
        return istrue;
    }

    public void setIstrue(int istrue) {
        this.istrue = istrue;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(int issuccess) {
        this.issuccess = issuccess;
    }

}
