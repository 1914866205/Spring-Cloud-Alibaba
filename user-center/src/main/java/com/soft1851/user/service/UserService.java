package com.soft1851.user.service;

import com.soft1851.user.entity.User;
import com.soft1851.user.entity.dto.LoginDTO;
import com.soft1851.user.entity.dto.UserAddBonusMsgDTO;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2020年09月25日 15:02:00
 */
public interface UserService {
    User findUserById(int id);

    User updateBonus(UserAddBonusMsgDTO updateBonus);

    User login(LoginDTO loginDTO,String openid);
}
