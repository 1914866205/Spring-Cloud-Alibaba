package com.soft1851.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2020年09月24日 14:38:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mid_user_share")
public class MidUserShare {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Integer id;
    @Column(name = "share_id")
    private Integer shareId;
    @Column(name = "user_id")
    private Integer userId;
}
