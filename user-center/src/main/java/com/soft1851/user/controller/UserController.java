package com.soft1851.user.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.soft1851.user.common.ResponseResult;
import com.soft1851.user.entity.MidUserShare;
import com.soft1851.user.entity.User;
import com.soft1851.user.entity.dto.*;
import com.soft1851.user.service.UserService;
import com.soft1851.user.util.JwtOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description TODO
 * @createTime 2020年09月23日 09:32:00
 */
@RestController
@RequestMapping(value = "user")
@Slf4j
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class UserController {
    private final UserService userService;
    private final WxMaService wxMaService;
    private final JwtOperator jwtOperator;

    @GetMapping("/hello")
    public String sayHello() {
        log.info("我被调用了");
        return "Hello , this massage is from user-center";
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @PostMapping("/post/json")
    public String post(@RequestBody MidUserShare midUserShare) {
        return "来自post请求发送的消息:" + midUserShare;
    }

    @PostMapping("/update/bonus")
    public User updateBonus(@RequestBody UserAddBonusMsgDTO updateBonus) {
        System.out.println(updateBonus);
        return userService.updateBonus(updateBonus);
    }

    @PostMapping(value = "/login")
    public ResponseResult getToken(@RequestBody LoginDTO loginDTO) throws WxErrorException {
        String openId;
        //微信小程序登录，需要code请求openId
        if (loginDTO.getLoginCode() != null) {
            // 微信服务端校验是否已经登录的结果
            WxMaJscode2SessionResult result = this.wxMaService.getUserService()
                    .getSessionInfo(loginDTO.getLoginCode());
            log.info(result.toString());
            //微信的openId,用户在微信这边的唯一标识
            openId = result.getOpenid();
        } else {
            openId = loginDTO.getOpenId();
        }
        // 看用户是否注册，如果没有注册就插入数据，如果已经注册就返回其信息
        User user = userService.login(loginDTO, openId);
        // 颁发token
        Map<String, Object> userInfo = new HashMap<>(3);
        userInfo.put("id", user.getId());
        userInfo.put("wxNickname", user.getWxNickname());
        userInfo.put("role", user.getRoles());
        String token = jwtOperator.generateToken(userInfo);
        log.info("{}登录成功，生成的token={},有效期到:{}",
                user.getWxNickname(),
                token,
                jwtOperator.getExpirationTime());
        //构造返回结果
        System.out.println(8);

        LoginRespDTO loginRespDTO = LoginRespDTO.builder()
                .user(UserRespDTO.builder()
                        .id(user.getId())
                        .wxNickname(user.getAvatarUrl())
                        .bonus(user.getBonus())
                        .build())
                .token(JwtTokenRespDTO
                        .builder()
                        .token(token)
                        .expirationTime(jwtOperator.getExpirationTime().getTime())
                        .build()).
                        build();
        return ResponseResult.builder().code(0).data(loginRespDTO).msg("200").build();

    }
}
