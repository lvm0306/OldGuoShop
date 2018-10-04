package com.lovesosoi.oldguoshop.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.lookbi.baselib.bean.NewApkInfo;
import com.lookbi.baselib.net.RxSchedulers;
import com.lookbi.baselib.utils.DialogUtils;
import com.lookbi.baselib.utils.LogUtil;
import com.lookbi.womenprison.net.Api;
import com.lookbi.womenprison.net.BaseObserver;

import java.io.File;

public class ApkUpdate {
    private static Activity context;

    private static String apkurl;
    public static boolean isDownLoad = false;
    private static OnUpdateLisenter onUpdateLisenter;
    private boolean isShowPoup = true;

    public String getApkurl() {
        return apkurl;
    }

    public ApkUpdate(Activity mContext) {
        context = mContext;
    }

    public interface OnUpdateLisenter {
        void update(String newVersion);
    }


    public void setOnUpdateLisenter(OnUpdateLisenter lisenter) {
        onUpdateLisenter = lisenter;
    }

    public void getNewApkInfo(boolean isShowToast, boolean isShowPoupu) {
        isShowPoup = isShowPoupu;
        getNewApkInfo(isShowToast);
    }

    public void getNewApkInfo(final boolean isShowToast) {// 检测更新
        if (isShowToast) {
            ToastUtil.show(context, "版本检测中....");
        }
        BaseObserver mObserver = new BaseObserver<NewApkInfo>(context,null) {

            @Override
            protected void onSuccess(NewApkInfo info) {
                if (null != info) {
                    int newver = Integer.parseInt(info.getApkVersion().trim().replaceAll(
                            "\\.", ""));
                    int ver = Integer.parseInt(UtilTools.getVersion(context)
                            .trim().replaceAll("\\.", ""));
                    LogUtil.e("apk", "newver=" + newver + ",ver=" + ver);
                    if (newver > ver) {
                        LogUtil.e("下载apk");
                        if (isShowPoup) {
                            showDialog(info.getApkVersion(), info.getNewApkUrl(), info
                                    .getUpdateMessage());
                        }
                        if (onUpdateLisenter != null) {
                            onUpdateLisenter.update(info.getApkVersion());
                        }
                        return;
                    }
//                    if (newver == ver) {
//                        if (info.getPatchVersion() != null) {
//                            if (!TextUtils.isEmpty(info.getPatchVersion())) {
//                                if (TextUtils.isEmpty(SPUtil.getString(context,
//                                        "PatchVersion", ""))) {
//                                    LogUtil.e("下载补丁1");
//                                    //下载补丁文件
//                                    downLoadPatch(info.getPatchUrl(), info.getPatchVersion());
//                                    return;
//                                }
//                                int newPatchVersion = Integer.parseInt(info.getPatchVersion()
//                                        .trim().replaceAll(
//                                                "\\.", ""));
//                                int oldVersion = Integer.parseInt(SPUtil.getString(context,
//                                        "PatchVersion", "2.0.0.0").trim().replaceAll(
//                                        "\\.", ""));
//                                if (newPatchVersion > oldVersion) {
//                                    LogUtil.e("下载补丁2");
//                                    //下载补丁文件
//                                    downLoadPatch(info.getPatchUrl(), info.getPatchVersion());
//                                } else {
//                                    LogUtil.e("版本已最新1");
//                                    if (isShowToast) {
//                                        ToastUtil.show(context, "版本已最新");
//                                    }
//                                }
//                            } else {
//                                LogUtil.e("版本已最新2");
//
//                                if (isShowToast) {
//                                    ToastUtil.show(context, "版本已最新");
//                                }
//                            }
//                        } else {
//                            LogUtil.e("版本已最新3");
//
//                            if (isShowToast) {
//                                ToastUtil.show(context, "版本已最新");
//                            }
//                        }
//                        return;
//                    }
                    if (isShowToast) {
                        ToastUtil.show(context, "版本已最新");
                    }
                    LogUtil.e("版本已最新4");
                } else {
                    LogUtil.e("检测版本更新失败,为空");
                    if (isShowToast) {
                        ToastUtil.show(context, "版本已最新");
                    }
                    LogUtil.e("版本已最新5");
                }
            }

            @Override
            protected void onError(String e) {
                LogUtil.e("e"+e);
                if (isShowToast) {
                    ToastUtil.show(context, "版本已最新");
                }
                LogUtil.e("版本已最新6");

            }

            @Override
            protected void onEnd() {

            }
        };
        mObserver.setShowLoading(false);
        Api.getService().getNewApkInfo()
                .compose(RxSchedulers.compose(context))
                .subscribe(mObserver);
    }

    private void showDialog(final String newVersion, String url, String msg) {
        apkurl = url;
        if (isDownLoad) {
            ToastUtil.show(context, "正在下载最新版本");
            return;
        }
        DialogUtils.showUpdateApkDialog(context, newVersion, msg, new
                DialogUtils.OnUpdateApkListener() {


                    @Override
                    public void onCancle() {

                    }

                    @Override
                    public void onDownload() {
                        checkSDCardPermission();
                    }
                }).show();
    }

    /**
     * 检查用户设备是否拥有读写SDCard该权限
     */
    private void checkSDCardPermission() {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0x110);
        } else {
            downLoadApk();
        }
    }

    public static void downLoadApk() {
        String midrname = "/sdcard" + UpdateCommon.SAVE_APP_LOCATION + File.separator;
        UtilTools.isFoldersExists(midrname);

        File file = new File(midrname);
        File[] files = file.listFiles();
        for (int j = 0; j < files.length; j++) {
            UtilTools.deleteFile(files[j].getAbsolutePath());
        }
        UpdateAppManager.downloadApk(context, apkurl, "版本更新", "智慧管家");
        isDownLoad = true;
    }

//    private void downLoadPatch(String patchUrl, final String patchVersion) {
//        if (patchUrl == null) {
//            LogUtil.e("补丁为空");
//            return;
//        }
//        if (TextUtils.isEmpty(patchUrl)) {
//            LogUtil.e("补丁路径为空");
//            return;
//        }
//        Observer mObserver = new DownLoadObserver(context) {
//
//            @Override
//            protected void onSuccess(ResponseBody info) {
//                LogUtil.e("onSuccess");
//                if (info != null) {
//                    if (writeResponseBodyToDisk(info, patchVersion)) {
//
//                    } else {
//                        LogUtil.e("下载补丁失败");
//                    }
//                } else {
//                    LogUtil.e("info == null");
//                }
//
//            }
//
//            @Override
//            protected void onError(String e) {
//                LogUtil.e("e=" + e);
//
//            }
//
//            @Override
//            protected void onEnd() {
//                LogUtil.e("onEnd");
//            }
//        };
//        Api.getService().downloadFile(patchUrl)
//                .compose(RxSchedulers.compose(context))
//                .subscribe(mObserver);
//    }
//
//    private static boolean writeResponseBodyToDisk(ResponseBody body, String patchVersion) {
//
//        String midrname = "/sdcard" + UpdateCommon.SAVE_APPPATCH_LOCATION + File.separator;
//        UtilTools.isFoldersExists(midrname);
//        File file = new File(midrname);
//        File[] files = file.listFiles();
//        for (int j = 0; j < files.length; j++) {
//            UtilTools.deleteFile(files[j].getAbsolutePath());
//        }
//        try {
//            File futureStudioIconFile = new File(UpdateCommon.APPPATCH_FILE_NAME);
//
//            InputStream inputStream = null;
//            OutputStream outputStream = null;
//
//            try {
//                byte[] fileReader = new byte[1024];
//
//                long fileSize = body.contentLength();
//                long fileSizeDownloaded = 0;
//
//                inputStream = body.byteStream();
//                outputStream = new FileOutputStream(futureStudioIconFile);
//
//                while (true) {
//                    int read = inputStream.read(fileReader);
//
//                    if (read == -1) {
//                        break;
//                    }
//
//                    outputStream.write(fileReader, 0, read);
//
//                    fileSizeDownloaded += read;
//
//                    LogUtil.e("writeResponseBodyToDisk--", "file download: " + fileSizeDownloaded
//                            + "" +
//                            " of " + fileSize);
//                }
//                outputStream.flush();
//                TinkerInstaller.onReceiveUpgradePatch(context, UpdateCommon.APPPATCH_FILE_NAME);
//                SPUtil.setString(context, "PatchVersion", patchVersion);
//                return true;
//            } catch (IOException e) {
//                return false;
//            } finally {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//
//                if (outputStream != null) {
//                    outputStream.close();
//                }
//            }
//        } catch (IOException e) {
//            return false;
//        }
//    }

}
