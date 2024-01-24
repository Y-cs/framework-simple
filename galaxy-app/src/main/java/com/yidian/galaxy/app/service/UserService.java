package com.yidian.galaxy.app.service;

import com.yidian.galaxy.app.entity.dto.AppUserInfoDto;
import com.yidian.galaxy.app.entity.vo.AppUserLoginVO;

/**
 * user service
 *
 * @author changshuai.yuan create on 2024/1/22 17:28
 */
public interface UserService {
    
    AppUserInfoDto login(AppUserLoginVO appUserLoginVO);
    
}
