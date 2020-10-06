package com.soft1851.content.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName SwaggerConfiguration.java
 * @Description TODO
 * @createTime 2020年10月05日 15:29:00
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "share-app Swagger文档",
                "github地址: ",
                "API V1.0",
                "Terms of service",
                new Contact("倪涛涛", "https://1914866205.xyz", "1914866205@qq.com"),
                "Apache", "http://www.apache.org/", Collections.emptyList()
        );
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.soft1851.content"))
                .build()
                .apiInfo(apiInfo());
    }
}
