package com.soft1851.content.service.Impl;

import com.soft1851.content.domain.entity.MidUserShare;
import com.soft1851.content.mapper.MidUserShareMapper;
import com.soft1851.content.service.MidUserShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName MidUserShareService.java
 * @Description TODO
 * @createTime 2020年09月19日 21:26:00
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class MidUserShareServiceImpl implements MidUserShareService {
    private final MidUserShareMapper midUserShareMapper;


    @Override
    public MidUserShare findById(int id) {
        return midUserShareMapper.selectByPrimaryKey(id);
    }
}
