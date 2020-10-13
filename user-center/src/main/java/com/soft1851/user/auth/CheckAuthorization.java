package com.soft1851.user.auth;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName CheckAuthorization.java
 * @Description TODO
 * @createTime 2020年10月13日 15:29:00
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorization {
    String value();
}
