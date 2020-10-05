package com.soft1851.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

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
@Table(name = "share")
public class Share {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "title")
    private String title;
    @Column(name = "create_time")
    private Timestamp createTime;
    @Column(name = "update_time")
    private Timestamp updateTime;
    @Column(name = "is_original")
    private Integer isOriginal;
    @Column(name = "author")
    private String author;
    @Column(name = "cover")
    private String cover;
    @Column(name = "summary")
    private String summary;
    @Column(name = "download_url")
    private String downloadUrl;
    @Column(name = "audit_status")
    private String auditStatus;
    @Column(name = "reason")
    private String reason;
    @Column(name = "price")
    private Integer price;
    @Column(name = "buy_count")
    private Integer buyCount;
    @Column(name = "show_flag")
    private Boolean showFlag;
}
