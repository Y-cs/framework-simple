package com.yidian.galaxy.app.controller;

import com.yidian.galaxy.app.entity.dto.AppUserInfoDto;
import com.yidian.galaxy.app.entity.vo.AppUserLoginVO;
import com.yidian.galaxy.app.service.UserService;
import com.yidian.galaxy.web.ano.PublicApi;
import com.yidian.galaxy.web.entity.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * user controller
 *
 * @author changshuai.yuan create on 2024/1/22 17:26
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
@Tag(name = "用户操作")
public class UserController {
    
    private final UserService userService;
    
    @PublicApi
    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<AppUserInfoDto> login(@RequestBody @Validated AppUserLoginVO appUserLoginVO) {
        try {
            return Result.success(userService.login(appUserLoginVO));
        } catch (Exception e) {
            log.error("登录错误：", e);
            return Result.fail(e);
        }
    }
    
}
