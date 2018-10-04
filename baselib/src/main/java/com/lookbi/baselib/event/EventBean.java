package com.lookbi.baselib.event;


import java.io.Serializable;

/**
 * Created by zhangyisheng on 2017/8/26.
 */

public class EventBean implements Serializable{

    private int event;
    private String data;
    private Object obj;
    private String p;
    private String c;
    private String a;

    public EventBean(int event) {
        this.event = event;
    }

    public EventBean(int event, String data) {
        this.event = event;
        this.data = data;
    }
    public EventBean(int event, String p, String c, String a) {
        this.event = event;
        this.p = p;
        this.c = c;
        this.a = a;
    }

    public EventBean(int event, Object obj) {
        this.event = event;
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
