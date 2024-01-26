package com.yidian.galaxy.common.support;

import com.yidian.galaxy.common.biz.NoticeTemplateBiz;
import com.yidian.galaxy.common.consts.NoticeTemplateConst;
import com.yidian.galaxy.common.domain.NoticeTemplateDo;
import com.yidian.galaxy.common.entity.dto.NoticeDto;
import com.yidian.galaxy.common.support.notice.DxbNoticeStrategy;
import com.yidian.galaxy.common.support.notice.NoticeStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

/**
 * NoticeSupport 消息通知
 *
 * @author changshuai.yuan create on 2024/1/25 11:29
 */
@Slf4j
@Component
public class NoticeSupport {
    
    private final Map<NoticeTemplateConst.ChannelEnum, NoticeStrategy> strategy = new EnumMap<>(
            NoticeTemplateConst.ChannelEnum.class);
    
    private final NoticeTemplateBiz noticeTemplateBiz;
    
    public NoticeSupport(NoticeTemplateBiz noticeTemplateBiz, DxbNoticeStrategy dxbNoticeStrategy) {
        this.noticeTemplateBiz = noticeTemplateBiz;
        strategy.put(NoticeTemplateConst.ChannelEnum.SMS_DXB, dxbNoticeStrategy);
    }
    
    public boolean pushMsg(NoticeTemplateConst.UsesEnum usesEnum, String recipients, Object... args) {
        doPushMsg(new NoticeDto().setUsesEnum(usesEnum).setRecipients(recipients).setArgs(args));
        return true;
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
        NoticeStrategy noticeSupport = strategy.get(channel);
        boolean success = false;
        try {
            success = noticeSupport.sendMsg(template, noticeDto);
        } catch (Throwable t) {
            log.error("消息通知失败", t);
            //下游 抛出没有意义
        } finally {
            //todo 记录日志
            if (!success) {
                //todo 重试
            }
        }
    }
    
}
