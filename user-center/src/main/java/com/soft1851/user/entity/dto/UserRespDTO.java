package com.soft1851.user.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName UserRespDTO.java
 * @Description 返回结果中的用户数据对象
 * @createTime 2020年10月13日 15:50:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRespDTO {
    /**
     * 积分
     */
    private Integer id;
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * 积分
     */
    private Integer bonus;
    /**
     * 微信昵称
     */
    private String wxNickname;

}
