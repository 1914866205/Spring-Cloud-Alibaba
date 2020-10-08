package com.soft1851.content.feignclient;

import com.soft1851.content.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.content.domain.dto.UserDTO;
import com.soft1851.content.domain.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName UserCenterFeignClient.java
 * @Description TODO
 * @createTime 2020年09月29日 20:21:00
 */
//@FeignClient(name = "user-center",configuration = UserCenterFeignConfiguration.class)
@FeignClient(name = "user-center")
public interface UserCenterFeignClient {
    /**
     * http://user-center/user/find/{id}
     * @param id
     * @return
     */
    @GetMapping("/user/find/{id}")
    UserDTO findUserById(@PathVariable Integer id);

    /**
     * Hello测试
     */
    @GetMapping("/user/hello")
    String sayHello();

    /**
     * http://user-center/user/update/bonus
     * @param
     * @return
     */
    @GetMapping("/user/update/bonus")
    User updateBonus(@RequestBody UserAddBonusMsgDTO updateBonus);



}
