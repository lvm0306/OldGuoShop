package com.lovesosoi.oldguoshop.utils;

import java.io.File;

/**
 * Created by maimingliang on 2016/10/8.
 */

public class UpdateCommon {


    public final static String SAVE_APP_NAME = "womenprison_app.apk";
    public final static String SAVE_APPPATCH_NAME = "womenprison_app_patch.apk";

    public final static String SAVE_APP_LOCATION = "/download/womenprison";
    public final static String SAVE_APPPATCH_LOCATION = "/download/womenprison";

    public final static String APP_FILE_NAME = "/sdcard"+SAVE_APP_LOCATION+ File.separator + SAVE_APP_NAME;
    public final static String APPPATCH_FILE_NAME = "/sdcard"+SAVE_APPPATCH_LOCATION+ File.separator + SAVE_APPPATCH_NAME;
    public static final String DOWNLOAD_APK_ID_PREFS = "download_apk_id_prefs";
}
