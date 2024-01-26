package com.yidian.galaxy.app.controller;

import com.yidian.galaxy.app.service.SmsService;
import com.yidian.galaxy.web.ano.PublicApi;
import com.yidian.galaxy.web.entity.result.Result;
import com.yidian.galaxy.web.session.UserHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SmsController
 *
 * @author changshuai.yuan create on 2024/1/25 14:55
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/sms")
@Tag(name = "短信")
public class SmsController {
    
    private final SmsService smsService;
    
    @PublicApi
    @Operation(summary = "发送验证码", description = "手机号非必填,不填写的时候默认使用当前登录人,此时未登录报错")
    @PostMapping("/captcha/{phone}")
    public Result<Boolean> captcha(@PathVariable(required = false) String phone) {
        if (StringUtils.isBlank(phone)) {
            phone = UserHolder.getUser().getPhone();
        }
        return Result.success(smsService.captcha(phone));
    }
    
}
