package com.soft1851.content.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName TestBaiduFeignClient.java
 * @Description TODO
 * @createTime 2020年09月30日 11:34:00
 */
@FeignClient(name = "baidu", url = "http://www.baidu.com")
public interface TestBaiduFeignClient {

    @GetMapping("")
    String index();
}
