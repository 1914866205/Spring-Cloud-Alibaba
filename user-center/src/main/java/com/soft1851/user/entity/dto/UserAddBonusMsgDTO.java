package com.soft1851.user.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName UserAddBonusMsgDTO.java
 * @Description TODO
 * @createTime 2020年10月08日 09:54:00
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonusMsgDTO {
    /**
     * 为谁加的积分
     */
    private Integer userId;
    /**
     * 加多少积分
     */
    private Integer bonus;

    /**
     * 修改原因
     */
    private String msg;
}
