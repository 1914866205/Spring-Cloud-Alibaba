package com.soft1851.content.service.Impl;

import com.soft1851.content.common.ResponseResult;
import com.soft1851.content.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.content.domain.entity.User;
import com.soft1851.content.feignclient.UserCenterFeignClient;
import com.soft1851.content.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2020年09月25日 15:02:00
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserCenterFeignClient userCenterFeignClient;

    @Override
    public User updateBonus(UserAddBonusMsgDTO updateBonus) {
        return userCenterFeignClient.updateBonus(updateBonus);
    }

    @Override
    public ResponseResult userSign(Integer userId) {
        System.out.println("进入签到Service");
        return userCenterFeignClient.userSign(userId);
    }

    @Override
    public ResponseResult bonusLog(Integer userId) {
        System.out.println("准备进入usercenter");
        return userCenterFeignClient.bonusLog(userId);
    }
}
