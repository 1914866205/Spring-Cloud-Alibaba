package com.soft1851.content.service;

import com.soft1851.content.domain.dto.ShareDTO;

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
}
