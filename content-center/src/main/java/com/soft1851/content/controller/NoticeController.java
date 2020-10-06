package com.soft1851.content.controller;

import com.soft1851.content.domain.entity.Notice;
import com.soft1851.content.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName NoticeController.java
 * @Description TODO
 * @createTime 2020年10月05日 10:15:00
 */
@RestController
@RequestMapping("/notice")
@Api(tags = "公共接口", value = "提供公共相关的Rest API")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping(value = "/one")
    @ApiOperation(value = "查询最新的一条公告", notes = "查询最新的一条公告")
    public Notice getNotice() {
        return this.noticeService.getLatest();
    }
}
