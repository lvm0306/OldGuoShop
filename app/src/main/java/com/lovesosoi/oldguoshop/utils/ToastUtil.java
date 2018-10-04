package com.lovesosoi.oldguoshop.utils;

import android.content.Context;
import android.widget.Toast;

import com.lovesosoi.oldguoshop.AppContext;


/**
 * Created by Administrator on 2017/2/26.
 */

public class ToastUtil {
    static Toast mToast;

    /***
     * 单例Toast
     */
    public static void show(Context mContext, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    public static void show(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(AppContext.getInstance().getContext().getApplicationContext()
                    , "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    public static void showNoDev() {
        if (mToast == null) {
            mToast = Toast.makeText(AppContext.getInstance().getContext().getApplicationContext()
                    , "", Toast.LENGTH_SHORT);
        }
        mToast.setText("该模块开发中");
        mToast.show();
    }
}
