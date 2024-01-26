package com.yidian.galaxy.app.controller;

import com.yidian.galaxy.app.entity.dto.AppUserInfoDto;
import com.yidian.galaxy.app.entity.vo.AppUserLoginVo;
import com.yidian.galaxy.app.entity.vo.AppUserRegisterVo;
import com.yidian.galaxy.common.entity.vo.AppUserUpdateVo;
import com.yidian.galaxy.app.service.UserService;
import com.yidian.galaxy.web.ano.PublicApi;
import com.yidian.galaxy.web.entity.result.Result;
import io.swagger.annotations.ApiOperation;
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
    public Result<AppUserInfoDto> login(
            @RequestBody @Validated(AppUserLoginVo.PhonePasswordLogin.class) AppUserLoginVo appUserLoginVO) {
        return Result.success(userService.login(appUserLoginVO));
    }
    
    @PostMapping("/register")
    @PublicApi
    @ApiOperation("注册")
    public Result<?> register(@RequestBody @Validated AppUserRegisterVo userRegisterVO) {
        userService.register(userRegisterVO);
        return Result.success();
    }
    
    @PostMapping("/getUserInfo")
    @ApiOperation("获取当前登录人信息")
    public Result<AppUserInfoDto> getUserInfo() {
        return Result.success(userService.getUserInfo());
    }
    
    @PostMapping("/updateUserInfo")
    @ApiOperation("更新用户信息")
    public Result<Boolean> updateUserInfo(AppUserUpdateVo appUserUpdateVo) {
        return Result.success(userService.updateUserInfo(appUserUpdateVo));
    }
    
}
