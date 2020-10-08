package com.soft1851.content.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName AuditStatusEnum.java
 * @Description TODO
 * @createTime 2020年09月29日 20:48:00
 */
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     * 待审核
     */
    NOT_YET,
    /**
     * 审核通过
     */
    PASS    ,
    /**
     * 审核不通过
     */
    REJECT
}
