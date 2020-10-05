package com.soft1851.content.mapper;

import com.soft1851.content.domain.entity.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName UserMapper.java
 * @Description TODO
 * @createTime 2020年09月24日 22:06:00
 */
@Component
public interface UserMapper extends Mapper<User> {
}
