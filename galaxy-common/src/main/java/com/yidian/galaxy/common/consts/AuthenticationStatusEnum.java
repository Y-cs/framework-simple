package com.yidian.galaxy.common.consts;

import com.google.common.collect.ImmutableMap;
import com.yidian.galaxy.cornerstone.lambda.CollectMerge;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Function;

/**
 * AuthenticationStatusEnum
 *
 * @author changshuai.yuan create on 2024/1/23 17:00
 */
@Getter
@RequiredArgsConstructor
public enum AuthenticationStatusEnum {
    
    NOT_AUTH(0, "未认证");
    
    private final int code;
    
    private final String msg;
    
    private static final ImmutableMap<Integer, AuthenticationStatusEnum> BY_CODE = Arrays.stream(
            AuthenticationStatusEnum.values()).collect(
            ImmutableMap.toImmutableMap(AuthenticationStatusEnum::getCode, Function.identity(), CollectMerge::byFirst));
    
    public static AuthenticationStatusEnum byCode(Integer code) {
        return BY_CODE.get(code);
    }
    
}
