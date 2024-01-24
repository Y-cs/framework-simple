package com.yidian.galaxy.common.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yidian.galaxy.web.entity.BaseModel;
import lombok.Data;

/**
 * AccountDo
 *
 * @author changshuai.yuan create on 2024/1/23 16:07
 */
//@EqualsAndHashCode(callSuper = true)
@Data
@TableName("account")
public class AccountDo extends BaseModel {
    
    private Long id;
    
    private String phone;
    
    private Integer platform;
    
}
