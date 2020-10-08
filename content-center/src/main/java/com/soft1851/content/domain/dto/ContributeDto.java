package com.soft1851.content.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName ContributeDto.java
 * @Description TODO
 * @createTime 2020年10月07日 09:08:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("投稿")
public class ContributeDto {

    /**
     * 发布人ID
     */
    @ApiModelProperty(name = "userId", value = "发布人昵称")
    private Integer userId;

    /**
     * 标题
     */
    @ApiModelProperty(name = "title", value = "标题")
    private String title;
    /**
     * 是否原创
     */
    @ApiModelProperty(name = "isOriginal", value = "是否原创")
    private Integer isOriginal;

    /**
     * 发布人昵称
     */
    @ApiModelProperty(name = "author", value = "发布人昵称")
    private String author;
    /**
     * 封面
     */
    @ApiModelProperty(name = "cover", value = "封面")
    private String cover;

    /**
     * 总结
     */
    @ApiModelProperty(name = "summary", value = "总结")
    private String summary;
    /**
     * 价格
     */
    @ApiModelProperty(name = "price", value = "价格")
    private Integer price;
    /**
     * 下载地址
     */
    @ApiModelProperty(name = "downloadUrl", value = "下载地址")
    private String downloadUrl;

//    /**
//     * 购买数量
//     */
//    @ApiModelProperty(name = "buyCount", value = "购买数量")
//    private int buyCount;

//    /**
//     * 展示标志
//     */
//    @ApiModelProperty(name = "showFlag", value = "展示标志")
//    private Boolean showFlag;
//
//    /**
//     * 审核状态
//     */
//    @ApiModelProperty(name = "auditStatus", value = "审核状态")
//    private Boolean auditStatus;

//    /**
//     * 原因
//     */
//    @ApiModelProperty(name = "reason", value = "原因")
//    private String reason;

}
