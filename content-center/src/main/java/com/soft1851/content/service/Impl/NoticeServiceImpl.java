package com.soft1851.content.service.Impl;

import com.soft1851.content.domain.entity.Notice;
import com.soft1851.content.mapper.NoticeMapper;
import com.soft1851.content.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName NoticeServiceImpl.java
 * @Description TODO
 * @createTime 2020年09月25日 15:53:00
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;

    @Override
    public Notice findById(int id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Notice getLatest() {
        Example example = new Example(Notice.class);
        //按id降序
        example.setOrderByClause("id DESC");
        //构造条件器
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("showFlag", 1);
        //返回值是List
        return noticeMapper.selectByExample(example).get(0);
    }
}
