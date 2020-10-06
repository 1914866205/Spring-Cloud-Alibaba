package com.soft1851.content.controller;

import com.purgeteam.dispose.starter.annotation.IgnoreResponseAdvice;
import com.soft1851.content.domain.dto.ShareDTO;
import com.soft1851.content.domain.entity.Share;
import com.soft1851.content.service.ShareService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName ShareController.java
 * @Description TODO
 * @createTime 2020年09月29日 20:43:00
 */
@RestController
@RequestMapping(value = "/shares")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ShareController {
    private final ShareService shareService;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查询指定id的分享详情",notes="查询指定id的分享详情")
    public ShareDTO findById(@PathVariable Integer id) {
        return shareService.findById(id);
    }

    @ApiIgnore //文档忽略此接口或字段
    @IgnoreResponseAdvice //此接口返回值不会被包装层restful风格
    @GetMapping(value = "/hello")
    public String getHello() {
        return shareService.sayHello();
    }

    @GetMapping("/query")
    @ApiOperation(value = "分享列表", notes = "分享列表")
    public List<Share> query(
        @RequestParam(required = false) String title,
        @RequestParam(required = false,defaultValue = "1") Integer pageNo,
        @RequestParam(required = false,defaultValue = "10") Integer pageSize,
        @RequestParam(required = false)Integer userId)throws Exception{
        if (pageSize > 100) {
            pageSize = 100;
        }
        return this.shareService.query(title, pageNo, pageSize, userId).getList();

    }

}
