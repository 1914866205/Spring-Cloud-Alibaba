package com.soft1851.user.service.impl;

import com.alibaba.nacos.client.identify.STSConfig;
import com.soft1851.user.common.ResponseResult;
import com.soft1851.user.entity.BonusEventLog;
import com.soft1851.user.entity.User;
import com.soft1851.user.entity.dto.LoginDTO;
import com.soft1851.user.entity.dto.UserAddBonusMsgDTO;
import com.soft1851.user.mapper.BonusEventLogMapper;
import com.soft1851.user.mapper.UserMapper;
import com.soft1851.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.runtime.Desc;
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
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public User findUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User updateBonus(UserAddBonusMsgDTO updateBonus) {
        User user = userMapper.selectByPrimaryKey(updateBonus.getUserId());
        user.setBonus(user.getBonus()+updateBonus.getBonus());
        userMapper.updateByPrimaryKey(user);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        Timestamp ts = Timestamp.valueOf(time);
        bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .event(updateBonus.getMsg())
                        .createTime(ts)
                        .description(updateBonus.getMsg())
                        .userId(updateBonus.getUserId())
                        .value(updateBonus.getBonus()).build());
        return user;
    }


    @Override
    public User login(LoginDTO loginDTO, String openid) {
        //现根据openid查找用户
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
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
        return users.get(0);
    }

    @Override
    public ResponseResult userSign(Integer userId) {
        /**
         * 3.用户中心根据userId按时间降序排序查询该用户的内容为签到的积分记录
         * 4.取出积分记录，获取当前时间毫秒值 - 该积分记录创建时间的毫秒值
         * 5.如果>24.60.60.1000 则为新的一天 创建新的用积分记录，并修改该用户的积分
         */
        Example example = new Example(BonusEventLog.class);
        //此处存中文  是在给自己买坑
        example.createCriteria().andEqualTo("userId", userId).andEqualTo("event", "签到");
        example.setOrderByClause("create_time Desc");
        List<BonusEventLog> bonusEventLogs = bonusEventLogMapper.selectByExample(example);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        Timestamp now = Timestamp.valueOf(time);
        //如果该用户未签到过，直接添加记录
        if (bonusEventLogs.isEmpty()) {
            bonusEventLogMapper.insert(BonusEventLog.builder().createTime(now).userId(userId).event("签到").description("签到+5").value(5).build());
            User user = userMapper.selectByPrimaryKey(userId);
            Integer bonus = user.getBonus();
            bonus += 5;
            user.setBonus(bonus);
            userMapper.updateByPrimaryKey(user);
            return ResponseResult.builder().data("成功").msg(String.valueOf(bonus)).code(200).build();
        } else {
            long nowTime = now.getTime();
            long early = bonusEventLogs.get(0).getCreateTime().getTime();
            long result = nowTime - early - 24 * 60 * 60 * 1000;
            //如果时间超过一天，添加记录
            if (result >= 0) {
                //添加记录
                bonusEventLogMapper.insert(BonusEventLog.builder().createTime(now).userId(userId).event("签到").description("签到+5").value(5).build());
                User user = userMapper.selectByPrimaryKey(userId);
                Integer bonus = user.getBonus();
                bonus += 5;
                user.setBonus(bonus);
                userMapper.updateByPrimaryKey(user);
                return ResponseResult.builder().data(String.valueOf(bonus)).msg("成功").code(200).build();
            } else {
                System.out.println("距离下次签到时间还有： " + (-result) / 1000 + "秒");
                return ResponseResult.builder().data("失败").msg(String.valueOf(-result/1000)).code(201).build();
            }
        }
    }

    @Override
    public ResponseResult bonusLog(Integer userId) {
        Example example = new Example(BonusEventLog.class);
        example.createCriteria().andEqualTo("userId", userId);
        List<BonusEventLog> bonusEventLogs = bonusEventLogMapper.selectByExample(example);
        return ResponseResult.builder().code(200).msg("ok").data(bonusEventLogs).build();
    }
}
