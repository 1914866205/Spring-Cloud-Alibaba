package com.soft1851.user.entity.dto;

import com.soft1851.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName LoginResDTO.java
 * @Description TODO
 * @createTime 2020年10月12日 12:07:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespDTO {
    /**
     * 用户信息
     */
    private User user;
    /**
     * token数据
     */
    private JwtTokenRespDTO token;
}
