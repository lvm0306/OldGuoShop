package com.lovesosoi.oldguoshop.utils;

import java.security.MessageDigest;

public class StringSHA1 {
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        if (b != null) {
            for (int n = 0; n < b.length; n++) {
                stmp = (Integer.toHexString(b[n] & 0XFF));
                if (stmp.length() == 1) {
                    hs = hs + "0" + stmp;
                } else {
                    hs = hs + stmp;
                }
            }
        }
        return hs;
    }

    //SHA1 加密实例
    public static String encryptToSHA(String info) {
        byte[] digesta = null;
        try {
            // 得到一个SHA-1的消息摘要
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            // 添加要进行计算摘要的信息
            alga.update(info.getBytes());
            // 得到该摘要
            digesta = alga.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将摘要转为字符串
        String rs = byte2hex(digesta);
        return rs;
    }
}
