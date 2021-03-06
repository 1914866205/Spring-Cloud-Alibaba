package com.soft1851.content.domain.dto;

import com.soft1851.content.domain.entity.Share;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName ShareDTO.java
 * @Description TODO
 * @createTime 2020年09月29日 15:42:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("分享详情，和发布人昵称")
public class ShareDTO {
    @ApiModelProperty(name = "share", value = "分享资源信息")
    private Share share;

    /**
     * 发布人昵称
     */
    @ApiModelProperty(name = "wxNickname", value = "发布人昵称")
    private String wxNickname;


//    /**
//     * id
//     */
//    private Integer id;
//
//    /**
//     * 发布人id
//     */
//    private Integer userId;
//
//    /**
//     * 标题
//     */
//    private String title;
//
//    /**
//     * 创建时间
//     */
//    private Date createTime;
//
//    /**
//     * 修改时间
//     */
//    private Date updateTime;
//
//    /**
//     * 是否原创 0:否 1:是
//     */
//    private Boolean isOriginal;
//
//    /**
//     * 作者
//     */
//    private String author;
//
//    /**
//     * 封面
//     */
//    private String cover;
//
//    /**
//     * 概要信息
//     */
//    private String summary;
//
//    /**
//     * 价格（需要的积分）
//     */
//    private Integer price;
//
//    /**
//     * 下载地址
//     */
//    private String downloadUrl;
//
//    /**
//     * 下载数
//     */
//    private Integer buyCount;
//
//    /**
//     * 是否显示 0:否 1:是
//     */
//    private Boolean showFlag;
//
//    /**
//     * 审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过
//     */
//    private String auditStatus;
//
//    /**
//     * 审核不通过原因
//     */
//    private String reason;
//
//    /**
//     * 发布人
//     */
//    private String wxNickname;
}
