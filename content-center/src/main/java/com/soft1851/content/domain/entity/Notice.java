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
@Table(name = "notice")
@ApiModel("公告")
public class Notice {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    @ApiModelProperty(name = "id",value = "公告id")
    private Integer id;

    @Column(name = "create_time")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    //东八区时区
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @ApiModelProperty(name = "content",value = "内容")
    @Column(name = "content")
    private String content;

    @ApiModelProperty(name = "showFlag",value = "是否显示 0 ：否   1：是")
    @Column(name = "show_flag")
    private Boolean showFlag;
}
