package com.yidian.galaxy.common.strategy;

import com.yidian.galaxy.common.biz.NoticeTemplateBiz;
import com.yidian.galaxy.common.consts.NoticeTemplateConst;
import com.yidian.galaxy.common.domain.NoticeTemplateDo;
import com.yidian.galaxy.common.entity.dto.NoticeDto;
import com.yidian.galaxy.common.strategy.notice.DxbNoticeSupport;
import com.yidian.galaxy.common.strategy.notice.NoticeSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

/**
 * NoticeStrategy 消息通知
 *
 * @author changshuai.yuan create on 2024/1/25 11:29
 */
@Slf4j
@Component
public class NoticeStrategy {
    
    private final Map<NoticeTemplateConst.ChannelEnum, NoticeSupport> strategy = new EnumMap<>(
            NoticeTemplateConst.ChannelEnum.class);
    
    private final NoticeTemplateBiz noticeTemplateBiz;
    
    public NoticeStrategy(NoticeTemplateBiz noticeTemplateBiz, DxbNoticeSupport dxbNoticeSupport) {
        this.noticeTemplateBiz = noticeTemplateBiz;
        strategy.put(NoticeTemplateConst.ChannelEnum.SMS_DXB, dxbNoticeSupport);
    }
    
    public boolean pushMsg(NoticeTemplateConst.UsesEnum usesEnum, String recipients, Object... args) {
        doPushMsg(new NoticeDto().setUsesEnum(usesEnum).setRecipients(recipients).setArgs(args));
        return false;
    }
    
    /**
     * 下游消费
     *
     * @param noticeDto noticeDto
     */
    private void doPushMsg(NoticeDto noticeDto) {
        NoticeTemplateConst.UsesEnum usesEnum = noticeDto.getUsesEnum();
        NoticeTemplateDo template = noticeTemplateBiz.findTemplate(usesEnum);
        if (template == null || template.getChannel() == null) {
            log.error("消息通知失败,模板配置存在问题,{}", usesEnum.name());
            return;
        }
        NoticeTemplateConst.ChannelEnum channel = template.getChannel();
        NoticeSupport noticeSupport = strategy.get(channel);
        boolean sendResult = noticeSupport.sendMsg(template, noticeDto);
    }
    
}
