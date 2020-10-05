package com.soft1851.content.service.Impl;

import com.soft1851.content.domain.entity.Share;
import com.soft1851.content.domain.dto.ShareDTO;
import com.soft1851.content.domain.dto.UserDTO;
import com.soft1851.content.feignclient.UserCenterFeignClient;
import com.soft1851.content.mapper.ShareMapper;
import com.soft1851.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName ShareServiceImpl.java
 * @Description TODO
 * @createTime 2020年09月25日 15:56:00
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
@Slf4j
public class ShareServiceImpl implements ShareService {
    private final ShareMapper shareMapper;
    private final UserCenterFeignClient userCenterFeignClient;

//    @Override
//    public List<Share> findById(String userId) {
//        return shareMapper.select(Share.builder().userId(userId).build());
//    }

//    @Override
//    public Share findShareById(Integer shareId) {
//        return shareMapper.select(Share.builder().id(shareId).build()).get(0);
//    }

    @Override
    public ShareDTO findById(Integer shareId) {
        //获取分享实体
        Share share = shareMapper.selectByPrimaryKey(shareId);
        //获得发布人id
        Integer userId = share.getUserId();
        // 1. 代码不可读
        // 2. 复杂的url难以维护：https://user-center/s?ie={ie}&f={f}&rsv_bp=1&rsv_idx=1&tn=baidu&wd=a&rsv_pq=c86459bd002cfbaa&rsv_t=edb19hb%2BvO%2BTySu8dtmbl%2F9dCK%2FIgdyUX%2BxuFYuE0G08aHH5FkeP3n3BXxw&rqlang=cn&rsv_enter=1&rsv_sug3=1&rsv_sug2=0&inputT=611&rsv_sug4=611
        // 3. 难以相应需求的变化，变化很没有幸福感
        // 4. 编程体验不统一

        UserDTO userDTO = userCenterFeignClient.findUserById(userId);
        ShareDTO shareDTO = new ShareDTO();
        //属性的装配
        BeanUtils.copyProperties(share, shareDTO);
        shareDTO.setWxNickname(userDTO.getWxNickname());
        return shareDTO;
    }

    @Override
    public String sayHello() {
        return userCenterFeignClient.sayHello();
    }
}
