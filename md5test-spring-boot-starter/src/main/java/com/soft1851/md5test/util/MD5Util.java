package com.soft1851.md5test.util;

import java.security.NoSuchAlgorithmException;

/**
 * @author ÄßÌÎÌÎ
 * @version 1.0.0
 * @ClassName MD5Util.java
 * @Description TODO
 * @createTime 2020Äê10ÔÂ06ÈÕ 20:08:00
 */
public class MD5Util {
    public static String getMD5(String source) {
        return getMD5(source.getBytes());
    }
    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            java.security.MessageDigest md = null;
            md = java.security.MessageDigest.getInstance("MD5");
            byte tmp[];
            synchronized (MD5Util.class) {
                md.update(source);
                tmp = md.digest();
            }
            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte bytes = tmp[i];
                str[k++] = hexDigits[bytes >>> 4 & 0xf];
                str[k++] = hexDigits[bytes & 0xf];
            }
            s = new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return s;
    }
}
