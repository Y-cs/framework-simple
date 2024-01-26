package com.yidian.galaxy.common.support.notice;

import com.yidian.galaxy.common.domain.NoticeTemplateDo;
import com.yidian.galaxy.common.entity.dto.NoticeDto;

/**
 * NoticeTemplate
 *
 * @author changshuai.yuan create on 2024/1/25 11:32
 */
public interface NoticeStrategy {
    
    boolean sendMsg(NoticeTemplateDo template, NoticeDto noticeDto);
}
