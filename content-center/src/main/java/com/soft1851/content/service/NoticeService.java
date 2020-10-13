package com.soft1851.content.service;

import com.soft1851.content.domain.entity.Notice;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName NoticeService.java
 * @Description TODO
 * @createTime 2020年09月25日 15:53:00
 */
public interface NoticeService {
    Notice findById(int id);

    /**
     * 获得最新的公共
     *
     * @return
     */
    Notice getLatest();
}
