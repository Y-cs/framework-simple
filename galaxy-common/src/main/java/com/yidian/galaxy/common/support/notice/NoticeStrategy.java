package com.yidian.galaxy.common.support.notice;

import com.yidian.galaxy.common.domain.NoticeTemplateDo;
import com.yidian.galaxy.common.entity.dto.NoticeDto;

/**
 * NoticeTemplate
 *
 * @author changshuai.yuan create on 2024/1/25 11:32
 */
public interface NoticeStrategy {
    
    /**
     * 发送消息
     *
     * @param template  使用模板
     * @param noticeDto 消息内容
     * @return 返回true意味不需要重试, false意味着需要重试
     */
    boolean sendMsg(NoticeTemplateDo template, NoticeDto noticeDto);
}
