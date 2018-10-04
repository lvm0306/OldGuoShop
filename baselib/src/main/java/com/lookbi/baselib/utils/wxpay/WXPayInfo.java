package com.lookbi.baselib.utils.wxpay;

import java.io.Serializable;

/**
 * Created by zhengkq on 2017/1/17.
 */

public class WXPayInfo implements Serializable {
    private String productname;
    private double totalamount;

    public WXPayInfo(String productname, double totalamount) {
        this.productname = productname;
        this.totalamount = totalamount;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
    }
}
