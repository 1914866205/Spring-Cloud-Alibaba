package com.soft1851.content.controller;

import com.soft1851.content.domain.dto.ShareAuditDTO;
import com.soft1851.content.domain.entity.Share;
import com.soft1851.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName ShareAdminController.java
 * @Description 管理员
 * @createTime 2020年10月08日 10:07:00
 */
@RestController
@RequestMapping("/admin/shares")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ShareAdminController {
    private final ShareService shareService;

    @PutMapping(value = "/audit/{id}")
    public Share auditById(@PathVariable Integer id, @RequestBody ShareAuditDTO shareAuditDTO) {
        //此处需要认证授权
        return this.shareService.auditById(id, shareAuditDTO);
    }
}
