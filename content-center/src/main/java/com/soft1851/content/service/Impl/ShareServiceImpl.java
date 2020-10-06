package com.soft1851.content.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft1851.content.domain.entity.MidUserShare;
import com.soft1851.content.domain.entity.Share;
import com.soft1851.content.domain.dto.ShareDTO;
import com.soft1851.content.domain.dto.UserDTO;
import com.soft1851.content.feignclient.UserCenterFeignClient;
import com.soft1851.content.mapper.MidUserShareMapper;
import com.soft1851.content.mapper.ShareMapper;
import com.soft1851.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

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
    private final MidUserShareMapper midUserShareMapper;
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

    @Override
    public PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId) {
        //启动分页
        PageHelper.startPage(pageNo, pageSize);
        //构造查询实例
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        //如果标题关键字不空，则加上模糊查询条件，否则结果即所有数据
        if (StringUtil.isNotEmpty(title)) {
            criteria.andLike("title", "%" + title + "%");
        }
        //执行案条件查询
        List<Share> shares = this.shareMapper.selectByExample(example);
        //处理后的Share数据列表
        List<Share> shareDeal;
        //1.如果用户未登陆，那么downloadUrl全部设为null
        if (userId == null) {
            shareDeal = shares.stream()
                    .peek(share -> {
                        share.setDownloadUrl(null);
                    })
                    .collect(Collectors.toList());
        }
        //2.如果用户登录了，那么查询一下mid_user_share,如果没有数据，那么这条share的downloadUrl也为null
        //只有自己分享的资源才能看到下载链接，否则显示“兑换”
        else {
            shareDeal = shares.stream()
                    .peek(share -> {
                        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                                MidUserShare.builder()
                                        .userId(userId)
                                        .shareId(share.getId())
                                        .build()
                        );
                        if (midUserShare == null) {
                            share.setDownloadUrl(null);
                        }
                    })
                    .collect(Collectors.toList());
        }
        return new PageInfo<>(shareDeal);
    }
}
