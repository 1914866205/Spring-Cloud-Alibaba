package com.soft1851.content.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ÄßÌÎÌÎ
 * @version 1.0.0
 * @ClassName ShareServiceTest.java
 * @Description TODO
 * @createTime 2020Äê10ÔÂ14ÈÕ 21:26:00
 */
@SpringBootTest
class ShareServiceTest {
    @Resource
    private ShareService shareService;
    @Test
    void query() {
        System.out.println(shareService.findById(1));
//        System.out.println(shareService.query("",1,2,1));
    }
}