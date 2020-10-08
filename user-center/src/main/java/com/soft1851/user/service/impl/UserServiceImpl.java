package com.soft1851.user.service.impl;

import com.soft1851.user.entity.User;
import com.soft1851.user.entity.dto.UserAddBonusMsgDTO;
import com.soft1851.user.mapper.UserMapper;
import com.soft1851.user.service.UserService;
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
    private final UserMapper userMapper;

    @Override
    public User findUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User updateBonus(UserAddBonusMsgDTO updateBonus) {
        User user = userMapper.selectByPrimaryKey(updateBonus.getUserId());
        user.setBonus(updateBonus.getBonus());
        userMapper.updateByPrimaryKey(user);
        return user;
    }
}
