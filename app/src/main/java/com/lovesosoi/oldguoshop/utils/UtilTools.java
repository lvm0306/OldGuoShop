package com.lovesosoi.oldguoshop.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.lookbi.baselib.utils.LogUtil;
import com.lookbi.baselib.utils.TimeUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UtilTools {
    private static String TAG = "TAG--UtilTools";

    private static Pattern mPattern;
    private static Matcher mMatcher;


    /**
     * 获取SD卡根目录路径
     *
     * @return
     */
    public static String getSdCardPath() {
        boolean exist = isExitsSdcard();
        String sdpath = "";
        if (exist) {
            sdpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            sdpath = "暂无SD卡";
        }
        LogUtil.e("sdpath=" + sdpath);
        return sdpath;

    }

    /**
     * 获取文件后缀名 .jpg
     *
     * @param filename
     * @return
     */
    public static String getFileExtension(String filename) {
        if (TextUtils.isEmpty(filename)) {
            return filename;
        }
        return filename.substring(filename.lastIndexOf("."));

    }

    /**
     * 获取文件后缀名 jpg 没有.
     *
     * @param filename
     * @return
     */
    public static String getFileExtensionName(String filename) {
        if (TextUtils.isEmpty(filename)) {
            return filename;
        }
        return filename.substring(filename.lastIndexOf(".") + 1);

    }

    /**
     * 获取文件后缀名
     *
     * @param url
     * @return
     */
    public static String getFileExtensionFromUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            int fragment = url.lastIndexOf('#');
            if (fragment > 0) {
                url = url.substring(0, fragment);
            }

            int query = url.lastIndexOf('?');
            if (query > 0) {
                url = url.substring(0, query);
            }

            int filenamePos = url.lastIndexOf('/');
            String filename =
                    0 <= filenamePos ? url.substring(filenamePos + 1) : url;

            // if the filename contains special characters, we don't
            // consider it valid for our matching purposes:
            if (!filename.isEmpty() &&
                    Pattern.matches("[a-zA-Z_0-9\\.\\-\\(\\)\\%]+", filename)) {
                int dotPos = filename.lastIndexOf('.');
                if (0 <= dotPos) {
                    return filename.substring(dotPos + 1);
                }
            }
        }

        return "";
    }


    /**
     * 判断sd卡下多级文件夹是否存在
     *
     * @param strFolder
     * @return
     */
    public static boolean isFoldersExists(String strFolder) {
        File file = new File(strFolder);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return true;
            } else {
                return false;

            }
        }
        return true;

    }


    /**
     * 删除文件
     *
     * @param path
     */
    public static boolean deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.delete();
        } else {
            return true;
        }

    }

    /**
     * Sd卡是否存在
     */
    public static Boolean isHaveSDCard(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            ToastUtil.show(context, "SD卡不存在");
            return false;
        }
    }

    /**
     * 隐藏软键盘
     */
    public static void hideSoftInputMode(Context context, View windowToken) {
        InputMethodManager imm = ((InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE));
        imm.hideSoftInputFromWindow(windowToken.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 2  * 分辨率获取
     * 3  * @return 高宽数组
     * 4
     */
    static int[] met = null;

    public static int[] getWindowManager(Context con) {
        if (met == null) {
            Display display = ((Activity) con).getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            met = new int[2];
            met[0] = displayMetrics.widthPixels;
            met[1] = displayMetrics.heightPixels;
        }
        return met;
    }

    /**
     * MD5加密
     */
    public static String MD5(String pwd) {
        String result = "";
        if (pwd != null) {
            try {
                // 指定加密的方式为MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 进行加密运算
                byte bytes[] = md.digest(pwd.getBytes());
                for (int i = 0; i < bytes.length; i++) {
                    // 将整数转换成十六进制形式的字符串 这里与0xff进行与运算的原因是保证转换结果为32位
                    String str = Integer.toHexString(bytes[i] & 0xFF);
                    if (str.length() == 1) {
                        str += "F";
                    }
                    result += str;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toUpperCase();
    }

    /**
     * string转int
     *
     * @param str
     * @return
     */
    public static int str2Int(String str) {
//        int i = (int)Double.parseDouble(str);
        return Integer.valueOf(str).intValue();
    }

    public static int float2Int(float f) {
        int i = (int) f;
        return i;
    }

    /**
     * string转double
     *
     * @param str
     * @return
     */
    public static double str2Double(String str) {

        return Double.parseDouble(str) * 100 * 0.01;
    }

    /**
     * Double转float
     *
     * @return
     */
    public static float double2Float(Double d) {
        float f = d.floatValue();
        return f;
    }


    /**
     * double保留两位数字
     * 返回值是double（已知BUG：返回值小数位数可能为无限）
     *
     * @return
     */
    public static double Double2(Double d) {
        if (d != null) {

            DecimalFormat df = new DecimalFormat("#0.00");
            return str2Double(df.format(d));
        }
        return d;
    }

    /**
     * double保留两位数字
     * 返回值是string
     *
     * @return
     */
    public static String double2(Double d) {
        if (d != null) {
            DecimalFormat df = new DecimalFormat("#0.00");
            return df.format(d);
        }
        return d + "";
    }


    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public static String getVersion(Context con) {
        try {
            PackageManager manager = con.getPackageManager();
            PackageInfo info = manager.getPackageInfo(con.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "暂无版本号";
        }
    }

    /**
     * 检测网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService
                (Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 检测Sdcard是否存在
     *
     * @return
     */
    public static boolean isExitsSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取状态栏高度
     *
     * @param v
     * @return
     */
    public static int getStatusBarHeight(View v) {
        if (v == null) {
            return 0;
        }
        Rect frame = new Rect();
        v.getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }


    /**
     * 截取len位字符串 两位：110105 为11
     */
    public static String interceptStr(String str, int len) {
        if (str != null) {
            String d = str.substring(0, len);
            return d;
        }
        return str;
    }

    /**
     * 截取2016-04-05 16:30:00   为04-05 16：30
     */
    public static String ntercept_MD_HM(String str) {
        if (str != null) {
            String d = str.substring(5, str.length());
            String c = d.substring(0, 11);
            return c;
        }
        return str;
    }

    /**
     * 截取2016-04-05 16:30:00   为04-05
     */
    public static String intercept_MD(String str) {
        if (str != null) {
            return str.substring(5, 10);
        }
        return str;
    }


    /**
     * 截取2016-04-05 16:30:00   为04
     */
    public static int intercept_M(String str) {
        if (str != null) {
            return str2Int(str.substring(5, 7));
        }
        return 0;
    }

    /**
     * 截取2016-04-05 16:30:00   为2016-04-05 16:30
     */
    public static String intercept_YMDHM(String str) {
        if (str != null) {
            String d = str.substring(0, str.length() - 3);
            return d;
        }
        return str;
    }

    /**
     * 截取2016-04-05 16:30:00   为2016-04
     */
    public static String intercept_YM(String str) {
        if (str != null) {
            String d = str.substring(0, 7);
            return d;
        }
        return str;
    }

    /**
     * 截取2016-04-05 16:30:00   为05
     */
    public static String intercept_dd(String str) {
        if (str != null) {
            String d = str.substring(8, 10);
            LogUtil.e("d=" + d);
            return d;
        }

        return str;
    }

    /**
     * 截取123.45   为123
     */
    public static String intercept_Int_Point(String str) {
        if (str != null) {
            String d = str.substring(0, str.lastIndexOf("."));
            return d;
        }
        return str;
    }

    /**
     * 截取2016-04-05 16:30:00   为2016-04-05
     */
    public static String intercept_YMD(String str) {
        if (str != null) {
            String d = str.substring(0, str.lastIndexOf(" "));
            return d;
        }
        return str;
    }


    /**
     * 截取2016-04-05 16:30:00   为2016.04.05
     */
    public static String intercept_YMD_Point(String str) {
        if (str != null) {
            String d = str.substring(0, str.lastIndexOf(" "));

            return d.replace("-", ".");
        }
        return str;
    }

    /**
     * 截取2016-04-05 16:30:00   为2016.04.05
     */
    public static String replaceTime_Point(String str) {
        if (str != null) {

            return str.replace("-", ".");
        }
        return str;
    }

    /**
     * 截取2016-04-05 16:30:00   为2016/04/05
     */
    public static String intercept_YMD_X(String str) {
        if (str != null) {
            String d = str.substring(0, str.lastIndexOf(" "));

            return d.replace("-", "/");
        }
        return str;
    }

    /**
     * 截取2016-04-05 16:30:00   为2016
     */
    public static String intercept_Y(String str) {
        if (str != null) {
            return str.substring(0, 4);
        }
        return str;
    }

    /**
     * 替换2016-04-05 16:30:00   为2016年04月05日 16:30
     */
    public static String replaceTime(String str) {
        if (str != null) {
//            String year = str.substring(0, 5);
//            String month = str.substring(5, 8);
//            String day = str.substring(8, 10);
//            String hour = str.substring(11, 14);
//            String minute = str.substring(14, 17);
//            String second = str.substring(17, 19);
            return str.substring(0, 4) + "年" + str.substring(6, 7) + "月" + str.substring(8, 9) +
                    "日 "
                    + str.substring(10, 16);
        }
        return str;
    }

    /**
     * 替换2016-04-05 16:30:00   为2016年04月05日
     */
    public static String replaceTime_YMD(String str) {
        if (str != null) {
            return str.substring(0, 4) + " 年 " + str.substring(5, 7) + " 月 " + str.substring(8,
                    10) +
                    " 日";
        }
        return str;
    }

    /**
     * 替换2016-04-05 16:30:00   为2016年04月
     */
    public static String replaceTime_YM(String str) {
        if (str != null) {
            return str.substring(0, 4) + "年" + str.substring(5, 7) + "月";
        }
        return str;
    }

    /**
     * 替换2016-04-05 16:30:00   04月05日
     */
    public static String replaceTime_MD(String str) {
        if (str != null) {
            return str.substring(5, 7) + "月" + str.substring(8, 10) + "日";
        }
        return str;
    }

    /**
     * 截取2016-04-05 16:30:00   为16:30
     */
    public static String intercept_HM(String str) {
        if (str != null) {
            return str.substring(11, 16);
        }
        return str;
    }

    /**
     * 截取2016-04-05 16:30:00   为16:30:00
     */
    public static String intercept_HMS(String str) {
        if (str != null) {
            String d = str.substring(str.lastIndexOf(" "), str.length());
            return d;
        }
        return str;
    }

    public static float getRatingBarNum(double num) {
        if (num == 0) {
            return 0;
        }
        if (num / 100 < 1) {
            return 0.5f;
        }
        if (num / 100 >= 1 && num / 100 < 1.5) {
            return 1f;
        }
        if (num / 100 >= 1.5 && num / 100 < 2) {
            return 1.5f;
        }
        if (num / 100 >= 2 && num / 100 < 2.5) {
            return 2f;
        }
        if (num / 100 >= 2.5 && num / 100 < 3) {
            return 2.5f;
        }
        if (num / 100 >= 3 && num / 100 < 3.5) {
            return 3f;
        }
        if (num / 100 >= 3.5 && num / 100 < 4) {
            return 3.5f;
        }
        if (num / 100 >= 4 && num / 100 < 4.5) {
            return 4f;
        }
        if (num / 100 >= 4.5 && num / 100 < 5) {
            return 4.5f;
        }
        if (num / 100 >= 5) {
            return 5f;
        }
        return 0;
    }

    /**
     * 秒数   转为00:00:00
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) {
            return "00:00";
        } else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99) {
                    return "99:59:59";
                }
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + Integer.toString(i);
        } else {
            retStr = "" + i;
        }
        return retStr;
    }


    /**
     * 截取com.zys.jym.lanhu.activity.LoginActivity@1f188df  为com.zys.jym.lanhu.activity.LoginActivity
     */
    public static String intercept_cls(String str) {
        if (str != null) {
            String d = str.substring(0, str.lastIndexOf("@"));
            return d;
        }
        return str;
    }

    public static String intercept_index_before(String str, String indexstr) {
        if (str != null) {
            String d = str.substring(str.lastIndexOf(indexstr), str.length());
            return d;
        }
        return str;
    }

    public static String intercept_index_after(String str, String indexstr) {
        if (str != null) {
            String d = str.substring(str.lastIndexOf(indexstr), str.length());
            return d;
        }
        return str;
    }


    /**
     * 替换字符"T"  2016-04-05T16:30:00为 2016-04-05 16:30:00
     */
    public static String replace_T_Str(String s) {
        if (s != null) {
            String s1 = s.replaceFirst("T", " ");
            return s1;
        }
        return s;
    }

    /**
     * 替换字符"T"  2016-04-05T16:30:00为 2016-04-05 16:30:00
     */
    public static String replace_hh_Str(String s) {
        try {
            if (s != null) {
                String s1 = s.replace("\\n", "\n");
                return s1;
            }
        } catch (Exception e) {
//            Loge(TAG,e.toString());
            return s;
        }
        return s;
    }

    /**
     * 替换  18637752014为 186*****014
     */
    public static String replace_phone_Str(String s) {
        if (s != null) {
            String s1 = s.substring(0, 3) + "****" + s.substring(7, s.length());
            return s1;
        }
        return s;
    }

    /**
     * 替换  18637752014为 186*****014
     */
    public static String replace_phone_Str2(String s) {
        if (s != null) {
            String s1 = s.substring(0, 3) + "****" + s.substring(7, s.length());
            return s1;
        }
        return s;
    }

    /**
     * 替换  215766856@qq.com为 2******@qq.com
     */
    public static String replace_email_Str(String s) {
        if (s != null) {
            String a[] = s.split("@");
            String s1 = a[0].substring(0, 1) + "******@";
            String s2 = s1 + a[1];
            return s2;
        }
        return s;
    }

    /**
     * 替换 41215119931212515x为 41***********11
     */
    public static String replace_idCard_Str(String s) {
        if (s != null) {
            String s1 = s.substring(0, 2) + "***********" + s.substring(s.length() - 2, s.length());
            return s1;
        }
        return s;
    }

    /**
     * 替换 41215119931212515x为 41***********11
     */
    public static String replace_idCard_Str2(String s) {
        if (s != null) {
            String s1 = s.substring(0, 4) + "*******" + s.substring(s.length() - 4, s.length());
            return s1;
        }
        return s;
    }


    /**
     * 限制输入框 小数点后位数为两位
     */
    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

    }

    /**
     * 验证邮箱 必须带@，并且@后必须带域名，如.com
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(" +
                "([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        mPattern = Pattern.compile(str);
        mMatcher = mPattern.matcher(email);
        return mMatcher.matches();
    }

    /**
     * 验证输入身份证号
     *
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isIDcard(String idcard) {
//        Loge(TAG, "idcard.length()=" + idcard.length());
        if (idcard.length() == 15) {
            //15位检验
            String str = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
            mPattern = Pattern.compile(str);
            mMatcher = mPattern.matcher(idcard);
            return mMatcher.matches();
        }
        if (idcard.length() == 18) {
            //18位检验
            String str = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})" +
                    "|\\d{3}[A-Z])$";
            mPattern = Pattern.compile(str);
            mMatcher = mPattern.matcher(idcard);
            return mMatcher.matches();
        }
        return false;
    }

    /**
     * 验证手机号 11位手机号
     *
     * @param number
     * @return
     */
    public static boolean isPhoneNumber(String number) {
        mPattern = Pattern
                .compile("^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$|(^" +
                        "(13\\d|14[57]|15[^4,\\D]|16[6]|17[0-9]|19[0-9]|18\\d)" +
                        "\\d{8}|170[059]\\d{7}$)");
        mMatcher = mPattern.matcher(number);
        return mMatcher.matches();
    }

    /**
     * 验证密码 6-20位数字或英文字符（不含全角字符），区分大小写，不能带空格，不能带中文字符
     *
     * @param password
     * @return
     */
    public static boolean isPassword(String password) {
        mPattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$");
        mMatcher = mPattern.matcher(password);
        return mMatcher.matches();
    }

    /**
     * 验证密码 6-12位数字英文混合密码，区分大小写，不能带空格，不能带中文字符
     *
     * @param password
     * @return
     */
    public static boolean isHHPassword(String password) {
        mPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,12}$");
        mMatcher = mPattern.matcher(password);
        return mMatcher.matches();
    }


    /**
     * 验证推荐人账号6位数字
     *
     * @param RefereeCode
     * @return
     */
    public static boolean isRefereeCode(String RefereeCode) {
        mPattern = Pattern.compile("^[0-9]{6}$");
        mMatcher = mPattern.matcher(RefereeCode);
        return mMatcher.matches();
    }

    /**
     * 验证是否带有@符号
     *
     * @param username
     * @return
     */
    public static boolean isEmailType(String username) {
        mPattern = Pattern.compile("^.*@.*$");
        mMatcher = mPattern.matcher(username);
        return mMatcher.matches();
    }

    public static boolean isQQCorrect(String str) {
        Pattern p = Pattern.compile("[1-9][0-9]{4,11}");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 验证昵称 2-16位中英文、数字、下划线(开头结尾不能是下划线)
     *
     * @param nickname
     * @return
     */
    public static boolean isNickName(String nickname) {
        mPattern = Pattern
                .compile("^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{2,16}+$");
        mMatcher = mPattern.matcher(nickname);
        return mMatcher.matches();
    }

    public static Bitmap drawable2bitmap(Context context, int pic) {
        Resources res = context.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, pic);
        return bmp;
    }

    public static void copyText(Context context, CharSequence textCopy) {
        if (Build.VERSION.SDK_INT > 11) {
            android.content.ClipboardManager c = (android.content.ClipboardManager) context
                    .getSystemService(Context.CLIPBOARD_SERVICE);
            c.setText(textCopy);
        } else {
            android.text.ClipboardManager c = (android.text.ClipboardManager) context
                    .getSystemService(Context.CLIPBOARD_SERVICE);
            c.setText(textCopy);
        }
    }


    /**
     * 禁止EditText输入空格
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int
                    dstart, int dend) {
                if (source.equals(" ")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }


    public static String getDistance(double startx, double starty, double endx, double endy) {
        double lat1 = startx;
        double lat2 = starty;

        double lon1 = endx;
        double lon2 = endy;

        //地球半径
        double R = 6371;

        //两点间距离 km，如果想要米的话，结果*1000
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) *
                Math.cos(lon2 - lon1)) * R;
        if (d < 1) {
            return (int) d * 1000 + "m";
        } else {
            return String.format("%.2f", d) + "km";
        }
    }

//    /**
//     * 通过Uri跳转到百度地图导航
//     */
//    public static void startNative_Baidu(Activity activity, LatLng pt1, LatLng pt2) {
//        try {
//            double dis = DistanceUtil.getDistance(new LatLng(pt1.latitude, pt1.longitude), new
// LatLng(pt2.latitude, pt2.longitude));
//            if (dis <= 100) {
//                Toast.makeText(activity, "起点、途经点、终点距离太近", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            String start_latlng = pt1.latitude + "," + pt1.longitude;
//            String end_latlng = pt2.latitude + "," + pt2.longitude;
//            Intent intent = Intent.getIntent("intent://map/direction?origin=latlng:" +
// start_latlng + "|name:" + "Start" + "&destination=latlng:" + end_latlng + "|name:" + "End" +
// "&mode=riding&src=这里随便写#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
//            activity.startActivity(intent);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(activity, "地址解析错误", Toast.LENGTH_SHORT).show();
//        }
//    }


    /**
     * 禁止EditText输入空格和限制最大位数
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText, final int maxLength) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int
                    dstart, int dend) {
                if (source.equals(" ") || dend > maxLength || end > 3) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 禁止EditText输入特殊字符和空格
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpeChat(EditText editText) {

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int
                    dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=_-|{}':;',\\[\\]" +
                        ".<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find() || source.equals(" ")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 禁止EditText输入特殊字符和空格 和最大长度
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpeChat(EditText editText, final int maxLength) {

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int
                    dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=_-|{}':;',\\[\\]" +
                        ".<>/?~！@#￥……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find() || source.equals(" ") || dend > maxLength || end > 3) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 2  * 转换时间格式
     * 4
     */
    public static String getDateFormat(Date dtime, String pattern) {
        try {
            SimpleDateFormat sfd = new SimpleDateFormat(pattern);
            return sfd.format(dtime);
        } catch (Exception e) {
// TODO: handle exception
            return "";
        }
    }

    /**
     * Sd卡是否存在
     */
    public static boolean isSDCard(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            //setToastShow(context, "SD卡不存在");
            return false;
        }
    }

    /**
     * 设置图片色彩值
     *
     * @param mIvImg
     * @param saturation 饱和度 0：灰色，50正常，100过度彩色
     */
    public static void setColorMatrix(ImageView mIvImg, int saturation) {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(saturation);//饱和度 0灰色 100过度彩色，50正常
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        mIvImg.setColorFilter(filter);
    }


    public static File saveBitmapFile(String path, Bitmap bitmap) {
        File file = null;
        try {
//            String p=UtilTools.getSdCardPath() + "/zhgj/img/";
            UtilTools.isFoldersExists(path);
            file = new File(path, TimeUtil.getTimeStamp() + ".jpeg");//将要保存图片的路径
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
