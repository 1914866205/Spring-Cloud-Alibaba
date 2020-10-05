package com.soft1851.user.service.impl;

import com.soft1851.user.entity.BonusEventLog;
import com.soft1851.user.mapper.BonusEventLogMapper;
import com.soft1851.user.service.BonusEventLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName BonusEventLogServiceImpl.java
 * @Description TODO
 * @createTime 2020年09月25日 15:44:00
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class BonusEventLogServiceImpl implements BonusEventLogService {
    private final BonusEventLogMapper bonusEventLogMapper;
    @Override
    public BonusEventLog findBonusEventLogById(int id) {
        return bonusEventLogMapper.selectByPrimaryKey(id);
    }
}
