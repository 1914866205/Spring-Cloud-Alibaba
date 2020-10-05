package com.soft1851.content.controller;

import com.soft1851.content.domain.dto.ShareDTO;
import com.soft1851.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ShareDTO findById(@PathVariable Integer id) {
        return shareService.findById(id);
    }

    @GetMapping(value = "/hello")
    public String getHello() {
        return shareService.sayHello();
    }
}
