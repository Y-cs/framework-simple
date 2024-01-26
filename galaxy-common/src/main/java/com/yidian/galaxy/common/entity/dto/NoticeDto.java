package com.yidian.galaxy.common.entity.dto;

import com.yidian.galaxy.common.consts.NoticeTemplateConst;
import lombok.Data;

/**
 * NoticeDto
 *
 * @author changshuai.yuan create on 2024/1/25 11:44
 */
@Data
public class NoticeDto {
    
    private NoticeTemplateConst.UsesEnum usesEnum;
    
    private String recipients;
    
    private Object[] args;
    
}
