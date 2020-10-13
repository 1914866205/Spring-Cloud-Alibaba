package com.soft1851.content.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.content.domain.dto.ContributeDto;
import com.soft1851.content.domain.dto.ShareAuditDTO;
import com.soft1851.content.domain.dto.ShareDTO;
import com.soft1851.content.domain.entity.Share;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName ShareService.java
 * @Description TODO
 * @createTime 2020年09月25日 15:55:00
 */
public interface ShareService {
//    /**
//     * 查询该用户的所有分享
//     * @param userId
//     * @return
//     */
//    List<Share> findById(String userId);


    ShareDTO findById(Integer shareId);

    String sayHello();

    /**
     * 根据标题迷糊查询某个用户的分析列表数据，title为空则为所有数据，查询结果分页
     *
     * @param title
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId);

    /**
     * 投稿
     *
     * @param contributeDto
     * @return
     */
    String addContribute(ContributeDto contributeDto);

    /**
     * 审核投稿
     *
     * @param id
     * @param shareAuditDTO
     * @return
     */
    Share auditById(Integer id, ShareAuditDTO shareAuditDTO);

}
