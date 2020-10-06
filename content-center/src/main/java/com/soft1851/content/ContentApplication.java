package com.soft1851.content;

import com.purgeteam.dispose.starter.annotation.EnableGlobalDispose;
import com.soft1851.content.configuration.GlobalFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author nitaotao
 */
@SpringBootApplication
@MapperScan("com.soft1851.content.mapper")
@EnableFeignClients(defaultConfiguration = GlobalFeignConfiguration.class)
//@EnableFeignClients
//@EnableSwagger2
@EnableGlobalDispose
public class ContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
