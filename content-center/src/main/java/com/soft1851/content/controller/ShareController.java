package com.soft1851.content.controller;

import com.purgeteam.dispose.starter.annotation.IgnoreResponseAdvice;
import com.soft1851.content.common.ResponseResult;
import com.soft1851.content.domain.dto.ContributeDto;
import com.soft1851.content.domain.dto.ShareDTO;
import com.soft1851.content.domain.entity.Share;
import com.soft1851.content.service.ShareService;
import com.soft1851.content.util.JwtOperator;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import sun.rmi.runtime.Log;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName ShareController.java
 * @Description TODO
 * @createTime 2020年09月29日 20:43:00
 */
@Slf4j
@RestController
@RequestMapping(value = "/share")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ShareController {
    private final ShareService shareService;
    private final JwtOperator jwtOperator;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查询指定id的分享详情", notes = "查询指定id的分享详情")
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
    public ResponseResult query(
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "X-Token", required = false) String token) {
        if (pageSize > 100) {
            pageSize = 100;
        }
        Integer userId = null;
        if (StringUtils.isNotBlank(token)) {
            Claims claims = this.jwtOperator.getClaimsFromToken(token);
            log.info(claims.toString());
            userId = (Integer) claims.get("id");
        } else {
            log.info("没有token");
        }

        List<Share> list = this.shareService.query(title, pageNo, pageSize, userId).getList();
        System.out.println("查询的结果" + list.toString());
        ResponseResult build = ResponseResult.builder().code(0).msg("200").data(list).build();
        System.out.println(build.toString());
        return build;
    }

    @PostMapping("/contribute")
    @ApiOperation(value = "投稿", notes = "投稿")
    public ResponseResult contribute(@RequestBody ContributeDto contributeDto) {
        return shareService.addContribute(contributeDto);
    }

    @GetMapping("/query/my")
    @ApiOperation(value = "分享列表", notes = "分享列表")
    public ResponseResult query(@RequestParam Integer userId) {
        return shareService.queryMy(userId);
    }


    @GetMapping("/exchange")
    @ApiOperation(value = "兑换", notes = "兑换")
    public ResponseResult exchange(@RequestParam String userId, @RequestParam String shareId) {
        return shareService.exchange(userId, shareId);
    }

    @GetMapping("/my")
    public ResponseResult myContribute(@RequestParam String author) {
        return shareService.myContribute(author);
    }
}
