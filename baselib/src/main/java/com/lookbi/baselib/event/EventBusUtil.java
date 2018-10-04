package com.lookbi.baselib.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zhangyisheng on 2018/4/2.
 */

public class EventBusUtil {

    public static void register(Object obj) {
        EventBus.getDefault().register(obj);
    }

    public static void unRegister(Object obj) {
        if (isRegistered(obj)) {
            EventBus.getDefault().unregister(obj);
        }
    }

    public static boolean isRegistered(Object obj) {
        return EventBus.getDefault().isRegistered(obj);
    }

    public static void post(int event, Object obj) {
        EventBus.getDefault().post(new EventBean(event, obj));
    }

    public static void post(int event) {
        EventBus.getDefault().post(new EventBean(event));
    }
}
