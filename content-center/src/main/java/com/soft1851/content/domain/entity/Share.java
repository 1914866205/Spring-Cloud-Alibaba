package com.soft1851.content.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("分享")
public class Share {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "分享id")
    private Integer id;

    @Column(name = "user_id")
    @ApiModelProperty(name = "userId", value = "分享人id")
    private Integer userId;

    @Column(name = "title")
    @ApiModelProperty(name = "title", value = "分享内容标题")
    private String title;
    @Column(name = "create_time")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    @ApiModelProperty(name = "updateTime", value = "更新")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Timestamp updateTime;
    @ApiModelProperty(name = "isOriginal", value = "是否原创")
    @Column(name = "is_original")
    private Integer isOriginal;
    @ApiModelProperty(name = "author", value = "作者姓名")
    @Column(name = "author")
    private String author;
    @ApiModelProperty(name = "cover", value = "封面")
    @Column(name = "cover")
    private String cover;
    @ApiModelProperty(name = "summary", value = "总数")
    @Column(name = "summary")
    private String summary;
    @ApiModelProperty(name = "downloadUrl", value = "下载地址")
    @Column(name = "download_url")
    private String downloadUrl;
    @ApiModelProperty(name = "auditStatus", value = "编辑状态")
    @Column(name = "audit_status")
    private String auditStatus;
    @ApiModelProperty(name = "reason", value = "原因")
    @Column(name = "reason")
    private String reason;
    @ApiModelProperty(name = "price", value = "价格")
    @Column(name = "price")
    private Integer price;
    @ApiModelProperty(name = "buyCount", value = "购买数量")
    @Column(name = "buy_count")
    private Integer buyCount;
    @ApiModelProperty(name = "showFlag", value = "是否显示 0 ：否   1：是")
    @Column(name = "show_flag")
    private Boolean showFlag;
}
