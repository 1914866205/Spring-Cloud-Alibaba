package com.soft1851.content.controller;

import com.soft1851.content.domain.entity.User;
import com.soft1851.content.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName ControllerHello.java
 * @Description TODO
 * @createTime 2020年09月23日 09:34:00
 */
@RestController
@RequestMapping(value = "/content")
public class ControllerHello {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ShareService shareService;

    @GetMapping(value = "/call")
    public String getHello() {
        return restTemplate.getForObject("http://localhost:8090/user/hello", String.class);
    }

    @GetMapping(value = "/call/ribbon")
    public String callByRibbon() {
        return restTemplate.getForObject("http://user-center/user/hello", String.class);
    }

    @GetMapping(value = "/call/user/{id}")
    public User callByUser(@PathVariable String id) {
        return restTemplate.getForObject("http://user-center/user/find/" + id, User.class);
    }

//    @GetMapping(value = "/share/{id}")
//    public ShareDTO findById(@PathVariable Integer id) {
//        //根据shareId获得userId
//        Share share = shareService.findShareById(id);
//        //根据userId获得user信息
//        User user = restTemplate.getForObject("http://user-center/user/find/" + share.getUserId(), User.class);
//        return ShareDTO.builder().auditStatus(share.getAuditStatus()).author(share.getAuthor()).avatarUrl(user.getAvatarUrl()).bonus(user.getBonus()).buyCount(share.getBuyCount()).cover(share.getCover()).createTime(share.getCreateTime()).downloadUrl(share.getDownloadUrl()).id(share.getId()).isOriginal(share.getIsOriginal()).price(share.getPrice()).reason(share.getReason()).roles(user.getRoles()).showFlag(share.getShowFlag()).summary(share.getSummary()).title(share.getTitle()).updateTime(share.getUpdateTime()).wxId(user.getWxId()).wxNickname(user.getWxNickname()).userId(share.getUserId()).build();
//    }

}
