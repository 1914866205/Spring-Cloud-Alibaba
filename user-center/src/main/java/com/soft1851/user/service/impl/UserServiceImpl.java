package com.soft1851.user.service.impl;

import com.soft1851.user.entity.User;
import com.soft1851.user.entity.dto.LoginDTO;
import com.soft1851.user.entity.dto.UserAddBonusMsgDTO;
import com.soft1851.user.mapper.UserMapper;
import com.soft1851.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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


    @Override
    public User login(LoginDTO loginDTO, String openid) {
        //现根据openid查找用户
        Example example=new Example(User.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("wxId", loginDTO.getOpenId());
        List<User> users = this.userMapper.selectByExample(example);
        //没找到，是新用户，直接注册
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        Timestamp ts = Timestamp.valueOf(time);

        if (users.size() == 0) {
            User saveUser = User.builder()
                    .avatarUrl(loginDTO.getAvatarUrl())
                    .roles("users")
                    .bonus(100)
                    .createTime(ts)
                    .updateTime(ts)
                    .build();
            this.userMapper.insert(saveUser);
            return saveUser;
        }
        return users.get(0);    }
}
