package com.yidian.galaxy.common.consts;

import com.google.common.collect.ImmutableMap;
import com.yidian.galaxy.cornerstone.lambda.CollectMerge;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Function;

/**
 * UserSexEnum
 *
 * @author changshuai.yuan create on 2024/1/24 16:07
 */
@Getter
@RequiredArgsConstructor
public enum UserSexEnum {
    
    NOT_KNOW(0, "未知"),
    BOY(1, "男"),
    GIRL(2, "女");
    
    private final int code;
    
    private final String msg;
    
    private static final ImmutableMap<Integer, UserSexEnum> BY_CODE = Arrays.stream(UserSexEnum.values())
            .collect(ImmutableMap.toImmutableMap(UserSexEnum::getCode, Function.identity(), CollectMerge::byFirst));
    
    public static UserSexEnum byCode(Integer code) {
        return BY_CODE.get(code);
    }
    
}
