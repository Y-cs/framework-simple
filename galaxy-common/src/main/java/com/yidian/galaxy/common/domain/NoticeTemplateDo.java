package com.yidian.galaxy.common.domain;

import com.yidian.galaxy.common.consts.NoticeTemplate;
import com.yidian.galaxy.web.entity.BaseModel;
import lombok.Data;

/**
 * NoticeTemplateDo
 *
 * @author changshuai.yuan create on 2024/1/24 18:03
 */
@Data
public class NoticeTemplateDo extends BaseModel {
    
    private Long id;
    
    private NoticeTemplate.UsesEnum uses;
    
    private NoticeTemplate.ChannelEnum channel;
    
    private String notice;
    
}
