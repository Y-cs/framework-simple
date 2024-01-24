package com.yidian.galaxy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yidian.galaxy.common.consts.AccountPlatformEnum;
import com.yidian.galaxy.common.domain.AccountDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * AccountMapper
 *
 * @author changshuai.yuan create on 2024/1/23 16:05
 */
@Repository
public interface AccountMapper extends BaseMapper<AccountDo> {
    
    Boolean checkPhonePlatform(@Param("phone") String phone, @Param("platform") AccountPlatformEnum platformEnum);
    
}
