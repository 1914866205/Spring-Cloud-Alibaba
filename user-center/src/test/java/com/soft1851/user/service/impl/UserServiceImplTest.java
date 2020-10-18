package com.soft1851.user.service.impl;

import com.soft1851.user.mapper.BonusEventLogMapper;
import com.soft1851.user.mapper.UserMapper;
import com.soft1851.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ÄßÌÎÌÎ
 * @version 1.0.0
 * @ClassName UserServiceImplTest.java
 * @Description TODO
 * @createTime 2020Äê10ÔÂ16ÈÕ 15:39:00
 */
@SpringBootTest
class UserServiceImplTest {
    @Resource
    private UserService userService;
    @Test
    void userSign() {
//        userService.userSign(1);
        System.out.println(userService.bonusLog(1));
    }
}