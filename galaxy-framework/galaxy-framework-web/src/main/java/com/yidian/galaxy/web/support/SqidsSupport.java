package com.yidian.galaxy.web.support;


import com.yidian.galaxy.web.exception.Asserts;
import org.apache.commons.lang3.StringUtils;
import org.sqids.Sqids;

import java.util.List;

/**
 * 一个短链支持工具
 *
 * @author changshuai.yuan create on 2024/1/24 16:38
 * @see <a href="https://sqids.org/java">文档</a>
 */
public class SqidsSupport {
    
    private static final Sqids L_11 = Sqids.builder().minLength(11).build();
    
    public static String encodeL11ByPhone(String phone) {
        Asserts.isTrue(StringUtils.isBlank(phone) || phone.length() != 11).throwBusinessException("手机号数据错误");
        return L_11.encode(List.of(Long.parseLong(phone)));
    }
    
}
