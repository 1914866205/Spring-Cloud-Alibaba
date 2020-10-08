package com.soft1851.content.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft1851.content.domain.dto.*;
import com.soft1851.content.domain.entity.MidUserShare;
import com.soft1851.content.domain.entity.Share;
import com.soft1851.content.domain.enums.AuditStatusEnum;
import com.soft1851.content.feignclient.UserCenterFeignClient;
import com.soft1851.content.mapper.MidUserShareMapper;
import com.soft1851.content.mapper.ShareMapper;
import com.soft1851.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.sql.SQLOutput;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    private final RocketMQTemplate rocketMQTemplate;
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

    @Override
    public String addContribute(ContributeDto contributeDto) {
        Share share = new Share();
        //属性的装配
//        BeanUtils.copyProperties(share, contributeDto);
        share.setId((int) System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        Timestamp ts = Timestamp.valueOf(time);
        share.setUserId(contributeDto.getUserId());
        share.setAuthor(contributeDto.getAuthor());
        share.setCover(contributeDto.getCover());
        share.setDownloadUrl(contributeDto.getDownloadUrl());
        share.setIsOriginal(contributeDto.getIsOriginal());
        share.setSummary(contributeDto.getSummary());
        share.setPrice(contributeDto.getPrice());
        share.setTitle(contributeDto.getTitle());
        share.setUserId(contributeDto.getUserId());
        share.setCreateTime(ts);
        share.setUpdateTime(ts);
        share.setShowFlag(false);
        share.setAuditStatus("NOT_YET");
        share.setBuyCount(0);
        share.setReason("");
        shareMapper.insert(share);
        midUserShareMapper.insert(MidUserShare.builder().userId(contributeDto.getUserId()).shareId(share.getId()).build());
        return "投稿成功";
    }

    @Override
    public Share auditById(Integer id, ShareAuditDTO shareAuditDTO) {
        // 1.查询share是否存在，不存在或者当前的audit_status ！=NOT_YES  , 那么抛异常
        Share share = this.shareMapper.selectByPrimaryKey(id);
        if (share == null) {
            throw new IllegalArgumentException("参数非法！该分享不存在");
        }
        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalArgumentException(("参数非法！该分享已通过审核或审核不通过"));
        }
        // 2.审核资源，将状态改为PASS或REJECT
        //这个API的主要流程是审核，所以不需要等更新积分的结果返回，可以将加积分改为异步
        share.setAuditStatus(shareAuditDTO.getAuditStatusEnum().toString());
        this.shareMapper.updateByPrimaryKey(share);
        // 3.如果PASS,那么发送消息给rocketmq,让用户中心去消费，并为发布人添加积分
        if (AuditStatusEnum.PASS.equals(shareAuditDTO.getAuditStatusEnum())) {
            this.rocketMQTemplate.convertAndSend(
                    "add-bonus",
                    UserAddBonusMsgDTO.builder()
                            .userId(share.getUserId())
                            .bonus(50)
                            .build());
        }
        return share;
    }
}
