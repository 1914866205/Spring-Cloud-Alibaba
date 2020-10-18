package com.soft1851.content.service;

import com.soft1851.content.common.ResponseResult;
import com.soft1851.content.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.content.domain.entity.User;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2020年09月25日 15:02:00
 */
public interface UserService {
    User updateBonus(UserAddBonusMsgDTO updateBonus);

    ResponseResult userSign(Integer userId);

    ResponseResult bonusLog(Integer userId);
}
