package com.soft1851.gateway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName TimeBetweenConfig.java
 * @Description TODO
 * @createTime 2020年10月09日 10:57:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeBetweenConfig {
    private LocalTime start;
    private LocalTime end;
}
