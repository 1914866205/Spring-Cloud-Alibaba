package com.soft1851.content.feignclient;

import com.soft1851.content.domain.entity.MidUserShare;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName UserCenterFeignClient.java
 * @Description TODO
 * @createTime 2020年09月29日 20:21:00
 */
//@FeignClient(name = "user-center",configuration = UserCenterFeignConfiguration.class)
@FeignClient(name = "user-center")
public interface TestUserCenterFeignClient {
    @GetMapping("/user/post/json")
    String post(MidUserShare midUserShare);

}
