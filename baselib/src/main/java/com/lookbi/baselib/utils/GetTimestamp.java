package com.lookbi.baselib.utils;

import java.util.Date;

/**
 * Created by zzz on 2017/11/7.
 */

public class GetTimestamp {
    /**
     * 获取精确到秒的时间戳
     * @param date
     * @return
     */
    public static long  getSecondTimestampTwo(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Long.valueOf(timestamp);
    }
}
