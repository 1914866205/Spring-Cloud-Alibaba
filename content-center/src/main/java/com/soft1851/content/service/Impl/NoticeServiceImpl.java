package com.soft1851.content.service.Impl;

import com.soft1851.content.domain.entity.Notice;
import com.soft1851.content.mapper.NoticeMapper;
import com.soft1851.content.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName NoticeServiceImpl.java
 * @Description TODO
 * @createTime 2020年09月25日 15:53:00
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;
    @Override
    public Notice findById(int id) {
        return noticeMapper.selectByPrimaryKey(id);
    }
}
