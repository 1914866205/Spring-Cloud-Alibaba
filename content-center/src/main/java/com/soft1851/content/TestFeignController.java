package com.soft1851.content;

import com.soft1851.content.domain.entity.MidUserShare;
import com.soft1851.content.feignclient.TestBaiduFeignClient;
import com.soft1851.content.feignclient.TestUserCenterFeignClient;
import com.soft1851.content.feignclient.UserCenterFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName TestFeignController.java
 * @Description TODO
 * @createTime 2020年09月30日 11:01:00
 */
@RestController
@RequestMapping("/test")
public class TestFeignController {
    @Resource
    private TestUserCenterFeignClient testUserCenterFeignClient;
    @Resource
    private UserCenterFeignClient userCenterFeignClient;
    @Autowired
    private TestBaiduFeignClient testBaiduFeignClient;

    @GetMapping(value = "/test-q")
    public String post(MidUserShare midUserShare) {
        return testUserCenterFeignClient.post(midUserShare);
    }

    @GetMapping(value = "/baidu")
    public String baiduIndex() {
//        return testBaiduFeignClient.index();
        return userCenterFeignClient.findUserById(1).toString();
    }

}
