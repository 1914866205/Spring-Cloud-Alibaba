package com.soft1851.user.service;

import com.soft1851.user.entity.User;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2020年09月25日 15:02:00
 */
public interface UserService {
    User findUserById(int id);
}
