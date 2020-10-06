package com.soft1851.content.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.soft1851.content.domain.entity.MidUserShare;
import com.soft1851.content.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName TestController.java
 * @Description TODO
 * @createTime 2020年09月23日 08:35:00
 */
@Slf4j
@RestController
@RequestMapping(value = "test")
public class TestController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    private TestService testService;

    /**
     * 测试：服务发现，证明内容中心总能找到用户中心
     *
     * @return 用户中心所有实例的地址信息
     */
    @GetMapping("/discovery")
    public List<ServiceInstance> getInstances() {
        //查询指定服务的所有实例的信息
        return this.discoveryClient.getInstances("user-center");
    }

    @GetMapping("/call/hello")
    public String callUserCenter() {
        //用户中心的所有的实例信息
        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
//        int i = (int) Math.floor(Math.random() * (instances.size() - 1));
//        String targetUrl = instances.get(i).getUri().toString() + "/user/hello";
//        System.out.println(i);
//        System.out.println(targetUrl);
        String targetUrl = instances.stream().map(instance -> instance.getUri().toString() + "/user/hello")
                .findFirst().orElseThrow(() -> new IllegalArgumentException("当前没有实例！"));
        List<String> targetUrls = instances.stream().map(instance -> instance.getUri().toString() + "/user/hello")
                .collect(Collectors.toList());
//        //随机数
//        int i2 = ThreadLocalRandom.current().nextInt(targetUrls.size());
//        log.info("请求的目标地址：{}", targetUrls.get(i2));
        return restTemplate.getForObject(targetUrl, String.class);
    }

    @GetMapping("/get/{info}")
    public String get(@PathVariable String info) {
        return "来自get请求发送的消息:" + info;
    }

    @PostMapping("/post")
    public String post(@RequestBody String info) {
        return "来自post请求发送的消息:" + info;
    }

    @PostMapping("/post/json")
    public String post(@RequestBody MidUserShare midUserShare) {
        return "来自post请求发送的消息:" + midUserShare;
    }


    @GetMapping("/test-a")
    public String testA() {
        testService.conmmonMethod();
        return "test-a";
    }

    @GetMapping("/test-b")
    public String testB() {
        testService.conmmonMethod();
        return "test-b";
    }

    @GetMapping("byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")       //这个value必须和 Sentinel 的资源名一样
    public String byResource() {
        return "按名称限流";
    }


    public String handleException(BlockException blockException) {
        return "服务不可用";
    }

}
