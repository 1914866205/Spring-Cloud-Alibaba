package com.soft1851.md5test.autoconfiguration;

import com.soft1851.md5test.service.MD5Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ÄßÌÎÌÎ
 * @version 1.0.0
 * @ClassName MD5Configuration.java
 * @Description TODO
 * @createTime 2020Äê10ÔÂ06ÈÕ 20:19:00
 */
@Configuration
public class MD5Configuration {
    @Bean
    MD5Service md5Service(){
        return new MD5Service();
    }
}
