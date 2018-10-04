package com.lookbi.baselib.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zzz on 2017/10/23.
 * isDebug上线要改为flase,防止抓到日志
 */

public class LogUtil {
    private static boolean isDebug = true;
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e("TAG--LogUtil", msg);
        }
    }

    public static void e(String tag, int msg) {
        if (isDebug) {
            Log.e(tag, msg + "");
        }
    }

    public static void e(int msg) {
        if (isDebug) {
            Log.e("TAG--LogUtil", msg + "");
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d("TAG--LogUtil", msg);
        }
    }

    public static void e(Object object, String msg) {
        if (isDebug) {
            Log.e(object.getClass().getSimpleName(), msg);
        }
    }

    public static void printLine(String tag, boolean isTop) {
        if (isDebug) {
            if (isTop) {
                e(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
            } else {
                e(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
            }
        }

    }

    public static void printJson(String tag, String msg, String headString) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);//返回格式化的json字符串，数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }
        printLine(tag, true);
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            {
                e(tag, "║ " + line);
            }
        }
        printLine(tag, false);
    }
}
