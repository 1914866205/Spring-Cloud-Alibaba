package com.soft1851.content.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName ResponseResult.java
 * @Description TODO
 * @createTime 2020年09月29日 20:41:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResult implements Serializable {
    private static final long serialVersionUID = -3948389268046368059L;

    private Integer code;
    private String msg;
    private Object data;
}
