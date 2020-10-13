package com.soft1851.user.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName LoginDTO.java
 * @Description 用户登录时的传输对象（客户端->后台）
 * @createTime 2020年10月12日 12:04:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    private String openId;
    private String loginCode;
    private String wxNickname;
    private String avatarUrl;
}
