package com.soft1851.content;

import org.springframework.web.client.RestTemplate;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName SentineTest.java
 * @Description TODO
 * @createTime 2020年10月06日 15:45:00
 */
public class SentineTest {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 100; i++) {
            String object = restTemplate.getForObject("http://localhost:8083/test/byResource", String.class);
            System.out.println("ok");
            Thread.sleep(250);
        }
    }
}
