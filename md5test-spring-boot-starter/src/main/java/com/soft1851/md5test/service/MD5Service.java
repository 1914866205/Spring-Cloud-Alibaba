package com.soft1851.md5test.service;

import com.soft1851.md5test.util.MD5Util;

/**
 * @author ÄßÌÎÌÎ
 * @version 1.0.0
 * @ClassName MD5Service.java
 * @Description TODO
 * @createTime 2020Äê10ÔÂ06ÈÕ 20:17:00
 */
public class MD5Service {
    public String getMD5(String input) {
        return MD5Util.getMD5(input.getBytes());
    }
}
