package com.lovesosoi.oldguoshop.bean;

import java.io.Serializable;

/**
 * Created by zhangyisheng on 2018/4/7.
 */

public class NewApkInfo implements Serializable {
//    {
//        "data":
//        {
//            "newApkUrl":"",
//                "patchUrl":"",
//                "apkVersion":"1.0.0",
//                "patchVersion":"1.0.0",
//                "updateMessage":"1、优化部分内容\n 2、修复部分BUG"
//        },
//        "error":"",
//            "status":1
//    }

    private String newApkUrl;
    private String patchUrl;
    private String apkVersion;
    private String patchVersion;
    private String updateMessage;

    public String getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(String patchVersion) {
        this.patchVersion = patchVersion;
    }

    public String getNewApkUrl() {
        return newApkUrl;
    }

    public void setNewApkUrl(String newApkUrl) {
        this.newApkUrl = newApkUrl;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }

    public String getApkVersion() {
        return apkVersion;
    }

    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }


    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }
}
