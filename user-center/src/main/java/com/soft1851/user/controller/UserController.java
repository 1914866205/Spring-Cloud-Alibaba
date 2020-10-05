package com.soft1851.user.controller;

import com.soft1851.user.entity.MidUserShare;
import com.soft1851.user.entity.User;
import com.soft1851.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description TODO
 * @createTime 2020年09月23日 09:32:00
 */
@RestController
@RequestMapping(value = "user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/hello")
    public String sayHello() {
        log.info("我被调用了");
        return "Hello , this massage is from user-center";
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable int id) {
        return userService.findUserById(id);
    }
    @PostMapping("/post/json")
    public String post(@RequestBody MidUserShare midUserShare) {
        return "来自post请求发送的消息:"+midUserShare;
    }
}
