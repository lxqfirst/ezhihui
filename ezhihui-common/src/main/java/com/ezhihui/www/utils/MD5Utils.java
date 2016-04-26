package com.ezhihui.www.utils;

import java.security.MessageDigest;

/**
 * Created by lxq on 16/4/26.
 */
public class MD5Utils {
    private static final char[] bits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public MD5Utils() {
    }

    public static String md5(String content) {
        if (content != null && !content.isEmpty()) {
            try {
                byte[] e = content.getBytes();
                MessageDigest mdInst = MessageDigest.getInstance("MD5");
                mdInst.update(e);
                byte[] md = mdInst.digest();
                int j = md.length;
                char[] str = new char[j * 2];
                int k = 0;

                for (int i = 0; i < j; ++i) {
                    byte byte0 = md[i];
                    str[k++] = bits[byte0 >>> 4 & 15];
                    str[k++] = bits[byte0 & 15];
                }

                return new String(str);
            } catch (Exception var9) {
                return null;
            }
        } else {
            throw new IllegalArgumentException("should not be null or empty");
        }
    }
}
