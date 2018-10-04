package com.lookbi.baselib.bean;

import java.io.Serializable;

public class BaseBoolData implements Serializable {

    private boolean issuccess;
    private boolean isexist;
    private boolean istrue;

    public boolean isIstrue() {
        return istrue;
    }

    public void setIstrue(boolean istrue) {
        this.istrue = istrue;
    }

    public boolean isIsexist() {
        return isexist;
    }
    public void setIsexist(boolean isexist) {
        this.isexist = isexist;
    }

    public boolean isIssuccess() {
        return issuccess;
    }

    public void setIssuccess(boolean issuccess) {
        this.issuccess = issuccess;
    }
}
