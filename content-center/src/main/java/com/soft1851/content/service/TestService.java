package com.soft1851.content.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName TestService.java
 * @Description TODO
 * @createTime 2020年10月06日 16:10:00
 */
@Service
@Slf4j
public class TestService {
    public String conmmonMethod() {
        log.info("info...........");
        return "common";
    }
}
